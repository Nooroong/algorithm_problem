import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;


/**
 * 
 * @author JiYeon Sin
 * 예제를 보면 수열에 관한 문제임을 알 수 있다.
 * 
 */

public class Main {
	static BufferedReader br;
	static StringTokenizer st;
	
	public static int ELEMENT_COUNT = 0; // 전체 원소의 수
	public static int SELECT_COUNT = 0; // 수열의 길이
	public static int [] elementList; // 전체 원소를 갖고 있는 배열
	public static int[] selectElementList; // 선택한 원소를 담아줄 배열
	public static boolean[] usedElementList; // 수열에 넣은 원소 정보를 저장하는 배열
	
	
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		

		// n과 m을 입력받는다.
		st = new StringTokenizer(br.readLine().trim());
		ELEMENT_COUNT = Integer.parseInt(st.nextToken());
		SELECT_COUNT = Integer.parseInt(st.nextToken());
		
		// 전체 원소를 배열에 저장한다.(1 ~ n)
		elementList = new int[ELEMENT_COUNT];
		for(int idx = 0; idx < elementList.length; idx++) {
			elementList[idx] = idx + 1;
		}
		
		selectElementList = new int[SELECT_COUNT];
		usedElementList = new boolean[ELEMENT_COUNT];
		
		permutation(0);
	}
	
	
	
	
	// 파라미터: 수열에서 몇번째 숫자를 고르는지, 몇번째 원소를 수열에 넣을 것인지
	public static void permutation(int selectIdx) {
		// 1. 종료 조건
		// 숫자를 다 뽑았으면 출력해주고 종료
		if(selectIdx == SELECT_COUNT) {
			for(int idx = 0; idx < selectElementList.length; idx++) {
				System.out.print(selectElementList[idx] + " ");
			}
			System.out.println();
			return;
		}
		
		// 2. 전 처리 로직
		// 아직 다 선택하지 않았다면,
		for(int elementIdx = 0; elementIdx < ELEMENT_COUNT; elementIdx++) {
			// 이미 선택한 원소라면 pass
			if(usedElementList[elementIdx]) {
				continue;
			}
			
			// 해당 원소를 아직 사용하지 않았다면 -> 선택을 한다.
			usedElementList[elementIdx] = true;
			selectElementList[selectIdx] = elementList[elementIdx];
			
			// 3. 재귀 호출
			permutation(selectIdx + 1);
			
			// 4. 후 처리 로직
			// 현재 위치에서 다음번 숫자를 뽑기 위해
			usedElementList[elementIdx] = false;
		}
	}
}