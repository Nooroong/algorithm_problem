import java.io.*;
import java.util.*;

/**
 * 
 * @author JiYeon Sin
 * 
 * 1. 테스트 케이스 개수를 입력받고 반복한다.
 * 2. n을 입력받는다.
 * 3. 1부터 n까지 반복하며 홀수는 더하고 짝수는 뺀다.
 * 4. 누적된 결과를 출력한다.
 *
 */
public class Solution {
	public static BufferedReader br;
	public static StringTokenizer st;
	public static StringBuilder sb;
	
	public static int testCase; // 테스트 케이스의 개수
	
	public static int num;
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		// 1. 테스트 케이스 개수를 입력받고 반복한다.
		testCase = Integer.parseInt(br.readLine().trim());
		
		for(int tc = 1; tc <= testCase; tc++) {
			num = Integer.parseInt(br.readLine().trim()); // 2. n을 입력받는다.
			
			
			// 3. 1부터 n까지 반복하며 홀수는 더하고 짝수는 뺀다.
			int result = 0;
			for(int n = 1; n <= num; n++) {
				if(n%2 == 1) result += n;
				else result -= n;
			}
			
			// 4. 누적된 결과를 출력한다.
			sb.append("#").append(tc).append(" ").append(result);
			System.out.println(sb);
			sb.setLength(0);
		}
	}
}
