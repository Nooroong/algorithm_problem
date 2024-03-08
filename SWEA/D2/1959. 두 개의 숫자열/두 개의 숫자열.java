import java.io.*;
import java.util.*;

/**
 * 
 * @author JiYeon Sin
 * 
 * 1. 테스트 케이스의 개수를 입력받고 반복한다.
 * 2. 최대값을 저장하는 변수를 초기화
 * 3. 두 숫자열의 길이를 입력받는다.
 * 4. 두 숫자열을 저장할 배열을 만들고 값을 입력받는다.
 * 5. 길이가 짧은 쪽의 숫자열을 긴쪽에 맞춰 한 칸씩 이동하면서 계산을 하고 최대값을 갱신한다.
 * 6. 최대값을 출력한다.
 */
public class Solution {
	public static BufferedReader br;
	public static StringTokenizer st;
	public static StringBuilder sb;
	
	public static int testCase; // 테스트 케이스의 개수
	
	public static int maxResult; // 최대값
	
	public static int aLen, bLen; // 두 숫자열의 길이 
	public static int[] aArr, bArr; // 두 숫자열 배열
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		
		// 1. 테스트 케이스의 개수를 입력받고 반복한다.
		testCase = Integer.parseInt(br.readLine().trim());
		
		for(int tc = 1; tc <= testCase; tc++) {
			maxResult = Integer.MIN_VALUE; // 2. 최대값을 저장하는 변수를 초기화
			
			// 3. 두 숫자열의 길이를 입력받는다.
			st = new StringTokenizer(br.readLine().trim());
			aLen = Integer.parseInt(st.nextToken());
			bLen = Integer.parseInt(st.nextToken());
			
			
			// 4. 두 숫자열을 저장할 배열을 만들고 값을 입력받는다.
			aArr = new int[aLen];
			bArr = new int[bLen];
			
			// 숫자열 a 입력받기
			st = new StringTokenizer(br.readLine().trim());
			for(int idx = 0; idx < aLen; idx++)
				aArr[idx] = Integer.parseInt(st.nextToken());
			
			// 숫자열 b 입력받기
			st = new StringTokenizer(br.readLine().trim());
			for(int idx = 0; idx < bLen; idx++)
				bArr[idx] = Integer.parseInt(st.nextToken());
			
			
			// 5. 길이가 짧은 쪽의 숫자열을 긴쪽에 맞춰 한 칸씩 이동하면서 계산을 하고 최대값을 갱신한다.
			if(aLen < bLen) {
				for(int startIdx = 0; startIdx <= bLen-aLen; startIdx++) {
					int curResult = 0;
					
					for(int idx = 0; idx < aLen; idx++) {
						curResult += aArr[idx] * bArr[startIdx+idx];
					}
					
					if(curResult > maxResult) maxResult = curResult;
					
				}
			} else {
				for(int startIdx = 0; startIdx <= aLen-bLen; startIdx++) {
					int curResult = 0;
					
					for(int idx = 0; idx < bLen; idx++) {
						curResult += bArr[idx] * aArr[startIdx+idx] ;
					}
					
					if(curResult > maxResult) maxResult = curResult;
					
				}
			}
			
			
			// 6. 최대값을 출력한다.
			sb.append("#").append(tc).append(" ").append(maxResult);
			System.out.println(sb);
			sb.setLength(0);
		}
	}
}
