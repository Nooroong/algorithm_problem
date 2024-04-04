import java.io.*;
import java.util.*;

/**
 * 
 * @author JiYeon Sin
 * 부분집합 느낌으로 풀어보기
 * 
 * 1. 입력받기
 * 2. 부분집합으로 선반 높이 이상인 탑의 최소 높이를 구한다.
 * 	2-1. 기저조건: 모든 직원들을 다 살펴본 경우
 * 		2-1-1. 탑의 높이가 선반 높이를 넘는 경우 최소값을 갱신한다.
 * 	2-2. 현재 직원이 탑을 만드는 경우
 * 	2-3. 현재 직원이 탑을 만들지 않는 경우
 * 3. '최소높이 - 선반 높이'를 출력한다.
 */
public class Solution {
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	
	static int testCase;
	
	static int employeeCount, shelfsHeight; // 직원 수, 선반 높이
	static int[] employeeHeight; // 직원들의 키
	static int minTopHeight; // 조건을 만족하는 탑 중 최소 높이
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		testCase = Integer.parseInt(br.readLine().trim());
		
		for(int tc = 1; tc <= testCase; tc++) {
			st = new StringTokenizer(br.readLine().trim());
			
			// 1. 입력받기
			// 직원수, 선반 높이
			employeeCount = Integer.parseInt(st.nextToken());
			shelfsHeight = Integer.parseInt(st.nextToken());
			
			// 직원들의 키
			employeeHeight = new int[employeeCount];
			st = new StringTokenizer(br.readLine().trim());
			for(int idx = 0; idx < employeeCount; idx++) {
				employeeHeight[idx] = Integer.parseInt(st.nextToken());
			}
			
			// 2. 부분집합으로 선반 높이 이상인 탑의 최소 높이를 구한다.
			minTopHeight = Integer.MAX_VALUE;
			subSet(0, 0);
			
			
			// 3. '최소높이 - 선반 높이'를 출력한다.
			sb.append("#").append(tc).append(" ").append(minTopHeight - shelfsHeight);
			System.out.println(sb);
			sb.setLength(0);
		}
	}
	
	
	static void subSet(int eIdx, int sumHeight) {
		// 2-1. 기저조건: 모든 직원들을 다 살펴본 경우
		if(eIdx == employeeCount) {
			// 2-1-1. 탑의 높이가 선반 높이를 넘는 경우 최소값을 갱신한다.
			if(sumHeight >= shelfsHeight) {
				minTopHeight = Integer.min(sumHeight, minTopHeight);
			}
			
			return;
		}
		
		// 2-2. 현재 직원이 탑을 만드는 경우
		subSet(eIdx+1, sumHeight+employeeHeight[eIdx]);
		
		// 2-3. 현재 직원이 탑을 만들지 않는 경우
		subSet(eIdx+1, sumHeight);
	}	
}
