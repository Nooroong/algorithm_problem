import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Arrays;

public class Solution {
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		
		// 10번의 테스트 케이스를 받는다.
		int testCase = 10;
		
		
		int dumpCnt; // 덤프 횟수
		int []boxes; // 상자들의 높이
		int boxesLen; // 상자들의 수
		
		
		
		
		for(int tc = 1; tc <= testCase; tc++) {
			dumpCnt = Integer.parseInt(br.readLine().trim()); // 덤프 횟수 입력받기
			
			// 상자들의 높이 입력받기
			st = new StringTokenizer(br.readLine().trim()); // 기본은 whitespace를 기준으로 잘라준다. -> 잘라진 각 요소는 토큰
			boxesLen = st.countTokens(); // 토큰의 수 만큼 박스 배열의 길이를 설정
			boxes = new int[boxesLen];
			for(int idx = 0; st.hasMoreTokens(); idx++) { // 입력받은 문자열 박스 높이 정보를 정수로 바꿔서 배열에 넣어준다.
				boxes[idx] = Integer.parseInt(st.nextToken());
			}

			
			
			
			
			// 덤프 횟수만큼 박스 옮기기 과정을 반복한다.
			while(dumpCnt-- > 0) {
				// 상자들의 높이를 정렬한다.(오름차순)
				Arrays.sort(boxes);
				
				//가장 뒤의 인덱스(제일 높음)의 박스를 가장 앞의 인덱스(제일 낮음)로 옮겨준다.
				boxes[boxesLen-1]--;
                boxes[0]++;
			}
			
			
			// 마지막으로 다시 정렬을 해준다.
			Arrays.sort(boxes);
			
			// 가장 높은 곳과 가장 낮은 곳의 높이차를 출력.
			sb.append("#").append(tc).append(" ").append(boxes[boxesLen-1] - boxes[0]);
			System.out.println(sb);
			sb.setLength(0);
		}

	}
}