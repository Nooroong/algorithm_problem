import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * BOJ
 * @author eunwoo.lee
 * 
 * 1. 전체 무게를 입력받는다.
 * 2. 5kg 봉지 사용 가능 여부 확인 
 * 		while (5kg 개수>0)
 * 			5kg개수 : 전체 무게/5 부터 시작
 * 			만약 전체 무게 - 5*(5kg개수) 가 3의 배수라면 
 * 			전체 봉지 개수  = 5kg개수 + (전체 무게 - 5*(5kg개수))/3 으로 갱신 후 
 * 			break
 * 3. 만약 봉지 개수가 여전히 -1 이라면 (5kg를 사용할 수 없음)
 * 	만약 전체 무게%3==0 이면
 * 		전체 봉지 개수  = totalWeight/3
 * 4. 전체 봉지 무게 출력
 */

public class Main {
	
	public static BufferedReader br;
	public static StringTokenizer st;
	
	public static int totalWeight; // 전체 무게
	public static int sugarNum; // 봉지 개수
	
	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		
		// 1. 전체 무게를 입력받는다.
		st = new StringTokenizer(br.readLine().trim());
		totalWeight = Integer.parseInt(st.nextToken());
		
		// 2. 5kg 봉지 사용 가능 여부 확인
		sugarNum = -1;
		int num5kg = totalWeight/5;
		
		while(num5kg>0) {
			// 전체무게 - 5*(5kg개수) 가 3의 배수라면 
			if ((totalWeight-5*num5kg)%3==0) {
				sugarNum = num5kg + (totalWeight-5*num5kg)/3;
				break;
			}
			num5kg--;
		}
		
		// 3. 만약 봉지 개수가 여전히 -1 이라면 (5kg를 사용할 수 없음)
		if (sugarNum==-1) {
			if (totalWeight%3==0) {
				sugarNum = totalWeight/3;
			}
		}
		
		// 4. 전체 봉지 무게 출력
		System.out.println(sugarNum);
	}

}
