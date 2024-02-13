import java.io.*;
import java.util.*;

/**
 * 
 * @author JiYeon Sin
 * 1. 테스트케이스 개수를 입력받는다.
 * 2. 테스트 케이스만큼 반복한다.
 * 3. 규영이의 카드 정보를 입력받으면서 인영이가 가질 수 있는 카드 정보를 얻어낸다.
 * 4. 9!가지의 카드 순서를 뽑는다.
 * 5. 카드를 다 뽑았으면 규영이와 인영의 점수를 비교한다.
 */

public class Solution {
	public static BufferedReader br;
	public static StringBuilder sb;
	public static StringTokenizer st;
	
	public static int testCase; // 테스트 케이스 개수
	
	public static final int NUM_OF_CARD = 9; // 한 사람이 가지는 카드의 수
	public static int cards[]; // 규영이가 받은 카드에 대한 정보
	
	public static int gyuYeongScore; // 규영이가 얻은 점수
	public static int inYeongScore; // 인영이가 얻은 점수
	public static int winCnt; // 규영이가 이긴 횟수
	public static int lossCnt; // 규영이가 진 횟수
	
	public static List<Integer> elementList; // 고를 수 있는 원소들 배열
	public static boolean[] elementUsedCheckList; // 원소의 선택여부
	public static int selectCnt; // 골라야하는 원소의 수
	public static int[] selectElementList; // 고른 원소
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		selectCnt = NUM_OF_CARD;
		
		
		// 1. 테스트케이스 개수를 입력받는다.
		testCase = Integer.parseInt(br.readLine().trim());
		
		
		// 2. 테스트 케이스만큼 반복한다.
		for(int tc = 1; tc <= testCase; tc++) {
			// 각종 초기화
			gyuYeongScore = 0;
			inYeongScore = 0;
			winCnt = 0;
			lossCnt = 0;
			elementList = new ArrayList<Integer>();
			elementUsedCheckList = new boolean[NUM_OF_CARD];
			selectElementList = new int[NUM_OF_CARD];
			
			for(int idx = 1; idx <= NUM_OF_CARD*2; idx++) {
				elementList.add(idx);
			}
			
			
			// 3. 규영이의 카드 정보를 입력받는다.
			cards = new int[NUM_OF_CARD];
			st = new StringTokenizer(br.readLine().trim());
			for(int idx = 0; idx < NUM_OF_CARD; idx++) {
				cards[idx] = Integer.parseInt(st.nextToken());
				
				// 인영이의 카드 정보를 정리한다.
				elementList.remove(elementList.indexOf(cards[idx]));
			}
			
			// nPn의 순열로 풀 수 있나?
			permutation(0);
			
			sb.append("#").append(tc).append(" ").append(winCnt).append(" ").append(lossCnt);
			System.out.println(sb);
			sb.setLength(0);
		}
	}
	
	
	
	
	public static void permutation(int selectIdx) {
		// 1) 기저조건: r개의 원소를 다 고른경우
		// 5. 카드를 다 뽑았으면 규영이와 인영의 점수를 비교한다.
		if(selectIdx == selectCnt) {
			gyuYeongScore = 0;
			inYeongScore = 0;
			
			for(int idx = 0; idx < selectCnt; idx++) {
				if(cards[idx] > selectElementList[idx]) {
					gyuYeongScore += cards[idx] + selectElementList[idx];
				} else if(cards[idx] < selectElementList[idx]) {
					inYeongScore += cards[idx] + selectElementList[idx];
					
				}
			}

			if (gyuYeongScore > inYeongScore) winCnt++;
			else lossCnt++;
			
			return;
		}
		

		for(int idx = 0; idx < selectCnt; idx++) {
			// 2) 전처리
			if(elementUsedCheckList[idx]) {
				continue;
			}
			
			elementUsedCheckList[idx] = true;
			selectElementList[selectIdx] = elementList.get(idx);
			
			
			// 3) 재귀호출
			permutation(selectIdx+1);
			
			
			// 4) 후처리
			elementUsedCheckList[idx] = false;
		}
	}

}

