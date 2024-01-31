import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.util.StringTokenizer;

/**
 * 
 * @author JiYeon Sin
 * 예제를 보면 조합 문제라는 것을 알 수 있다.
 * 
 * 1. n과 m을 입력받는다.(elementCount, selectCount)
 * 2. 재귀함수로 해결한다.
 *
 */
public class Main {
	static public BufferedReader br;
	static public StringBuilder sb;
	static public StringTokenizer st;
	
	// 조합과 관련된 정보들
	static public int elementCount; // nCr에서 n
	static public int selectCount; // nCr에서 r
	static public int[] selectElementList; // 선택한 수열

	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		
		// n과 m을 입력받는다.(elementCount, selectCount)
		st = new StringTokenizer(br.readLine().trim());
		elementCount = Integer.parseInt(st.nextToken());
		selectCount = Integer.parseInt(st.nextToken());
		
		selectElementList = new int[selectCount];
		
		combination(0, 1);
		
	}
	
	public static void combination(int selectIdx, int elementIdx) {
		// 1. 기저 조건
		// 1-1. 다 골랐으면 출력하고 종료
		if(selectIdx == selectCount) {
			for(int idx = 0; idx < selectCount; idx++) {
				System.out.print(selectElementList[idx] + " ");
			}
			System.out.println();
			return;
		}
		// 1-2. 모든 원소를 다 탐색했다면 돌아가기
		// (1부터 n까지 다 봤는데도 더 보려고 하는 경우)
		if(elementIdx > elementCount) {
			return;
		}
		
		
		// 2. 전 처리: 지금 숫자를 고른 경우
		selectElementList[selectIdx] = elementIdx;
		
		// 3. 재귀 호출: 다음 원소 고르기
		combination(selectIdx + 1, elementIdx + 1);
		
		
		// 2. 전 처리: 지금 숫자를 안 고른 경우
		selectElementList[selectIdx] = 0;
		
		// 3. 재귀 호출: 다음 원소 고르기
		combination(selectIdx, elementIdx + 1);
	}
}