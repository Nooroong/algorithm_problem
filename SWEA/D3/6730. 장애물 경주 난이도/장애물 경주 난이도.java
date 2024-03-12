
import java.io.*;
import java.util.*;

/**
 * 
 * @author JiYeon Sin
 * 
 * 1. 테스트 케이스의 개수를 입력받는다.
 * 2. 최대 높이차 변수들을 적절히 초기화한다.
 * 3. 블록의 개수를 입력받는다.
 * 4. 4. 블록들의 높이를 입력받으면서 최대 높이차를 구한다.
 * 5. 0번째 블록부터 n-2번째 블록까지 높이차를 계산하고 최대값 변수를 갱신한다.
 * 6. 결과를 출력한다.
 *
 */
public class Solution {
	public static BufferedReader br;
	public static StringTokenizer st;
	public static StringBuilder sb;
	
	public static int testCase; // 테스트 케이스의 개수
	
	public static int blockCount; // 블록의 개수	
	public static int preBlockHeight, curBlockHeight; // 이전 블록의 높이, 현재 블록의 높이
	public static int maxUp, maxDown; // 올라갈 때 최대 높이 차, 내려갈 때 최대 높이 차
	
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		// 1. 테스트 케이스의 개수를 입력받는다.
		testCase = Integer.parseInt(br.readLine().trim());
		
		for(int tc = 1; tc <= testCase; tc++) {
			// 2. 최대 높이차 변수들을 적절히 초기화한다. 높이차의 최소값은 0
			maxUp = maxDown = 0;
			
			// 3. 블록의 개수를 입력받는다.
			blockCount = Integer.parseInt(br.readLine().trim());
			
			// 4. 블록들의 높이를 입력받으면서 최대 높이차를 구한다.
			st = new StringTokenizer(br.readLine().trim());
			preBlockHeight = Integer.parseInt(st.nextToken()); // 첫번째 블록의 높이를 담는다.
			
			// 1번째 블록부터 n-1번째 블록까지에 대해 반복 
			for(int idx = 1; idx < blockCount; idx++) {
				curBlockHeight = Integer.parseInt(st.nextToken()); // 현재 올라가려는 블록의 높이
				int heightDiff = Math.abs(preBlockHeight - curBlockHeight); // 두 블록의 높이 차
				
				// 올라가는지, 내려가는지 체크하고 최대값을 갱신
				if(preBlockHeight < curBlockHeight) { // 올라간다
					maxUp = (heightDiff > maxUp) ? heightDiff : maxUp; 
				} else { // 내려간다
					maxDown = (heightDiff > maxDown) ? heightDiff : maxDown;
				}
				
				preBlockHeight = curBlockHeight; // 다음 블록으로 넘어간다.
			}
			
			sb.append("#").append(tc).append(" ").append(maxUp).append(" ").append(maxDown);
			System.out.println(sb);
			sb.setLength(0);
		}
	}
}
