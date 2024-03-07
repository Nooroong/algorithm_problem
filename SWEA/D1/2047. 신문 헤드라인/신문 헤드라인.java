import java.io.*;
import java.util.*;

/**
 * 
 * @author JiYeon Sin
 * 
 * 1. 문자열을 입력받아 char형 배열에 저장한다.
 * 2. 문자열의 문자를 하나씩 살핀다.
 * 3. 문자가 소문자에 해당하면 대문자로 변경한다.
 * 4. 변환된 결과를 출력한다.
 */
public class Solution {
	public static BufferedReader br;
	public static StringTokenizer st;
	public static StringBuilder sb;
	
	public static char[] input;
	
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		// 1. 문자열을 입력받아 char형 배열에 저장한다.
		input = br.readLine().trim().toCharArray();
		
		// 2. 문자열의 문자를 하나씩 살핀다.
		for(int idx = 0; idx < input.length; idx++) {
			// 3. 문자가 소문자에 해당하면 대문자로 변경한다.
			if(input[idx] >= 'a' && input[idx] <= 'z') {
				// 이렇게 하면 아스키 코드 값을 몰라도 대문자로 변환 가능
				// 소문자가 대문자보다 값이 크다는 것만 기억하면 된다.
				input[idx] -= 'a'-'A';
			}
		}
		
		// 4. 변환된 결과를 출력한다.
		for(int idx = 0; idx < input.length; idx++)
			sb.append(input[idx]);
			
		System.out.println(sb);
	}
}
