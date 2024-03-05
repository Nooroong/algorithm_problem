import java.io.*;
import java.util.*;

/**
 * 
 * @author JiYeon Sin
 * 
 * 1. 테스트 케이스의 개수를 입력받는다.
 * 2. 10개의 숫자를 입력받으면서 최대값을 구한다.
 * 3. 형식에 맞게 출력한다.
 */
public class Solution {
	public static BufferedReader br;
	public static StringTokenizer st;
	public static StringBuilder sb;
	
	public static int testCase; // 테스트 케이스의 개수
	
	public static int maxNumber; // 최대값
	public static final int NUMBER_COUNT = 10; // 숫자들의 개수
	
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		
		// 1. 테스트 케이스의 개수를 입력받는다.
		testCase = Integer.parseInt(br.readLine().trim());
		
		for(int tc = 1; tc <= testCase; tc++) {
			// 2. 10개의 숫자를 입력받으면서 최대값을 구한다.
			maxNumber = Integer.MIN_VALUE;
			st = new StringTokenizer(br.readLine().trim());
			for(int idx = 0; idx < NUMBER_COUNT; idx++) {
				int num = Integer.parseInt(st.nextToken());
				if(num > maxNumber) maxNumber = num;
			}
			

			
			// 3. 형식에 맞게 출력한다.
			sb.append("#").append(tc).append(" ").append(maxNumber);
			System.out.println(sb);
			sb.setLength(0);
		}
	}
}
