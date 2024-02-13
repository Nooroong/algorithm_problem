import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * BOJ
 * @author eunwoo.lee
 * 
 * 1. 입력
 * 	1-1. 정점의 개수, 간선의 개수, 탐색 시작 정점을 입력받는다.
 * 	1-2. 간선의 개수만큼 반복해 연결하는 두 정점을 입력받는다.
 * 
 * 2. DFS
 * 3. 방문 배열 초기화
 * 4. BFS
 * 5. 출력
 */

public class Main {
	
	public static BufferedReader br;
	public static StringBuilder sb;
	public static StringTokenizer st;
	
	public static int nodeNum, edgeNum, startNode;
	public static int[][] graph;
	public static boolean[] isVisited;
	public static Queue<Integer> queue;
	
	public static void dfs(int node) {
		// 현재 노드 방문처리
		isVisited[node] = true;
		sb.append(node+" ");
		// 현재 노드와 연결되어있고 방문하지 않았다면 재귀 실행
		for (int adjIdx=1; adjIdx<=nodeNum; adjIdx++) {
			if (graph[node][adjIdx]==1 && !isVisited[adjIdx]) {
				dfs(adjIdx);
			}
		}
	}
	
	public static void bfs(int node) {
		queue.add(node);
		isVisited[node] = true;
		while (!queue.isEmpty()) {
			int v = queue.poll();
			sb.append(v+" ");
			// 현재 노드와 연결되어있고 방문하지 않았다면
			for (int adjIdx=1; adjIdx<=nodeNum; adjIdx++) {
				if (graph[v][adjIdx]==1 && !isVisited[adjIdx]) {
					queue.add(adjIdx);
					isVisited[adjIdx] = true;
				}
			}
		}
	}
	

	public static void main(String[] args) throws Exception{
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		// 1. 입력
		// 1-1. 정점의 개수, 간선의 개수, 탐색 시작 정점을 입력받는다.
		st = new StringTokenizer(br.readLine().trim());
		nodeNum = Integer.parseInt(st.nextToken());
		edgeNum = Integer.parseInt(st.nextToken());
		startNode = Integer.parseInt(st.nextToken());
		// 1-2. 간선의 개수만큼 반복해 연결하는 두 정점을 입력받는다.
		graph = new int[nodeNum+1][nodeNum+1];		
		for (int edgeIdx=0; edgeIdx<edgeNum; edgeIdx++) {
			st = new StringTokenizer(br.readLine().trim());
			int edgeNode1 = Integer.parseInt(st.nextToken());
			int edgeNode2 = Integer.parseInt(st.nextToken());
			graph[edgeNode1][edgeNode2] = graph[edgeNode2][edgeNode1] = 1;
		}
		isVisited = new boolean[nodeNum+1];
		dfs(startNode);
		sb.append("\n");
		isVisited = new boolean[nodeNum+1];
		queue = new ArrayDeque<>();
		bfs(startNode);
		
		System.out.println(sb);

	}

}
