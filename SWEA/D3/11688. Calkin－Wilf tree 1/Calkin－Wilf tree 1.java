
import java.io.*;
import java.util.*;

/**
 * 
 * @author JiYeon Sin
 * 
 * 1. 테스트 케이스 개수를 입력받는다.
 * 2. 분모, 분자의 값을 1로 초기화한다.
 * 3. 방향에 대한 문자열을 입력받는다.
 * 4. 문자열에서 문자를 하나씩 읽는다.
 *  4-1. 문자가 'L'인 경우 분모 += 분자
 *  4-2. 문자가 'R'인 경우 분자 += 분모
 * 5. 마지막 노드의 값(분자 분모)를 출력한다.
 */
public class Solution {
	public static BufferedReader br;
	public static StringTokenizer st;
	public static StringBuilder sb;
	
	public static int testCase; // 테스트 케이스의 개수
	
	public static int numerator, denominator; // 분자, 분모
	public static String direction;
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		// 1. 테스트 케이스 개수를 입력받는다.
		testCase = Integer.parseInt(br.readLine().trim());
		
		for(int tc = 1; tc <= testCase; tc++) {
			// 2. 분모, 분자의 값을 1로 초기화한다.
			numerator = denominator = 1;
			
			// 3. 방향에 대한 문자열을 입력받는다.
			direction = br.readLine().trim();
			
			// 4. 문자열에서 문자를 하나씩 읽는다.
			for(int idx = 0; idx < direction.length(); idx++ ) {
				if(direction.charAt(idx) == 'L')
					denominator += numerator; // 4-1. 문자가 'L'인 경우 분모 += 분자
				else
					numerator += denominator; // 4-2. 문자가 'R'인 경우 분자 += 분모
			}
			
			// 5. 마지막 노드의 값(분자 분모)를 출력한다.
			sb.append("#").append(tc).append(" ").append(numerator).append(" ").append(denominator);
			System.out.println(sb);
			sb.setLength(0);
		}
	}
}
