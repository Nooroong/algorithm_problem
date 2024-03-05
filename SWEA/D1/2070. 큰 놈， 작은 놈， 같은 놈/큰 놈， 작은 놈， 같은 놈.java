import java.io.*;
import java.util.*;

/**
 * 
 * @author JiYeon Sin
 * 
 * 1. 테스트 케이스의 개수를 입력받는다.
 * 2. 두 개의 숫자를 입력받는다.
 * 3. 대소관계를 비교하여 등호 또는 부등호를 출력한다.
 */
public class Solution {
	public static BufferedReader br;
	public static StringTokenizer st;
	public static StringBuilder sb;
	
	public static int testCase; // 테스트 케이스의 개수
	public static int num1, num2; // 입력을 받는 두 개의 숫자 
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		
		// 1. 테스트 케이스의 개수를 입력받는다.
		testCase = Integer.parseInt(br.readLine().trim());
		
		for(int tc = 1; tc <= testCase; tc++) {
			sb.append("#").append(tc).append(" ");
			
			
			// 2. 두 개의 숫자를 입력받는다.
			st = new StringTokenizer(br.readLine().trim());
			num1 = Integer.parseInt(st.nextToken());
			num2 = Integer.parseInt(st.nextToken());
			
			// 3. 대소관계를 비교하여 등호 또는 부등호를 출력한다.
			if(num1 < num2) sb.append("<");
			else if(num1 > num2) sb.append(">");
			else sb.append("=");
			
			System.out.println(sb);
			sb.setLength(0);
		}
	}
}
