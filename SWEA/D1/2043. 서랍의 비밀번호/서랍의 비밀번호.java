import java.io.*;
import java.util.*;

/**
 * 
 * @author JiYeon Sin
 * 항상 p > k라는 얘기가 없으므로 경우를 나눠서 답을 찾아야 한다.
 * 
 * 1. p와 k를 입력받는다.
 * 2. 횟수를 구해 출력한다.
 *  2-1. p >= k인 경우 p-k+1을 출력한다.
 *  2-1. p < k인 경우 1001+p-k를 출력한다.
 *
 */
public class Solution {
	public static BufferedReader br;
	public static StringTokenizer st;
	public static StringBuilder sb;
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		// 1. p와 k를 입력받는다.
		st = new StringTokenizer(br.readLine().trim());
		int p = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		
		// 2. 횟수를 구해 출력한다.
		//  2-1. p >= k인 경우 p-k+1을 출력한다.
		//  2-1. p < k인 경우 1001+p-k를 출력한다.
		System.out.println((p >= k) ? p-k+1 : 1001+p-k);
	}
}
