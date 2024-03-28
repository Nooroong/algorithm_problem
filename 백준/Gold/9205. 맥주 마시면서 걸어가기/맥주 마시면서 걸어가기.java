import java.io.*;
import java.util.*;

/**
 * 
 * @author JiYeon Sin
 * 맥주를 최대 20병까지 들 수 있으므로 최대 1000m까지는 편의점에 들르지 않고 이동할 수 있다.
 * 집, 편의점, 락페를 노드로 본다.
 * 상근이의 현재 위치부터 방문하지 다른 노드까지의 거리를 측정하고
 * 이동할 수 있는 노드로 전부 이동한다.(BFS)(이미 방문한 곳은 다시 갈 이유가 없는듯?)
 * 락페에 도착하면 happy, 이동할 수 있는 곳은 다 가봤는데도 락페에 도착하지 못하면 sad를 출력.
 */

public class Main {
	static BufferedReader br;
	static StringTokenizer st;
	
	static int testCase;
	
	static int conveniCount; // 편의점 개수
	static Point homePoint; // 상근이집 좌표
	static Point[] node; // 편의점 좌표
	static Point rockFestPoint; // 락페 좌표
	
	static boolean isHappy;
	
	static int[] deltaRow = { -1, 0, 1, 0 };
	static int[] deltaCol = { 0, 1, 0, -1 };
	
	static final int MAX_MOVE_LEN = 1000;
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		testCase = Integer.parseInt(br.readLine().trim());
		
		
		for(int tc = 1; tc <= testCase; tc++) {
			isHappy = false;
			
			pointInput(); // 좌표를 입력받기
			
			BFS();
			
			System.out.println(isHappy ? "happy" : "sad");
		}
	}
	
	
	
	
	
	
	static void pointInput() throws IOException {
		conveniCount = Integer.parseInt(br.readLine().trim()); // 편의점 개수 입력받기
		
		
		node = new Point[conveniCount+2];
		
		// 상근이집 좌표 입력
		st = new StringTokenizer(br.readLine().trim());
		homePoint = new Point(Integer.parseInt(st.nextToken()),
						      Integer.parseInt(st.nextToken()), 0);
		node[0] = homePoint;
		
		
		// 편의점 좌표 입력
		for(int cIdx = 1; cIdx <= conveniCount; cIdx++) {
			st = new StringTokenizer(br.readLine().trim());
			node[cIdx] = new Point(Integer.parseInt(st.nextToken()),
										   Integer.parseInt(st.nextToken()), cIdx);
		}
		
		// 락페 좌표 입력
		st = new StringTokenizer(br.readLine().trim());
		rockFestPoint = new Point(Integer.parseInt(st.nextToken()),
				     			  Integer.parseInt(st.nextToken()), conveniCount+1);
		node[conveniCount+1] = rockFestPoint;
	}
	
	
	static void BFS() {
		Queue<Point> queue = new ArrayDeque<>();
		boolean[] visited = new boolean[conveniCount+2]; // 집과 락페도 방문 노드 중 하나
		
		queue.offer(node[0]); // 집 처리
		
		while(!queue.isEmpty() ) {
			Point curPoint = queue.poll();
			
			// 락페에 도착하면 끝
			if(curPoint.order == rockFestPoint.order) {
				isHappy = true;
				return;
			}
			
			
			if(visited[curPoint.order]) continue; // 이미 방문한 노드는 재방문하지 않는다.
			visited[curPoint.order] = true; // 방문하지 않은 노드에 대해 방문처리
			
			// 상근이의 현재 위치로부터 편의점에 대해 거리 측정
			for(int idx = 1; idx < conveniCount+2; idx++) {
				int dist = getDistance(curPoint, node[idx]);
				
				// 해당 노드로 이동할 수 있는 경우 간다. == 큐에 넣는다.
				if(dist <= MAX_MOVE_LEN) {
					queue.offer(node[idx]);
				}
			}
			
		}
	
	}
	
	
	static int getDistance(Point p1, Point p2) {
		return Math.abs(p1.row-p2.row) + Math.abs(p1.col-p2.col);
	}
	
	
	static class Point {
		int row, col;
		int order;

		public Point() { }

		public Point(int row, int col, int order) {
			super();
			this.row = row;
			this.col = col;
			this.order = order;
		}
	}
}
