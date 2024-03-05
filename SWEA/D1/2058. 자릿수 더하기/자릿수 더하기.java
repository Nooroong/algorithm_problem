import java.io.*;
import java.util.*;

/**
 * 
 * @author JiYeon Sin
 * 
 * 1. 숫자를 입력받는다.
 * 2. 숫자가 0 이하가 될 때까지 반복한다.
 * 3. 가중치가 가장 낮은 자리의 값을 누적한다.
 * 4. 입력받은 값을 10으로 나눠 다음 자리를 계산하도록 한다.
 * 5. 합을 출력한다.
 *
 */
public class Solution {
	public static BufferedReader br;
	
	public static int number;
	public static int sum = 0; // 각 자릿수의 합
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		
		// 1. 숫자를 입력받는다.
		number = Integer.parseInt(br.readLine().trim());
		
		// 2. 숫자가 0 이하가 될 때까지 반복한다.
		while(number > 0) {
			sum += number%10; // 3. 가중치가 가장 낮은 자리의 값을 누적한다.
			number /= 10; // 4. 입력받은 값을 10으로 나눠 다음 자리를 계산하도록 한다.
		}
		
		
		System.out.println(sum); // 5. 합을 출력한다.
	}
}
