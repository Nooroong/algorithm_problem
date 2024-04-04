import java.io.*;
import java.util.*;

/**
 * 
 * @author JiYeon Sin
 * 
 * 미먼 확장에서 방문처리는 필요없다.
 *
 */
public class Main {
	static BufferedReader br;
	static StringTokenizer st;
	
	static int time;
	static int rowSize, colSize;
	static int[][] room;
	
	static Point topAirCleaner, bottomAirCleaner;	
	
	// 미먼 확장과 반시계 순환에 사용
	static int[] deltaRow = { -1, 0, 1, 0 };
	static int[] deltaCol = { 0, 1, 0, -1 };
	
	// 시계 방향 순환에 사용 (하, 우, 상, 좌)
	static int[] clockWiseRow = { 1, 0, -1, 0 };
	static int[] clockWiseCol = { 0, 1, 0, -1 };
	
	static Queue<Point> queue = new ArrayDeque<>();
	
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		
		st = new StringTokenizer(br.readLine().trim());
		rowSize = Integer.parseInt(st.nextToken());
		colSize = Integer.parseInt(st.nextToken());
		time = Integer.parseInt(st.nextToken());
		
		room = new int[rowSize][colSize];
		
		// 방의 정보를 입력받는다.
		for(int rowIdx = 0; rowIdx < rowSize; rowIdx++) {
			st = new StringTokenizer(br.readLine().trim());
			for(int colIdx = 0; colIdx < colSize; colIdx++) {
				room[rowIdx][colIdx] = Integer.parseInt(st.nextToken());
				
				// 미먼은 큐에 넣는다.
				if(room[rowIdx][colIdx] > 0) {
					queue.offer(new Point(rowIdx, colIdx, room[rowIdx][colIdx]));
				}
			}
		}
		
		
		// 0열에서 공청의 위치를 찾는다.
		for(int rowIdx = 0; rowIdx < rowSize; rowIdx++) {
			if(room[rowIdx][0] == -1) {
				// 크기는 두 행을 차지한다.
				topAirCleaner = new Point(rowIdx, 0, -1);
				bottomAirCleaner = new Point(rowIdx+1, 0, -1);
				break;
			}
		}
		
		
		
		// 시간이 다 할때까지 반복한다
		while(time-- > 0) {
			mimunExtension(); // 미세먼지를 확장시킨다.
			runAirCleaner(); // 공기청정기가 작동한다.
			addMimunToQueue(); // 남은 미먼을 큐에 넣는다.
		}
		
		// 남은 미먼의 양을 출력한다.
		System.out.println(leftMimunCount());
	}
	
	
	static void mimunExtension() {
		while(!queue.isEmpty()) {
			Point curPoint = queue.poll();
			int curRow = curPoint.row;
			int curCol = curPoint.col;
			int curAmount = curPoint.amount;
			
			int extensionCount = 0; // 확산된 칸의 수
			
			for(int dir = 0; dir < deltaRow.length; dir++) {
				int newRow = curPoint.row + deltaRow[dir];
				int newCol = curPoint.col + deltaCol[dir];
				
				if(!isAvailable(newRow, newCol)) continue;
				if(room[newRow][newCol] == -1) continue; // 공기 청정기가 있는 곳으로는 확장할 수 없다.
				
				room[newRow][newCol] += (curAmount / 5); // 1/5만큼 확산된다.
				extensionCount++;
			}
			
			// 현재 칸 미먼 감소.
			room[curRow][curCol] -= (curAmount / 5) * extensionCount;
			if(room[curRow][curCol] < 0) room[curRow][curCol] = 0; // 음수가 될 경우 0으로 바꿔준다.
		}

	}
	
	
	static void runAirCleaner() {
		// 값을 미는 방식 말고, 현재 값을 앞으로 끌어다오는 방식으로 구현
		
		// 위쪽 - 반시계방향 순환(상, 우, 하, 좌 순서)
		int curRow = topAirCleaner.row;
		int curCol = topAirCleaner.col;
				
		for(int dir = 0; dir < deltaRow.length; ) {
			// 현재 위치
			curRow += deltaRow[dir];
			curCol += deltaCol[dir];
			
			// 현 위치가 배열의 범위를 벗어나거나, 위쪽 공기청정기보다 더 아래로 내려갔다면
			if(!isAvailable(curRow, curCol) || curRow > topAirCleaner.row) {
				// 이전 위치로 되돌아가고
				curRow -= deltaRow[dir];
				curCol -= deltaCol[dir];
				
				// 방향을 전환한다.
				dir++;
				continue;
			}
			

			// 앞쪽 요소의 좌표를 계산한다.
			int preRow = curRow-deltaRow[dir];
			int preCol = curCol-deltaCol[dir];
			
			// 현재 위치의 값을 앞쪽 방향으로 보낸다.
			// if문 부분: 먼지가 공기청정기에 들어가면 사라진다.
			if(room[preRow][preCol] != -1)
				room[preRow][preCol] = room[curRow][curCol];
		}
		
		// 위의 과정을 다 수행하면 공기청정기 바로 오른쪽의 요소에 -1이 들어가게 된다.
		// 실제로는 미먼이 밀려서 깨끗한 상태가 되야하므로 0으로 값을 바꿔준다.
		// 열의 크기는 최소 6이상이므로 범위를 벗어나는 경우는 없다.
		room[topAirCleaner.row][topAirCleaner.col + 1] = 0;

		
		
		// 아래쪽 - 시계방향 순환(위와 동일. 방향만 다름.)
		curRow = bottomAirCleaner.row;
		curCol = bottomAirCleaner.col;

		for(int dir = 0; dir < clockWiseRow.length; ) {
			curRow += clockWiseRow[dir];
			curCol += clockWiseCol[dir];
			
			// // 현 위치가 배열의 범위를 벗어나거나, 아래쪽 공기청정기보다 더 위로 올라갔다면
			if(!isAvailable(curRow, curCol) || curRow < bottomAirCleaner.row) {
				// 이전 위치로 되돌아가고
				curRow -= clockWiseRow[dir];
				curCol -= clockWiseCol[dir];
				
				// 방향을 전환한다.
				dir++;
				continue;
			}
			
			// 앞쪽 요소의 좌표를 계산한다.
			int preRow = curRow-clockWiseRow[dir];
			int preCol = curCol-clockWiseCol[dir];
			
			// // 현재 위치의 값을 앞쪽 방향으로 보낸다.
			if(room[preRow][preCol] != -1)
				room[preRow][preCol] = room[curRow][curCol];
		}
		
		// 공기청정기 오른쪽 요소는 깨끗해짐
		room[bottomAirCleaner.row][bottomAirCleaner.col + 1] = 0;
	}
	
	static void addMimunToQueue() {
		// 공기청정기 작동 후 다음 번 확산을 위해 방에 남은 미먼을 큐에 넣는다
		// 확장이 끝나면 다음 확장을 위해 전체 방에서 미먼이 있는 칸을 큐에 넣는다.
		for(int rowIdx = 0; rowIdx < rowSize; rowIdx++) {
			for(int colIdx = 0; colIdx < colSize; colIdx++) {
				if(room[rowIdx][colIdx] > 0)
					queue.offer(new Point(rowIdx, colIdx, room[rowIdx][colIdx]));
			}
		}
	}
	
	
	static int leftMimunCount() {
		int count = 0;
		
		for(int rowIdx = 0; rowIdx < rowSize; rowIdx++) {
			for(int colIdx = 0; colIdx < colSize; colIdx++) 
				if(room[rowIdx][colIdx] > 0)
					count += room[rowIdx][colIdx];
		}
		
		return count;
	}
	
	static boolean isAvailable(int row, int col) {
		return (row >= 0 && row < rowSize && col >= 0 && col < colSize);
	}
	
	static void printRoom() {
		for(int rowIdx = 0; rowIdx < rowSize; rowIdx++)
			System.out.println(Arrays.toString(room[rowIdx]));
		System.out.println();
	}
	
	
	static class Point {
		int row;
		int col;
		int amount; // 다른 칸의 확장으로 기존 값이 변할 수도 있기 때문에 기존 미먼 양도 기억해야 한다.
		
		public Point(int row, int col, int amount) {
			this.row = row;
			this.col = col;
			this.amount = amount;
		}
	}
	
	
	
	
}