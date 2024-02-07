import java.util.*;
import java.io.*;

/**
 * 
 * @author JiYeon Sin
 * 1. 색종이의 수를 입력받는다.
 * 2. 흰 종이 배열을 생성한다.
 * 3. 색종이를 붙인 위치를 입력받는다.
 *    (c r)을 입력받았을 때, 붙인 색종이가 배열에서 차지하는 영역은
 *    (r-1, c-1) ~ (r+9, c+9)가 된다.
 * 4. 위치를 바탕으로 색종이가 붙여지는 위치의 배열 값을 1로 바꾼다.
 * 5. 색종이 정보를 모두 입력받았으면 배열의 모든 값을 더하고 출력한다.
 * 
 */

public class Main {
	public static BufferedReader br;
	public static StringTokenizer st;
	
	public static final int WHITE_PAPER_SIZE = 100; // 흰 종이의 크기
	public static final int COLORED_PAPER_SIZE = 10; // 색 종이의 크기
	
	public static int[][] whitePaper; // 흰 종이 배열
	public static int numOfColoredPaper; // 색종이의 수
	public static int startCol; // 붙인 색종이의 열 시작점
	public static int startRow; // 붙인 색종이의 행 시작점
	
	public static int coloredArea = 0; // 색종이가 붙여진 영역의 넓이
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		
		// 1. 색종이의 수를 입력받는다.
		numOfColoredPaper = Integer.parseInt(br.readLine().trim());
		
		// 2. 흰 종이 배열을 생성한다.
		whitePaper = new int[WHITE_PAPER_SIZE][WHITE_PAPER_SIZE];
		
		
		
		while(numOfColoredPaper > 0) {
			// 3. 색종이를 붙인 위치를 입력받는다.
			st = new StringTokenizer(br.readLine().trim());
			startCol = Integer.parseInt(st.nextToken()) - 1;
			startRow = Integer.parseInt(st.nextToken()) - 1;
			
			// 4. 위치를 바탕으로 색종이가 붙여지는 위치의 배열 값을 1로 바꾼다.
			// (r-1, c-1) ~ (r+9, c+9)
			for(int rowIdx = startRow; rowIdx < startRow+COLORED_PAPER_SIZE; rowIdx++) {
				for(int colIdx = startCol; colIdx < startCol+COLORED_PAPER_SIZE; colIdx++) {
					whitePaper[rowIdx][colIdx] = 1;
				}
			}
			
			numOfColoredPaper--;	
		}
		
		
		
		
		// 5. 색종이 정보를 모두 입력받았으면 배열의 모든 값을 더하고 출력한다.
		for(int rowIdx = 0; rowIdx < WHITE_PAPER_SIZE; rowIdx++) {
			for(int colIdx = 0; colIdx < WHITE_PAPER_SIZE; colIdx++) {
				coloredArea += whitePaper[rowIdx][colIdx];
			}
		}
		System.out.println(coloredArea);
	}
}

