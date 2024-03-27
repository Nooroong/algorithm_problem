import java.util.*;
import java.io.*;

/**
 * 
 * @author JiYeon Sin
 * 이진탐색으로 풀어보기
 * 
 * 1. 수열의 길이를 입력받는다.
 * 2. 수열을 입력받아 배열에 저장한다.
 * 3. 수열의 첫번째 값에 대해 먼저 처리를 해준다.
 * 4. 수열의 나머지 각 숫자에 대해 dp table 상에서의 위치를 찾아준다.
 *  4-1. 각 위치의 최소값을 갱신해준다.
 *  4-2. 현재 값이 끝에 새롭게 추가되는 경우 임시(?) LIS 길이가 1 증가된다.
 * 5. dp table의 유효한 값의 길이를 형식에 맞게 출력한다.
 */
public class Solution {
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;
	
	static int testCase; // 테스트 케이스의 개수
	
	static int numSeqLen; // 수열의 길이
	static int[] numSeq; // 테케마다 주어지는 수열
	
	static int[] dpTable; // 각 길이의 마지막 위치에 올 수 있는 최소값을 담는 dp table
	static int dpEndIdx; // dp table에서 유효한 값이 있는 마지막 인덱스
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		testCase = Integer.parseInt(br.readLine().trim());
		for(int tc = 1; tc <= testCase; tc++) {
			// 1. 수열의 길이를 입력받는다.
			numSeqLen = Integer.parseInt(br.readLine().trim());
			
			// 각종 할당 및 초기화
			numSeq = new int[numSeqLen];
			dpTable = new int[numSeqLen];
			
			// 2. 수열을 입력받아 배열에 저장한다.
			st = new StringTokenizer(br.readLine().trim());
			for(int idx = 0; idx < numSeqLen; idx++) {
				numSeq[idx] = Integer.parseInt(st.nextToken());
			}
			
			// 3. 수열의 첫번째 값에 대해 먼저 처리를 해준다.
			dpTable[0] = numSeq[0];
			dpEndIdx = 0;
			
			
			// 4. 수열의 나머지 각 숫자에 대해 dp table 상에서의 위치를 찾아준다.
			for(int idx = 1; idx < numSeqLen; idx++) {
				int targetIdx = binarySearch(0, dpEndIdx, idx);
				
				// 4-1. 각 위치의 최소값을 갱신해준다.
				if(numSeq[idx] < dpTable[targetIdx]) {
					dpTable[targetIdx] = numSeq[idx];
					
				} else if(numSeq[idx] > dpTable[targetIdx]) {
					dpTable[targetIdx+1] = numSeq[idx];
					
					// 4-2. 현재 값이 끝에 새롭게 추가되는 경우 임시(?) LIS 길이가 1 증가된다. 
					if(targetIdx == dpEndIdx) {
						dpEndIdx++;
					}
					
				}
			}
			
			// 5. dp table의 유효한 값의 길이를 형식에 맞게 출력한다.
			sb.append("#").append(tc).append(" ").append(dpEndIdx+1);
			System.out.println(sb);
			sb.setLength(0);
		}
		
		
	}
	
	// 파라미터: 탐색의 시작점과 끝점
	static int binarySearch(int left, int right, int seqIdx) {
		int mid = (left+right) / 2;
		
		while(left < right) {
			if(numSeq[seqIdx] <= dpTable[mid]) {
				right = mid-1;
			} else {
				left = mid+1;
			}
			
			mid = (left+right) / 2;
		}
		
		// left와 right가 만나는 지점을 반환한다.
		return mid;
	}
}
