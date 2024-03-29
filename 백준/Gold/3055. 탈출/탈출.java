import java.io.*;
import java.util.*;

/**
 * 
 * @author JiYeon Sin
 * 최단 경로 찾기 -> BFS
 * 
 * 물이 찰 예정은 고슴도치가 이동할 수 없다.
 * 따라서 물을 먼저 퍼트린 후 고슴도치를 이동시킨다.
 * 문제에서 물이 하나만 주어진다는 얘기가 없다 -> 초기 맵에 물이 여러개가 있을 수도 있음을 고려
 * 
 * 1. 맵 사이즈를 입력받고 할당하기
 * 2. 맵 입력받기 + 각종 오브젝트들 위치 찾기
 * 3. 도치를 규칙에 따라 이동시킨다.
 * 	3-1. 큐가 빌 때 까지 반복.
 * 	3-2. 물을 먼저 퍼트린다.
 * 	3-3. 1초마다 도치를 한 칸만 이동시킨다.
 * 	3-4. 도치가 다음에 이동할 위치가 목적지라면 성공, 큐가 빌때까지 목적지에 도달하지 못하면 실패
 * 4. 결과를 출력한다.
 *
 */
public class Main {
	static BufferedReader br;
	static StringTokenizer st;
	
	static int[] deltaRow = { -1, 0, 1, 0 };
	static int[] deltaCol = { 0, 1, 0, -1 };
	
	static final char BLANK = '.',
					  WATER = '*',
					  ROCK = 'X',
					  GOAL = 'D',
					  DOCHI = 'S';
	
	static int goalRow, goalCol;
	
	static int rowSize, colSize;
	static char[][] map;
	
	static Queue<Point> waterQueue = new ArrayDeque<>();
	static Queue<Point> dochiQueue = new ArrayDeque<>();
	
	static boolean[][] dochiVisited;
	static int time;
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		
		// 1. 맵 사이즈를 입력받고 할당하기
		st = new StringTokenizer(br.readLine().trim());
		rowSize = Integer.parseInt(st.nextToken());
		colSize = Integer.parseInt(st.nextToken());
		map = new char[rowSize][colSize];
		
		
		// 2. 맵 입력받기 + 각종 오브젝트들 위치 찾기
		for(int rowIdx = 0; rowIdx < rowSize; rowIdx++) {
			String line = br.readLine().trim();
			for(int colIdx = 0; colIdx < colSize; colIdx++) {
				map[rowIdx][colIdx] = line.charAt(colIdx);
				
				if(map[rowIdx][colIdx] == DOCHI) {
					// BFS에 사용할 큐에 출발 도치의 좌표를 넣어준다.
					dochiQueue.offer(new Point(rowIdx, colIdx));
					
				} else if(map[rowIdx][colIdx] == WATER) {
					waterQueue.offer(new Point(rowIdx, colIdx));
					
				} else if(map[rowIdx][colIdx] == GOAL) {
					goalRow = rowIdx;
					goalCol = colIdx;
				}
			}
		}
		
		
		// 3. 도치를 규칙에 따라 이동시킨다.
		// 목적지에 도달하지 못한 경우 KAKTUS 출력.
		System.out.println(dochiMove() > 0 ? time : "KAKTUS");
	}
	
	
	
	static void waterSpread() {
		// 큐의 사이즈를 바탕으로 함수 call 한 번에 한 level씩만 탐색하도록 한다.
		int size = waterQueue.size();
		
		while(size-- > 0) {
			Point curPoint = waterQueue.poll();
			int curRow = curPoint.row,
				curCol = curPoint.col;

			
			// 물을 상, 하, 좌, 우로 퍼트린다.
			for(int dir = 0; dir < deltaRow.length; dir++) {
				int newRow = curRow + deltaRow[dir];
				int newCol = curCol + deltaCol[dir];
				
				if(!isAvailable(newRow, newCol)) continue; // 맵 밖을 벗어난 경우
				
				// 물은 돌, 목적지를 뚫을 수 없다.
				// 이미 물이 있는 곳으로는 갈 필요가 없다.
				if(map[newRow][newCol] == WATER ||
				   map[newRow][newCol] == ROCK ||
				   map[newRow][newCol] == GOAL)
					continue; 
				
				
				// 물이 갈 수 있는 새로운 위치로 퍼진다.
				map[newRow][newCol] = WATER;
				waterQueue.offer(new Point(newRow, newCol));
			}	
		}		
	}
	
	
	static int dochiMove() {
		dochiVisited = new boolean[rowSize][colSize];
		dochiVisited[dochiQueue.peek().row][dochiQueue.peek().col] = true; // 도치 시작 위치 방문처리
		time = 1; // 시간은 1초부터 시작
		
		
		// 3-1. 큐가 빌 때 까지 반복.
		// 큐에는 물에 빠지지 않은 도치들만 남게 된다.
		while(!dochiQueue.isEmpty()) {
			int size = dochiQueue.size();
 
			waterSpread(); // 3-2. 물을 먼저 퍼트린다.
			
			// 3-3. 1초마다 도치를 한 칸만 이동시킨다.
			while(size-- > 0) {
				Point curPoint = dochiQueue.poll();
				int curRow = curPoint.row, curCol = curPoint.col;
				
				
				// 상, 하, 좌, 우 이동
				for(int dir = 0; dir < deltaRow.length; dir++) {
					int newRow = curRow + deltaRow[dir];
					int newCol = curCol + deltaCol[dir];
					
					if(!isAvailable(newRow, newCol)) continue; // 맵 밖을 벗어난 경우
					
					if(map[newRow][newCol] == GOAL) return time+1; // 도치가 목적지에 도달하면 time+1 반환
					
					// 도치는 물에 잠길 곳, 돌, 이미 방문한 곳으로는 새롭게 탐색할 수 없다.
					if(map[newRow][newCol] == WATER ||
					   map[newRow][newCol] == ROCK ||
					   dochiVisited[newRow][newCol])
						continue;
					

					dochiVisited[newRow][newCol] = true; // 새로운 탐색지에 도치를 방문 처리한다.
					map[newRow][newCol] = DOCHI;
					dochiQueue.offer(new Point(newRow, newCol));
				}
			}
//			printMap();
			time++; // 시간을 증가시킨다.
			
		}
		
		
		// 3-4. 도치가 다음에 이동할 위치가 목적지라면 성공, 큐가 빌때까지 목적지에 도달하지 못하면 실패
		return -1;
	}
	
	static void printMap() {
		System.out.println("time: " + time);
		for(int rowIdx = 0; rowIdx < rowSize; rowIdx++)
			System.out.println(Arrays.toString(map[rowIdx]));
	}
	
	static boolean isAvailable(int row, int col) {
		return (row >= 0 && row < rowSize && col >= 0 && col < colSize);
	}
	
	
	static class Point {
		int row, col;

		public Point(int row, int col) {
			super();
			this.row = row;
			this.col = col;
		}

		
	}
}
