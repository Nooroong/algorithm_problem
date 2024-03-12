
import java.io.*;
import java.util.*;

/**
 * 
 * @author JiYeon Sin
 * 모든 변의 길이가 같은 평행사변형 == 정사각형
 * 
 * 1. 테스트케이스의 개수를 입력받는다.
 * 2. 변의 길이를 입력받는다.
 * 3. 형식에 맞게 n*n을 출력한다.
 *
 */
public class Solution {
	public static BufferedReader br;
	public static StringTokenizer st;
	public static StringBuilder sb;
	
	public static int testCase; // 테스트 케이스의 개수
	
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		// 1. 테스트케이스의 개수를 입력받는다.
		testCase = Integer.parseInt(br.readLine().trim());
		
		for(int tc = 1; tc <= testCase; tc++) {
			// 2. 변의 길이를 입력받는다.
			int len = Integer.parseInt(br.readLine().trim());
			
			// 3. 형식에 맞게 n*n을 출력한다.
			sb.append("#").append(tc).append(" ").append(len*len);
			System.out.println(sb);
			sb.setLength(0);
		}
	}
}
