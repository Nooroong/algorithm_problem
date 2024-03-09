import java.io.*;
import java.util.*;

/**
 * 
 * @author JiYeon Sin
 * 
 * 1. 테스트 케이스의 개수를 입력받는다.
 * 2. a사 요금, b사 기본요금, b사 기본요금 사용량, b사 요금, 종민이의 사용량을 입력받는다.
 * 3. a사의 요금을 계산한다.
 * 4. b사의 요금을 계산한다.
 *  4-1. 사용량이 기본 사용량 이하인 경우는 기본 요금만 낸다.
 *  4-2. 아닌 경우 (사용량-기본 사용량)*요금 + 기본 요금을 내야한다.
 * 5. 더 적은 요금을 형식에 맞게 출력한다.
 *
 */
public class Solution {
	public static BufferedReader br;
	public static StringTokenizer st;
	public static StringBuilder sb;
	
	public static int testCase; // 테스트 케이스의 개수
	
	public static int aPrice, bPrice; // 각 회사의 수도요금
	public static int aChargePerLiter, bChargePerLiter; // 각 회사의 리터 당 요금
	public static int basicUsage, basicPrice; // B사의 기본 사용량, 기본 요금
	public static int waterUsed; // 종민이의 사용량
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		// 1. 테스트 케이스의 개수를 입력받는다.
		testCase = Integer.parseInt(br.readLine().trim());
		
		for(int tc = 1; tc <= testCase; tc++) {
			// 2. a사 요금, b사 기본요금, b사 기본요금 사용량, b사 요금, 종민이의 사용량을 입력받는다.
			st = new StringTokenizer(br.readLine().trim());
			aChargePerLiter = Integer.parseInt(st.nextToken());
			basicPrice = Integer.parseInt(st.nextToken());
			basicUsage = Integer.parseInt(st.nextToken());
			bChargePerLiter = Integer.parseInt(st.nextToken());
			waterUsed = Integer.parseInt(st.nextToken());
			
			aPrice = waterUsed * aChargePerLiter; // 3. a사의 요금을 계산한다.
			
			// 4. b사의 요금을 계산한다.
			if(waterUsed <= basicUsage) {
				bPrice = basicPrice; // 4-1. 사용량이 기본 사용량 이하인 경우는 기본 요금만 낸다.
			} else {
				// 4-2. 아닌 경우 (사용량-기본 사용량)*요금 + 기본 요금을 내야한다.
				bPrice = (waterUsed-basicUsage)*bChargePerLiter + basicPrice; 
			}
			
			// 5. 더 적은 요금을 형식에 맞게 출력한다.
			sb.append("#").append(tc).append(" ").append((aPrice < bPrice) ? aPrice : bPrice);
			System.out.println(sb);
			sb.setLength(0);
		}
	}
}
