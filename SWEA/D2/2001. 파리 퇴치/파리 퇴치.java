import java.io.*;
import java.util.*;

/**
 * 
 * @author JiYeon Sin
 * 완탐으로 풀기
 * 1. 테스트 케이스의 수를 입력받는다.
 * 2. 테스트 케이스만큼 반복
 * 3-1. 파리의 개수가 있는 영역의 크기를 입력받는다.(flyAreaSize)
 * 3-2. 파리채의 크기를 입력받는다.(swatterSize)
 * 4. 파리의 수를 입력받는다.
 * 5. 0행 0열부터 (flyAreaSize-swatterSize)행 (flyAreaSize-swatterSize)열(포함)까지 탐색을 한다.
 * 6. 매 반복마다 해당 영역의 파리의 총합을 구한다.
 * 7. 최대 파리의 수(maxFly)와 비교하여 필요하면 값을 갱신한다.
 * 8. 반복이 끝나면 maxFly를 형식에 맞춰 출력한다.
 */
public class Solution {
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;
	
	static int testCase; // 테스트 케이스의 수
	
	static int flyAreaSize; // 파리 영역의 크기
	static int[][] flyArea; // 파리의 수를 담는 배열
	static int swatterSize; // 파리채의 크기
	static int maxFly = Integer.MIN_VALUE; // 죽일수 있는 파리의 최대 수
	
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		// 1. 테스트 케이스의 수를 입력받는다.
		testCase = Integer.parseInt(br.readLine().trim());
		
		
		
		
		// 2. 테스트 케이스만큼 반복
		for(int tc = 1; tc <= testCase; tc++) {
			maxFly = Integer.MIN_VALUE; // 초기화 주의!!
			
			// 3. 영역의 크기와 파리채의 크기를 입력받는다.
			st = new StringTokenizer(br.readLine().trim());
			flyAreaSize = Integer.parseInt(st.nextToken());
			swatterSize = Integer.parseInt(st.nextToken());
			
			flyArea = new int[flyAreaSize][flyAreaSize];
			
			// 4. 파리의 수를 입력받는다.
			for(int rowIdx = 0; rowIdx < flyAreaSize; rowIdx++) {
				st = new StringTokenizer(br.readLine().trim());
				
				for(int colIdx = 0; colIdx < flyAreaSize; colIdx++) {
					flyArea[rowIdx][colIdx] = Integer.parseInt(st.nextToken());
				}
			}
			
			// 5. 0행 0열부터 (flyAreaSize-swatterSize)행 (flyAreaSize-swatterSize)열(포함)까지 탐색을 한다.
			for(int rowIdx = 0; rowIdx <= flyAreaSize-swatterSize; rowIdx++) {
				for(int colIdx = 0; colIdx <= flyAreaSize-swatterSize; colIdx++) {
					// 6. 매 반복마다 해당 영역의 파리의 총합을 구한다.
					int sumOfFly = 0;
					for(int swatterRow = rowIdx; swatterRow < rowIdx+swatterSize; swatterRow++) {
						for(int swatterCol = colIdx; swatterCol < colIdx+swatterSize; swatterCol++) {
							sumOfFly += flyArea[swatterRow][swatterCol];
						}
						
					}
					
					// 7. 최대 파리의 수(maxFly)와 비교하여 필요하면 값을 갱신한다.
					maxFly = (sumOfFly > maxFly) ? sumOfFly : maxFly;
				}
			}
			
			
			// 8. 반복이 끝나면 maxFly를 형식에 맞춰 출력한다.
			sb.append("#").append(tc).append(" ").append(maxFly);
			System.out.println(sb);
			sb.setLength(0);
		}
	}

}
