import java.io.*;
import java.util.*;

/**
 * 
 * @author JiYeon Sin
 * 
 * 1. 테스크 케이스 개수를 입력받는다.
 * 2. flag를 true로 초기화
 * 3. 스도쿠의 값들을 입력받는다.
 * 4. 스도쿠의 값이 유효한지 확인하고 출력한다.
 *  4-1. 가로에 대해 확인
 *   4-1-1. 각 행에 대해 숫자들이 한 번씩 나왔는지 boolean 배열로 확인한다.
 *  4-2. 세로에 대해 확인
 *   4-2-1. 각 열에 대해 숫자들이 한 번씩 나왔는지 boolean 배열로 확인한다.
 *  4-3. 3*3 구역에 대해 확인
 *   4-3-1. 각 구역에 대해 숫자들이 한 번씩 나왔는지 boolean 배열로 확인한다.
 *
 */
public class Solution {
	public static BufferedReader br;
	public static StringTokenizer st;
	public static StringBuilder sb;
	
	public static int testCase;
	
	public static final int ELEMENT_COUNT = 9;
	
	public static int[][] puzzle;
	
	public static boolean flag;
	public static boolean[] check; // 1~9 숫자의 등장을 확인한다.
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		// 1. 테스크 케이스 개수를 입력받는다.
		testCase = Integer.parseInt(br.readLine().trim());
		
		
		
		for(int tc = 1; tc <= testCase; tc++) {
			flag = true; // 2. flag를 true로 초기화
			puzzle = new int[ELEMENT_COUNT][ELEMENT_COUNT];
			
			// 3. 스도쿠의 값들을 입력받는다.
			for(int rowIdx = 0; rowIdx < ELEMENT_COUNT; rowIdx++) {
				st = new StringTokenizer(br.readLine().trim());
				for(int colIdx = 0; colIdx < ELEMENT_COUNT; colIdx++) {
					puzzle[rowIdx][colIdx] = Integer.parseInt(st.nextToken());
				}
			}
			
			
			// 4. 스도쿠의 값이 유효한지 확인하고 출력한다.
			sb.append("#").append(tc).append(" ").append(isValid());
			System.out.println(sb);
			sb.setLength(0);
		}
	}
	
	
	
	
	public static int isValid() {
		checkHorizontality(); // 4-1. 가로에 대해 확인
		if(flag) checkVerticality(); // 4-2. 세로에 대해 확인
		if(flag) checkSmallSquare(); // 4-3. 3*3 구역에 대해 확인
		
		return flag ? 1 : 0;
	}
	
	
	
	public static void checkHorizontality() {
		// 4-1-1. 각 행에 대해 숫자들이 한 번씩 나왔는지 boolean 배열로 확인한다.
		for(int rowIdx = 0; rowIdx < ELEMENT_COUNT; rowIdx++) {
			check = new boolean[ELEMENT_COUNT];
			int count = 0;
			
			// 행 체크
			for(int colIdx = 0; colIdx < ELEMENT_COUNT; colIdx++) {
				if(!check[ puzzle[rowIdx][colIdx]-1 ]) {
					check[ puzzle[rowIdx][colIdx]-1 ] = true;
					count++;
				}
			}
			
			// 한 행이라도 유효하지 않다면 flag변수를 false로 변경하고 확인 종료
			if(count != ELEMENT_COUNT) {
				flag = false;
				return;
			}
		}
	}
	
	public static void checkVerticality() {
		// 4-2-1. 각 열에 대해 숫자들이 한 번씩 나왔는지 boolean 배열로 확인한다.
		for(int colIdx = 0; colIdx < ELEMENT_COUNT; colIdx++) {
			check = new boolean[ELEMENT_COUNT];
			int count = 0;
			
			// 열 체크
			for(int rowIdx = 0; rowIdx < ELEMENT_COUNT; rowIdx++) {
				if(!check[ puzzle[rowIdx][colIdx]-1 ]) {
					check[ puzzle[rowIdx][colIdx]-1 ] = true;
					count++;
				}
			}
			
			// 한 열이라도 유효하지 않다면 flag변수를 false로 변경하고 확인 종료
			if(count != ELEMENT_COUNT) {
				flag = false;
				return;
			}
		}
	}
	
	public static void checkSmallSquare() {
		// 4-3-1. 각 구역에 대해 숫자들이 한 번씩 나왔는지 boolean 배열로 확인한다.
		for(int startRow = 0; startRow < ELEMENT_COUNT; startRow+=3) {
			for(int startCol = 0; startCol < ELEMENT_COUNT; startCol+=3) {
				// 3*3 구역 체크
				check = new boolean[ELEMENT_COUNT];
				int count = 0;
				
				for(int rowIdx = startRow; rowIdx < startRow+3; rowIdx++) {
					for(int colIdx = startCol; colIdx < startCol+3; colIdx++) {
						if(!check[ puzzle[rowIdx][colIdx]-1 ]) {
							check[ puzzle[rowIdx][colIdx]-1 ] = true;
							count++;
						}
					}
				}
				
				// 한 구역이라도 유효하지 않다면 flag변수를 false로 변경하고 확인 종료
				if(count != ELEMENT_COUNT) {
					flag = false;
					return;
				}
			}
		}
	}

}
