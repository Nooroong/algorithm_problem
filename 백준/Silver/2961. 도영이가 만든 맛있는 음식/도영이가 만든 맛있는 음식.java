import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * @author JiYeon Sin
 * 조건을 만족하는 부분집합을 구하면 된다.
 * 
 * 1. 재료의 개수를 입력받는다.
 * 2. 재료의 수 만큼 반복을 한다.
 * 3. 신맛과 쓴맛을 입력받아 배열에 저장한다.
 * 4. 
 *
 */
public class Main {
	static BufferedReader br;
	static StringTokenizer st;
	
	static int numOfIngredient; // 재료의 개수
	static int[][] taste; // 재료의 맛 정보를 담는 배열 (신맛, 쓴맛)
	static int minDiff = Integer.MAX_VALUE; // 신맛과 쓴맛의 최소 차를 담는다.
	
	static boolean[] usedIngredient;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		
		numOfIngredient = Integer.parseInt(br.readLine().trim()); // 1. 재료의 개수를 입력받는다.
		taste = new int[numOfIngredient][2];
		usedIngredient = new boolean[numOfIngredient];
		
		// 2. 재료의 수 만큼 반복을 한다.
		for(int idx = 0; idx < numOfIngredient; idx++) {
			// 3. 신맛과 쓴맛을 입력받아 배열에 저장한다.
			st = new StringTokenizer(br.readLine().trim());
			taste[idx][0] = Integer.parseInt(st.nextToken());
			taste[idx][1] = Integer.parseInt(st.nextToken());
		}
		
		
		// 신맛은 곱이라서 초기값으로 1을 넣어줘야 한다.
		powerSet(0, 1, 0);
		
		System.out.println(minDiff);
	}
	
	
	// 현재 원소의 인덱스, 지금까지의 신맛(곱), 지금까지의 쓴맛(합)
	public static void powerSet(int selectIdx, int totalSour, int totalBitter) {
		// 1. 기저조건
		// 집합의 모든 원소들을 살펴봤다면 탐색 종료
		// 지금 구한 부분집합의 맛 차이가 최소값이라면 갱신도 해준다.
		if(selectIdx == numOfIngredient) {
			// 재료를 하나는 무조건 사용해야한다.
			if(totalSour > 1 || totalBitter > 0) {
				int tasteDiff = Math.abs(totalSour - totalBitter);
				minDiff = (tasteDiff < minDiff) ? tasteDiff : minDiff;
			}
			
			return;
		}
		
		// 2-1. 전 처리와 재귀호출(재료를 고른 경우)
		usedIngredient[selectIdx] = true;
		powerSet(selectIdx + 1, totalSour * taste[selectIdx][0], totalBitter + taste[selectIdx][1]);
		
		// 2-2. 전 처리와 재귀호출(재료를 안 고른 경우)
		usedIngredient[selectIdx] = false;
		powerSet(selectIdx + 1, totalSour, totalBitter);
	}
}
