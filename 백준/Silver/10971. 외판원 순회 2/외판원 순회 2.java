import java.io.*;
import java.util.*;

/**
 * 
 * @author JiYeon Sin
 * 방향 그래프
 * 모든 도시를 한번만  거쳐서 원점으로 돌아와야 한다.
 * 
 * 시작 도시는 아무곳이나 선택해도 무방하다.
 * 왜냐하면 모든 정점을 거치면서 사이클을 만들어야 하기 때문이다.
 * 
 * 1번 정점을 선택한 뒤 모든 경우의 수에 대해서 탐색을 한다.
 * 지금까지의 비용 합이 기존의 최소 비용을 넘어선 경우에는 더 이상의 탐색을 진행하지 않는다.(백트래킹)
 */
public class Main {
	public static BufferedReader br;
	public static StringTokenizer st;
	
	public static int cityCount;
	public static int[][] adjMatrix;
	
	public static boolean[] visited;
	public static int startIdx = 0;
	
	public static int minCost;
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		
		// 도시의 수를 입력받는다.
		cityCount = Integer.parseInt(br.readLine().trim());
		
		// 초기화
		adjMatrix = new int[cityCount][cityCount];
		visited = new boolean[cityCount];
		minCost = Integer.MAX_VALUE;
		
		// 인접 행렬 정보를 입력받는다.
		for(int rowIdx = 0; rowIdx < cityCount; rowIdx++) {
			st = new StringTokenizer(br.readLine().trim());
			for(int colIdx = 0; colIdx < cityCount; colIdx++) {
				adjMatrix[rowIdx][colIdx] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 출발 정점은 0번으로 한다.
		calcMinCost(startIdx, 0, 0);
		
		System.out.println(minCost);
	}
	
	// 약간 DFS하듯이 하면 될듯?
	public static void calcMinCost(int currentIdx, int currentCost, int depth) {
		// 가지치기: 현재 비용 합이 최소 비용을 넘어서면 탐색을 중단한다.
		if(currentCost > minCost) return;
		
		// 기저조건: 모든 정점을 방문하고 출발 정점으로 돌아온 경우
		if(depth == cityCount && currentIdx == startIdx) {
			if(currentCost < minCost) minCost = currentCost; // 최소 비용 갱신
			
			return;
		}
		
		
		
		
		// 인접 행렬을 바탕으로 아직 방문하지 않은 정점으로 방문을 한다.
		for(int idx = 0; idx < cityCount; idx++) {
			if(adjMatrix[currentIdx][idx] != 0 && !visited[idx]) {
				visited[idx] = true;
				calcMinCost(idx, currentCost+adjMatrix[currentIdx][idx], depth+1);
				visited[idx] = false;
			}
		}
	}

}
