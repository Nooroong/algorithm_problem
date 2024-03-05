import java.io.*;
import java.util.*;

/**
 * 
 * @author JiYeon Sin
 * 
 * 1. 문자열을 입력받아 String 타입 변수에 저장한다.
 * 2. 문자열에서 문자들을 하나씩 살핀다.
 *  3. 대소문자를 구분하여 처리한다. 현재 문자에 a나 A를 빼주고 1을 더하면 된다.
 *  4. 공백을 붙인다.
 * 5. 완성된 문자열을 출력한다.
 */
public class Solution {
	public static BufferedReader br;
	public static StringTokenizer st;
	public static StringBuilder sb;
	
	public static String str;
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		
		str = br.readLine().trim(); // 1. 문자열을 입력받아 String 타입 변수에 저장한다.
		
		for(int idx = 0; idx < str.length(); idx++) {
			// 2. 문자열에서 문자들을 하나씩 살핀다.
			char curChar = str.charAt(idx);
			
			
			// 3. 대소문자를 구분하여 처리한다. 현재 문자에 a나 A를 빼주고 1을 더하면 된다.
			if(curChar >= 'a') {
				sb.append(curChar - 'a' + 1);
			} else {
				sb.append(curChar -'A' + 1);
			}
			
			sb.append(" "); // 4. 공백을 붙인다.
		}
		
		System.out.println(sb); // 5. 완성된 문자열을 출력한다.
	}
}
