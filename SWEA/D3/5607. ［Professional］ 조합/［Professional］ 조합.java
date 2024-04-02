import java.io.*;
import java.util.*;

/**
 * 
 * @author JiYeon Sin
 * 
 * nCr = n!*((n-r)!*r!)^(-1)
 * 페르마의 소정리에서 a^(p-1) = 1, a^(p-2) = a^(-1)
 * 따라서 ((n-r)!*r!)^(-1) = ((n-r)!*r!)^(p-2) 가 된다.
 * 
 * overflow를 방지하기 위해 곱셈을 할 때마다 모듀럴 연산을 취해준다.
 * 
 * 1. 0부터 백만까지 팩토리얼을 구해서 배열이 미리 저장해놓는다.
 * 2. n과 r을 입력받는다.
 * 3. ((n-r)!*r!)^(-1)은 페르마의 소정리에 의해 ((n-r)!*r!)^(p-2)와 같다.
 *    분할정복으로 거듭제곱을 빠르게 계산한다.
 * 4. 조합 계산 결과를 출력한다.
 * 
 */
public class Solution {
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	
	static int testCase;
	static int n, r;
	static final int MOD = 1234567891;
	static long[] factoArr;
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		// 1. 0부터 백만까지 팩토리얼을 구해서 배열이 미리 저장해놓는다.
		factoArr = new long[1000001];
		factorial();
		
		testCase = Integer.parseInt(br.readLine().trim());
		for(int tc = 1; tc <= testCase; tc++) {
			// 2. n과 r을 입력받는다.
			st = new StringTokenizer(br.readLine().trim());
			n = Integer.parseInt(st.nextToken());
			r = Integer.parseInt(st.nextToken());
			
			// 3. ((n-r)!*r!)^(-1)은 페르마의 소정리에 의해 ((n-r)!*r!)^(p-2)와 같다.
			//    분할정복으로 거듭제곱을 빠르게 계산한다.
			long numerator = calcPow(factoArr[n-r] * factoArr[r] % MOD, MOD-2);
	
			// 4. 조합 계산 결과를 출력한다.
			sb.append("#").append(tc).append(" ").append((factoArr[n] * numerator) % MOD);
			System.out.println(sb);
			sb.setLength(0);
			
		}
		
		
	}
	
	
	private static void factorial() {
		factoArr[0] = factoArr[1] = 1;
		
		for(int i = 2; i <= 1000000; i++) {
			factoArr[i] = (factoArr[i-1] * i) % MOD;
		}
	}
	
	
	// 분할정복으로 거듭제곱을 빠르게 계산한다.
	// a^x = a^(x/2) * a^(x/2) 이고 a^0 = 1이다.
	private static long calcPow(long base, int index) {
		if(index == 0) return 1;
		
		long sqrt = calcPow(base, index/2) % MOD;
		
		if(index%2 == 0) {
			return sqrt * sqrt % MOD;
		} else {
			return ((sqrt * sqrt) % MOD * base) % MOD;
		}
	}
}
