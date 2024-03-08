import java.io.*;
import java.util.*;

/**
 * 
 * @author JiYeon Sin
 * 
 * 1. 테스트 케이스 개수를 입력받고 반복한다.
 * 2. 2개의 수를 입력받는다.
 * 3. a를 b로 나눈 몫과 나머지를 계산하여 출력한다.
 */
public class Solution {
	public static BufferedReader br;
	public static StringTokenizer st;
	public static StringBuilder sb;
	
	public static int testCase; // 테스트 케이스의 개수
	
	public static int num1, num2;
	
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		// 1. 테스트 케이스 개수를 입력받고 반복한다.
		testCase = Integer.parseInt(br.readLine().trim());
		
		for(int tc = 1; tc <= testCase; tc++) {
			
			// 2. 2개의 수를 입력받는다.
			st = new StringTokenizer(br.readLine().trim());
			num1 = Integer.parseInt(st.nextToken());
			num2 = Integer.parseInt(st.nextToken());
			
			// 3. a를 b로 나눈 몫과 나머지를 계산하여 출력한다.
			sb.append("#").append(tc).append(" ").append(num1/num2).append(" ").append(num1%num2);
			System.out.println(sb);
			sb.setLength(0);
		}
	}
}
