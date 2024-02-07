import java.io.*;
import java.util.*;

/**
 * 
 * @author JiYeon Sin
 * 1. 테스트 케이스 개수를 입력받는다.
 * 2. 식재료의 수를 입력받는다.
 * 3. 식재료 정보를 입력받는다.
 * 4. 한 요리에 들어가는 식재료의 수 n/2를 구한다.
 * 5. 각 요리의 맛 점수를 저장할 리스트를 생성한다.
 * 6. 재귀적으로 식재료 조합을 구한다.
 * 	7. 각 조합에 대해 맛 점수를 구한다.
 *     예를 들어 재료를 1, 2, 3, 4를 구했다면
 *     2중 반복문을 통해 배열의 값을 다 더해주면 된다.
 * 8. 맛 점수를 저장한 배열에서 양끝단에서 시작해서 하나씩 범위를 좁혀가며
 *    두 요리의 맛의 차이를 구한다.
 *    (만약 식재료가 6개 있었다면 배열의 첫번째 요소에는 식재료 1, 2, 3에 대한 요리가,
 *    마지막 요소에는 식재료 4, 5, 6에 대한 요리가 들어간다.
 *    중심을 기준으로 대칭되는 요소끼리 합치면 전체집합(?)이 된다.)
 * 9. 최소 맛 차이를 갱신한다.
 * 10. 최소 맛 차이를 출력한다. 
 *
 */
public class Solution {
	public static BufferedReader br;
	public static StringTokenizer st;
	public static StringBuilder sb;
	
	public static int testCase; // 테이스 케이스 개수
	public static int numOfIngre; // 식재료의 수
	public static int[][] ingreInfo; // 식재료 정보
	
	public static int selectCount; // 조합에서 골라야하는 원소의 수
	public static int[] selectElementList; // 조합에서 골라낸 원소들
	public static List<Integer> tasteScore; // 각 요리의 맛 점수를 저장할 리스트
	
	public static int minTasteDiff; // 최소 맛 차이
	
	
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		
		// 1. 테스트 케이스 개수를 입력받는다.
		testCase = Integer.parseInt(br.readLine().trim());
		
		for(int tc = 1; tc <= testCase; tc++) {
			minTasteDiff = Integer.MAX_VALUE;
			
			// 2. 식재료의 수를 입력받는다.
			numOfIngre = Integer.parseInt(br.readLine().trim());
			ingreInfo = new int[numOfIngre][numOfIngre];
			
			// 3. 식재료 정보를 입력받는다.
			for(int rowIdx = 0; rowIdx < numOfIngre; rowIdx++) {
				st = new StringTokenizer(br.readLine().trim());
				for(int colIdx = 0; colIdx < numOfIngre; colIdx++) {
					ingreInfo[rowIdx][colIdx] = Integer.parseInt(st.nextToken());
				}
			}
			
			
			// 4. 한 요리에 들어가는 식재료의 수 n/2를 구한다.
			selectCount = numOfIngre / 2;
		    selectElementList = new int[selectCount];
			
			// 5. 각 요리의 맛 점수를 저장할 리스트를 생성한다.
			tasteScore = new ArrayList<Integer>();
			
				
			combination(0, 0); // 6. 재귀적으로 식재료 조합을 구한다.
			
			
			// 8. 맛 점수를 저장한 배열에서 양끝단에서 시작해서 하나씩 범위를 좁혀가며
			// 두 요리의 맛의 차이를 구한다.
			for(int frontIdx = 0, rearIdx = tasteScore.size()-1; frontIdx < rearIdx; frontIdx++, rearIdx--) {
				int tempTasteDiff = Math.abs(tasteScore.get(frontIdx) - tasteScore.get(rearIdx));
				
				// 9. 최소 맛 차이를 갱신한다.
				minTasteDiff = (tempTasteDiff < minTasteDiff) ? tempTasteDiff : minTasteDiff;
			}
		
			
			// 10. 최소 맛 차이를 출력한다.
			sb.append("#").append(tc).append(" ").append(minTasteDiff);
			System.out.println(sb);
			sb.setLength(0);
		}
	}
	
	
	
	
	
	public static void combination(int selectIdx, int elementIdx) {
		// 기저조건 1: 원소를 다 골랐다면 고른 원소에 대한 맛점수를 구하고 리스트에 추가한다.
		if(selectIdx == selectCount) {
			// 7. 각 조합에 대해 맛 점수를 구한다.
			tasteScore.add(calcTasteScore(selectElementList));
			return;
		}
		// 기저조건 2: 모든 원소를 살폈다면 종료
		if(elementIdx == numOfIngre) {
			return;
		}
		
		// 현재 원소를 선택하는 경우
		selectElementList[selectIdx] = elementIdx;
		combination(selectIdx + 1, elementIdx + 1);
		
		// 현재 원소를 선택하지 않는 경우
		selectElementList[selectIdx] = 0;
		combination(selectIdx, elementIdx + 1);
	}
	
	
	// 고른 음식에 대한 맛점수를 계산하여 반환한다.
	// 파라미터: 고른 음식의 인덱스가 들어가있는 배열
	public static int calcTasteScore(int[] selectArr) {
		int totalTaste = 0;
		
		// 2중 반복문을 통해 배열의 값을 다 더해주면 된다.
		for(int rowIdx : selectArr) {
			for(int colIdx : selectArr) {
				totalTaste += ingreInfo[rowIdx][colIdx];
			}
		}
		
		return totalTaste;
	}
}
