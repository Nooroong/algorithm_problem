import java.io.*;
import java.util.*;

/**
 * 
 * @author JiYeon Sin
 * 
 * 
 * 순열을 통해 타자들의 순서를 정하고 공격을 수행하여 점수를 계산해낸다.
 *
 */

public class Main {
	public static BufferedReader br;
	public static StringTokenizer st;
	
	public static final int NUMBER_OF_HITTER = 9; // 타자들의 수
	
	public static int[] hitterOrder; // 타자들의 순서를 담는 배열
	
	public static int[] elementList;
	public static boolean[] elementUsedList;
	
	
	public static int inningCount; // 이닝 수
	public static int[][] inningResult; // 각 이닝의 결과
	
//	public static int loopCount = 1;
	
	public static int maxScore;
	public static int score;
	public static int outCount;
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		
		// 총 이닝 수를 입력받는다.
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
		
		
		// 각 이닝에서 얻는 결과를 입력받는다.
		for(int rowIdx = 0; rowIdx < inningCount; rowIdx++) {
			st = new StringTokenizer(br.readLine().trim());
			
			for(int colIdx = 0; colIdx < NUMBER_OF_HITTER; colIdx++) {
				inningResult[rowIdx][colIdx] = Integer.parseInt(st.nextToken());
			}
		}
		
		
		// 1선수는 4번타자 고정
		elementUsedList[0] = true;
		hitterOrder[3] = 1;
		
//		System.out.println(Arrays.toString(elementUsedList));
//		System.out.println(Arrays.toString(hitterOrder));
		
		getMaxScore(); // 최대 점수를 구한다.
		

		System.out.println(maxScore);
	}
	
	
	public static void getMaxScore() throws IOException {
		getHitterOrder(0);
	}
	
	
	// 순열로 타자들의 순서를 고른다.
	public static void getHitterOrder(int selectIdx) throws IOException {
		// 모든 선수들을 골랐다면 해당 순서를 바탕으로 점수를 계산한다.
		if(selectIdx == NUMBER_OF_HITTER) {
			int score = getScore();
			maxScore = (score > maxScore) ? score : maxScore; // 최대 점수를 갱신한다.
			
			return;
		}
		
		
		// 4번째 선수는 고정이므로 패스
		if(selectIdx == 3) {
			getHitterOrder(selectIdx+1);
		} else {
			// 아니라면 선수들을 고른다.
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
	
	
	
	
	public static int getScore() throws IOException {
		int score = 0; // 현재 선수들의 순서로 얻을 수 있는 점수
//		int outCount = 0;
		int hitterIndex = 0; // 몇번쨰 타자인지 순서를 저장
		
		
		
		// 총 이닝 수 만큼 반복하여 각 이닝에서의 점수를 계산한다.
		for(int inning = 0; inning < inningCount; inning++) {
			int base = 0; // 타자, 1루, 2루, 3루에 위치한 선수들의 정보를 담는다.
			int outCount = 0;
			
			// 일단 한무반복
			while(true) {
				int hitterNumber = hitterOrder[hitterIndex];
				int result = inningResult[inning][hitterNumber - 1]; // 타자 순서는 1부터 시작함에 주의
				
				base |= 1; // 타자가 타석에 들어온다.
				
				
				
				if(result == 0) outCount++;
				
				
				// 3아웃이 발생하면 이닝이 끝난다 -> break
				if(outCount == 3) {
					outCount = 0;
					hitterIndex = (hitterIndex+1) % 9;
					break;
				}
				
				
				// 현재 타자의 결과에 따라 진출을 한다.
				// 아웃(0)인 경우 result가 0이므로 진출이 없다.
				for(int run = 0; run < result; run++) {
					base <<= 1; // 1루씩 진출

					// 홈에 주자가 들어온 경우
					if( (base & (1 << 4)) != 0) {
						score++; // 1점 증가
					}
				}
				
				hitterIndex = (hitterIndex+1) % 9; // 다음번 타자
			}
		}
		
		return score;
	}
	
	
}
