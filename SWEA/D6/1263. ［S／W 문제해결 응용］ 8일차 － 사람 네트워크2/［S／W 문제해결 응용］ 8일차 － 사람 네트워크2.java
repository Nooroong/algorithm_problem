import java.util.*;
import java.io.*;

/**
 * 
 * @author JiYeon Sin
 * 모든 쌍 최단 경로 -> 플로이드 워샬
 * 
 * 1. 사람의 수를 입력받는다.
 * 2. 인접행렬을 입력받으면서 dp table을 초기화 해준다.
 * 	2-1. 자기자신을 향하는 경우가 아니면서 두 노드가 연결되지 않은 경우 비용은 최대값으로 초기화한다.
 * 	2-2. 두 노드가 연결된 경우에는 입력받는 가중치가 임시 최소 비용(최적해)가 된다.
 * 3. 플로이드 워샬로  모든 쌍의 최단 비용을 구한다.
 * 4. CC(i)를 구하면서 최소값을 찾아 변수에 저장한다.
 * 5. 형식에 맞게 최소 CC 출력한다.
 */
public class Solution {
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	
	static int testCase;
	
	static int humanCount; // 사람의 수
	static int[][] cost; // dp table. 최소 비용을 저장한다.
	
	static int minCC; // 최소 CC
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		testCase = Integer.parseInt(br.readLine().trim());
		for(int tc = 1; tc <= testCase; tc++) {
			minCC = Integer.MAX_VALUE;
			
			st = new StringTokenizer(br.readLine().trim());
			
			// 1. 사람의 수를 입력받는다.
			humanCount = Integer.parseInt(st.nextToken());
			
			
			// 2. 인접행렬을 입력받으면서 dp table을 초기화 해준다.
			cost = new int[humanCount][humanCount];
			int rowIdx, colIdx, adjMatrix;
			
			for(int idx = 0; idx < humanCount*humanCount; idx++) {
				rowIdx = idx / humanCount;
				colIdx = idx % humanCount;
				adjMatrix = Integer.parseInt(st.nextToken());
				

				if(rowIdx != colIdx && adjMatrix == 0)
					// 2-1. 자기자신을 향하는 경우가 아니면서 두 노드가 연결되지 않은 경우 비용은 최대값으로 초기화한다.
					cost[rowIdx][colIdx] = humanCount+1;
				else {
					// 2-2. 두 노드가 연결된 경우에는 입력받는 가중치가 임시 최소 비용(최적해)가 된다.
					cost[rowIdx][colIdx] = adjMatrix;
				}
			}
				
		
			
			// 3. 플로이드 워샬로  모든 쌍의 최단 비용을 구한다.
			for (int by = 0; by < humanCount; by++) { // 경유 노드
				for (int from = 0; from < humanCount; from++) {
					for (int to = 0; to < humanCount; to++) {
						// 기존 임시 최적 비용 vs 경유지를 고려한 비용
						cost[from][to] = Integer.min(cost[from][to], cost[from][by] + cost[by][to]);
					}
				}
				
			}
			
			
			
			// CC(i) = cost 배열의 i번째 행의 합
			// 4. CC(i)를 구하면서 최소값을 찾아 변수에 저장한다.
			for (rowIdx = 0; rowIdx < humanCount; rowIdx++) {
				int rowSum = 0;
				
				for (colIdx = 0; colIdx < humanCount; colIdx++)
					rowSum += cost[rowIdx][colIdx];
				
				if(rowSum < minCC) minCC = rowSum; // 최소 CC 갱신
			}
			
			
			// 5. 형식에 맞게 최소 CC 출력한다.
			sb.append("#").append(tc).append(" ").append(minCC);
			System.out.println(sb);
			sb.setLength(0);
		}
	}
}
