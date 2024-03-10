import java.io.*;
import java.util.*;

/**
 * 
 * @author JiYeon Sin
 * 
 * 마디의 최대 길이는 10이니까 이 안에서만 따지면 될듯?
 * 
 * 1. 테스트 케이스의 개수를 입력받는다.
 * 2. 길이가 30인 문자열을 입력받는다.
 * 3. 첫번째 문자부터 10번째 문자까지 반복한다.
 * 4. 임시변수에 문자를 하나씩 붙이고 임시변수의 길이만큼 탐색 범위 밖을 비교한다.
 *
 */
public class Solution {
	public static BufferedReader br;
	public static StringTokenizer st;
	public static StringBuilder sb;
	
	public static int testCase; // 테스트 케이스의 개수
	
	public static String str;
	public static String tempWord;
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		testCase = Integer.parseInt(br.readLine().trim());
		
		for(int tc = 1; tc <= testCase; tc++) {
			str = br.readLine().trim();
			
			tempWord = str.substring(0, 1);
			
			int len;
			for(len = 0; len < 10; len++) {
				tempWord = str.substring(0, len+1);
				boolean flag = true;
				
				for(int c = 0; c < tempWord.length(); c++) {
					if(tempWord.charAt(c) != str.charAt(len+1+c))
						flag = false;
				}
				
				if(flag) break;
			}
			
			sb.append("#").append(tc).append(" ").append(tempWord.length());
			System.out.println(sb);
			sb.setLength(0);
		}
	}
}
