import java.io.*;
import java.util.*;

/**
 * 
 * @author JiYeon Sin
 * 1. 각 테스트 케이스 번호를 입력받는다.
 * 2. 길이가 8인 평문 숫자배열을 입력받는다.(plain)
 * 3. 매 반복마다 감소할 수를 기억하고 있는 decrease 변수를 만든다.
 * 4. decrease는 1~5를 반복한다.
 * 	    따라서 decrease = (decrease % 5) + 1 과 같이 값을 주면 된다.
 * 5. 일단 무한반복
 * 	6. 배열의 첫번째 원소의 값을 decrease만큼 감소시킨다.
 *     값이 0이하라면 값을 0으로 바꿔준다.
 * 	7. 첫번째 원소를 제외한 나머지 원소들의 값을 앞으로 당긴다.
 * 	8. 첫번째 원소의 값을 마지막 인덱스에 넣어준다.
 * 	9. 마지막 원소의 값이 0이하라면 반복을 끝낸다.
 * 	10. 아니라면 decrease 값을 조정해준다.
 * 11. 최종적으로 완성된 암호문을 출력한다.
 * 
 */
public class Solution {
	public static BufferedReader br;
	public static StringBuilder sb;
	public static StringTokenizer st;
	
	
	public static final int TEST_CASE = 10; // 테스트 횟수
	public static final int PLAIN_LEN = 8; // 평문 배열의 길이
	public static int[] plain = new int[PLAIN_LEN]; // 평문 배열
	public static int decrease; // 매 반복마다 감소하는 수
	
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		
		
		for(int tc = 1; tc  <= TEST_CASE; tc++) {
			// 1. 각 테스트 케이스 번호를 입력받는다.
			Integer.parseInt(br.readLine().trim());
			
			// 2. 길이가 8인 평문 숫자배열을 입력받는다.(plain)
			st = new StringTokenizer(br.readLine().trim());
			for(int idx = 0; idx < PLAIN_LEN; idx++) {
				plain[idx] = Integer.parseInt(st.nextToken());
			}
			
			// 3. 매 반복마다 감소할 수를 기억하고 있는 decrease 변수를 만든다.
			decrease = 1;
			
			// 5. 일단 무한반복
			while(true) {
				// 6. 배열의 첫번째 원소의 값을 decrease만큼 감소시킨다.
				// 값이 0이하라면 값을 0으로 바꿔준다.
				// 원소들을 이동시켜야 하므로 임시 변수에 담아준다.
				int temp = (plain[0]-decrease) <= 0 ? 0 : plain[0]-decrease;
				
				// 7. 첫번째 원소를 제외한 나머지 원소들의 값을 앞으로 당긴다.
				for(int idx = 1; idx < PLAIN_LEN; idx++) {
					plain[idx-1] = plain[idx];
				}
				

				plain[PLAIN_LEN-1] = temp; // 8. 첫번째 원소의 값을 마지막 인덱스에 넣어준다.
				
				if(plain[PLAIN_LEN-1] <= 0) break; // 9. 마지막 원소의 값이 0이하라면 반복을 끝낸다.
				
				decrease = (decrease % 5) + 1; // 10. 아니라면 decrease 값을 조정해준다.
			}
			
			
			// 11. 최종적으로 완성된 암호문을 출력한다.
			sb.append("#").append(tc).append(" ");
			for(int num : plain) sb.append(num).append(" ");
			System.out.println(sb);
			sb.setLength(0);
		}

	}

}
