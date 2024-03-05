import java.io.*;
import java.util.*;

/**
 * 
 * @author JiYeon Sin
 * 
 * 1. 화폐 단위, 각 단위별 개수를 저장하는 배열을 만든다.
 * 2. 테스트 케이스의 개수를 입력받아 그만큼 반복한다.
 * 3. 각 케이스마다 단위별 개수 배열을 생성한다.
 * 4. 거스름돈을 입력받는다.
 * 5. 모든 화폐단위에 대해 개수를 계산한다.
 *  5-1. (남은 잔돈 / 화폐 단위)를 해당 단위 개수에 담는다.
 *  5-2. 잔돈에서 지금 계산해낸 금액을 빼닌다.
 * 6. 출력한다.
 */
public class Solution {
	public static BufferedReader br;
	public static StringTokenizer st;
	public static StringBuilder sb;
	
	public static int testCase; // 테스트 케이스의 개수
	
	// 1. 화폐 단위, 각 단위별 개수를 저장하는 배열을 만든다.
	public static int[] money = { 50000, 10000, 5000, 1000, 500, 100, 50, 10 }; // 잔돈 종류
	public static int[] moneyCount; // 잔돈 별 개수

	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		// 2. 테스트 케이스의 개수를 입력받아 그만큼 반복한다.
		testCase = Integer.parseInt(br.readLine().trim());
		
		for(int tc = 1; tc <= testCase; tc++) {
			moneyCount = new int[money.length]; // 3. 각 케이스마다 단위별 개수 배열을 생성한다.

			int change = Integer.parseInt(br.readLine().trim()); // 4. 거스름돈을 입력받는다.
			
			
			// 5. 모든 화폐단위에 대해 개수를 계산한다.
			for(int idx = 0; idx < money.length; idx++) {
				moneyCount[idx] = change/money[idx]; // 5-1. (남은 잔돈 / 화폐 단위)를 해당 단위 개수에 담는다.
				change -= money[idx]*moneyCount[idx]; // 5-2. 잔돈에서 지금 계산해낸 금액을 빼닌다.
			}
			
			
			
			// 6. 출력한다.
			sb.append("#").append(tc).append("\n");
			for(int idx = 0; idx < moneyCount.length; idx++)
				sb.append(moneyCount[idx]).append(" ");
			
			System.out.println(sb);
			sb.setLength(0);
		}
	}
}
