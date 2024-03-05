import java.io.*;
import java.util.*;

/**
 * 
 * @author JiYeon Sin
 * 
 * 1. 테스트 케이스 수를 입력받고 반복.
 * 2. 퍼즐의 크기와 단어의 길이를 입력받는다.
 * 3. 퍼즐 배열을 생성하고 정보를 입력받는다.
 * 4. 각 행, 열에 대해 탐색을 한다.
 * 	4-1. 연속된 빈칸의 수를 담는 배열을 선언한다.
 *  4-2. 각 라인을 돌면서 빈칸을 만나면 빈칸 수를 +1한다.
 *  4-3. 막힌 칸을 만나면 지금까지 센 빈칸의 수와 단어 길이를 비교한다. 둘이 같으면 출력값을 1 증가시킨다.
 *  4-4. 한 줄에 대한 탐색이 끝났을 때도 빈칸의 수와 단어 길이를 비교한다.
 * 5. 단어가 들어갈 수 있는 자리의 수를 출력한다.
 */
public class Solution {
	public static BufferedReader br;
	public static StringTokenizer st;
	public static StringBuilder sb;
	
	public static int testCase; // 테스트 케이스의 개수
	
	public static int puzzleSize; // 단어 퍼즐의 크기
	public static int[][] puzzle; // 단어 퍼즐
	
	public static int wordLen; // 단어의 길이
	
	public static int count; // 단어가 들어갈 수 있는 자리의 수
	
	public static final int BLANK = 1;
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		// 1. 테스트 케이스 수를 입력받고 반복.
		testCase = Integer.parseInt(br.readLine().trim());
		
		for(int tc = 1; tc <= testCase; tc++) {
			count = 0;
			
			// 2. 퍼즐의 크기를 입력받는다.
			st = new StringTokenizer(br.readLine().trim());
			puzzleSize = Integer.parseInt(st.nextToken());
			wordLen = Integer.parseInt(st.nextToken());
			
			// 3. 퍼즐 배열을 생성하고 정보를 입력받는다.
			puzzle = new int[puzzleSize][puzzleSize];
			for(int rowIdx = 0; rowIdx < puzzleSize; rowIdx++) {
				st = new StringTokenizer(br.readLine().trim());
				for(int colIdx = 0; colIdx < puzzleSize; colIdx++) 
					puzzle[rowIdx][colIdx] = Integer.parseInt(st.nextToken());
			}
			
			
			// 4. 각 행, 열에 대해 탐색을 한다.
			rowSearch();
			colSearch();
			
			// 5. 단어가 들어갈 수 있는 자리의 수를 출력한다.
			sb.append("#").append(tc).append(" ").append(count);
			System.out.println(sb);
			sb.setLength(0);
		}
	}
	
	
	public static void rowSearch() {
		int blankCount; // 4-1. 연속된 빈칸의 수를 담는 배열을 선언한다.
		
		for(int rowIdx = 0; rowIdx < puzzleSize; rowIdx++) {
			blankCount = 0;
			
			for(int colIdx = 0; colIdx < puzzleSize; colIdx++) {
				
				if(puzzle[rowIdx][colIdx] == BLANK) {
					// 4-2. 각 라인을 돌면서 빈칸을 만나면 빈칸 수를 +1한다.
					blankCount++;
				} else {
					// 4-3. 막힌 칸을 만나면 지금까지 센 빈칸의 수와 단어 길이를 비교한다. 둘이 같으면 출력값을 1 증가시킨다.
					if(blankCount == wordLen)
						count++;
					
					blankCount = 0; // 초기화 주의
				}
			}
			
			
			// 4-4. 한 줄에 대한 탐색이 끝났을 때도 빈칸의 수와 단어 길이를 비교한다.
			if(blankCount == wordLen)
				count++;
		}
	}
	
	
	// 위 함수와 동일
	public static void colSearch() {
		int blankCount;
		
		for(int colIdx = 0; colIdx < puzzleSize; colIdx++) {
			blankCount = 0;
			
			for(int rowIdx = 0; rowIdx < puzzleSize; rowIdx++) {
				if(puzzle[rowIdx][colIdx] == BLANK) {
					blankCount++;
				} else {
					if(blankCount == wordLen)
						count++;
						
					blankCount = 0;
				}
			}
			
			if(blankCount == wordLen)
				count++;
		}
	}
}
