
import java.io.*;
import java.util.*;

/**
 * 
 * @author JiYeon Sin
 * 
 * 1. 10번 반복한다.
 * 2. 회문의 길이를 입력받는다.
 * 3. 글자판의 정보를 입력받는다.
 * 4. 모든 행에 대해 회문을 찾는다.
 * 5. 모든 열에 대해 회문을 찾는다.
 * 6. 찾은 회문의 수를 출력한다.
 *
 */
public class Solution {
	public static BufferedReader br;
	public static StringTokenizer st;
	public static StringBuilder sb;
	
	public static int testCase; // 테스트 케이스의 개수
	
	public static int palendromLen; // 찾아야하는 회문의 길이
	public static int palendromCount; // 찾은 회문의 수
	public static char[][] wordMap; // 글자판
	public static final int MAP_SIZE = 8; // 글자판의 크기는 8*8
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		// 1. 10번 반복한다.
		testCase = 10;
		for(int tc = 1; tc <= testCase; tc++) {
			// 2. 회문의 길이를 입력받는다.
			palendromLen = Integer.parseInt(br.readLine().trim());
			
			// 3. 글자판의 정보를 입력받는다.
			wordMap = new char[MAP_SIZE][MAP_SIZE];
			for(int rowIdx = 0; rowIdx < MAP_SIZE; rowIdx++) {
				String line = br.readLine().trim();
				for(int colIdx = 0; colIdx < MAP_SIZE; colIdx++) {
					wordMap[rowIdx][colIdx] = line.charAt(colIdx);
				}
			}
			
			palendromCount = 0;
			palendromCount += findPalendromInRow(); // 4. 모든 행에 대해 회문을 찾는다.
			palendromCount += findPalendromInCol(); // 5. 모든 열에 대해 회문을 찾는다.
			
			
			// 6. 찾은 회문의 수를 출력한다.
			sb.append("#").append(tc).append(" ").append(palendromCount);
			System.out.println(sb);
			sb.setLength(0);
		}
	}
	
	
	public static int findPalendromInRow() {
		boolean flag;
		int result = 0;
		
		// 모든 행에 대해 가로로 회문을 찾는다.
		for(int rowIdx = 0; rowIdx < MAP_SIZE; rowIdx++) {
			for(int colIdx = 0; colIdx <= MAP_SIZE-palendromLen; colIdx++) {
				// 회문 검사
				flag = true;
				
				for(int left = colIdx, right = colIdx+palendromLen-1;
					left <= right; left++, right--) {
					if(wordMap[rowIdx][left] != wordMap[rowIdx][right]) {
						flag = false;
						break;
					}
				}
				
				if(flag) result++;
			}
		}
		
		return result;
	}
	
	
	public static int findPalendromInCol() {
		boolean flag;
		int result = 0;
		
		// 모든 열에 대해 세로로 회문을 찾는다.
		for(int colIdx = 0; colIdx < MAP_SIZE; colIdx++) {
			for(int rowIdx = 0; rowIdx <= MAP_SIZE-palendromLen; rowIdx++) {
				// 회문 검사
				flag = true;
				
				for(int left = rowIdx, right = rowIdx+palendromLen-1;
					left <= right; left++, right--) {
					if(wordMap[left][colIdx] != wordMap[right][colIdx]) {
						flag = false;
						break;
					}
				}
				
				if(flag) result++;
			}
		}
		
		return result;
	}
		
}
