import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static StringTokenizer st;

	private static int numberCount, order, numberArr[];

	public static void main(String[] args) throws IOException {
		// N, K 입력받기
		st = new StringTokenizer(br.readLine().trim());
		numberCount = Integer.parseInt(st.nextToken());
		order = Integer.parseInt(st.nextToken());

		// 숫자 배열 입력받기
		numberArr = new int[numberCount];
		st = new StringTokenizer(br.readLine().trim());
		for (int idx = 0; idx < numberCount; idx++) {
			numberArr[idx] = Integer.parseInt(st.nextToken());
		}

		// 정렬하기
		mergeSort(0, numberCount - 1);

		// 정답 출력
		System.out.println(numberArr[order - 1]);
	}

	// 병합 정렬
	private static void mergeSort(int startIdx, int endIdx) {
		// 아직 쪼갤 수 있다면
		if (startIdx < endIdx) {
			int midIdx = (startIdx + endIdx) / 2;

			// 반으로 쪼개고
			mergeSort(startIdx, midIdx);
			mergeSort(midIdx + 1, endIdx);

			// 합친다
			mergeSortedArr(startIdx, midIdx, endIdx);
		}

	}

	private static void mergeSortedArr(int startIdx, int midIdx, int endIdx) {
		// 정렬된 결과를 저장하는 임시 배열
		// 실제로 필요한 만큼만 배열을 할당한다.
		int[] sortedArr = new int[endIdx - startIdx + 1];

		int sortedArrIdx = 0;
		int leftArrIdx = startIdx;
		int rightArrIdx = midIdx + 1;

		// 오름차순 정렬
		while (leftArrIdx <= midIdx && rightArrIdx <= endIdx) {
			if (numberArr[leftArrIdx] <= numberArr[rightArrIdx]) {
				sortedArr[sortedArrIdx++] = numberArr[leftArrIdx++];
			} else {
				sortedArr[sortedArrIdx++] = numberArr[rightArrIdx++];
			}
		}

		while (leftArrIdx <= midIdx) {
			sortedArr[sortedArrIdx++] = numberArr[leftArrIdx++];
		}

		while (rightArrIdx <= endIdx) {
			sortedArr[sortedArrIdx++] = numberArr[rightArrIdx++];
		}

		// 원본 배열에 정렬된 값 복사
		for (int idx = 0; idx < sortedArr.length; idx++) {
			numberArr[startIdx + idx] = sortedArr[idx];
		}
	}
}
