import java.io.*;
import java.util.*;

/**
 * 
 * @author JiYeon Sin
 * 1. 테스트 케이스 갯수를 입력받는다.
 * 2. 재료의 수, 제한 칼로리를 입력받는다.
 * 3. 맛 점수와 칼로리를 입력받아 배열에 저장한다.
 * 4-1. 조합으로 푼다.
 * 4-2. 부분집합으로 푼다.
 *
 */

public class Solution {
	public static BufferedReader br;
	public static StringBuilder sb;
	public static StringTokenizer st;
	
	public static int numOfIngre; // 재료의 수
	public static int calLimit; // 제한 칼로리
	
	public static int[] taste; // 맛 점수를 저장하는 배열
	public static int [] cal; // 칼로리를 저장하는 배열
	
	public static int maxTaste; // 최고 맛점수
	
	public static boolean[] elementUsedCheckList; // 원소의 사용여부를 체크하는 배열
	
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		
		// 1. 테스트 케이스 갯수를 입력받는다.
		int testCase = Integer.parseInt(br.readLine().trim());
		
		
		
		for(int tc = 1; tc <= testCase; tc++) {
			// 2. 재료의 수, 제한 칼로리를 입력받는다.
			st = new StringTokenizer(br.readLine().trim());
			numOfIngre = Integer.parseInt(st.nextToken());
			calLimit = Integer.parseInt(st.nextToken());
			
			// 3. 맛 점수와 칼로리를 입력받아 배열에 저장한다.
			taste = new int[numOfIngre];
			cal = new int[numOfIngre];
			for(int idx = 0; idx < numOfIngre; idx++) {
				st = new StringTokenizer(br.readLine().trim());
				taste[idx] = Integer.parseInt(st.nextToken());
				cal[idx] = Integer.parseInt(st.nextToken());
			}
			
			// 4-1. 조합으로 푼다.
//			maxTaste = 0;
//			combination(0, 0, 0);
//			sb.append("#").append(tc).append(" ").append(maxTaste);
//			System.out.println(sb);
//			sb.setLength(0);
			
			// 4-2. 부분집합으로 푼다.
			elementUsedCheckList = new boolean[numOfIngre];
			maxTaste = 0;
			powerSet(0);
			sb.append("#").append(tc).append(" ").append(maxTaste).append("\n");
		}
		System.out.println(sb);
	}
	
	
	public static void combination(int totalCal, int totalTaste, int elementIdx) {
		// 1-1. 종료조건: 칼로리가 초과된 경우
		if(totalCal > calLimit) {
			return;
		}
		
		// 2. 전처리: 칼로리가 초과되지 않았으면 매번 최대 맛점수를 갱신한다.
		maxTaste = (totalTaste > maxTaste) ? totalTaste : maxTaste;
		
		// 1-2. 종료조건 : 모든 음식을 살펴본 경우
		if(elementIdx == numOfIngre) {
			return;
		}
		
		
		
		// 3. 재귀호출: 현재 음식을 고르는 경우
		combination(totalCal+cal[elementIdx], totalTaste+taste[elementIdx], elementIdx + 1);
		
		// 3. 재귀호출: 현재 음식을 안 고르는 경우
		combination(totalCal, totalTaste, elementIdx + 1);
	}
	
	
	
	public static void powerSet(int selectIdx) {
		// 1. 종료조건 : 모든 음식을 살펴본 경우
		if(selectIdx == numOfIngre) {
			int totalTaste = 0;
			int totalCal = 0;
			for(int idx = 0; idx < numOfIngre; idx++) {
				if(elementUsedCheckList[idx]) {
					totalTaste += taste[idx];
					totalCal += cal[idx];
				}
			}
			
			if(totalCal <= calLimit) {
				maxTaste = (totalTaste > maxTaste) ? totalTaste : maxTaste;
			}
			
			return;
		}
		
		
		
		// 3. 재귀호출: 현재 음식을 고르는 경우
		elementUsedCheckList[selectIdx] = true;
		powerSet(selectIdx+ 1);
		
		// 3. 재귀호출: 현재 음식을 안 고르는 경우
		elementUsedCheckList[selectIdx] = false;
		powerSet(selectIdx + 1);
	}
}
 