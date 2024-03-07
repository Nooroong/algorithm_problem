import java.io.*;
import java.util.*;

/**
 * 
 * @author JiYeon Sin
 * 
 * 
 * 1. a와 b가 낸 값을 입력받는다.
 * 2. %연산자를 이용해 승패를 비교하고 결과를 출력한다.
 */
public class Solution {
	public static BufferedReader br;
	public static StringTokenizer st;
	
	public static int a, b;
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		
		// 1. a와 b가 낸 값을 입력받는다.
		st = new StringTokenizer(br.readLine().trim());
		a = Integer.parseInt(st.nextToken());
		b = Integer.parseInt(st.nextToken());
		
		
		// 2. %연산자를 이용해 승패를 비교하고 결과를 출력한다.
		if(a == (b%3 + 1))
			System.out.println("A");
		else
			System.out.println("B");
		
	}
}
