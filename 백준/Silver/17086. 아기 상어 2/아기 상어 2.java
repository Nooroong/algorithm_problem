import java.io.*;
import java.util.*;

/**
 * 
 * @author JiYeon Sin
 * 
 * 빈칸에서 8방향으로 bfs를 이용.
 * 빈 칸과 아기 상어까지의 거리는 bfs의 탐색 level이 된다.
 * 
 * 1. 맵의 크기와 정보를 입력받는다.
 * 2. 모든 빈 칸에 대해 bfs를 시도한다.
 * 	2-1. 탐색도중 아기상어를 만나면 최대 거리를 갱신한다.
 * 3. 최대 안전 거리를 출력한다.
 *
 */
public class Main {
	static BufferedReader br;
	static StringTokenizer st;
	
	// 이동은 8방향
	static int[] deltaRow = { -1, 0, 1, 0, -1, 1, 1, -1 };
	static int[] deltaCol = { 0, 1, 0, -1, 1, 1, -1, -1 };
	
	static int rowSize, colSize, map[][];
	static int maxDistance; // 안전 거리의 최댓값
	
	// bfs
	static Queue<Point> queue = new ArrayDeque<>();
	static boolean[][] visited;
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		
		// 1. 맵의 크기와 정보를 입력받는다.
		// 맵의 크기 입력
		st = new StringTokenizer(br.readLine().trim());
		rowSize = Integer.parseInt(st.nextToken());
		colSize = Integer.parseInt(st.nextToken());
		
		map = new int[rowSize][colSize];
		visited = new boolean[rowSize][colSize];
		
		// 맵 정보 입력
		for(int rowIdx = 0; rowIdx < rowSize; rowIdx++) {
			st = new StringTokenizer(br.readLine().trim());
			
			for(int colIdx = 0; colIdx < colSize; colIdx++)
				map[rowIdx][colIdx] = Integer.parseInt(st.nextToken());
		}
		
		
		// 2. 모든 빈 칸에 대해 bfs를 시도한다.
		maxDistance = Integer.MIN_VALUE;
		
		for(int rowIdx = 0; rowIdx < rowSize; rowIdx++) { 
			for(int colIdx = 0; colIdx < colSize; colIdx++) {
				// 아기 상어가 있는 칸은 패스
				if(map[rowIdx][colIdx] == 1) continue;
				
				// bfs에 사용되는 visited, queue 초기화
				initVisited();
				queue.clear();
				
				queue.offer(new Point(rowIdx, colIdx));
				visited[rowIdx][colIdx] = true;
				bfs();
			}
		}
		
		// 3. 최대 안전 거리를 출력한다.
		System.out.println(maxDistance);
	}
	
	
	static void bfs() {
		int distance = 1;
		
		while(!queue.isEmpty()) {
			int size = queue.size();
			
			while(size-- > 0) {
				Point curPoint = queue.poll();
				int curRow = curPoint.row;
				int curCol = curPoint.col;
				
				// 8방향 탐색
				for(int dir = 0; dir < deltaRow.length; dir++) {
					int newRow = curRow + deltaRow[dir];
					int newCol = curCol + deltaCol[dir];
					
					// 지도 밖이나 이미 방문한 곳은 pass
					if(!boundaryCheck(newRow, newCol)) continue;
					if(visited[newRow][newCol]) continue;
					
					
					queue.offer(new Point(newRow, newCol));
					visited[newRow][newCol] = true;
					
					// 2-1. 탐색도중 아기상어를 만나면 최대 거리를 갱신한다.
					if(map[newRow][newCol] == 1) {
						maxDistance = Integer.max(distance, maxDistance);
						
						// 더이상 탐색할 필요가 없으므로 종료를 위해 큐를 비워버린다.
						// 안쪽 while문에서 사용하는 size도 0으로 해준다.
						queue.clear(); 
						size = 0;
						break;
					}

				}
			}
			
			distance++;
		}
	}
	
	
	static void initVisited() {
		for(int rowIdx = 0; rowIdx < rowSize; rowIdx++) 
			Arrays.fill(visited[rowIdx], false);
	}
	
	static boolean boundaryCheck(int rowIdx, int colIdx) {
		return (rowIdx >= 0 && rowIdx < rowSize && colIdx >= 0 && colIdx < colSize);
	}
	
	static class Point {
		int row;
		int col;
		
		public Point(int row, int col) {
			this.row = row;
			this.col = col;
		}
	}
}
