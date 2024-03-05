import java.io.*;
import java.util.*;

/**
 * 
 * @author JiYeon Sin
 * 
 * 1. 테스트 케이스의 개수를 입력받는다.
 * 2. 10개의 수를 입력받아 길이가 10인 배열에 저장한다.
 * 3. 배열을 정렬한다.
 * 4. 배열의 1번 인덱스부터 8번 인덱스까지 요소들의 합을 구한다.
 * 5. 합에 대한 평균을 구해 출력한다.(소수점 첫째 자리에서 반올림)
 */
public class Solution {
	public static BufferedReader br;
	public static StringTokenizer st;
	public static StringBuilder sb;
	
	public static int testCase; // 테스트 케이스의 개수
	
	public static int[] numberArr; // 10개의 숫자를 담을 배열
	public static final int NUMBER_COUNT = 10;
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		// 1. 테스트 케이스의 개수를 입력받는다.
		testCase = Integer.parseInt(br.readLine().trim());
		
		for(int tc = 1; tc <= testCase; tc++) {
			// 2. 10개의 수를 입력받아 길이가 10인 배열에 저장한다.
			numberArr = new int[NUMBER_COUNT];
			st = new StringTokenizer(br.readLine().trim());
			for(int idx = 0; idx < NUMBER_COUNT; idx++) {
				numberArr[idx] = Integer.parseInt(st.nextToken());
			}
			
			Arrays.sort(numberArr); // 3. 배열을 정렬한다.
			
			// 4. 배열의 1번 인덱스부터 8번 인덱스까지 요소들의 합을 구한다.
			double sum = 0;
			for(int idx = 1; idx < NUMBER_COUNT-1; idx++) {
				sum += numberArr[idx];
			}
			
			
			// 5. 합에 대한 평균을 구해 출력한다.(소수점 첫째 자리에서 반올림)
			int avg = (int)Math.round(sum/(NUMBER_COUNT-2));
			
			sb.append("#").append(tc).append(" ").append(avg);
			System.out.println(sb);
			sb.setLength(0);
		}
	}

}
