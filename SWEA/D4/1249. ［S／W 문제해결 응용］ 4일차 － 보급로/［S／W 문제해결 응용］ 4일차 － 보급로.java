import java.io.*;
import java.util.*;

/**
 * 
 * @author JiYeon Sin
 * 복구시간을 가중치라고 생각 -> 다익스트라
 * 
 * 1. 입력받기
 * 2. 다익스트라에 필요한 pq와 visited 생성
 * 3. 모든 정점까지의 비용을 최대값으로 초기화
 * 4. 시작점~시작점 비용은 0
 * 5. 다익스트라 시작
 * 	5-1. 미방문 정점 중 가장 가까운 정점을 선택(경유지)
 * 	5-2. 경유지 방문처리
 * 	5-3. 경유지와 인접한 모든 미방문 정점을 본다.
 * 	5-4. 인접 정점들의 최소 비용 갱신(기존 비용 vs 현재 경유지를 거쳐 가는 비용)
 * 6. 도착지까지의 최소 비용을 출력한다.
 * 
 */
public class Solution {
	static BufferedReader br;
	static StringBuilder sb;
	
	static int testCase;
	static int mapSize, map[][];
	
	static int[] deltaRow = { -1, 0, 1, 0 };
	static int[] deltaCol = { 0, 1, 0, -1 };
	
	static final int INF = 9*100*100 + 1;
	static Queue<Point> queue;
	static boolean[][] visited;
	static int[][] cost;
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		testCase = Integer.parseInt(br.readLine().trim());
		for(int tc = 1; tc <= testCase; tc++) {
			// 1. 입력받기
			// 맵의 크기 입력받기
			mapSize = Integer.parseInt(br.readLine().trim());
			map = new int[mapSize][mapSize];
			
			
			// 맵 정보 입력받기
			for(int rowIdx = 0; rowIdx < mapSize; rowIdx++) {
				String line = br.readLine().trim();
				
				for(int colIdx = 0; colIdx < mapSize; colIdx++)
					map[rowIdx][colIdx] = line.charAt(colIdx) - '0'; // type 주의
			}
			
			// 2. 다익스트라에 필요한 pq와 visited 생성
			queue = new PriorityQueue<>();
			visited = new boolean[mapSize][mapSize];
			
			// 3. 모든 정점까지의 비용을 최대값으로 초기화
			cost = new int[mapSize][mapSize];
			for(int rowIdx = 0; rowIdx < mapSize; rowIdx++)
				Arrays.fill(cost[rowIdx], INF);
			
			// 4. 시작점~시작점 비용은 0
			cost[0][0] = 0;
			
			// 5. 다익스트라 시작
			queue.offer(new Point(0, 0, 0));
			while(!queue.isEmpty()) {
				// 5-1. 미방문 정점 중 가장 가까운 정점을 선택(경유지)
				Point curPoint = queue.poll();
				int curRow = curPoint.row;
				int curCol = curPoint.col;
				
				// 5-2. 경유지 방문처리
				visited[curRow][curCol] = true;
							
				// 5-3. 경유지와 인접한 모든 미방문 정점을 본다.
				for(int dir = 0; dir < deltaRow.length; dir++) {
					int newRow = curRow + deltaRow[dir];
					int newCol = curCol + deltaCol[dir];
					
					if(!boundaryCheck(newRow, newCol)) continue;
					if(visited[newRow][newCol]) continue;
					
					// 5-4. 인접 정점들의 최소 비용 갱신(기존 비용 vs 현재 경유지를 거쳐 가는 비용)
					cost[newRow][newCol] = Integer.min(cost[newRow][newCol],
							   cost[curRow][curCol] + map[newRow][newCol]);
					
					
					queue.offer(new Point(newRow, newCol, cost[newRow][newCol]));
				}
			}
			
			
			// 6. 도착지까지의 최소 비용을 출력한다.
			sb.append("#").append(tc).append(" ").append(cost[mapSize-1][mapSize-1]);
			System.out.println(sb);
			sb.setLength(0);
		}
		
	}
	
	static boolean boundaryCheck(int row, int col) {
		return (row >= 0 && row < mapSize && col >= 0 && col < mapSize);
	}
	
	
	static class Point implements Comparable<Point> {
		int row;
		int col;
		int cost;

		public Point(int row, int col, int cost) {
			this.row = row;
			this.col = col;
			this.cost = cost;
		}

		@Override
		public int compareTo(Point o) {
			// 미방문 정점에 대한 정렬은 비용을 기준으로 한다.
			return (this.cost - o.cost);
		}
		
		
	}

}
