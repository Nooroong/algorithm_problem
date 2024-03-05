import java.io.*;
import java.util.*;

/**
 * 
 * @author JiYeon Sin
 * 
 * 1. 숫자를 입력받는다.
 * 2. 숫자가 0 미만이 될 때까지 반복한다.
 * 3. 숫자와 공백을 출력한다.
 * 4. 숫자에서 1을 뺀다.
 */
public class Solution {
	public static BufferedReader br;
	public static StringBuilder sb;
	
	public static int number; // 테스트 케이스의 개수
	
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		number = Integer.parseInt(br.readLine().trim()); // 1. 숫자를 입력받는다.
		
		// 2. 숫자가 0 미만이 될 때까지 반복한다.
		while(number >= 0) {
			sb.append(number).append(" "); // 3. 숫자와 공백을 출력한다.
			number--; // 4. 숫자에서 1을 뺀다.
		}
			
		System.out.println(sb); // 출력
	}
}
