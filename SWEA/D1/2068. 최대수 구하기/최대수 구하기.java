import java.io.*;
import java.util.*;

/**
 * 
 * @author JiYeon Sin
 * 
 * 1. 테스트 케이스의 개수를 입력받는다.
 * 2. 10개의 숫자를 입력받아 배열에 저장한다.
 * 3. 배열을 오름차순으로 정렬한다.
 * 4. 배열의 가장 마지막 인덱스의 값을 형식에 맞게 출력한다.
 */
public class Solution {
	public static BufferedReader br;
	public static StringTokenizer st;
	public static StringBuilder sb;
	
	public static int testCase; // 테스트 케이스의 개수
	
	public static int[] numberArr; // 숫자들을 담는 배열
	public static final int NUMBER_COUNT = 10; // 숫자들의 개수
	
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		
		// 1. 테스트 케이스의 개수를 입력받는다.
		testCase = Integer.parseInt(br.readLine().trim());
		
		for(int tc = 1; tc <= testCase; tc++) {
			numberArr = new int[NUMBER_COUNT];
			
			// 2. 10개의 숫자를 입력받아 배열에 저장한다.
			st = new StringTokenizer(br.readLine().trim());
			for(int idx = 0; idx < NUMBER_COUNT; idx++)
				numberArr[idx] = Integer.parseInt(st.nextToken());
			
			Arrays.sort(numberArr); // 3. 배열을 오름차순으로 정렬한다.

			
			// 4. 배열의 가장 마지막 인덱스의 값을 형식에 맞게 출력한다.
			sb.append("#").append(tc).append(" ").append(numberArr[NUMBER_COUNT-1]);
			System.out.println(sb);
			sb.setLength(0);
		}
	}
}
