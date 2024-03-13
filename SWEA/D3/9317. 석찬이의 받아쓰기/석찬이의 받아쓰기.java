
import java.io.*;
import java.util.*;

/**
 * 
 * @author JiYeon Sin
 * 
 * 1. 테스트 케이스의 개수를 입력받는다.
 * 2. 문자열의 길이를 입력받는다.
 * 3. 원문과 받아적은 문자열을 입력받는다.
 * 4. 둘을 한 문자씩 비교하면서 같은 문자의 개수를 센다.
 * 5. 맞게 받아 적은 문자의 개수를 출력한다.
 */
public class Solution {
	public static BufferedReader br;
	public static StringTokenizer st;
	public static StringBuilder sb;
	
	public static int testCase; // 테스트 케이스의 개수
	
	public static int strLen; // 문자열의 길이
	public static String origin, dictation; // 원분, 받아쓰기
	public static int correctCount; // 맞게 받아적은 문자의 개수
	
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		// 1. 테스트 케이스의 개수를 입력받는다.
		testCase = Integer.parseInt(br.readLine().trim());
		
		for(int tc = 1; tc <= testCase; tc++) {
			// 2. 문자열의 길이를 입력받는다.
			strLen = Integer.parseInt(br.readLine().trim());
			
			// 3. 원문과 받아적은 문자열을 입력받는다.
			origin = br.readLine().trim();
			dictation = br.readLine().trim();
			
			// 4. 둘을 한 문자씩 비교하면서 같은 문자의 개수를 센다.
			correctCount = 0;
			for(int idx = 0; idx < strLen; idx++) {
				if(origin.charAt(idx) == dictation.charAt(idx))
					correctCount++;
			}
			
			// 5. 맞게 받아 적은 문자의 개수를 출력한다.
			sb.append("#").append(tc).append(" ").append(correctCount);
			System.out.println(sb);
			sb.setLength(0);
		}
	}
}
