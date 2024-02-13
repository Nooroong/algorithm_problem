import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/**
 * SWEA
 * @author eunwoo.lee
 * 
 * 1. 입력
 * 	1-1. 테스트 케이스의 수를 입력받는다.
 * 	1-2. 테스트 케이스의 수만큼 반복한다.
 * 	1-3. 규영이의 카드 set을 입력받는다.
 * 	1-4. 인영이의 카드 set을 만든다.(오름차순)
 * 2. 인영이의 카드 set으로 9개 중 9개 선택하는 순열 만들기
 * 	3. 한 순열이 만들어지면 그 순열로 game 진행
 * 	4. game 진행 후 결과 비교해서 승리, 패배 수 count
 * 
 * 
 *
 */

public class Solution {
	
	public static final int SELECT_COUNT=9; // 선택할 원소 개수
	
	public static int[] selectElementList; // 선택한 원소를 담아놓을 배열
	public static boolean[] elementUsedCheckList; // 원소 사용 여부 체크 배열
	
	
	public static BufferedReader br;
	public static StringBuilder sb;
	public static StringTokenizer st;
	
	public static List<Integer> kCards; // 규영이의 카드 set 저장할 배열
	public static int[] iCards; // 인영이의 카드 set 저장할 배열
	
	public static int kScore, iScore; // 규영인영의 점수
	public static int kWin, kLoose; // 규영 이기고 진 횟수 
	
	
	
	public static void game(int[] iCardPermutation) {
		
		// 초기화
		kScore = 0;
		iScore = 0;
		
		// 게임 9번 실행
		for (int cardIdx=0; cardIdx<9; cardIdx++) {
			int kCard = kCards.get(cardIdx);
			int iCard = iCardPermutation[cardIdx];
			
			if (kCard>iCard) {
				kScore += kCard+iCard;}
			else {
				iScore += kCard+iCard;}
		}
		
		// 4. game 진행 후 결과 비교해서 승리, 패배 수 count
		if (kScore>iScore) {
			
			kWin++;
		}else if (kScore<iScore) {
			kLoose++;
		}			
		
	}
	
	public static void permutation(int selectedIdx) {
		// 인영이의 카드 순열을 만드는 메소드
		// 기존 코드의 elementList-> iCards
		
		// 1. 기저 조건  (종료 조건)
		// 선택하고자 하는 원소의 개수를 다 선택했다면
		if (selectedIdx==9) {
			// 3. 한 순열이 만들어지면 그 순열로 game 진행
			game(selectElementList);
			return;
		}
		
		
		// 아직 다 선택하지 않았다면
		// 2. 전처리 로직
		for (int elementIdx=0; elementIdx<9; elementIdx++) {
			// 이미 선택한 원소라면 pass
			if (elementUsedCheckList[elementIdx])
				continue;
			
			// 해당 원소를 아직 사용하지 않았다면 사용!
			elementUsedCheckList[elementIdx] = true;
			selectElementList[selectedIdx] = iCards[elementIdx];
			
			// 3. 재귀호출
			permutation(selectedIdx+1);
			
			// 4. 후처리 로직
			elementUsedCheckList[elementIdx] = false; // 사용했으면 되돌려 주기
		}
		
		
		
	}
	

	public static void main(String[] args) throws Exception{
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		// 1. 입력
		// 1-1. 테스트 케이스의 수를 입력받는다.
		st = new StringTokenizer(br.readLine().trim());
		int testCase = Integer.parseInt(st.nextToken());
		//1-2. 테스트 케이스의 수만큼 반복한다.
		for (int tc=1; tc<=testCase; tc++) {
			
			sb.append("#").append(tc).append(" ");
			
			// 초기화
			selectElementList = new int[9];
			elementUsedCheckList = new boolean[9];

			
			kWin=0;
			kLoose=0;			
			
			// 1-3. 규영이의 카드 set을 입력받는다.
			kCards = new ArrayList<>();
			st = new StringTokenizer(br.readLine().trim());
			for (int kCardIdx=0; kCardIdx<9; kCardIdx++) {
				kCards.add(Integer.parseInt(st.nextToken()));
			}
			// 1-4. 인영이의 카드 set을 만든다.(오름차순)
			iCards = new int[9];
			int icardIdx = 0;
			for (int cardNum=1; cardNum<=18; cardNum++) {
				if (!kCards.contains(cardNum)) {
					iCards[icardIdx++] = cardNum;
				}
			}
			
			// 2. 인영이의 카드 set으로 9개 중 9개 선택하는 순열 만들고
			permutation(0);
			
			// 5. 출력
			sb.append(kWin+" "+kLoose+"\n");	
		}
		System.out.println(sb);

	}

}
