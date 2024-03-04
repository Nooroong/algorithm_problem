import java.io.*;
import java.util.*;

/**
 * 
 * @author JiYeon Sin
 * 
 * 1. 테스트 케이스의 개수를 입력받는다.
 * 2. 시와 분을 입력받는다.
 * 3. 분끼리 더하고 자리올림을 한다.
 * 4. 시끼리 더하고 자리올림을 한다. 시는 1 이상 12 이하의 정수임에 유의한다.
 * 5. 결과를 출력한다.
 */
public class Solution {
	public static BufferedReader br;
	public static StringTokenizer st;
	public static StringBuilder sb;
	
	public static int testCase; // 테스트 케이스의 개수
	
	public static int h1, h2, m1, m2;
	public static int resultH, resultM;
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		// 1. 테스트 케이스의 개수를 입력받는다.
		testCase = Integer.parseInt(br.readLine().trim());
		
		for(int tc = 1; tc <= testCase; tc++) {
			resultH = resultM = 0;
			
			// 2. 시와 분을 입력받는다.
			st = new StringTokenizer(br.readLine().trim());
			h1 = Integer.parseInt(st.nextToken());
			m1 = Integer.parseInt(st.nextToken());
			h2 = Integer.parseInt(st.nextToken());
			m2 = Integer.parseInt(st.nextToken());
			
			// 3. 분끼리 더하고 자리올림을 한다.
			resultM = (m1+m2) % 60;
			resultH = (m1+m2) / 60;
			
			// 4. 시끼리 더하고 자리올림을 한다. 시는 1 이상 12 이하의 정수임에 유의한다.
			resultH += (h1+h2);
			if(resultH % 12 == 0) resultH = 12; // 시가 12의 배수인 경우 12를 출력
			else if(resultH > 12) resultH %= 12; // 12의 배수가 아니면서 12보다 큰 경우는 12의 나머지를 출력
			
			
			
			// 5. 결과를 출력한다.
			sb.append("#").append(tc).append(" ").append(resultH).append(" ").append(resultM);
			System.out.println(sb);
			sb.setLength(0);
		}
	}

}
