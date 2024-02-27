import java.io.*;
import java.util.*;

/**
 * 
 * @author JiYeon Sin
 * 다익스트라
 * 노드가 최대 2만개라서 인접 행렬 말고 인접 리스트를 사용해야 한다.
 *
 */
public class Main {
	public static BufferedReader br;
	public static StringTokenizer st;
	public static StringBuilder sb;
	
	public static int numOfVertex, numOfEdge; // 정점, 간선의 개수
	public static int startVertexIndex; // 시작 정점의 번호
	
	public static List<Vertex>[] adjList; // 인접 리스트
	public static final int INF = 20000 * 10; // 모든 간선의 가중치는 10 이하
	
	public static int[] distance; // 시작 정점에서의 거리
	public static Queue<Vertex> queue; // 인접한 정점 중 최단 거리의 정점을 찾기 위해 우선순위 큐를 사용한다.
	public static boolean[] visited;
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		// 정점의 개수, 간선의 개수를 입력받는다.
		st = new StringTokenizer(br.readLine().trim());
		numOfVertex = Integer.parseInt(st.nextToken());
		numOfEdge = Integer.parseInt(st.nextToken());
		
		// 시작 정점의 번호를 입력받는다.
		startVertexIndex = Integer.parseInt(br.readLine().trim());
		
		
		// 인접 리스트를 생성한다.
		adjList = new List[numOfVertex+1]; // 정점 번호는 1부터 시작한다.
		for(int rowIdx = 0; rowIdx <= numOfVertex; rowIdx++)
			adjList[rowIdx] = new ArrayList<Vertex>();
		
		
		// 간선 정보를 입력받아 인접 리스트에 값을 채운다.
		for(int idx = 0; idx < numOfEdge; idx++) {
			st = new StringTokenizer(br.readLine().trim());
			
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			
			adjList[from].add(new Vertex(to, weight));
		}
		
		
		// 다익스트라로 최단 경로를 구한다.
		dijkstra(startVertexIndex);
		
		
		
		// 구한 최단 경로를 출력한다.
		for(int idx = 1; idx <= numOfVertex; idx++)
			sb.append((distance[idx] >= INF) ? "INF": distance[idx]).append("\n");
		System.out.println(sb);
	}
	
	
	
	public static void dijkstra(int startV) {
		Vertex currentV; // 현재 방문하는 정점
		int currentIdx; // 현재 방문하는 정점의 번호
		
		// 최단 경로 정보 초기화
		distance = new int[numOfVertex+1];
		Arrays.fill(distance, INF);
		
		// 각종 초기화
		queue = new PriorityQueue<>(); // 최단 거리 정점 찾기
		visited = new boolean[numOfVertex+1];
		
		
		// 출발지 정점을 처리한다.(거리0, 큐에 넣어주기)
		distance[startV] = 0;
		queue.offer(new Vertex(startV, 0));
		

		while(!queue.isEmpty()) {
			currentV = queue.poll(); // 현재 처리하는 정점
			currentIdx = currentV.idx;
			
			// 이미 처리한 정점은 패스
			if(visited[currentIdx]) continue;
			
			visited[currentIdx] = true;
			
			// 현재 정점과 인접한 정점의 거리 정보를 갱신한다.
			for(Vertex v : adjList[currentIdx]) {
				int vIdx = v.idx; // 인접 정점의 번호
				
				// 기존에 구한 인접정점의 최단거리, 현재 정점의 최단거리+현재 정점~인접 정점을 계산하여 최소값을 갱신한다. 
				distance[vIdx] = (distance[vIdx] < distance[currentIdx] + v.distance) ?
							   distance[vIdx] : distance[currentIdx] + v.distance;
				queue.offer(new Vertex(vIdx, distance[vIdx]));
			}
		}
	}
	
	
	
	static class Vertex implements Comparable<Vertex> {
		int idx;
		int distance; // 가중치가 될 수도 있고, 시작 정점에서의 거리가 될 수도 있다.
		
		public Vertex(int idx, int distance) {
			super();
			this.idx = idx;
			this.distance = distance;
		}
		
		// 정렬기준
		@Override
		public int compareTo(Vertex o) {
			return Integer.compare(this.distance, o.distance);
		}
	}
}
 