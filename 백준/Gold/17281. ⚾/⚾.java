import java.io.*;
import java.util.*;

/**
 * 
 * @author JiYeon Sin
 * 순열을 통해 타자들의 순서를 정하고 공격을 수행하여 점수를 계산해낸다.
 * 
 * 1. 총 이닝 수를 입력받는다.
 * 2. 각 이닝에서 얻는 결과를 입력받는다.
 * 3. 1선수를 4번 타자로 고정시킨다.
 * 4. 최대 점수를 구한다.
 * 	5. 순열로 타자들의 순서를 고른다.
 *  6. 모든 선수들을 골랐다면 해당 순서를 바탕으로 점수를 계산한다.
 *   6-1. 총 이닝 수 만큼 반복하여 각 이닝에서의 점수를 계산한다.
 *   6-2. 일단 무한반복을 돌린다.
 *   6-3. 현재 타자 번호와 타자의 결과를 구한다.
 *   6-4. 타자를 타석에 올린다.
 *   6-5. 아웃인 경우를 계산하고 3아웃인 경우 타자를 다음 번으로 넘기고 이닝도 다음 이닝으로 넘어간다.
 *   6-6. 현재 타자의 결과에 따라 shift 연산을 통해 진출을 한다.
 *   6-7. 다음번 타자를 구한다.
 *   6-8. 이닝이 끝나면 구한 점수를 반환한다.
 *  7. 최대 점수를 갱신한다.
 *  8. 타자 순서를 고를 때 4번째 선수는 고정이므로 패스한다.
 *  9. 아니라면 선수들을 고른다.
 * 10. 구한 최대 점수를 출력한다.
 */

public class Main {
	public static BufferedReader br;
	public static StringTokenizer st;
	
	public static final int NUMBER_OF_HITTER = 9; // 타자들의 수
	
	public static int[] hitterOrder; // 타자들의 순서를 담는 배열
	public static int[] elementList; // 원소 == 타자 (1번부터 시작)
	public static boolean[] elementUsedList; // 뽑은 타자들에 대한 정보 배열
	
	
	public static int inningCount; // 이닝 수
	public static int[][] inningResult; // 각 이닝의 결과
	
	
	public static int maxScore; // 최대 점수
	
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		
		// 1. 총 이닝 수를 입력받는다.
		inningCount = Integer.parseInt(br.readLine().trim());
		
		
		// 각종 할당 및 초기화
		inningResult = new int[inningCount][NUMBER_OF_HITTER];
		maxScore = Integer.MIN_VALUE;
		hitterOrder = new int[NUMBER_OF_HITTER];
		elementUsedList = new boolean[NUMBER_OF_HITTER];
		elementList = new int[NUMBER_OF_HITTER];
		for(int idx = 0; idx < NUMBER_OF_HITTER; idx++) {
			elementList[idx] = idx+1;
			
		}
		
		
		// 2. 각 이닝에서 얻는 결과를 입력받는다.
		for(int rowIdx = 0; rowIdx < inningCount; rowIdx++) {
			st = new StringTokenizer(br.readLine().trim());
			
			for(int colIdx = 0; colIdx < NUMBER_OF_HITTER; colIdx++) {
				inningResult[rowIdx][colIdx] = Integer.parseInt(st.nextToken());
			}
		}
		
		
		// 3. 1선수를 4번 타자로 고정시킨다.
		elementUsedList[0] = true;
		hitterOrder[3] = 1;

		
		getMaxScore(); // 4. 최대 점수를 구한다.
		

		System.out.println(maxScore); // 10. 구한 최대 점수를 출력한다.
	}
	
	
	
	public static void getMaxScore() throws IOException {
		getHitterOrder(0); // 5. 순열로 타자들의 순서를 고른다.
	}
	
	
	// 5. 순열로 타자들의 순서를 고른다.
	public static void getHitterOrder(int selectIdx) throws IOException {
		// 6. 모든 선수들을 골랐다면 해당 순서를 바탕으로 점수를 계산한다.
		if(selectIdx == NUMBER_OF_HITTER) {
			int score = getScore();
			maxScore = (score > maxScore) ? score : maxScore; // 7. 최대 점수를 갱신한다.
			
			return;
		}
		
		
		// 8. 타자 순서를 고를 때 4번째 선수는 고정이므로 패스한다.
		if(selectIdx == 3) {
			getHitterOrder(selectIdx+1);
		} else {
			// 9. 아니라면 선수들을 고른다.
			for(int idx = 0; idx < NUMBER_OF_HITTER; idx++) {
				if(elementUsedList[idx]) {
					continue;
				}
				
				elementUsedList[idx] = true;
				hitterOrder[selectIdx] = elementList[idx]; // 선수 번호는 1번부터 시작
				getHitterOrder(selectIdx+1);
				elementUsedList[idx] = false;
			}
		}
	}
	
	
	
	// 위에서 구한 선수들의 순서를 바탕으로 점수를 계산한다.
	public static int getScore() throws IOException {
		int score = 0; // 현재 선수들의 순서로 얻을 수 있는 점수
		int hitterIndex = 0; // 몇번쨰 타자인지 순서를 저장
		
		
		
		// 6-1. 총 이닝 수 만큼 반복하여 각 이닝에서의 점수를 계산한다.
		for(int inning = 0; inning < inningCount; inning++) {
			int base = 0; // 타자, 1루, 2루, 3루에 위치한 선수들의 정보를 비트단위로 담는다.(0번쨰 비트가 타자, 4번쨰 비트가 홈에 돌아오는 것을 의미한다.)
			int outCount = 0;
			
			// 6-2. 일단 무한반복을 돌린다.
			while(true) {
				// 6-3. 현재 타자 번호와 타자의 결과를 구한다.
				int hitterNumber = hitterOrder[hitterIndex];
				int result = inningResult[inning][hitterNumber - 1]; // 타자 순서는 1부터 시작함에 주의
				
				base |= 1; // 6-4. 타자를 타석에 올린다.
				
				// 6-5. 아웃인 경우를 계산하고 3아웃인 경우 타자를 다음 번으로 넘기고 이닝도 다음 이닝으로 넘어간다.
				if(result == 0) outCount++;
				if(outCount == 3) {
					outCount = 0; // 아웃 카운트 초기화
					hitterIndex = (hitterIndex+1) % 9; // 타자 넘기기
					break; // 다음 이닝으로
				}
				
				
				// 6-6. 현재 타자의 결과에 따라 shift 연산을 통해 진출을 한다.
				// 아웃(0)인 경우 result가 0이므로 진출이 없다.
				for(int run = 0; run < result; run++) {
					base <<= 1; // 1루씩 진출

					// 홈에 주자가 들어온 경우
					if( (base & (1 << 4)) != 0) {
						score++; // 1점 증가
					}
				}
				
				hitterIndex = (hitterIndex+1) % 9; // 6-7. 다음번 타자를 구한다.
			}
		}
		
		return score; // 6-8. 이닝이 끝나면 구한 점수를 반환한다.
	}
	
}
