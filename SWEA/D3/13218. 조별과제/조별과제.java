import java.io.*;
import java.util.*;

/**
 * 
 * @author JiYeon Sin
 * 3명 이상으로 구성된 조의 수를 최대화 -> 한 조에 3명씩 넣으면 된다.
 * 
 * 1. 테스트 케이스의 수를 입력받는다.
 * 2. 학생들의 수를 입력받는다.
 * 3. 학생 수를 3으로 나눈 몫을 출력한다.
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
		
		// 1. 테스트 케이스의 수를 입력받는다.
		testCase = Integer.parseInt(br.readLine().trim());
		
		for(int tc = 1; tc <= testCase; tc++) {
			// 2. 학생들의 수를 입력받는다.
			int studentCount = Integer.parseInt(br.readLine().trim());
			
			// 3. 학생 수를 3으로 나눈 몫을 출력한다.
			sb.append("#").append(tc).append(" ").append(studentCount/3);
			System.out.println(sb);
			sb.setLength(0);
		}
	}
}
