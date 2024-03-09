import java.io.*;
import java.util.*;

/**
 * 
 * @author JiYeon Sin
 * 
 * 1. 문제에서 주어진 소수들을 배열에 저장한다.
 * 2. 테스트 케이스 개수를 입력받고 그만큼 반복한다.
 * 3. 숫자 n을 입력받는다.
 * 4. n을 소수들로 순서대로 나눈다.
 *  4-1. 나누어떨어지는 동안 숫자를 소수로 계속 나눈다.
 *  4-2. 나눈 횟수를 출력문에 붙인다.
 */
public class Solution {
	public static BufferedReader br;
	public static StringTokenizer st;
	public static StringBuilder sb;
	
	public static int testCase; // 테스트 케이스의 개수
	
	// 1. 문제에서 주어진 소수들을 배열에 저장한다.
	public static int[] primeNums = { 2, 3, 5, 7, 11 };
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		// 2. 테스트 케이스 개수를 입력받고 그만큼 반복한다.
		testCase = Integer.parseInt(br.readLine().trim());
		
		for(int tc = 1; tc <= testCase; tc++) {
			sb.append("#").append(tc).append(" ");
			
			// 3. 숫자 n을 입력받는다.
			int num = Integer.parseInt(br.readLine().trim());
			
			// 4. n을 소수들로 순서대로 나눈다.
			for(int idx = 0; idx < primeNums.length; idx++) {
				// 4-1. 나누어떨어지는 동안 숫자를 소수로 계속 나눈다.
				int count = 0;
				while(num%primeNums[idx] == 0) {
					count++;
					num /= primeNums[idx];
				}
				
				sb.append(count).append(" "); // 4-2. 나눈 횟수를 출력문에 붙인다.
			}

			System.out.println(sb); // 결과를 출력
			sb.setLength(0);
		}
	}
}
