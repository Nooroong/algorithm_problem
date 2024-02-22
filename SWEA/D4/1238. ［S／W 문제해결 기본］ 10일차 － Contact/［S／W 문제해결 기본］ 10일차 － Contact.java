import java.util.*;
import java.io.*;

/**
 * 
 * @author JiYeon Sin
 * BFS를 이용하여 해결한다.
 * 각 너비의 탐색에서 번호가 가장 큰 사람들 기억해놓고
 * 마지막에 해당 번호를 출력하면 된다.
 * 
 * 1. 노드를 표현하는 클래스를 정의한다.
 * 2. 입력 받는 데이터의 길이와 시작점을 입력받는다.
 * 3. 인접 리스트를 생성한다.
 * 4. BFS를 위한 큐와 방문 기록 배열을 생성한다.
 * 5. 연락을 시작하는 사람의 번호를 큐에 넣는다.
 * 6. BFS를 돌린다.
 * 	7. 동일 너비의 탐색 대상 노드의 개수를 구한다.
 *  8. 위에서 구한 개수만큼 큐에서 원소를 빼닌다.
 *  9. 해당 탐색 레벨에서 가장 큰 번호를 찾는다.
 *  10. 현재 탐색 노드와 인접한 노드 중 방문하지 않은 노드 번호를 큐에 넣는다.
 *  11. 큐가 빌 때까지 7~10을 반복한다.
 * 12. 결과를 출력한다.
 */
public class Solution {
	// 1. 노드를 표현하는 클래스를 정의한다.
	static class Node {
		int to;
		Node next;

		public Node(int to, Node next) {
			super();
			this.to = to;
			this.next = next;
		}
	}
	
	
	public static BufferedReader br;
	public static StringTokenizer st;
	public static StringBuilder sb;
	
	public static final int TEST_CASE = 10; // 테스트 케이스의 수
	public static final int MAX_HUMAN = 100; // 최대 연락 인원
	
	public static int dataLen; // 입력 받는 데이터의 길이
	public static int startNumber; // 연락 시작점
	
	
	public static Queue<Integer> queue;
	public static Node[] adjList; // 인접 리스트
	public static boolean[] visited;
	
	public static int lastMaxNumber; // 나중에 연락 받는 사람 중 번호가 가장 큰 사람
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		
		for(int tc = 1; tc <= TEST_CASE; tc++) {
			// 입력 받는 데이터의 길이와 시작점을 입력받는다.
			st = new StringTokenizer(br.readLine().trim());
			dataLen = Integer.parseInt(st.nextToken());
			startNumber = Integer.parseInt(st.nextToken());
			
			// 2. 인접 리스트를 생성한다.
			adjList = new Node[MAX_HUMAN + 1]; // 사람 번호는 1부터 시작한다.

			
			
			// 3. 연락 방향 정보를 입력받으면서 간선 정보를 인접 리스트에 추가한다.
			st = new StringTokenizer(br.readLine().trim());
			for(int idx = 0; st.hasMoreTokens(); idx++) {
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				
				// 방향 그래프
				adjList[from] = new Node(to, adjList[from]);
			}
			
			
			// 4. BFS를 위한 큐와 방문 기록 배열을 생성한다.
			queue = new ArrayDeque<Integer>(); 
			visited = new boolean[MAX_HUMAN + 1];
			
			
			// 5. 연락을 시작하는 사람의 번호를 큐에 넣는다.
			queue.offer(startNumber);
			visited[startNumber] = true;
			
			BFS(); // 6. BFS를 돌린다.
			
			// 12. 결과를 출력한다.
			sb.append("#").append(tc).append(" ").append(lastMaxNumber);
			System.out.println(sb);
			sb.setLength(0);
		}
	}
	
	
	public static void BFS() {
		// 가장 마지막에 탐색하는 노드를 찾아야 한다.
		// 탐색의 최대 너비가 어떻게 될지 모르니까 그냥 매 탐색마다 최대 번호를 계산해준다.
		
		// 11. 큐가 빌 때까지 7~10을 반복한다.
		while(!queue.isEmpty()) {
			int curQueueSize = queue.size(); // 7. 동일 너비의 탐색 대상 노드의 개수를 구한다.
				
			lastMaxNumber = Integer.MIN_VALUE; 
			
			// 8. 위에서 구한 개수만큼 큐에서 원소를 빼닌다.
			while(curQueueSize > 0) {
				int curNodeIdx = queue.poll();
				
				// 9. 해당 탐색 레벨에서 가장 큰 번호를 찾는다. 
				if (curNodeIdx > lastMaxNumber) lastMaxNumber = curNodeIdx;
				
				
				// 10. 현재 탐색 노드와 인접한 노드 중 방문하지 않은 노드 번호를 큐에 넣는다.
				for(Node node = adjList[curNodeIdx]; node != null; node = node.next) {
					if(visited[node.to]) continue;
				
					visited[node.to] = true;
					queue.offer(node.to);
				}
				
				curQueueSize--;
			}
			
			// 이 부분에서 너비가 증가하게 된다.
		}
	}
	
}
