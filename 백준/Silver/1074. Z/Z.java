import java.util.*;
import java.io.*;

/**
 * 
 * @author JiYeon Sin
 * 모든 요소를 다 돌면 시간초과가 난다.
 * 목표 요소에 해당하는 영역만 탐색할 수 있도록 적절히 가지치기를 해줘야 한다.
 * 
 * 1. 배열의 크기, r, c를 입력받는다.
 * 2. z모양으로 탐색하기 위한 이동 방향, 탐색 시작 위치에 대한 배열을 선언 및 초기화한다.
 * 3. 탐색 순서를 저장하는 전역변수를 초기화한다.
 * 4. 아래와 같은 파라미터를 가지는 재귀함수를 호출한다.
 *    파라미터: 탐색 시작 행, 탐색 시작 열, 탐색 범위의 크기
 *    (탐색 시작 지점은 좌측 상단의 요소다.)
 *  5. 탐색 범위가 2*2가 되면 각 요소에 z모양으로 탐색 순서값을 넣고 쪼개기를 중단한다.
 *  	5-1. 현재 위치가 문제에서 요구하는 위치하면 탐색 순서를 출력하고 프로그램을 종료한다.
 *  6. 탐색 범위가 2*2보다 크면 탐색 범위를 반으로 줄이고 4영역에 대해 재귀적으로 탐색한다.
 *  7. 4영역을 모두 탐색하지 않고 목표 행, 열이 들어있는 영역만 탐색한다. 
 */

public class Main {
	public static BufferedReader br;
	public static StringTokenizer st;
	
	public static final int SEARCH_UNIT_SIZE = 2; // z모양 탐색 범위의 크기
	
	
	// 2. z모양으로 탐색하기 위한 이동 방향, 탐색 시작 위치에 대한 배열을 선언 및 초기화한다.
	public static int[] deltaRow = { 0, 0, 1, 1 };
	public static int[] deltaCol = { 0, 1, 0, 1 };
	public static final int DIRECTION = 4; // 이동하는 방향의 수
	
	
	public static int arraySize; // 배열의 크기
	
	public static int targetRow; // 문제에서 요구하는 요소의 행
	public static int targetCol; // 문제에서 요구하는 요소의 열
	
	public static int searchCnt; // 탐색 순서
	
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		
		
		// 1. 배열의 크기, r, c를 입력받는다.
		st = new StringTokenizer(br.readLine().trim());
		arraySize = Integer.parseInt(st.nextToken());
		arraySize = (int)Math.pow(2, arraySize); // 배열의 크기는 2^n * 2^n
		targetRow = Integer.parseInt(st.nextToken());
		targetCol = Integer.parseInt(st.nextToken());
		
		searchCnt = 0; // 3. 탐색 순서를 저장하는 전역변수를 초기화한다.
		
		zSearch(0, 0, arraySize); // 4. 탐색을 위한 재귀함수를 호출한다.
	}
	
	
	// 파라미터: 탐색 시작 행, 탐색 시작 열, 탐색 범위의 크기. (탐색 시작 지점은 좌측 상단의 요소다.)
	public static void zSearch(int startRow, int startCol, int searchSize) {
		// 5. 탐색 범위가 2*2가 되면 각 요소에 z모양으로 탐색 순서값을 넣고 쪼개기를 중단한다.
		if(searchSize == SEARCH_UNIT_SIZE) {
			int searchRow, searchCol; // 탐색 위치
			
			// z모양으로 탐색
			for(int dir = 0; dir < DIRECTION; dir++) {
				searchRow = startRow + deltaRow[dir];
				searchCol = startCol + deltaCol[dir];
				
				// 5-1. 현재 위치가 문제에서 요구하는 위치하면 탐색 순서를 출력하고 프로그램을 종료한다.
				if(searchRow == targetRow && searchCol == targetCol) {
					System.out.println(searchCnt);
					System.exit(0);
				}
				
				searchCnt++;
			}
			return; // 쪼개기 중단
		}
		
		
		
		// 6. 탐색 범위가 2*2보다 크면 탐색 범위를 반으로 줄이고 각 영역에 대해 다시 탐색을 한다.
		int halfSize = searchSize/2;
		for(int dir = 0; dir < DIRECTION; dir++) {
			int newStartRow = startRow + deltaRow[dir]*halfSize;
			int newStartCol = startCol + deltaCol[dir]*halfSize;
			
			
			// 탐색 영역의 좌상단 값이 x이고 탐색 영역의 크기가 n일 때,
			// 각 4분할 영역의 좌상단 값은
			// x + ((n/2)^2)*0, x + ((n/2)^2)*1, x + ((n/2)^2)*2, x + ((n/2)^2)*3이 된다.
			int newSearchCnt = searchCnt + halfSize*halfSize*dir;;
			
			
			
			// 7. 4영역을 모두 탐색하지 않고 목표 행, 열이 들어있는 영역만 탐색한다.
			if(targetRow >= newStartRow && targetRow < newStartRow+halfSize &&
				targetCol >= newStartCol && targetCol < newStartCol+halfSize) {
				searchCnt = newSearchCnt;
				zSearch(newStartRow, newStartCol, halfSize);
				
			}
		}
	}
}
