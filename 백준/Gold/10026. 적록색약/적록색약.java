import java.util.*;
import java.io.*;

/**
 * 
 * @author JiYeon Sin;
 * 그리드에 있는 색깔 뭉탱이의 개수를 찾아야 한다.
 * 다만 적록색약인 경우 R와 G를 같은 것으로 본다.
 * 
 * 0. 큐에 넣을 좌표 정보 클래스를 선언한다.
 * 1. 그리드의 세로(행), 가로(열) 길이를 입력받는다.
 * 2. 그리드의 정보를 입력받는다.
 * 3. 그리드 전체를 돌면서 색 뭉탱이의 수를 BFS로 구한다.
 *  4. 큐가 빌 때까지 계속 원소를 꺼내며 탐색을 진행한다.
 * 	5. 네 방향을 요소를 본다. 방문하지 않았으며 현재의 색과 같은 색을 가지는 요소를 이어서 탐색한다.
 * 	5-1. 적록색약의 경우 R와 G를 같은 것으로 본다.
 * 6. 결과를 적절히 출력한다.
 */
public class Main {
	// 0. 큐에 넣을 좌표 정보 클래스를 선언한다.
	static class Point {
		int row;
		int col;
		
		Point(int row, int col) {
			this.row = row;
			this.col = col;
		}
	}
	
	
	public static BufferedReader br;
	public static StringBuilder sb;
	
	public static int gridSize; // 그리드의 사이즈
	public static char[][] grid;
	
	
	public static Queue<Point> queue; // BFS에 사용되는 큐
	public static boolean[][] colorStrongVisited; // 색약이 아닌 사람의 각 요소 방문 여부
	public static boolean[][] colorWeakVisited; // 색약인 사람의 각 요소 방문 여부
	public static char preColor; // 이전에 본 색
	
	
	public static int colorStrongAreaCount; // 적록색약이 아닌 사람이 본 구역의 수
	public static int colorWeakAreaCount; // 적록색약인 사람이 본 구역의 수
	
	
	// 상, 우, 하, 좌 이동 정보
	public static int[] deltaRow = { -1, 0, 1, 0 };
	public static int[] deltaCol = { 0, 1, 0, -1 };
	public static final int DIRECTION = 4;
	
	
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		// 1. 그리드의 세로(행), 가로(열) 길이를 입력받는다.
		gridSize = Integer.parseInt(br.readLine().trim());
		
		
		// 각종 할당 및 초기화
		grid = new char[gridSize][gridSize];
		colorStrongAreaCount = colorWeakAreaCount = 0;
		queue = new ArrayDeque<Point>();
		colorStrongVisited = new boolean[gridSize][gridSize];
		colorWeakVisited = new boolean[gridSize][gridSize];
		
		
		// 2. 그리드의 정보를 입력받는다.
		for(int rowIdx = 0; rowIdx < gridSize; rowIdx++) {
			String line = br.readLine().trim();
			for(int colIdx = 0; colIdx < gridSize; colIdx++) {
				grid[rowIdx][colIdx] = line.charAt(colIdx);
			}
		}
		
		
		// 3. 그리드 전체를 돌면서 색 뭉탱이의 수를 BFS로 구한다.
		for(int rowIdx = 0; rowIdx < gridSize; rowIdx++) {
			for(int colIdx = 0; colIdx < gridSize; colIdx++) {
				// 방문하지 않은 노드가 있다면 탐색을 한다.

				// 색약인 사람
				if(!colorStrongVisited[rowIdx][colIdx]) {
					preColor = grid[rowIdx][colIdx];
					colorStrongVisited[rowIdx][colIdx] = true;
					queue.offer(new Point(rowIdx, colIdx));
					colorStrongBFS();
					
					colorStrongAreaCount++; // 뭉탱이 수 +1
				}
				
				// 색약이 아닌 사람
				if(!colorWeakVisited[rowIdx][colIdx]) {
					preColor = grid[rowIdx][colIdx];
					colorWeakVisited[rowIdx][colIdx] = true;
					queue.offer(new Point(rowIdx, colIdx));
					colorWeakBFS();
					
					colorWeakAreaCount++; // 뭉탱이 수 +1
				}
			}
		}
		
		
		// 6. 결과를 적절히 출력한다.
		sb.append(colorStrongAreaCount).append(" ").append(colorWeakAreaCount);
		System.out.println(sb);
	}
	
	
	
	
	
	public static void colorStrongBFS() { 
		// 4. 큐가 빌 때까지 계속 원소를 꺼내며 탐색을 진행한다.
		while(!queue.isEmpty()) {
			Point point = queue.poll();
			
			int currentRow = point.row;
			int currentCol = point.col;
			

			// 5. 네 방향을 요소를 본다. 방문하지 않았으며 현재의 색과 같은 색을 가지는 요소를 이어서 탐색한다.
			for(int dir = 0; dir < DIRECTION; dir++) {
				int newRow = currentRow + deltaRow[dir];
				int newCol = currentCol + deltaCol[dir];
				
				// 배열의 범위를 벗어나지 않도록 한다.
				if(newRow < 0 || newRow >= gridSize || newCol < 0 || newCol >= gridSize) {
					continue;
				}
				
				if(!colorStrongVisited[newRow][newCol] && grid[newRow][newCol] == preColor) {
					colorStrongVisited[newRow][newCol] = true;
					queue.offer(new Point(newRow, newCol));
				}
			}
		}
	}
	
	
	
	public static void colorWeakBFS() {
		// 4. 큐가 빌 때까지 계속 원소를 꺼내며 탐색을 진행한다.
		while(!queue.isEmpty()) {
			Point point = queue.poll();
			
			int currentRow = point.row;
			int currentCol = point.col;
			
			
			
			// 5. 네 방향을 요소를 본다. 방문하지 않았으며 현재의 색과 같은 색을 가지는 요소를 이어서 탐색한다.
			for(int dir = 0; dir < DIRECTION; dir++) {
				int newRow = currentRow + deltaRow[dir];
				int newCol = currentCol + deltaCol[dir];
				
				// 배열의 범위를 벗어나지 않도록 한다.
				if(newRow < 0 || newRow >= gridSize || newCol < 0 || newCol >= gridSize) {
					continue;
				}
				
				if(!colorWeakVisited[newRow][newCol]) {
					// 5-1. 적록색약의 경우 R와 G를 같은 것으로 본다.
					if(grid[newRow][newCol] == preColor ||
					  (grid[newRow][newCol] == 'R' && preColor == 'G') ||
					  (grid[newRow][newCol] == 'G' && preColor == 'R')) {
						colorWeakVisited[newRow][newCol] = true;
						queue.offer(new Point(newRow, newCol));
						preColor = grid[newRow][newCol];
					}
				}
			}
		}
	}
}
