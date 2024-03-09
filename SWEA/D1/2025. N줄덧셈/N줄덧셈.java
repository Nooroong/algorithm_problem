import java.io.*;
import java.util.*;

/**
 * 
 * @author JiYeon Sin
 * 
 * 1. 합계를 저장할 변수를 선언하고 0으로 초기화한다.
 * 2. 숫자를 입력받는다.
 * 3. 1부터 입력받은 숫자까지 반복하며 합을 구한다.
 * 4. 구한 합을 출력한다.
 *
 */
public class Solution {
	public static BufferedReader br;
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		
		// 1. 합계를 저장할 변수를 선언하고 0으로 초기화한다.
		int sum = 0;
		
		// 2. 숫자를 입력받는다.
		int num = Integer.parseInt(br.readLine().trim());
		
		// 3. 1부터 입력받은 숫자까지 반복하며 합을 구한다.
		for(int n = 1; n <= num; n++) {
			sum += n;
		}
		
		// 4. 구한 합을 출력한다.
		System.out.println(sum);
	}
}
