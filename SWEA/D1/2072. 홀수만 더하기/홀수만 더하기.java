import java.io.*;
import java.util.*;

/**
 * 
 * @author JiYeon Sin
 * 
 * 1. 테스트 케이스를 입력받는다.
 * 2. 숫자 10개를 입력받는다.
 * 3. 각 숫자가 홀수라면 변수에 누적시킨다.
 * 4. 누적된 값을 출력한다.
 */
public class Solution {
	public static BufferedReader br;
	public static StringTokenizer st;
	public static StringBuilder sb;
	
	public static int testCase; // 테스트 케이스의 개수
	public static final int NUM_COUNT = 10; // 한 줄ㅔ 들어오는 숫자의 개수
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		//1. 테스트 케이스를 입력받는다.
		testCase = Integer.parseInt(br.readLine().trim());
		
		for(int tc = 1; tc <= testCase; tc++) {
			int sum = 0;
			
			// 2. 숫자 10개를 입력받는다.
			st = new StringTokenizer(br.readLine().trim());
			
			for(int idx = 0; idx < NUM_COUNT; idx++) {
				// 3. 각 숫자가 홀수라면 변수에 누적시킨다.
				int num = Integer.parseInt(st.nextToken());
				if(num%2 == 1) sum+= num;
			}
			
			// 4. 누적된 값을 출력한다.
			sb.append("#").append(tc).append(" ").append(sum);
			System.out.println(sb);
			sb.setLength(0);
		}
	}
}
