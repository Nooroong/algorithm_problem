
import java.io.*;
import java.util.*;

/**
 * 
 * @author JiYeon Sin
 * 
 * 1. 테스트 케이스 개수를 입력받는다.
 * 2. 문자열을 입력받는다.
 * 3. 문자열에서 문자를 하나씩 확인한다.
 * 4. 현재 문자가 모음이 아닌 경우 stringbuilder에 붙인다.
 * 5. 결과를 출력한다.
 */
public class Solution {
	public static BufferedReader br;
	public static StringTokenizer st;
	public static StringBuilder sb;
	
	public static int testCase; // 테스트 케이스의 개수
	
	public static String inputString;
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		// 1. 테스트 케이스 개수를 입력받는다.
		testCase = Integer.parseInt(br.readLine().trim());
		
		for(int tc = 1; tc <= testCase; tc++) {
			sb.append("#").append(tc).append(" "); // 출력 형식 지키기
			
			inputString = br.readLine().trim(); // 2. 문자열을 입력받는다.
			
			// 3. 문자열에서 문자를 하나씩 확인한다.
			for(int idx = 0; idx < inputString.length(); idx++) {
				char curChar = inputString.charAt(idx);
				
				// 4. 현재 문자가 모음이 아닌 경우 stringbuilder에 붙인다.
				// 문자열에는 소문자만 있다.
				if(curChar != 'a' && curChar != 'i' && curChar != 'o' && curChar != 'u' && curChar != 'e')
					sb.append(curChar);
			}
			
			// 5. 결과를 출력한다.
			System.out.println(sb);
			sb.setLength(0);
		}
	}
}
