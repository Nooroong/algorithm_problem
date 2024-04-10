import java.io.*;
import java.util.*;

/**
 * 
 * @author JiYeon Sin
 * 
 * 빈 칸을 출발점으로 하여 bfs를 할 수도 있지만
 * 아기 상어를 출발점으로 하면 좀 더 빠르게 정답을 찾을 수 있다.
 * 가중치가 없는 그래프에서 bfs는 최단 거리를 찾는다.
 * 
 * 1. 맵의 크기와 정보를 입력받는다.
 * 	1-1. 아기 상어의 위치를 큐에 저장한다.
 * 2. 모든 아기 상어에 대해 bfs.
 * 	2-1. 아직 탐색되지 않은 빈칸이라면 거리를 기록하고 큐에 좌표를 삽입한다.
 * 		2-1-1. 이때 최대 안전 거리도 갱신한다.
 * 	2-2. 이미 탐색된 빈칸이라면 이미 다른 아기상어에 의해 최단 거리가 기록된 것이므로 pass
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
	static int[][] visited;
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		
		// 1. 맵의 크기와 정보를 입력받는다.
		// 맵의 크기 입력
		st = new StringTokenizer(br.readLine().trim());
		rowSize = Integer.parseInt(st.nextToken());
		colSize = Integer.parseInt(st.nextToken());
		
		map = new int[rowSize][colSize];
		visited = new int[rowSize][colSize];
		
		// 맵 정보 입력
		for(int rowIdx = 0; rowIdx < rowSize; rowIdx++) {
			st = new StringTokenizer(br.readLine().trim());
			
			for(int colIdx = 0; colIdx < colSize; colIdx++) {
				map[rowIdx][colIdx] = Integer.parseInt(st.nextToken());
				
				// 1-1. 아기 상어의 위치를 큐에 저장한다.
				if(map[rowIdx][colIdx] == 1) {
					queue.offer(new Point(rowIdx, colIdx));
					visited[rowIdx][colIdx] = -1; // 아기 상어가 있는 곳은 거리를 음수로 표시(미방문 빈칸과 구분)
				}
			}
				
		}
		
		
		// 2. 모든 아기 상어에 대해 bfs.
		maxDistance = Integer.MIN_VALUE;
		bfs();

		
		
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
					
					// 2-2. 이미 탐색된 빈칸이라면 이미 다른 아기상어에 의해 최단 거리가 기록된 것이므로 pass
					if(!boundaryCheck(newRow, newCol)) continue; // 얘는 맵을 벗어난 경우
					if(visited[newRow][newCol] != 0) continue;
					
					// 2-1. 아직 탐색되지 않은 빈칸이라면 거리를 기록하고 큐에 좌표를 삽입한다.
					queue.offer(new Point(newRow, newCol));
					visited[newRow][newCol] = distance;
					
					// 2-1-1. 이때 최대 안전 거리도 갱신한다.
					maxDistance = Integer.max(distance, maxDistance);

				}
			}
			
			distance++;
		}
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
