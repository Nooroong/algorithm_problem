
import java.io.*;
import java.util.*;

/**
 * 
 * @author JiYeon Sin
 * 
 * 1. 테스트 케이스의 개수를 입력받는다.
 * 2. 정수 a, b를 입력받는다.
 * 3. a부터 b까지 반복한다.
 * 4. 현재 숫자의 제곱근이 정수이고, n과 n^(0.5)이 모두 팰린드롬이면 카운트+1을 한다.
 * 5. 제곱 팰린드롬의 수를 출력한다.
 */
public class Solution {
	public static BufferedReader br;
	public static StringTokenizer st;
	public static StringBuilder sb;
	
	public static int testCase; // 테스트 케이스의 개수
	
	public static int fromNum, toNum;
	public static int palindromCount;
	public static boolean[] check;
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		// 1. 테스트 케이스의 개수를 입력받는다.
		testCase = Integer.parseInt(br.readLine().trim());
		
		for(int tc = 1; tc <= testCase; tc++) {
			// 2. 정수 a, b를 입력받는다.
			st = new StringTokenizer(br.readLine().trim());
			fromNum = Integer.parseInt(st.nextToken());
			toNum = Integer.parseInt(st.nextToken());
			
			
			// 3. a부터 b까지 반복한다.
			palindromCount = 0;
			for(int num = fromNum; num <= toNum; num++) {
				// 4. 현재 숫자의 제곱근이 정수이고, n과 n^(0.5)이 모두 팰린드롬이면 카운트+1을 한다.
				double sqr = Math.sqrt(num);
				
				// double 타입을 1로 나눴을 때 나머지가 0.0이면 소수점 이하로 값이 없다는 의미
				if(sqr%1 != 0.0) continue;
				
				if(isPalinDrom(""+num) && isPalinDrom(""+(int)sqr))
					palindromCount++;
					
			}
			
			// 5. 제곱 팰린드롬의 수를 출력한다.
			sb.append("#").append(tc).append(" ").append(palindromCount);
			System.out.println(sb);
			sb.setLength(0);
		}
	}
	
	
	// String type을 이용하면 회문인지 아닌지 쉽게 확인할 수 있다.
	public static boolean isPalinDrom(String num) {
		for(int left = 0, right = num.length()-1; left <= right; left++, right--) {
			if(num.charAt(left) != num.charAt(right)) {
				return false;
			}
		}
		
		return true;
	}
}
