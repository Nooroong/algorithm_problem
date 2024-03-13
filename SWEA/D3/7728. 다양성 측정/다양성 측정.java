
import java.io.*;
import java.util.*;

/**
 * 
 * @author JiYeon Sin
 * 
 * 1. 테스트 케이스의 개수를 입력받는다.
 * 2. 숫자를 입력받는다.(int로도 가능)
 * 3. 0부터 9까지의 숫자를 체크하는 boolean형 배열을 만든다.
 * 4. 입력받은 숫자 0이 될 때까지 일의 자리 숫자를 계속 확인한다.
 *  4-1. 처음 등장한 숫자라면 다양성을 1 증가시키고 등장처리를 한다.
 * 5. 다양성을 출력한다.
 */
public class Solution {
	public static BufferedReader br;
	public static StringTokenizer st;
	public static StringBuilder sb;
	
	public static int testCase; // 테스트 케이스의 개수
	
	public static int diversity; // 숫자의 다양성
	public static int number; // 문제에서 주어지는 숫자
	public static boolean[] numberCheck; // 각 자릿수의 등장을 체크
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		// 1. 테스트 케이스의 개수를 입력받는다.
		testCase = Integer.parseInt(br.readLine().trim());
		
		for(int tc = 1; tc <= testCase; tc++) {
			// 2. 숫자를 입력받는다.(int로도 가능)
			number = Integer.parseInt(br.readLine().trim());
			
			// 3. 0부터 9까지의 숫자를 체크하는 boolean형 배열을 만든다.
			numberCheck = new boolean[10];
			diversity = 0; // 요것도 초기화
			
			// 4. 입력받은 숫자 0이 될 때까지 일의 자리 숫자를 계속 확인한다.
			while(number > 0) {
				int curNum = number % 10;
				
				// 4-1. 처음 등장한 숫자라면 다양성을 1 증가시키고 등장처리를 한다.
				if(!numberCheck[curNum]) {
					diversity++;
					numberCheck[curNum] = true;
				}
				
				number /= 10;
			}
			
			// 5. 다양성을 출력한다.
			sb.append("#").append(tc).append(" ").append(diversity);
			System.out.println(sb);
			sb.setLength(0);
		}
	}
}
