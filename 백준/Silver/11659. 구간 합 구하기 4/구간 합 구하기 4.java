import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * @author JiYeon Sin
 * 
 * 1. 수의 개수, 합을 구해야 하는 횟수를 입력받는다.
 * 2. n개의 수를 입력받는다.
 * 3. 합을 구해야 하는 횟수만큼 반복을 진행한다.
 * 4. 합을 구해야하는 구간 sumStartIdx, sumEndIdx를 입력받는다.
 * 5. 합을 저장할 변수 sum을 선언한다.
 * 6. for문으로 구간을 돌면서 합을 구한다. ---> 당연히 시초가 난다.
 * 7. 출력한다.
 * 
 * ---------------------------------------------------------
 * 
 * <누적합(prefix sum)을 알고 이용하자>
 * 1. 수의 개수, 합을 구해야 하는 횟수를 입력받는다.
 * 2. n개의 수를 입력받는다.
 * 3. 수를 입력받으면서 해당 인덱스까지의 총합을 따로 배열에 저장해놓는다. (new!)
 * 4. 합을 구해야 하는 횟수만큼 반복을 진행한다.
 * 5. 합을 구해야하는 구간 sumStartIdx, sumEndIdx를 입력받는다.
 * 6. 누적합 배열을 이용하여 구간의 합을 구한다. (new!)
 *    (0부터 sumEndIdx까지의 합) - (0부터 sumStartIdx-1까지의 합) 
 *    이때 sumStartIdx가 1이라면 처음부터 sumEndIdx까지의 합을 구하라는 것이므로
 *    (0부터 sumStartIdx-1까지의 합)을 빼면 안 된다.
 * 7. 출력한다.
 * 
 * 이렇게 하면 메모리를 더 소모하는 대신 연산량을 줄일 수 있다.
 */
public class Main {
	public static BufferedReader br;
	public static StringTokenizer st;
	
	public static int numOfNumeric; // 수의 개수
	public static int sumCnt; // 합을 구해야 하는 횟수
	public static int[] numerics; // 숫자들
	public static int[] prefixSumOfNumerics; // numerics의 각 요소의 누적합을 저장하는 배열
	
	public static int sumStartIdx; // 합을 구해야 하는 구간의 시작 인덱스
	public static int sumEndIdx; // 합을 구해야 하는 구간의 마지막 인덱스
	
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		
		
		
		// 1. 수의 개수, 합을 구해야 하는 횟수를 입력받는다.
		st = new StringTokenizer(br.readLine().trim());
		numOfNumeric = Integer.parseInt(st.nextToken());
		sumCnt = Integer.parseInt(st.nextToken());
		
		// 2. n개의 수를 입력받는다.
		// 3. 수를 입력받으면서 해당 인덱스까지의 총합을 따로 배열에 저장해놓는다.
		numerics = new int[numOfNumeric];
		prefixSumOfNumerics = new int[numOfNumeric];
		
		st = new StringTokenizer(br.readLine().trim());
		
		for(int idx = 0; idx < numOfNumeric; idx++) {
			numerics[idx] = Integer.parseInt(st.nextToken());
			
			if(idx == 0) {
				prefixSumOfNumerics[idx] = numerics[idx];
			} else {
				prefixSumOfNumerics[idx] = prefixSumOfNumerics[idx-1] + numerics[idx];
			}
			
		}
		
		
		// 4. 합을 구해야 하는 횟수만큼 반복을 진행한다.
		for(int idx = 0; idx < sumCnt; idx++) {
			// 5. 합을 구해야하는 구간 sumStartIdx, sumEndIdx를 입력받는다.
			// 입력으로 들오오는 1부터 시작함에 주의하며,
			// 입력을 받은 값에서 1을 빼준다.
			st = new StringTokenizer(br.readLine().trim());
			sumStartIdx = Integer.parseInt(st.nextToken()) - 1;
			sumEndIdx = Integer.parseInt(st.nextToken()) - 1;
			
			// 6~7. 누적합 배열을 이용하여 구간의 합을 구하고 출력한다.
			int rtn = prefixSumOfNumerics[sumEndIdx];
			if(sumStartIdx != 0) {
				rtn -= prefixSumOfNumerics[sumStartIdx-1];
			}
			System.out.println(rtn);
		}
		
	}

}