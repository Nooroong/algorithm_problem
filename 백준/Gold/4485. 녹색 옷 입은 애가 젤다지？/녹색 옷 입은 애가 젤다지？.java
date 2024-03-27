import java.util.*;
import java.io.*;

/**
 * 
 * @author JiYeon Sin
 * 도둑 루피를 가중치로 보면 이 문제는 최단경로를 찾는 문제로 볼 수 있다. -> 다익스트라
 * 
 * 1. 동굴의 크기를 입력받는다. 0이 입력되면 종료.
 * 2. 동굴 배열을 할당하고 도둑루피 정보를 입력받는다.
 * 3. 링크는 0, 0부터 탐색을 시작한다. 다익스트라로 최단 경로를 구한다.
 * 4. 구한 최단 경로를 출력한다.
 * 
 */
public class Main {
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	
	static final int INF = Integer.MAX_VALUE;
	
	static int[] deltaRow = { -1, 0, 1, 0 };
	static int[] deltaCol = { 0, 1, 0, -1 };
	
	static int mapSize; // 동굴의 크기
	static int[][] map; // 동굴 정보
	static int curRow, curCol; // 링크의 현재 위치
	
	static int minRupeeLoss; // 잃은 최소 금액 
	
	static Queue<Point> queue; // 인접한 정점 중 최단 비용의 정점을 찾기 위해 우선순위 큐를 사용한다.
	static boolean[][] visited;
	static int[][] cost; // 각 지점까지의 비용을 저장하는 배열
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		for(int tc = 1; ; tc++) {
			minRupeeLoss = 0;
			
			// 1. 동굴의 크기를 입력받는다. 0이 입력되면 종료.
			mapSize = Integer.parseInt(br.readLine().trim());
			if(mapSize == 0) return;
			
			// 2. 동굴 배열을 할당하고 도둑루피 정보를 입력받는다.
			map = new int[mapSize][mapSize];
			for(int rowIdx = 0; rowIdx < mapSize; rowIdx++) {
				st = new StringTokenizer(br.readLine().trim());
				for(int colIdx = 0; colIdx < mapSize; colIdx++) {
					map[rowIdx][colIdx] = Integer.parseInt(st.nextToken());
				}
			}
			
			// 3. 링크는 0, 0부터 탐색을 시작한다. 다익스트라로 최단 경로를 구한다.
			curRow = curCol = 0;
			dijkstra(0, 0);
			
			// 4. 구한 최단 경로를 출력한다.
			sb.append("Problem ").append(tc).append(": ").append(cost[mapSize-1][mapSize-1]);
			System.out.println(sb);
			sb.setLength(0);
		}
		
	}
	
	
	
	public static void dijkstra(int startRow, int startCol) {
		// 각 정점의 비용을 담는 배열을 할당하고 INF로 초기화를 한다.
		cost = new int[mapSize][mapSize];
		for(int rowIdx = 0; rowIdx < mapSize; rowIdx++) {
			Arrays.fill(cost[rowIdx], INF);
		}
		
		// 큐와 방문배열 초기화
		queue = new PriorityQueue<>();
		visited = new boolean[mapSize][mapSize];
		
		// 시작 정점을 처리한다.
		// 이 문제에는 시작 정점에도 비용이 있다는 점에 주의!
		cost[startRow][startCol] = map[startRow][startCol];
		queue.offer(new Point(startRow, startCol, map[startRow][startCol]));
		
		while(!queue.isEmpty()) {
			Point current = queue.poll(); // 현재 처리할 정점
			int curRow = current.row;
			int curCol = current.col;
			
			// 이미 처리한 정점은 pass
			if(visited[curRow][curCol]) continue;
			
			visited[curRow][curCol] = true;
			
			
			// 현재 정점과 인접한 정점의 거리 정보를 갱신한다.
			for(int dir = 0; dir < deltaRow.length; dir++) {
				int newRow = curRow + deltaRow[dir];
				int newCol = curCol + deltaCol[dir];
				
				// 배열의 범위를 벗어나지 않도록 주의
				if(newRow < 0 || newRow >= mapSize || newCol < 0 || newCol >= mapSize) continue;
				
				// 인접 정점의 기존 비용 vs 현재 정점까지의 비용+인접 정점까지의 비용
				cost[newRow][newCol] = min(cost[newRow][newCol],
											cost[curRow][curCol] + map[newRow][newCol]);
				
				// 인점 정점을 다음번에 처리하기 위해 큐에 넣는다.
				queue.offer(new Point(newRow, newCol, cost[newRow][newCol]));
			}
		}
		
		
	}
	
	
	
	
	static class Point implements Comparable<Point> {
		int row, col; // 점의 좌표
		int rupee; // 도둑 루피
		
		public Point() {
			super();
		}
		
		public Point(int row, int col, int rupee) {
			super();
			this.row = row;
			this.col = col;
			this.rupee = rupee;
		}

		@Override
		public int compareTo(Point o) {
			return Integer.compare(this.rupee, o.rupee);
		}
	}
	
	
	static int min(int a, int b) {
		return (a <= b) ? a : b;
	}

}
