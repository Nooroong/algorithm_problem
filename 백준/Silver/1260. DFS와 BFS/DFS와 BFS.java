import java.util.*;
import java.io.*;

/**
 * 
 * @author JiYeon Sin
 * 1. 정점의 개수, 간선의 개수, 탐색 시작 정점 번호를 입력받는다.
 * 2. 간선의 정보를 리스트 type의 배열로 관리한다.
 * 3. 간선의 정보를 입력받는다.
 * 4. 입력받는 간선의 정보를 바탕으로 리스트에 원소를 추가한다.
 *    간선에는 방향성이 없음에 주의한다.
 * 5. 간선 정보를 오름차순으로 정렬한다.
 *    (방문할 수 있는 정점이 여러 개인 경우에는 정점 번호가 작은 것을 먼저 방문)
 * 6. bfs, dfs로 시작점부터 탐색을 하여 방문한 노드를 출력한다.
 * 
 * <dfs>
 * 재귀함수를 이용한다.
 * 1. 현재 노드를 방문처리 한다.
 * 2. 주변에 연결된 노드들을 살핀다.
 * 3. 방문하지 않은 노드가 있다면 방문한다.
 * 4. 주변의 노드를 다 방문했으면 탐색을 종료하고 이전노드로 돌아간다.
 * 
 * <bfs>
 * 큐를 이용한다.
 * 0. 우선 시작노드를 큐에 삽입한다.
 * 1. 큐가 빌 때까지 반복한다.
 * 2. 큐에서 원소를 하나 꺼내고 아직 방문하지 않았으면 방문처리를 한다.
 * 3. 현재 노드와 연결된 노드들을 살핀다.
 * 4. 방문하지 않은 노드가 있다면 큐에 삽입한다.
 */

public class Main {
	public static BufferedReader br;
	public static StringTokenizer st;
	public static StringBuilder sb;
	
	public static Queue<Integer> queue = new ArrayDeque<>(); // BFS를 위한 큐
	
	public static int numOfNode; // 정점의 개수
	public static int numOfEdge; // 간선의 개수
	public static int startNodeNum; // 탐색 시작 정점의 번호
	
	public static int fromNode; // 간선과 연결된 노드 1
	public static int toNode; // 간선과 연결된 노드 2
	public static boolean[] visited; // 노드의 방문여부를 저장하는 배열
	
	public static boolean endFlag; // dfs 종료 플래그
	
	public static List<Integer>[] edge; // 간선의 정보를 저장하는 리스트 배열
	
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		
		// 1. 정점의 개수, 간선의 개수, 탐색 시작 정점 번호를 입력받는다.
		st = new StringTokenizer(br.readLine().trim());
		numOfNode = Integer.parseInt(st.nextToken());
		numOfEdge = Integer.parseInt(st.nextToken());
		startNodeNum = Integer.parseInt(st.nextToken());
		
		
		// 2. 간선의 정보를 리스트 type의 배열로 관리한다.
		edge = new ArrayList[numOfNode + 1];
		for(int idx = 0; idx <= numOfNode; idx++) {
			edge[idx] = new ArrayList<Integer>();
		}
		
		
		// 3. 간선의 정보를 입력받는다.
		for(int idx = 0; idx < numOfEdge; idx++) {
			st = new StringTokenizer(br.readLine().trim());
			fromNode = Integer.parseInt(st.nextToken());
			toNode = Integer.parseInt(st.nextToken());
			
			// 4. 입력받는 간선의 정보를 바탕으로 리스트에 원소를 추가한다.
			// 간선에는 방향성이 없음에 주의한다.
			edge[fromNode].add(toNode);
			edge[toNode].add(fromNode);
		}
		
		
		// 5. 간선 정보를 오름차순으로 정렬한다.
		// (방문할 수 있는 정점이 여러 개인 경우에는 정점 번호가 작은 것을 먼저 방문)
		for(int idx = 1; idx <= numOfNode; idx++) {
			Collections.sort(edge[idx]);
		}
		

		// 6. bfs, dfs로 시작점부터 탐색을 하여 방문한 노드를 출력한다.
		// dfs
		visited = new boolean[numOfNode+1];
		dfs(startNodeNum);
		System.out.println(sb);
		sb.setLength(0);
		
		
		// bfs
		visited = new boolean[numOfNode+1];
		queue.offer(startNodeNum); // 0. 우선 시작노드를 큐에 삽입한다.
		bfs();
		System.out.println(sb);
	}
	
	
	
	public static void dfs(int currentNode) {
		// 1. 현재 노드를 방문처리 한다.
		sb.append(currentNode).append(" ");
		visited[currentNode] = true;
		
		
		// 2. 주변에 연결된 노드들을 살핀다.
		for(int node : edge[currentNode]) {
			// 이미 방문한 인근 노드는 건너뛴다.
			if(visited[node]) {
				continue;
			}
			
			// 3. 방문하지 않은 노드가 있다면 방문한다.
			dfs(node);
		}
		
		// 4. 주변의 노드를 다 방문했으면 탐색을 종료하고 이전노드로 돌아간다.
		return;
	}
	
	
	public static void bfs() {
		int currentNode;
		
		// 1. 큐가 빌 때까지 반복한다.
		while(!queue.isEmpty() ) {
			// 2. 큐에서 원소를 하나 꺼내고 아직 방문하지 않았으면 방문처리를 한다.
			currentNode = queue.poll();
			if(!visited[currentNode]) {
				visited[currentNode] = true;
				sb.append(currentNode).append(" ");
			}
			
			
			// 3. 현재 노드와 연결된 노드들을 살핀다.
			for(int node : edge[currentNode]) {
				// 이미 방문한 인근 노드는 건너뛴다.
				if(visited[node]) {
					continue;
				}
				
				// 4. 방문하지 않은 노드가 있다면 큐에 삽입한다.
				queue.offer(node);
			}
		}
	}
}
