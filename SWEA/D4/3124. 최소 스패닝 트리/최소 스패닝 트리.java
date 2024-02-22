import java.io.*;
import java.util.*;

/**
 * 
 * @author JiYeon Sin
 * 크루스칼 알고리즘을 통해 MST를 구성한다.
 * 
 * 1. 간선 리스트로 그래프를 표현하기 위한 클래스를 정의한다.(연결된 정점 2개, 가중치 정보)
 * 2. union-find 관련 변수와 메서드를 정의한다.
 * 3. 테스트 케이스의 개수를 입력받는다.
 * 4. 정점 개수와 간선 개수를 입력받는다.
 * 5. 간선의 정보를 입력받아 간선 리스트에 적절히 추가한다.
 * 6. 간선 리스트를 가중치를 기준으로 오름차순 정렬한다.
 * 7. 가중치가 가장 작은 간선을 차례대로 선택한다.
 * 	8. union연산을 수행하여 사이클이 발생하지 않으면 해당 간선을 선택한다.
 *  9. 총 가중치를 계산한다.
 * 9. 정점의 수-1개의 간선을 선택하면 가중치를 출력한다. 
 */

public class Solution {
	// 1. 간선 리스트로 그래프를 표현하기 위한 클래스를 정의한다.(연결된 정점 2개, 가중치 정보)
	static class Edge implements Comparable<Edge> {
		int from, to;
		int weight;
		
		
		public Edge(int from, int to, int weight) {
			super();
			this.from = from;
			this.to = to;
			this.weight = weight;
		}

		
		// 가중치를 기준으로 간선 정보를 정렬하기 위해 오버라이드한다.
		@Override
		public int compareTo(Edge o) {
			return Integer.compare(this.weight, o.weight);
		}
	}
	
	public static BufferedReader br;
	public static StringTokenizer st;
	public static StringBuilder sb;
	
	public static int testCase; // 테스트 케이스의 개수

	
	// 2. union-find 관련 변수와 메서드를 정의한다.
	public static int[] parentList;
	public static int[] rankList;
	
	
	public static int numOfVertex; // 정점의 개수
	public static int numOfEdge; // 간선의 개수
	
	public static Edge[] edgeList; // 간선 정보를 담는 리스트
	public static int selectEdgeCount; // 선택한 간선의 수
	
	// MST의 가중치
	// 정점의 최대 개수가 10만이고 가중치의 최대값이 100만이므로
	// 타입을 long으로 해줘야한다.
	public static long mstWeight; 
	
	
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		
		// 3. 테스트 케이스의 개수를 입력받는다.
		testCase = Integer.parseInt(br.readLine().trim());
		
		for(int tc = 1; tc <= testCase; tc++) {			
			// 4. 정점 개수와 간선 개수를 입력받는다.
			st = new StringTokenizer(br.readLine().trim());
			numOfVertex = Integer.parseInt(st.nextToken());
			numOfEdge = Integer.parseInt(st.nextToken());

			
			
			// 5. 간선의 정보를 입력받아 간선 리스트에 적절히 추가한다.
			edgeList = new Edge[numOfEdge];
			for(int edgeIdx = 0; edgeIdx < numOfEdge; edgeIdx++) {
				st = new StringTokenizer(br.readLine().trim());
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				int weight = Integer.parseInt(st.nextToken());
				
				edgeList[edgeIdx] = new Edge(from, to, weight); 
			}
			
			// 6. 간선 리스트를 가중치를 기준으로 오름차순 정렬한다.
			Arrays.sort(edgeList);
			
			
			// 7. 가중치가 가장 작은 간선을 차례대로 선택한다.
			// make-set
			parentList = new int[numOfVertex + 1]; // 정점의 번호는 1부터 시작한다
			rankList = new int[numOfVertex + 1];
			make();
			
			mstWeight = 0;
			selectEdgeCount = 0;
			for(Edge edge : edgeList) {
				if(!union(edge.from, edge.to)) continue;
				
				mstWeight += edge.weight; // 9. 총 가중치를 계산한다.
				selectEdgeCount++;
				
				// 9. 정점의 수-1개의 간선을 선택하면 가중치를 출력한다. 
				if(selectEdgeCount == numOfVertex-1) {
					break;
				}
			}
			
			
			sb.append("#").append(tc).append(" ").append(mstWeight);
			System.out.println(sb);
			sb.setLength(0);
		}
	}
	
	
	
	
	// 2. union-find 관련 변수와 메서드를 정의한다.
	public static void make() {
		for(int idx = 0; idx <= numOfVertex; idx++) {
			parentList[idx] = idx;
			rankList[idx] = 0;
		}
	}
	
	public static int find(int vertex) {
		if(parentList[vertex] == vertex) return vertex;
		return parentList[vertex] = find(parentList[vertex]);
	}
	
	public static boolean union(int vertex1, int vertex2) {
		int v1Root = find(vertex1);
		int v2Root = find(vertex2);
		
		if(v1Root == v2Root) return false;

		
		// 둘의 랭크에 따라 다르게 처리
		if(rankList[v1Root] > rankList[v2Root]) {
			parentList[v2Root] = v1Root;
			return true;
		}
		
		parentList[v1Root] = v2Root;
		
		if(rankList[v1Root] == rankList[v2Root]) {
			rankList[v2Root]++;
		}
		
		return true;
	}
}