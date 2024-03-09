import java.io.*;
import java.util.*;

/**
 * 
 * @author JiYeon Sin
 * 
 * 1. 두 개의 숫자를 입력받는다.
 * 2. +, -, *, / 연산을 수행한 결과를 차례대로 출력한다.
 *
 */
public class Solution {
	public static BufferedReader br;
	public static StringTokenizer st;
	public static StringBuilder sb;
	
	public static int num1, num2;
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		// 1. 두 개의 숫자를 입력받는다.
		st = new StringTokenizer(br.readLine().trim());
		num1 = Integer.parseInt(st.nextToken());
		num2 = Integer.parseInt(st.nextToken());
		
		// 2. +, -, *, / 연산을 수행한 결과를 차례대로 출력한다.
		sb.append(num1+num2).append("\n").append(num1-num2).append("\n").append(num1*num2).append("\n").append(num1/num2);
		System.out.println(sb);
	}
}
