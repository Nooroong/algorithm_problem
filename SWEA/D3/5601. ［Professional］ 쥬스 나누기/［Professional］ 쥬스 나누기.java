
import java.io.*;
import java.util.*;

/**
 * 
 * @author JiYeon Sin
 * 모두가 최선의 전략을 쓴다 -> 모두가 1/n리터를 잔에 따른다.
 * 
 * 1. 테스트 케이스의 개수를 입력받는다.
 * 2. 사람의 수를 입력받는다.
 * 3. '1/사람 수'를 사람 수만큼 반복 출력한다.
 */
public class Solution {
	public static BufferedReader br;
	public static StringTokenizer st;
	public static StringBuilder sb;
	
	public static int testCase; // 테스트 케이스의 개수
	
	public static int humanCount; // 사람의 수
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		// 1. 테스트 케이스의 개수를 입력받는다.
		testCase = Integer.parseInt(br.readLine().trim());
		
		for(int tc = 1; tc <= testCase; tc++) {
			// 2. 사람의 수를 입력받는다.
			humanCount = Integer.parseInt(br.readLine().trim());
			
			// 3. '1/사람 수'를 사람 수만큼 반복 출력한다.
			sb.append("#").append(tc).append(" ");
			for(int human = 0; human < humanCount; human++) {
				sb.append("1/").append(humanCount).append(" ");
			}
			
			System.out.println(sb);
			sb.setLength(0);
		}
	}
}
