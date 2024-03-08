import java.io.*;
import java.util.*;

/**
 * 
 * @author JiYeon Sin
 * 
 * 1. 테스트 케이스의 개수를 입력받는다.
 * 2. 행렬의 크기를 입력받는다.
 * 3. 행렬의 정보를 입력받는다.
 * 4. 원본 행렬을 90도, 180도, 270도 회전하여 각각 새로운 배열에 저장한다.
 * 5. 회전 결과를 적절히 출력한다.
 *
 */
public class Solution {
	public static BufferedReader br;
	public static StringTokenizer st;
	public static StringBuilder sb;
	
	public static int testCase; // 테스트 케이스의 개수
	
	public static int size; // 행렬의 크기
	
	// 원본 배열과 회전 결과를 저장하는 배열
	public static int[][] arr;
	public static int[][] rotate90;
	public static int[][] rotate180;
	public static int[][] rotate270;
	
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		// 1. 테스트 케이스의 개수를 입력받는다.
		testCase = Integer.parseInt(br.readLine().trim());
		
		for(int tc = 1; tc <= testCase; tc++) {
			// 2. 행렬의 크기를 입력받는다.
			size = Integer.parseInt(br.readLine().trim());
			
			// 배열 할당
			arr = new int[size][size];
			rotate90 = new int[size][size];
			rotate180 = new int[size][size];
			rotate270 = new int[size][size];
			
			
			// 3. 행렬의 정보를 입력받는다.
			for(int rowIdx = 0; rowIdx < size; rowIdx++) {
				st = new StringTokenizer(br.readLine().trim());
				for(int colIdx = 0; colIdx < size; colIdx++) {
					arr[rowIdx][colIdx] = Integer.parseInt(st.nextToken());
				}
			}
			
			// 4. 원본 행렬을 90도, 180도, 270도 회전하여 각각 새로운 배열에 저장한다.
			turn90Degree(); // 시계방향 90도 회전
			turn180Degree(); // 시계방향 180도 회전
			turn270Degree(); // 시계방향 270도 회전
			
			
			// 5. 회전 결과를 적절히 출력한다.
			sb.append("#").append(tc).append("\n");
			for(int rowIdx = 0; rowIdx < size; rowIdx++) {
				for(int colIdx = 0; colIdx < size; colIdx++)
					sb.append(rotate90[rowIdx][colIdx]);
				sb.append(" ");
				
				for(int colIdx = 0; colIdx < size; colIdx++)
					sb.append(rotate180[rowIdx][colIdx]);
				sb.append(" ");
				
				for(int colIdx = 0; colIdx < size; colIdx++)
					sb.append(rotate270[rowIdx][colIdx]);
				sb.append("\n");
			}
			
			System.out.print(sb);
			sb.setLength(0);
		}
	}
	
	
	public static void turn90Degree() {
		for(int originCol = 0, newRow =0; originCol < size; originCol++, newRow++) {
			for(int originRow = size-1, newCol = 0; originRow >= 0; originRow--, newCol++) {
				rotate90[newRow][newCol] = arr[originRow][originCol];
			}
		}
	}
	
	public static void turn180Degree() {
		for(int originRow = size-1, newRow = 0; originRow >= 0; originRow--, newRow++) {
			for(int originCol = size-1, newCol =0; originCol >= 0; originCol--, newCol++) {
				rotate180[newRow][newCol] = arr[originRow][originCol];
			}
		}
	}
	
	public static void turn270Degree() {
		for(int originCol = size-1, newRow = 0; originCol >= 0; originCol--, newRow++) {
			for(int originRow = 0, newCol =0; originRow < size; originRow++, newCol++) {
				rotate270[newRow][newCol] = arr[originRow][originCol];
			}
		}
	}
}
