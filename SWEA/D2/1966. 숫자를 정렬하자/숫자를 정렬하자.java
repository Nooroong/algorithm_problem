import java.io.*;
import java.util.*;

/**
 * 
 * @author JiYeon Sin
 * 
 * 1. 테스트 케이스 개수를 입력받고 반복한다.
 * 2. 숫자열의 길이를 입력받는다.
 * 3. 숫자열을 저장할 길이가 n인 배열을 만든다.
 * 4. 숫자열을 입력받아 배열에 저장한다.
 * 5. 배열을 정렬한다.
 * 6. 배열의 값을 형식에 맞춰 출력한다.
 *
 */
public class Solution {
	public static BufferedReader br;
	public static StringTokenizer st;
	public static StringBuilder sb;
	
	public static int testCase; // 테스트 케이스의 개수
	
	public static int length; // 숫자열의 길이
	public static int[] arr; // 숫자열을 저장할 배열
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		// 1. 테스트 케이스 개수를 입력받고 반복한다.
		testCase = Integer.parseInt(br.readLine().trim());
		
		for(int tc = 1; tc <= testCase; tc++) {
			// 2. 숫자열의 길이를 입력받는다.
			length = Integer.parseInt(br.readLine().trim());
			
			// 3. 숫자열을 저장할 길이가 n인 배열을 만든다.
			arr = new int[length];
			
			// 4. 숫자열을 입력받아 배열에 저장한다.
			st = new StringTokenizer(br.readLine().trim());
			for(int idx = 0; idx < length; idx++)
				arr[idx] = Integer.parseInt(st.nextToken());
			
			Arrays.sort(arr); // 5. 배열을 정렬한다.
			
			
			// 6. 배열의 값을 형식에 맞춰 출력한다.
			sb.append("#").append(tc).append(" ");
			for(int idx = 0; idx < length; idx++)
				sb.append(arr[idx]).append(" ");
			System.out.println(sb);
			
			sb.setLength(0);
		}
	}
}
