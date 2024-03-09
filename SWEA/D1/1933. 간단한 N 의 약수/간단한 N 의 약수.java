import java.io.*;
import java.util.*;

/**
 * 
 * @author JiYeon Sin
 * 
 * 1. 정수를 입력받는다.
 * 2. 1부터 n까지 n과 나눴을 때 나누어 떨어지는 수를 출력한다.
 *
 */
public class Solution {
	public static BufferedReader br;
	public static StringTokenizer st;
	public static StringBuilder sb;
	
	public static int num;
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		// 1. 정수를 입력받는다.
		num = Integer.parseInt(br.readLine().trim());
		
		// 2. 1부터 n까지 n과 나눴을 때 나누어 떨어지는 수를 출력한다.
		for(int n = 1; n <= num; n++) {
			if(num % n == 0) {
				sb.append(n).append(" ");
			}
		}
		
		System.out.println(sb);
	}
}
