import java.io.*;
import java.util.*;

/**
 * 
 * @author JiYeon Sin
 * DP를 통해 해결할 수 있다.
 *
 */
public class Main {
	public static BufferedReader br;
	public static StringTokenizer st;
	
	public static int[][] city; // 도시 정보 배열
	
	public static int x;
	public static int count;
	
	public static int[] memo;
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		
		
		// 1 <= x <= 10^6
		x = Integer.parseInt(br.readLine().trim());
		count = 0;
		memo = new int[x+1]; // 0, 1이 가장 작은 단위의 문제?
		
		
		
		// 그냥 2, 3으로 나누거나 1을 뺀 후 나누거나
		for(int idx = 2; idx < x+1; idx++) {
			if(idx % 6 == 0) memo[idx] = Math.min(memo[idx/3] + 1, memo[idx/2] + 1);
			else if(idx % 3 == 0) memo[idx] = memo[idx/3] + 1;
			else if(idx % 2 == 0) memo[idx] = memo[idx/2] + 1;
			else memo[idx] = memo[idx-1]+1;
			
			memo[idx] = Math.min(memo[idx], memo[idx-1] + 1);
		}
		
//		System.out.println(Arrays.toString(memo));
		System.out.println(memo[x]);
	}

}
