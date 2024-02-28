import java.io.*;
import java.util.*;

/**
 * 
 * @author JiYeon Sin
 * 반대로 생각해서 동쪽의 사이트가 서쪽의 n개의 사이트 모두를 고르는 경우의 수를 생각하면 된다? (동쪽C서쪽)
 * 빠르게 구하기 위해 DP로 해결한다.
 */
public class Main {
	public static BufferedReader br;
	public static StringTokenizer st;
	
	public static int testCase; // 테스트 케이스의 개수
	
	public static int west, east; // 서쪽, 동쪽 사이트의 개수
	
	public static int[][] combi; // 조합의 수를 저장하기 위한 dp table
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		
		// 테스트 케이스를 입력받는다.
		testCase = Integer.parseInt(br.readLine().trim());
		
		for(int tc = 1; tc <= testCase; tc++) {
			// 동쪽과 서쪽의 사이트 개수를 입력받는다.
			st = new StringTokenizer(br.readLine().trim());
			west = Integer.parseInt(st.nextToken());
			east = Integer.parseInt(st.nextToken());
			
			
			combi = new int[east+1][west+1]; // dp table을 생성한다.
			
			// dp table을 채워나간다.
			for (int i = 0; i <= east; i++) {
				for (int j = 0, end = (i < west) ? i : west;
					 j <= end; j++) {
					if(j == 0 || j == i) combi[i][j] = 1; // nC0, nCn의 경우 1을 넣는다.
					else combi[i][j] = combi[i-1][j-1] + combi[i-1][j]; // nCk = n-1Ck-1 + n-1Ck
				}
			}
			
			
			System.out.println(combi[east][west]); // 동쪽C서쪽 값을 출력한다.
		}
	}

}
