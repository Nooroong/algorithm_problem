import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * BOJ
 * @author eunwoo.lee
 * 
 * 1. 입력
 * 	1-1. 사람의 수와 친구 관계의 수를 입력받는다.
 * 	1-2. 친구관계를 입력받는다.
 * 		  간선정보 -> 인접리스트로
 * 2. 각 정점에 대해 dfs 시작
 * 	3. depth가 4가 되었다면, 정답 1로 설정 후 return
 * 	3. 연결된 정점 중 아직 방문하지 않았다면 탐색.
 * 3. 정답 출력
 *
 */

public class Main {
	
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;
	
	static int nodeNum, edgeNum;
	static ArrayList<Integer>[] graph;
	static boolean[] isVisited;
	static int friendship;
	
	public static void dfs(int depth, int startNode) {
		
		// 1. 기저조건
		if (depth==4) {
			friendship = 1;
			return;
		}
		
		// 현재 노드 방문처리
		isVisited[startNode] = true;
		
		for (int adjIdx=0; adjIdx<graph[startNode].size(); adjIdx++) {
			if (!isVisited[graph[startNode].get(adjIdx)]) {
				dfs(depth+1, graph[startNode].get(adjIdx));
			}
		}
		isVisited[startNode] = false;
	}

	public static void main(String[] args) throws Exception{
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		// 1. 입력
		// 1-1. 사람의 수와 친구 관계의 수를 입력받는다.
		st = new StringTokenizer(br.readLine().trim());
		nodeNum = Integer.parseInt(st.nextToken());
		edgeNum = Integer.parseInt(st.nextToken());

		// 1-2. 친구관계의 정보를 입력받는다.
		graph = new ArrayList[nodeNum];
		for (int nodeIdx=0; nodeIdx<nodeNum; nodeIdx++) {
			graph[nodeIdx] = new ArrayList<>();
		}
		for (int edgeIdx=0; edgeIdx<edgeNum; edgeIdx++) {
			st = new StringTokenizer(br.readLine().trim());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			graph[start].add(end);
			graph[end].add(start);
		}
		
		// 2. 각 정점에 대해 dfs 시작
		for (int nodeIdx=0; nodeIdx<nodeNum; nodeIdx++) {
			isVisited = new boolean[nodeNum];
			dfs(0, nodeIdx);
			if (friendship==1)
				break;
		}
		
		System.out.println(friendship);
	}

}