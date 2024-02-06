import java.io.*;
import java.util.*;

/**
 * 
 * @author JiYeon Sin
 * n개의 과자봉지 중에서 2개를 골라야하므로 조합으로 해결할 수 있다.
 * 
 * 1. 테스트 케이스 개수를 입력받는다.
 * 2. 테스트 케이스 만큼 반복한다.
 * 3. 과자 봉지의 개수와 무게 합 제한을 입력받는다.
 * 4. 과자 봉지의 무게를 입력받아 배열에 저장한다.
 * 5. 조합으로 해결한다.
 *    2개의 과자를 고를 때마다 무게합을 초과하는지 확인하고,
 *    넘지 않는다면 최대 무게 합을 갱신한다.
 * 6. 최대 무게를 출력한다.
 * 
 *
 */
public class Solution {
	public static StringBuilder sb;
	public static StringTokenizer st;
	public static BufferedReader br;
	
	public static final int  SELECT_COUNT = 2; // 골라야하는 원소의 수
	public static int[] selectElementList; // 고른 원소들이 담기는 배열
	
	public static int testCase; // 테스트 케이스의 수
	public static int numOfSnack; // 과자 봉지의 개수
	public static int weightLimit; // 무게 합 제한
	public static int[] snacks; // 과자 봉지 무게를 저장하는 배열
	public static int maxWeight; // 제한 무게를 초과하지 않는 최대 무게
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		// 1. 테스트 케이스 개수를 입력받는다.
		testCase = Integer.parseInt(br.readLine().trim());
		
		
		// 2. 테스트 케이스 만큼 반복한다.
		for(int tc = 1; tc <= testCase; tc++) {
			
			// 3. 과자 봉지의 개수와 무게 합 제한을 입력받는다.
			st = new StringTokenizer(br.readLine().trim());
			numOfSnack = Integer.parseInt(st.nextToken());
			weightLimit = Integer.parseInt(st.nextToken());
			
			
			// 4. 과자 봉지의 무게를 입력받아 배열에 저장한다.
			snacks = new int[numOfSnack];
			st = new StringTokenizer(br.readLine().trim());
			for(int idx = 0; st.hasMoreTokens(); idx++) {
				snacks[idx] = Integer.parseInt(st.nextToken());
			}
			
			
			// 5. 조합으로 해결한다.
			maxWeight = -1; // 과자를 들고 갈 방법이 없는 경우에는 -1
			selectElementList = new int[SELECT_COUNT];
			combination(0, 0);
			
			
			
			
			// 6. 최대 무게를 출력한다.
			sb.append("#").append(tc).append(" ").append(maxWeight);
			System.out.println(sb);
			sb.setLength(0);
		}
	}
	
	
	public static void combination(int selectIdx, int elementIdx) {
		// 1. 기저조건
		// 1-1. 원소를 다 뽑은 경우
		if(selectIdx == SELECT_COUNT) {
			// 무게합을 초과하는지 확인하고,
			// 넘지 않는다면 최대 무게 합을 갱신한다.
			int totalWeight = returnTotalWeight();
			if(totalWeight <= weightLimit) {
				maxWeight = (totalWeight > maxWeight) ? totalWeight : maxWeight;
			}
			
			return;
		}
		
		// 1-2. 모든 원소들을 다 살펴본 경우
		if(elementIdx == numOfSnack) {
			return;
		}
		
		// 2-1. 전처리 및 재귀호출: 현재 원소를 고른 경우
		selectElementList[selectIdx] = snacks[elementIdx];
		combination(selectIdx + 1, elementIdx + 1);
		
		// 2-1. 전처리 및 재귀호출: 현재 원소를 고르지 않은 경우
		selectElementList[selectIdx] = 0;
		combination(selectIdx, elementIdx + 1);
	}
	
	
	public static int returnTotalWeight() {
		int totalWeight = 0;
		for(int idx = 0; idx < SELECT_COUNT; idx++) {
			totalWeight += selectElementList[idx];
		}
		return totalWeight;
	}

}