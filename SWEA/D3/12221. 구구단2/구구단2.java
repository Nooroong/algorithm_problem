
import java.io.*;
import java.util.*;

/**
 * 
 * @author JiYeon Sin
 * 
 * 1. 테스트 케이스의 개수를 입력받는다.
 * 2. 두 정수를 입력받는다.
 * 3. 두 정수 중 하나라도 10 이상이라면 -1을 출력한다.
 * 4. 둘 다 10 미만이라면 둘을 곱한 값을 출력한다.
 *
 */
public class Solution {
	public static BufferedReader br;
	public static StringTokenizer st;
	public static StringBuilder sb;
	
	public static int testCase; // 테스트 케이스의 개수
	public static int num1, num2;
	public static int result; // 출력할 값
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		// 1. 테스트 케이스의 개수를 입력받는다.
		testCase = Integer.parseInt(br.readLine().trim());
		
		for(int tc = 1; tc <= testCase; tc++) {
			// 2. 두 정수를 입력받는다.
			st = new StringTokenizer(br.readLine().trim());
			num1 = Integer.parseInt(st.nextToken());
			num2 = Integer.parseInt(st.nextToken());
			
			// 3. 두 정수 중 하나라도 10 이상이라면 -1을 출력한다.
			// 4. 둘 다 10 미만이라면 둘을 곱한 값을 출력한다.
			if(num1 >= 10 || num2 >= 10) result = -1;
			else result = num1 * num2;
			
			sb.append("#").append(tc).append(" ").append(result);
			System.out.println(sb);
			sb.setLength(0);
		}
	}
}
