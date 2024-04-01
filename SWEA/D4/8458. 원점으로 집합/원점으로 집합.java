import java.io.*;
import java.util.*;

/**
 * 
 * @author JiYeon Sin
 * 
 * 
 *
 */
public class Solution {
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;

	static int testCase;
	
	static int dotCount;
	static long curDistance, maxDistance;
	static boolean available;
	
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		testCase = Integer.parseInt(br.readLine().trim());
		for(int tc = 1; tc <= testCase; tc++) {
			available = true;
			
			// 격자점의 개수를 입력받는다.
			dotCount = Integer.parseInt(br.readLine().trim());
			
			// 첫 값 입력처리
			st = new StringTokenizer(br.readLine().trim());
			maxDistance = curDistance = getDistance(Integer.parseInt(st.nextToken()),
								   Integer.parseInt(st.nextToken()));
			
			// 각 격자점의 좌표를 입력받으면서 최대 길이를 찾는다.
			// 이때 거리가 모두 짝수이거나 홀수여야 한다.
			for(int idx = 1; idx < dotCount; idx++) {
				st = new StringTokenizer(br.readLine().trim());
				curDistance = getDistance(Integer.parseInt(st.nextToken()),
									   Integer.parseInt(st.nextToken()));
				
				if(curDistance%2 != maxDistance%2) {
					available = false;
				}
				
				maxDistance = Long.max(curDistance, maxDistance);
			}
			
			
			
			
			sb.append("#").append(tc).append(" ");
			
			// 이동 시간이 i일 때, 총 이동거리는 i*(i+1)/2가 된다.
			// 점의 이동 거리가 d 일때 d와 i의 관계는 i*(i+1)/2 >= d가 되어야 한다.
			// 이때 양변의 값은 둘 다 짝수이거나 둘 다 홀수여야 한다.
			if(available) {
				long time = timeOfDistance(maxDistance);
				if((time*(time+1)/2)%2 != maxDistance%2) {
					time += 1 + (time%2);
				}
				
				sb.append(time);
				
			} else {
				sb.append(-1);
			}
			
	
			System.out.println(sb);
			sb.setLength(0);
		}
	}
	
	
	
	// 격자점과 원점과의 거리를 반환하는 함수
	static long getDistance(long x, long y) {
		return Math.abs(x) + Math.abs(y);
	}
	
	// 거리에 대해 걸리는 시간
	static long timeOfDistance(long distance) {
		return (long)Math.ceil((Math.sqrt(1+8*distance)-1)/2);
	}

}
