import java.io.*;
import java.util.*;

/**
 * 
 * @author JiYeon Sin
 *
 * 1. 숫자를 입력받는다.
 * 2. 1부터 n까지 반복한다.
 *  3. 각 숫자를 임시 변수에 옮겨놓는다.
 *  4. 현재 검토하는 숫자가 0이 될 때까지 가중치가 낮은 자릿수를 체크한다.
 *  5. 각 자릿수에 3, 6, 9가 들어있으면 박수를 치고 flag를 true로 변경한다.
 *  6. flag가 false라면 그냥 숫자를 출력한다.
 *  7. 공백을 출력한다.
 */
public class Solution {
	public static BufferedReader br;
	public static StringTokenizer st;
	public static StringBuilder sb;
	
	public static int testCase; // 테스트 케이스의 개수
	
	public static int number;
	
	public static boolean clapFlag;
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		
		// 1. 숫자를 입력받는다.
		number = Integer.parseInt(br.readLine().trim());
		
		// 2. 1부터 n까지 반복한다.
		for(int num = 1; num <= number; num++) {
			clapFlag = false;
			int tempNum = num; // 3. 각 숫자를 임시 변수에 옮겨놓는다.
			
			// 4. 현재 검토하는 숫자가 0이 될 때까지 가중치가 낮은 자릿수를 체크한다.
			while(tempNum > 0) {
				// 5. 각 자릿수에 3, 6, 9가 들어있으면 박수를 치고 flag를 true로 변경한다.
				if(tempNum%10 == 3 || tempNum%10 == 6 || tempNum%10 == 9) {
					sb.append("-");
					clapFlag = true;
				}
				tempNum /= 10;
			}
			
			
			// 6. flag가 false라면 그냥 숫자를 출력한다.
			if(!clapFlag) sb.append(num);
			
			sb.append(" "); // 7. 공백을 출력한다.
		}
		
		System.out.println(sb);
	}

}
