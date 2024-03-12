import java.io.*;
import java.util.*;

/**
 * 
 * @author JiYeon Sin
 * 
 * 1. 테스트 케이스의 개수를 입력받는다.
 * 2. 현재 시각과 지난 시간을 입력받는다.
 * 3. 둘을 더하고 24로 나눈 나머지를 구해 출력한다.
 */
public class Solution {
	public static BufferedReader br;
	public static StringTokenizer st;
	public static StringBuilder sb;
	
	public static int testCase; // 테스트 케이스의 개수
	
	public static int startTime, after; // 현재 시각, 지난 시간
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		// 1. 테스트 케이스의 개수를 입력받는다.
		testCase = Integer.parseInt(br.readLine().trim());
		
		for(int tc = 1; tc <= testCase; tc++) {
			// 2. 현재 시각과 지난 시간을 입력받는다.
			st = new StringTokenizer(br.readLine().trim());
			startTime = Integer.parseInt(st.nextToken());
			after = Integer.parseInt(st.nextToken());
			
			// 3. 둘을 더하고 24로 나눈 나머지를 구해 출력한다.
			sb.append("#").append(tc).append(" ").append((startTime+after)%24);
			System.out.println(sb);
			sb.setLength(0);
		}
	}
}
