import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Arrays;
import java.util.stream.Stream;

/**
 * 
 * @author JiYeon Sin
 * 1. 10번의 테스트 케이스를 받는다.
 * 2. 첫줄에 덤프 횟수, 다음 줄에 상자들의 높이를 받는다.
 * 3. 상자들의 높이를 내림차순으로 정렬한다.
 * 4. 가장 앞의 인덱스의 박스를 가장 마지막 인덱스로 옮겨준다.
 * 5. 덤프 횟수만큼 3~4의 과정을 반복한다.
 * 6. 마지막으로 다시 정렬을 해준다.
 * 7. 가장 높은 곳과 가장 낮은 곳의 높이차를 반환한다.
 * 
 */

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
				// 상자들의 높이를 병합정렬로 내림차순 정렬한다.
				MergeSortDESC(boxes, 0, boxesLen-1);
				
				//가장 앞의 인덱스(제일 높음)의 박스를 가장 마지막 인덱스(제일 낮음)로 옮겨준다.
				boxes[0]--;
				boxes[boxesLen-1]++;
			}
			
			
			// 마지막으로 다시 정렬을 해준다.
			MergeSortDESC(boxes, 0, boxesLen-1);
			
			// 가장 높은 곳과 가장 낮은 곳의 높이차를 출력.
			sb.append("#").append(tc).append(" ").append(boxes[0] - boxes[boxesLen-1]);
			System.out.println(sb);
			sb.setLength(0);
		}

	}
	
	
	
	
	// 대충 병합정렬 해주는 코드
	// 파라미터: 병합정렬 대상이 되는 배열, 가장 왼쪽의 인덱스, 가장 오른쪽의 인덱스
	public static void MergeSortDESC(int []arr, int left, int right) {
		int mid;
		if(left < right) { // 배열을 반으로 나눌 수 있다면
			mid = (left+right) / 2;
			MergeSortDESC(arr, left, mid); // 반갈 1
			MergeSortDESC(arr, mid+1, right); // 반갈 2
			MergeTwoArea(arr, left, mid, right); // 정렬하며 합치기
		}
	}
	
	
	// 대충 반으로 나눠진 두 배열을 모으면서 정렬해주는 코드
	public static void MergeTwoArea(int []arr, int left, int mid, int right) {
		int []sortedArr = new int[right+1]; // 임시적으로 정렬된 결과를 담는 배열
		int sortedIdx = left; // 임시 배열의 인덱스
		int leftArrIdx = left; // 반으로 나눠진 배열 중 왼쪽 배열의 인덱스
		int rightArrIdx = mid+1; // 반으로 나눠진 배열 중 오른쪽 배열의 인덱스
		
		// 반으로 나눠진 배열이 2개 있다.
		// 두 배열 각각에서 앞에서부터 값을 비교하고
		// 정렬 목적에 맞는 값부터 임시 배열에 차곡차곡 담아준다.
		while(leftArrIdx <= mid && rightArrIdx <= right) {
			if(arr[leftArrIdx] > arr[rightArrIdx]) {
				sortedArr[sortedIdx++] = arr[leftArrIdx++];
			} else {
				sortedArr[sortedIdx++] = arr[rightArrIdx++];
			}
		}
		
		// 위의 과정을 거치면 무조건 한쪽 배열의 값은 임시 배열에 다 들어가게 된다.
		// 그럼 남은 배열의 값들은 임시 배열에 넣어주면 된다.
		if(leftArrIdx > mid) {
			while(rightArrIdx <= right) {
				sortedArr[sortedIdx++] = arr[rightArrIdx++];
			}
		} else {
			while(leftArrIdx <= mid) {
				sortedArr[sortedIdx++] = arr[leftArrIdx++];
			}
		}
		
		
		// 임시 배열에 들어있는 정렬된 값을
		// 원래 배열에 그대로 복사해준다.
		for(int idx = left; idx <= right; idx++) {
			arr[idx] = sortedArr[idx];
		}
	}

}