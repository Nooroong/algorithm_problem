import java.io.*;
import java.util.*;

/**
 * 
 * @author JiYeon Sin
 * <조건>
 * 1) 구역을 공집합이 아닌 두 집합로 나눠야한다.
 * 2) 한 집합에 속해있는 선거구들은 모두 연결되어야 한다.
 * 
 * 백준시를 두 선거구로 나눌 수 있는 모든 경우의 수를 따진다.
 * -> 조합(nCi. i는 1부터 n-1까지)
 * 두 선거구로 나눴으면 각 선거구에 대해 BFS로 탐색을 하면서 가능한 방법인지 따지고
 * 가능하다면 인구수의 차이를 구한다.
 * BFS로 탐색한 원소의 수가 selectCount와 다르면
 * 이는 불가능한 방법이다.
 * 
 * 인접 리스트를 만들기 위해 노드 클래스를 정의한다.
 * 구역의 개수를 입력받는다.
 * 구역의 번호는 1부터 시작한다.
 * 각 구역의 인구수를 입력받는다.
 * 각 구역과 인접한 구역의 정보를 입력받는다.
 */
public class Main {
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
	
	public static int numOfArea; // 구역의 개수
	public static int[] population; // 각 구역의 인구 수
	
	public static Node[] adjList; // 인접 리스트
	
	public static int selectCount; // 뽑아야하는 원소의 수
	public static boolean[] elementUsedList; // 사용한 원소 정보
	public static boolean sperated = false;
	
	// BFS를 위한 변수들
	public static Queue<Integer> queue;
	public static boolean[] visited;
	
	public static int minPopulation = Integer.MAX_VALUE;
	
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		
		numOfArea = Integer.parseInt(br.readLine().trim()); // 구역의 개수를 입력받는다.
		
		
		// 각 구역의 인구수를 입력받는다.
		population = new int[numOfArea + 1]; // 구역의 번호는 1부터 시작한다.
		st = new StringTokenizer(br.readLine().trim());
		for(int idx = 1; idx <= numOfArea; idx++) {
			population[idx] = Integer.parseInt(st.nextToken());
		}
		
		// 인접 리스트를 생성한다.
		adjList = new Node[numOfArea + 1];
		
		
		// 인접한 구역 정보를 입력받아 인접 리스트를 완성한다.
		for(int fromIdx = 1; fromIdx <= numOfArea; fromIdx++) {
			st = new StringTokenizer(br.readLine().trim());
			
			int adjCount = Integer.parseInt(st.nextToken());
			for(int adjIdx = 0; adjIdx < adjCount; adjIdx++) {
				int to = Integer.parseInt(st.nextToken());
				adjList[fromIdx] = new Node(to, adjList[fromIdx]);
				adjList[to] = new Node(fromIdx, adjList[to]);
			}
		}
		
		
		
		// 백준시를 두 개의 구역으로 나눈다. (nCi. i는 1부터 n-1까지)
		for(selectCount = 1; selectCount < numOfArea; selectCount++) {
			elementUsedList = new boolean[numOfArea+1];
			combination(0, 1); // 구역의 번호는 1번부터
		}
		
		
		
		System.out.println((sperated) ? minPopulation : "-1");
	}
	
	
	
	
	
	
	public static void combination(int selectIdx, int elementIdx) {
		// 기저조건1 : 뽑을만큼 뽑은경우
		if(selectIdx == selectCount) {
			// 두 선거구로 나눴으면 각 선거구에 대해 BFS로 탐색을 하면서
			// 모든 구역이 연결되는지 확인하고 인구수의 차이를 구한다.
			int diff = populationDiff();
			
			minPopulation = (diff < minPopulation) ? diff : minPopulation;
//			if(sperated && diff != -1) {
//				
//			}
			
			return;
		}
		
		
		
		
		// 기저조건 2: 모든 원소를 다 살핀 경우
		if(elementIdx == numOfArea) return;
		
		// 현재 원소를 뽑은 경우
		elementUsedList[elementIdx] = true;
		combination(selectIdx+1, elementIdx+1);
		
		
		// 현재 원소를 안 뽑은 경우
		elementUsedList[elementIdx] = false;
		combination(selectIdx, elementIdx+1);
	}
	
	
	public static int populationDiff() {
		// 두 개의 구역은  elementUsedList가 true인 것과 false인 것으로 나눌 수 있다.
		int area1PopulSum = 0; // 1번 선거구의 총 인구수
		int area2PopulSum = 0; // 2번 선거구의 총 인구수
		
		
		queue = new ArrayDeque<Integer>(); // BFS를 위한 큐 생성
		visited = new boolean[numOfArea + 1];
		
		
		
		// 큐에 첫 원소를 넣기 위해 for를 돌린다.
		
		// 선거구 1: elementUsedList가 true인 것
		for(int idx = 1; idx <= numOfArea; idx++) {
			if(elementUsedList[idx]) {
				visited[idx] = true;
				queue.offer(idx);
				area1PopulSum = trueElementBFS();
				// 인구수를 구해야 한다.
				break;
			}
		}
		
		// 선거구 2: elementUsedList가 false인 것
		for(int idx = 1; idx <= numOfArea; idx++) {
			if(!elementUsedList[idx]) {
				visited[idx] = true;
				queue.offer(idx);
				area2PopulSum = falseElementBFS();
				break;
			}
		}
		
		if(area1PopulSum == -1 || area2PopulSum == -1) {
			return Integer.MAX_VALUE;
		} else {
			sperated = true;
		}
		
		return Math.abs(area1PopulSum-area2PopulSum);
	}
	
	
	
	// 조합으로 구성한 선거구에서 elementUsedList가 true인 것만 탐색
	public static int trueElementBFS() {
		int populSum = 0; // 해당 선거구의 총 인구수
		int searchCount = 0; // 탐색에 성공한 노드의 수
		
		while(!queue.isEmpty()) {
			int curNode = queue.poll();

			populSum += population[curNode];
			searchCount++;
			
			for(Node node = adjList[curNode]; node != null; node = node.next) {
				if(visited[node.to]) continue;
				
				
				if(elementUsedList[node.to])
				{
					visited[node.to] = true;
					queue.offer(node.to);
					
				}
			}
		}
		

		if(searchCount != selectCount) return -1; // 고른 선거구 전체를 탐색할 수 없다면
		return populSum;
	}
	
	
	// 조합으로 구성한 선거구에서 elementUsedList가 false인 것만 탐색
	public static int falseElementBFS() {
		int populSUm = 0; // 해당 선거구의 총 인구수
		int searchCount = 0; // 탐색에 성공한 노드의 수
		
		while(!queue.isEmpty()) {
			int curNode = queue.poll();

			populSUm += population[curNode];
			searchCount++;
			
			for(Node node = adjList[curNode]; node != null; node = node.next) {
				if(visited[node.to]) continue;
				
				if(!elementUsedList[node.to])
				{
					visited[node.to] = true;
					queue.offer(node.to);
				}
				
			}
		}
		
		
		if(searchCount != (numOfArea-selectCount)) return -1; // 고른 선거구 전체를 탐색할 수 없다면
		return populSUm;
	}

	
	
}
