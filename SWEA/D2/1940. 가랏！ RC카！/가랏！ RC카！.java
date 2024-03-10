import java.io.*;
import java.util.*;

/**
 * 
 * @author JiYeon Sin
 * 
 * 1. 테스트 케이스의 개수를 입력받는다.
 * 2. 이동거리와 속력을 0으로 초기화한다.
 * 3. 명령어의 개수를 입력받고 개수만큼 반복한다.
 * 4. 명령어를 입력받는다.
 * 5. 가속명령을 입력받은 경우 속력을 입력만큼 증가시킨다.
 * 6. 감속명령을 입력받은 경우 속력을 입력받은 만큼 감소시킨다.
 *    속력은 0보다 작아질 수 없다.
 * 7. 현재 속력을 바탕으로 이동거리를 더한다.
 * 8. 결과를 출력한다.
 */
public class Solution {
	public static BufferedReader br;
	public static StringTokenizer st;
	public static StringBuilder sb;
	
	public static int testCase; // 테스트 케이스의 개수
	
	public static int commandCount; // 명령의 개수
	public static int distance, velocity; // 이동거리, 현재 속력
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		// 1. 테스트 케이스의 개수를 입력받는다.
		testCase = Integer.parseInt(br.readLine().trim());
		
		for(int tc = 1; tc <= testCase; tc++) {
			// 2. 이동거리와 속력을 0으로 초기화한다.
			distance = velocity = 0;
			
			// 3. 명령어의 개수를 입력받고 개수만큼 반복한다.
			commandCount = Integer.parseInt(br.readLine().trim());
			for(int commandIdx = 0; commandIdx < commandCount; commandIdx++) {
				// 4. 명령어를 입력받는다.
				st = new StringTokenizer(br.readLine().trim());
				
				int command = Integer.parseInt(st.nextToken());
				
				
				if(command == 1) {
					// 5. 가속명령을 입력받은 경우 속력을 입력만큼 증가시킨다.
					velocity += Integer.parseInt(st.nextToken());
				} else if(command == 2) {
					// 6. 감속명령을 입력받은 경우 속력을 입력받은 만큼 감소시킨다.
					velocity -= Integer.parseInt(st.nextToken());
					velocity = (velocity < 0) ? 0 : velocity; // 속력은 0보다 작아질 수 없다.
				}
				
				distance += velocity; // 7. 현재 속력을 바탕으로 이동거리를 더한다.
			}
			
			// 8. 결과를 출력한다.
			sb.append("#").append(tc).append(" ").append(distance);
			System.out.println(sb);
			sb.setLength(0);
		}
	}
}
