import java.io.*;
import java.util.*;

/**
 * 
 * @author JiYeon Sin
 * 
 * 1. 테스트 케이스의 개수를 입력받는다.
 * 2. flag 변수를 하나 선언하고 true로 초기화한다.
 * 3. 단어를 입력받아 char형 배열에 저장한다.
 * 4. 양끝단에서부터 문자들을 하나씩 비교한다. 두 포인터가 엇갈릴때까지 반복한다.
 *  4-1. 두 문자가 다르면 flag를 false로 바꾸고 반복문을 빠져나온다.
 * 5. flag값에 따라 적절히 출력을 한다.
 */
public class Solution {
	public static BufferedReader br;
	public static StringTokenizer st;
	public static StringBuilder sb;
	
	public static int testCase; // 테스트 케이스의 개수
	
	public static char[] charArr;
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		// 1. 테스트 케이스의 개수를 입력받는다.
		testCase = Integer.parseInt(br.readLine().trim());
		
		for(int tc = 1; tc <= testCase; tc++) {
			boolean flag = true; // 2. flag 변수를 하나 선언하고 true로 초기화한다.
			
			// 3. 단어를 입력받아 char형 배열에 저장한다.
			charArr = br.readLine().trim().toCharArray();
			
			// 4. 양끝단에서부터 문자들을 하나씩 비교한다. 두 포인터가 엇갈릴때까지 반복한다.
			for(int leftIdx = 0, rightIdx = charArr.length-1; leftIdx <= rightIdx; leftIdx++, rightIdx--) {
				// 4-1. 두 문자가 다르면 flag를 false로 바꾸고 반복문을 빠져나온다.
				if(charArr[leftIdx] != charArr[rightIdx]) {
					flag = false;
					break;
				}
			}
			
			// 5. flag값에 따라 적절히 출력을 한다.
			sb.append("#").append(tc).append(" ").append((flag) ? 1 : 0);
			System.out.println(sb);
			sb.setLength(0);
		}
	}
}
