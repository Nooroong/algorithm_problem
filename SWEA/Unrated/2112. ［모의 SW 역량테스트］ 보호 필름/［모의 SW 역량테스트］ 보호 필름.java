import java.io.*;
import java.util.*;

/**
 * 
 * @author JiYeon Sin
 * 성능검사를 통과할 때까지 약물 투입 횟수를 1씩 늘려나가야 한다.
 * 이때 약물은 A의 경우와 B의 경우가 있다.
 * 따라서 각 행에 대해 약물을 넣지 않는 경우, A를 투입하는 경우, B를 투입하는 경우가 있다.
 * 전체 경우의 수는 3^(행의 수)로 부분집합을 응용하여 해결한다.
 * 
 * 1. 필름 두께, 가로크기, 합격 기준을 입력받는다. 
 * 2. 필름 정보를 입력받는다.
 * 3. 필름 정보에 대한 복사본 배열을 만들고 초기화 작업을 해준다.
 * 4. 부분집합을 응용하여 3가지 경우에 대해 생각한다.
 * 	4-1. 기저조건 : 모둔 원소를 다 살펴본 경우
 * 		4-1-1. 약품 주입에 대한 결과를 확인하고 참이라면 최소값을 갱신한다.
 * 	4-2. 모든 원소를 다 보지 못한 경우
 * 		4-2-1. 현재 행에 아무 약품도 넣지 않거나,
 * 		4-2-2. 현재 행에 약품 A를 투입하거나,
 * 		4-2-3. 현재 행에 약품 B를 투입한다.
 * 5. 최소 약품 주입 횟수를 출력한다.
 */
public class Solution {
	public static BufferedReader br;
	public static StringTokenizer st;
	public static StringBuilder sb;
	
	public static int testCase; // 테스트 케이스의 개수
	
	public static int[][] originFilm; // 보호필름 원본 배열
	public static int[][] copiedFilm; // 보호필름 복사본 배열
	public static int rowSize, colSize; // 보호필름 두께, 가로크기
	
	public static int standard; // 합격 기준
	public static int minChemicalCount; // 최소 약물 투입 횟수
	
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		testCase = Integer.parseInt(br.readLine().trim());
		
		for(int tc = 1; tc <= testCase; tc++) {
			minChemicalCount = Integer.MAX_VALUE; // 초기화
			
			// 1. 필름 두께, 가로크기, 합격 기준을 입력받는다.
			st = new StringTokenizer(br.readLine().trim());
			rowSize = Integer.parseInt(st.nextToken());
			colSize = Integer.parseInt(st.nextToken());
			standard = Integer.parseInt(st.nextToken());
			
			// 2. 필름 정보를 입력받는다.
			originFilm = new int[rowSize][colSize];
			for(int rowIdx = 0; rowIdx < rowSize; rowIdx++) {
				st = new StringTokenizer(br.readLine().trim());
				
				for(int colIdx = 0; colIdx < colSize; colIdx++) {
					originFilm[rowIdx][colIdx] = Integer.parseInt(st.nextToken());
				}
			}
			
			// 3. 필름 정보에 대한 복사본 배열을 만들고 초기화 작업을 해준다.
			copiedFilm = new int[rowSize][colSize];
			copiedFilmInit();
			
			// 4. 부분집합을 응용하여 3가지 경우에 대해 생각한다.
			subSet(0, 0);
			
			// 5. 최소 약품 주입 횟수를 출력한다.
			sb.append("#").append(tc).append(" ").append(minChemicalCount);
			System.out.println(sb);
			sb.setLength(0);
		}
	}
	
	
	// 성능검사는 copy에 대해서 한다.
	private static boolean check() {
		// 모든 열에 대해, 합격기준만큼 특성 반복이 나타나야 한다.
		for(int colIdx = 0; colIdx < colSize; colIdx++) {
			boolean colCheckResult = false; // 현재 열 검사 결과
			int repeatCount = 0; // 특성 반복 횟수
			int preCell = copiedFilm[0][colIdx]; // 해당 열의 가장 첫 값
			
			for(int rowIdx = 0; rowIdx < rowSize; rowIdx++) {
				if(preCell == copiedFilm[rowIdx][colIdx]) { 
					repeatCount++;
				} else {
					repeatCount = 1;
				}
				
				if(repeatCount == standard) {
					colCheckResult = true;
					break;
				}
				
				preCell = copiedFilm[rowIdx][colIdx]; // 이전 셀 값 갱신
			}
			
			// 하나의 열이라도 k번의 반복이 나타나지 못하면 성능 검사를 통과할 수 없다.
			if(!colCheckResult) return false;
		}
		
		
		return true;
	}
	
	
	// rowIdx번째 행에 약품을 투입
	private static void chemicalInjection(int rowIdx, int value) {
		if(value == -1) {
			// 원상 복구
			for(int colIdx = 0; colIdx < colSize; colIdx++)
				copiedFilm[rowIdx][colIdx] = originFilm[rowIdx][colIdx];
		} else if(value == 0) {
			// 약품 A
			Arrays.fill(copiedFilm[rowIdx], 0);
		} else if(value == 1) {
			// 약품 B
			Arrays.fill(copiedFilm[rowIdx], 1);
		}
	}
	
	// 복사본 초기화
	private static void copiedFilmInit() {
		for(int rowIdx = 0; rowIdx < rowSize; rowIdx++) {
			for(int colIdx = 0; colIdx < colSize; colIdx++) {
				copiedFilm[rowIdx][colIdx] = originFilm[rowIdx][colIdx];
			}
		}
	}
	
	
	private static void subSet(int rowIdx, int drugInjectionCount) {
		// 4-1. 기저조건 : 모둔 원소를 다 살펴본 경우
		if(rowIdx == rowSize) {
			// 4-1-1. 약품 주입에 대한 결과를 확인하고 참이라면 최소값을 갱신한다.
			if(check()) {
				minChemicalCount =  Integer.min(drugInjectionCount, minChemicalCount);
			}
			
			return;
		}
		
		// 4-2. 모든 원소를 다 보지 못한 경우
		// 4-2-1. 현재 행에 아무 약품도 넣지 않거나,
		subSet(rowIdx+1, drugInjectionCount);
		
		// 4-2-2. 현재 행에 약품 A를 투입하거나,
		chemicalInjection(rowIdx , 0);
		subSet(rowIdx+1, drugInjectionCount+1);
		chemicalInjection(rowIdx , -1); // 원상복구
		
		// 4-2-3. 현재 행에 약품 B를 투입한다.
		chemicalInjection(rowIdx , 1);
		subSet(rowIdx+1, drugInjectionCount+1);
		chemicalInjection(rowIdx , -1); // 원상복구
	}

}
