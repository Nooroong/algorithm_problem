import java.io.*;
import java.util.*;

/**
 * 
 * @author JiYeon Sin
 * 
 * 1. 테스트 케이스 개수를 입력받는다.
 * 2. 최소 운동 시간, 최대 운동 시간, 이번주 운동 시간을 입력받는다.
 * 3. 이번주 운동 시간이 최대 운동시간보다 크다면 -1을 출력
 * 4. 3이 아닌 경우,
 *  4-1. 이번주 운동 시간이 최소 운동 시간 이상이라면 0 출력
 *  4-2. 이번주 운동 시간이 최소 운동 시간 미만이라면 최소-이번주 출력 
 *
 */
public class Solution {
	public static BufferedReader br;
	public static StringTokenizer st;
	public static StringBuilder sb;
	
	public static int testCase; // 테스트 케이스의 개수
	
	// 최소 운동 시간, 최대 운동 시간, 이번주 운동 시간, 남은 운동 시간
	public static int minTime, maxTime, curTime, restTime;
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		// 1. 테스트 케이스 개수를 입력받는다.
		testCase = Integer.parseInt(br.readLine().trim());
		
		for(int tc = 1; tc <= testCase; tc++) {
			// 2. 최소 운동 시간, 최대 운동 시간, 이번주 운동 시간을 입력받는다.
			st = new StringTokenizer(br.readLine().trim());
			minTime = Integer.parseInt(st.nextToken());
			maxTime = Integer.parseInt(st.nextToken());
			curTime = Integer.parseInt(st.nextToken());
			
			
			if(curTime > maxTime) {
				// 3. 이번주 운동 시간이 최대 운동시간보다 크다면 -1을 출력
				restTime = -1;
			}
			else { // 4. 3이 아닌 경우,
				if(curTime >= minTime) restTime = 0; // 4-1. 이번주 운동 시간이 최소 운동 시간 이상이라면 0 출력 
				else restTime = minTime - curTime; // 4-2. 이번주 운동 시간이 최소 운동 시간 미만이라면 최소-이번주 출력
			}
			
			sb.append("#").append(tc).append(" ").append(restTime);
			System.out.println(sb);
			sb.setLength(0);
		}
	}
}
