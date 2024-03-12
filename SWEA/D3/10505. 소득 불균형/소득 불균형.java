import java.io.*;
import java.util.*;

/**
 * 
 * @author JiYeon Sin
 * 
 * 1. 테스트 케이스 개수를 입력받는다.
 * 2. 소득 정보의 수를 입력받는다.
 * 3. 소득 정보를 입력받아 배열에 저장하고 평균을 구한다.
 * 4. 배열의 모든 원소에 대해 평균 이하의 소득을 가진 사람들의 수를 구한다.
 * 5. 출력한다.
 *
 */
public class Solution {
	public static BufferedReader br;
	public static StringTokenizer st;
	public static StringBuilder sb;
	
	public static int testCase; // 테스트 케이스의 개수
	
	
	public static int salaryCount;
	public static double salaryAvg;
	public static int[] salary;
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		// 1. 테스트 케이스 개수를 입력받는다.
		testCase = Integer.parseInt(br.readLine().trim());
		
		for(int tc = 1; tc <= testCase; tc++) {
			// 2. 소득 정보의 수를 입력받는다.
			salaryCount = Integer.parseInt(br.readLine().trim());
			
			// 3. 소득 정보를 입력받아 배열에 저장하고 평균을 구한다.
			salary = new int[salaryCount];
			salaryAvg = 0.0;
			
			st = new StringTokenizer(br.readLine().trim());
			for(int idx = 0; idx < salaryCount; idx++) {
				salary[idx] = Integer.parseInt(st.nextToken());
				salaryAvg += salary[idx];
			}
			
			salaryAvg /= salaryCount;
			
			
			// 4. 배열의 모든 원소에 대해 평균 이하의 소득을 가진 사람들의 수를 구한다.
			int avgLessCount = 0;
			for(int idx = 0; idx < salaryCount; idx++) {
				if(salary[idx] <= salaryAvg) avgLessCount++;
			}
			
			// 5. 출력한다.
			sb.append("#").append(tc).append(" ").append(avgLessCount);
			System.out.println(sb);
			sb.setLength(0);
		}
	}
}
