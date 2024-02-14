import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * BOJ
 * @author eunwoo.lee
 * 
 * <zSearch>
 * 1. 기저 조건
 * 	반으로 쪼개다 배열의 사이즈가 1이 되면 return
 * 3. 재귀호출
 * 	계속 쪼개면서 target이 속한 사분면을 기준으로 count값을 더해준다.
 * 
 * <main>
 * 1. 배열의 크기, 대상 rowIdx, 대상 colIdx를 입력받는다.
 * 2. 재귀 시작
 * 3. 정답 출력
 */

public class Main {
	
	public static BufferedReader br;
	public static StringBuilder sb;
	public static StringTokenizer st;
	
	public static int size, targetRowIdx, targetColIdx;
	public static int count; // 탐색 순서 기록할 변수

	public static void main(String[] args) throws Exception{
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		// 1. 배열의 크기, 대상 rowIdx, 대상 colIdx를 입력받는다.
		st = new StringTokenizer(br.readLine().trim());
		size = Integer.parseInt(st.nextToken());
		targetRowIdx = Integer.parseInt(st.nextToken());
		targetColIdx = Integer.parseInt(st.nextToken());
		
		// 2. 재귀시작
		count = 0;
		int arraySize = (int) Math.pow(2, size);
		zSearch(arraySize, targetRowIdx, targetColIdx);
		 
		// 3. 정답 출력
		System.out.println(count);

	}
	
	public static void zSearch(int size, int targetRIdx, int targetCIdx) {
		// 1. 기저 조건
		// 반으로 쪼개다 배열의 사이즈가 1이 되면 return
		if (size==1)
			return;
		
		// 2. 재귀 호출
		// target이 왼쪽 상단에 속해있을 경우
		if (targetRIdx<size/2 && targetCIdx<size/2) {
			zSearch(size/2, targetRIdx, targetCIdx);
		}
		// target이 오른쪽 상단에 속해있을 경우
		else if (targetRIdx<size/2 && targetCIdx>=size/2) {
			count += size*size / 4;
			zSearch(size/2, targetRIdx, targetCIdx-size/2);
		}
		// target이 왼쪽 하단에 속해있을 경우
		else if (targetRIdx>=size/2 && targetCIdx<size/2) {
			count += (size*size / 4)*2;
			zSearch(size/2, targetRIdx-size/2, targetCIdx);
		}
		// target이 오른쪽 하단에 속해있을 경우
		else if (targetRIdx>=size/2 && targetCIdx>=size/2) {
			count += (size*size / 4)*3;
			zSearch(size/2, targetRIdx-size/2, targetCIdx-size/2);
		}
		
		
	}

}
