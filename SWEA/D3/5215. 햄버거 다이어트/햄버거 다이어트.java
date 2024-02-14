import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * SWEA
 * @author eunwoo.lee
 * 
 * <powerSet>
 * 1. 기저 조건
 * 	모든 원소를 선택했다면
 *  	선택된 원소들의 칼로리 합, 점수 합 구하기
 * 		선택된 원소들의 칼로리 합이 제한 칼로리를 넘지 않는다면
 * 			점수의 총합이 다른 조합의 점수보다 크다면 갱신
 * 	return
 * 2. 해당 원소를 사용한다면
 * 	2-2. 전처리 로직 : 해당 원소 사용 true
 * 	2-3. 재귀 호출
 * 3. 해당 원소를 사용하지 않는다면
 * 	3-2. 전처리 로직 : 해당 원소 사용 false
 * 	3-3. 재귀 호출
 * 
 * 
 * <main>
 * 1. 입력
 * 	1-1. 테스트케이스 수를 입력받는다.
 * 	1-2. 테스트케이스 수만큼 반복한다.
 * 	1-3. 재료의 수, 제한 칼로리를 입력받는다.
 * 	1-4. 재료의 수만큼 맛 점수와 칼로리를 입력받는다.
 * 2. 부분집합 재귀 실행
 * 3. 점수 최대값 출력
 *
 */

public class Solution {
	public static BufferedReader br;
	public static StringBuilder sb;
	public static StringTokenizer st;
	
	public static boolean[] elementUsedList;
	public static int maxScore; // 맛 점수 최대값
	
	public static int testCase; // 테스트케이스의 수
	public static int ingredNum, limitCal; // 재료의 수, 제한 칼로리
	public static int[] score; // 맛 점수 저장 배열
	public static int[] calorie; // 칼로리 저장 배열
	
	public static void powerSet(int selectIdx) {
		
		// 1. 기저 조건
		// 모든 원소를 선택했다면
		if (selectIdx==ingredNum) {
			int selectedCalSum=0;
			int selectedScoreSum=0;
			// 선택된 원소들의 칼로리 합, 점수 합 구하기
			for (int usedIdx=0; usedIdx<ingredNum; usedIdx++) {
				if (elementUsedList[usedIdx]==true) {
					selectedCalSum += calorie[usedIdx];
					selectedScoreSum += score[usedIdx];
				}
			}
			// 선택된 원소들의 칼로리 합이 제한 칼로리를 넘지 않는다면
			if (selectedCalSum<=limitCal) {
				if (selectedScoreSum>maxScore) {
					maxScore = selectedScoreSum;
				}
			}
			return;
		}
		
		// 2. 해당 원소를 사용한다면 
		// 2-2. 전처리 로직 : 해당 원소 사용 true
		elementUsedList[selectIdx] = true;
		// 2-3. 재귀 호출
		powerSet(selectIdx+1);
		
		// 3. 해당 원소를 사용한다면 
		// 3-2. 전처리 로직 : 해당 원소 사용 true
		elementUsedList[selectIdx] = false;
		// 3-3. 재귀 호출
		powerSet(selectIdx+1);
		
		
	}

	public static void main(String[] args) throws Exception{
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		// 1. 입력
		// 1-1. 테스트케이스 수를 입력받는다.
		st = new StringTokenizer(br.readLine().trim());
		testCase = Integer.parseInt(st.nextToken());
			
		// 1-2. 테스트케이스 수만큼 반복한다.
		for (int tc=1; tc<=testCase; tc++) {
			sb.append("#"+tc+" ");
			
			// 1-3. 재료의 수, 제한 칼로리를 입력받는다.
			st = new StringTokenizer(br.readLine().trim());
			ingredNum = Integer.parseInt(st.nextToken());
			limitCal = Integer.parseInt(st.nextToken());
			
			// 1-4. 재료의 수만큼 맛 점수와 칼로리를 입력받는다.
			score = new int[ingredNum];
			calorie = new int[ingredNum];
			for (int ingredIdx=0; ingredIdx<ingredNum; ingredIdx++) {
				st = new StringTokenizer(br.readLine().trim());
				score[ingredIdx] = Integer.parseInt(st.nextToken());
				calorie[ingredIdx] = Integer.parseInt(st.nextToken());
			}		
			
			// 2. 부분집합 재귀 실행
			elementUsedList = new boolean[ingredNum];
			maxScore = 0;
			powerSet(0);
			// 3. 점수 최대값 출력
			sb.append(maxScore+"\n");
		}
		System.out.println(sb);

	}

}
