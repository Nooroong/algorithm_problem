import java.io.*;
import java.util.*;

/**
 * 
 * @author JiYeon Sin
 * 
 * 1. 테스트 케이스 개수를 입력받는다.
 * 2. 첫 번째 날짜와 두번쨰 날짜를 입력받는다.
 * 3. 시작 달의 일과 마지막 달의 일을 더한다. (시작과 마지막이 같은 경우 생각)
 * 4. 나머지 중간에 낀 달의 일수를 더한다.
 * 5. 결과를 출력한다.
 *
 */
public class Solution {
	public static BufferedReader br;
	public static StringTokenizer st;
	public static StringBuilder sb;
	
	public static int testCase; // 테스트 케이스의 개수
	
	// 월 별 날짜 수
	public static int[] days = { 0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
	
	public static int fromMonth, toMonth;
	public static int fromDay, toDay;
	public static int dDay;
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		// 1. 테스트 케이스 개수를 입력받는다.
		testCase = Integer.parseInt(br.readLine().trim());
		
		for(int tc = 1; tc <= testCase; tc++) {
			// 2. 첫 번째 날짜와 두번쨰 날짜를 입력받는다.
			st = new StringTokenizer(br.readLine().trim());
			fromMonth = Integer.parseInt(st.nextToken());
			fromDay = Integer.parseInt(st.nextToken());
			toMonth = Integer.parseInt(st.nextToken());
			toDay = Integer.parseInt(st.nextToken());
			
			// 3. 시작 달의 일과 마지막 달의 일을 더한다. (시작과 마지막이 같은 경우 생각)
			dDay = (toMonth > fromMonth) ? toDay + (days[fromMonth]-fromDay+1) : toDay-fromDay+1;
			
			// 4. 나머지 중간에 낀 달의 일수를 더한다.
			for(int m = fromMonth+1; m < toMonth; m++) {
				dDay += days[m];
			}
			
			// 5. 결과를 출력한다.
			sb.append("#").append(tc).append(" ").append(dDay);
			System.out.println(sb);
			sb.setLength(0);
		}
	}
}
