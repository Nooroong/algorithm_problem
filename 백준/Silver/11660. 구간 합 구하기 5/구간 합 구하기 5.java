import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 
 * @author JiYeon Sin
 * (x1, x2)를 좌측상단, (y1, y2)를 우측하단으로 하는
 * 사각형 영역의 합을 출력해야 한다.
 * 
 * 각 요소는 0,0부터 자신의 위치까지의 사각형 영역의 합을 누적합으로 갖는다.
 * 현재 요소의 좌표가 (x, y)라면 누적합은 다음과 같이 구한다.
 * (x, y-1)의 누적합 + (x-1, y)의 누적합 - (X-1, Y-1)의 누적합 + 현 위치의 값
 * 
 * 1. 표의 크기와 합을 구해야 하는 횟수를 입력받는다.
 * 2. 표의 내용물을 입력받는다.
 * 3. 입력을 받으면서 현재 요소의 누적합을 구한다.
 * 4. 합을 구하는 횟수만큼 반복한다.
 * 5. 네 개의 정수(좌표 정보)를 입력받는다.
 * 6. 위의 공식을 적절히 활용하여 방식으로 해당 영역의 합을 구한다. 
 * 7. 구한 합을 출력한다.
 */
public class Main {
	public static BufferedReader br;
	public static StringTokenizer st;
	
	public static int tableSize; // 표의 크기
	public static int sumCount; // 합을 구해야 하는 횟수
	
	public static int[][] table; // 표
	public static int[][] tablePrefixSum; // table의 각 행에 대해서만 누적합을 저장
	
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		
		
		// 1. 표의 크기와 합을 구해야 하는 횟수를 입력받는다.
		st = new StringTokenizer(br.readLine().trim());
		tableSize = Integer.parseInt(st.nextToken());
		sumCount = Integer.parseInt(st.nextToken());
		
		// 2. 표의 내용물을 입력받는다.
		table = new int[tableSize][tableSize];
		tablePrefixSum = new int[tableSize][tableSize];
		
		for(int rowIdx = 0; rowIdx < tableSize; rowIdx++) {
			st = new StringTokenizer(br.readLine().trim());
			
			for(int colIdx = 0; colIdx < tableSize; colIdx++) {
				table[rowIdx][colIdx] = Integer.parseInt(st.nextToken());
				
				// 3. 입력을 받으면서 현재 요소의 누적합을 구한다.
				// (x, y-1)의 누적합 + (x-1, y)의 누적합 - (X-1, Y-1)의 누적합 + 현 위치의 값
				int tempPrefix = table[rowIdx][colIdx];
				if((rowIdx-1 >= 0) && (colIdx-1 >= 0)) {
					tempPrefix -= tablePrefixSum[rowIdx-1][colIdx-1];
					tempPrefix += tablePrefixSum[rowIdx][colIdx-1];
					tempPrefix += tablePrefixSum[rowIdx-1][colIdx];
				} else if(colIdx - 1 >= 0) {
					tempPrefix += tablePrefixSum[rowIdx][colIdx-1];
				} else if(rowIdx - 1 >= 0) {
					tempPrefix += tablePrefixSum[rowIdx-1][colIdx];
				}
				tablePrefixSum[rowIdx][colIdx] = tempPrefix;
			}
		}
		
		
		
		// 4. 합을 구하는 횟수만큼 반복한다.
		for(int cnt = 0; cnt < sumCount; cnt++) {
			int sum = 0; // 출력해야하는 값(네모 영역의 합)
			
			// 5. 네 개의 정수(좌표 정보)를 입력받는다.
			// 예제에서는 인덱스가 1부터 시작함을 볼 수 있다.
			// 따라서 실제로는 1을 빼줘야 한다.
			int x1, y1, x2, y2;
			st = new StringTokenizer(br.readLine().trim());
			x1 = Integer.parseInt(st.nextToken()) - 1;
			y1 = Integer.parseInt(st.nextToken()) - 1;
			x2 = Integer.parseInt(st.nextToken()) - 1;
			y2 = Integer.parseInt(st.nextToken()) - 1;
			 
			
			// 6. 위의 공식을 적절히 활용하여 방식으로 해당 영역의 합을 구한다. 
			sum = tablePrefixSum[x2][y2];
			if((x1-1 >= 0) && (y1-1 >= 0)) {
				sum += tablePrefixSum[x1-1][y1-1];
				sum -= tablePrefixSum[x2][y1-1];
				sum -= tablePrefixSum[x1-1][y2];
			} else if(y1 - 1 >= 0) {
				sum -= tablePrefixSum[x2][y1-1];;
			} else if(x1 - 1 >= 0) {
				sum -= tablePrefixSum[x1-1][y2];
			}
			
			// 7. 구한 합을 출력한다.
			System.out.println(sum);
		}
	}

}

