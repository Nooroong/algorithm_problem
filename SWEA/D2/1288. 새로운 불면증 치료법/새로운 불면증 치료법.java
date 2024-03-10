import java.io.*;
import java.util.*;

/**
 * 
 * @author JiYeon Sin
 * 
 * 1. 테스트 케이스를 입력받는다.
 * 2. 숫자 n을 입력받는다.
 * 3. 0~9까지의 숫자를 봤는지 저장하는 boolean형 배열과 count 변수를 만든다.
 * 4. 계속 n의 배수를 구한다.
 * 	4-1. 각 자리수를 확인한다.
 *  4-2. 새로나온 숫자라면 배열의 값을 true로 바꾸고 count를 증가시킨다.
 *  4-3. count가 10이상이 되면 반복문을 빠져나온다.
 * 5. xN을 출력한다.
 *
 */
public class Solution {
	public static BufferedReader br;
	public static StringTokenizer st;
	public static StringBuilder sb;
	
	public static int testCase; // 테스트 케이스의 개수
	
	public static int num;
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		// 1. 테스트 케이스를 입력받는다.
		testCase = Integer.parseInt(br.readLine().trim());
		
		for(int tc = 1; tc <= testCase; tc++) {
			// 2. 숫자 n을 입력받는다.
			num = Integer.parseInt(br.readLine().trim());
			
			// 3. 0~9까지의 숫자를 봤는지 저장하는 boolean형 배열과 count 변수를 만든다.
			boolean[] numCheck = new boolean[10];
			int count = 0;
			
			// 4. 계속 n의 배수를 구한다.
			int x = 1;
			while(true) {
				int xNum = num*x;
				
				// 4-1. 각 자리수를 확인한다.
				while(xNum > 0) {
					// 4-2. 새로나온 숫자라면 배열의 값을 true로 바꾸고 count를 증가시킨다.
					if(!numCheck[xNum%10]) {
						numCheck[xNum%10] = true;
						count++;
					}
					
					xNum /= 10;
				}
				
				// 4-3. count가 10이상이 되면 반복문을 빠져나온다.
				if(count >= 10) break;
				
				x++;
			}
			
			// 5. xN을 출력한다.
			sb.append("#").append(tc).append(" ").append(x*num);
			System.out.println(sb);
			sb.setLength(0);
		}
	}
}
