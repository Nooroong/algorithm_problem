import java.io.*;
import java.util.*;

/**
 * 
 * @author JiYeon Sin
 * 2^0 ~ 2^n을 구한다.
 * 
 * 1. 지수를 입력받는다.
 * 2. 밑을 2로 설정한다.
 * 3. 2^0 ~ 2^n을 반복문으로 계산 후 출력한다.
 *    거듭제곱은 Math.pow로 구할 수 있다.(double을 반환함에 주의)
 */
public class Solution {
	public static BufferedReader br;
	public static StringBuilder sb;
	
	public static int index; // 지수
	public static int base; // 밑
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		index = Integer.parseInt(br.readLine().trim()); // 1. 지수를 입력받는다.
		base = 2; // 2. 밑을 2로 설정한다.
		
		
		// 3. 2^0 ~ 2^n을 반복문으로 계산 후 출력한다.
		for(int idx = 0; idx <= index; idx++) {
			sb.append((int)Math.pow(base, idx)).append(" ");
		}
		
		System.out.println(sb);
	}
}
