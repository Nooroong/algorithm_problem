import java.io.*;
import java.util.*;

/**
 * 
 * @author JiYeon Sin
 *
 */
public class Main {
	public static BufferedReader br;
	public static StringTokenizer st;
	public static StringBuilder sb;
	
	public static int[] belt; // 벨트 위의 초밥 정보
	
	public static int dishCount; // 접시의 수
	public static int numOfSushi; // 초밥의 가짓수
	public static int continuousEatNum; // 연속해서 먹는 접시의 개수
	public static int couponNum; // 쿠폰 번호
	
	public static boolean canUseCoupon; // 쿠폰 사용 가능 여부
	public static boolean couponUsed; // 쿠폰 사용 여부
	public static int[] ateSushi; // 초밥 종류별로 먹은 개수를 값으로 가진다.
	
	public static int curSushiKind = 0; // 현재 먹은 초밥의 종류 수
	public static int maxSushiKind = 0; // 최대로 먹을 수 있는 초밥 가짓수
	
	
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		int printCnt = 1;
		
		// 벨트에 놓인 접시 수, 초밥의 가짓수, 연속해서 먹는 접시 수, 쿠폰 번호를 입력받는다.
		st = new StringTokenizer(br.readLine().trim());
		dishCount = Integer.parseInt(st.nextToken());
		numOfSushi = Integer.parseInt(st.nextToken());
		continuousEatNum = Integer.parseInt(st.nextToken());
		couponNum = Integer.parseInt(st.nextToken());
		
		
		// 벨트에 놓인 초밥 접시의 정보를 입력받는다.
		belt = new int[dishCount];
		for(int idx = 0; idx < dishCount; idx++) {
			belt[idx] = Integer.parseInt(br.readLine().trim());
		}
		
		
		ateSushi = new int[numOfSushi + 1]; // 초밥은 1번부터 시작한다.
		
		
		
		// 초밥을 먹는 범위의 첫 인덱스와 끝 인덱스
		int searchLeftIdx = 0;
		int searchRightIdx = continuousEatNum-1;
		
		
		// 일단 가장 처음에 먹는 정보를 처리한다.
		canUseCoupon = true;
		couponUsed = false;
		for(int idx = searchLeftIdx; idx <= searchRightIdx; idx++) {
			int ateSushiNum = belt[idx]; // 지금 먹은 초밥의 종류
			
			// 새롭게 먹는 초밥 종류라면
			if(ateSushi[ateSushiNum]++ == 0) {
				curSushiKind++; // 종류 수 증가
			}
			
			// 먹은 초밥이 쿠폰 번호와 같으면 
			if(ateSushiNum == couponNum) {
				canUseCoupon = false; // 쿠폰을 사용할 수 없음
				couponUsed = false;
			}
		}
		
		// 쿠폰을 사용할 수 있으면 종류 +1
		if(canUseCoupon && !couponUsed) {
			curSushiKind++;
			couponUsed = true;
		}
		
		maxSushiKind = curSushiKind;
//		System.out.println(printCnt++ + ": " + curSushiKind);
		
		
		// 이제 줄줄이 초밥을 먹는다.
		while(true) {
			// 벨트 탐색 범위의 시작점 초밥 빼내기
			int oldSushi = belt[searchLeftIdx];
			
			// 빼낸 초밥의 먹은 개수가 1개라면
			if(ateSushi[oldSushi]-- == 1) {
				curSushiKind--; // 종류수 -1
				
				
				// 그러면서 빼낸 초밥이 쿠폰 번호에 해당하는 초밥이라면
				if(oldSushi == couponNum) {
					canUseCoupon = true;
					couponUsed = false;
				}
			}
			

			// 한 바퀴를 다 돌아야하기 때문에 나머지 연산자를 꼭 써줘야 한다.	
			searchLeftIdx = (searchLeftIdx + 1) % dishCount; // 시작점 +한 칸
			searchRightIdx = (searchRightIdx + 1) % dishCount; // 끝점 +한 칸
			
			
			// 벨트를 한 바퀴 다 돈 경우 탐색 종료
			if(searchLeftIdx == 0) break;
			
			
			
			int newSushi = belt[searchRightIdx]; // 초밥 한 칸 더 먹기
			
			// 지금 먹은 초밥을 먹은 개수가 이제까지 0개였다면(새롭게 먹음) 종류 수 +1
			if(ateSushi[newSushi]++ == 0) {
				curSushiKind++;
				
				
				// 새로 먹은 초밥이 쿠폰 번호하고 같다면
				if(newSushi == couponNum) {
					// 이전에 쿠폰을 사용했다면 종류 -1
					if(couponUsed) curSushiKind--;
					
					
					// 쿠폰을 사용할 수 없다.
					canUseCoupon = false;
					couponUsed = false;
				}
			}
			
			
			// 쿠폰을 사용할 수 있으면 종류 +1
			if(canUseCoupon && !couponUsed) {
				curSushiKind++;
				couponUsed = true;
			}
			
			// 최대 종류값 갱신
			if(curSushiKind > maxSushiKind) maxSushiKind = curSushiKind;
			
//			System.out.println(printCnt++ + ": " + curSushiKind);
		}
		
		
		System.out.println(maxSushiKind);
	}
}