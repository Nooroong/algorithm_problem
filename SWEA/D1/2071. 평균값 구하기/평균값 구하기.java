import java.io.*;
import java.util.*;

/**
 * 
 * @author JiYeon Sin
 * 
 * 1. 테스트 케이스 개수를 입력받는다.
 * 2. 10개의 숫자를 입력받으면서 합을 구한다.
 * 3. 숫자를 다 입력받으면 평균을 구한다.
 * 4. 평균을 출력한다. 
 */
public class Solution {
	public static BufferedReader br;
	public static StringTokenizer st;
	public static StringBuilder sb;
	
	public static int testCase; // 테스트 케이스의 개수
	public static final int NUM_COUNT = 10; // 각 줄의 숫자들의 개수
	
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		
		// 1. 테스트 케이스 개수를 입력받는다.
		testCase = Integer.parseInt(br.readLine().trim());
		
		for(int tc = 1; tc <= testCase; tc++) {
			double sum = 0; // 숫자들의 합
			int avg = 0; // 반올림 한 평균
			
			// 2. 10개의 숫자를 입력받으면서 합을 구한다.
			st = new StringTokenizer(br.readLine().trim());
			for(int idx = 0; idx < NUM_COUNT; idx++) {
				sum += Integer.parseInt(st.nextToken());
			}
			
			// 3. 숫자를 다 입력받으면 평균을 구한다.
			avg = (int)Math.round(sum/NUM_COUNT);
			
			// 4. 평균을 출력한다. 
			sb.append("#").append(tc).append(" ").append(avg);
			System.out.println(sb);
			sb.setLength(0);
		}
	}

}
