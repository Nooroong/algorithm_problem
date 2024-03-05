import java.io.*;
import java.util.*;

/**
 * 
 * @author JiYeon Sin
 * 
 * 1. 숫자를 입력받는다.
 * 2. 숫자가 0보다 큰 동안 반복한다.
 * 3. StringBuilder를 이용해 #을 하나씩 붙인다.
 * 4. 숫자를 1감소시킨다.
 * 5. while문을 빠져나오면 출력을 한다.
 */
public class Solution {
	public static BufferedReader br;
	public static StringTokenizer st;
	public static StringBuilder sb;
	
	public static int number; // 문제에서 주어지는 숫자
	
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		// 1. 숫자를 입력받는다.
		number = Integer.parseInt(br.readLine().trim());
		
		// 2. 숫자가 0보다 큰 동안 반복한다.
		while(number > 0) {
			sb.append("#"); // 3. StringBuilder를 이용해 #을 하나씩 붙인다.
			number--; // 4. 숫자를 1감소시킨다.
		}
		
		System.out.println(sb); // 5. while문을 빠져나오면 출력을 한다.
	}
}
