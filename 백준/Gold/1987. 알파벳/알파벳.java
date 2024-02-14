import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * BOJ
 * @author eunwoo.lee
 * 
 * <dfs> : 2차원 배열 dfs+backtracking
 * 1. 기저조건
 * 	현재 칸의 알파벳이 이미 사용되었다면
 * 	현재까지의 누적이동칸과 maxBox 비교해 갱신
 * 	return
 * 2. 전처리 로직
 * 	현재 칸 알파벳 방문처리
 * 3. 재귀호출
 * 	다음 인덱스 만들기
 * 		다음 인덱스가 범위 안에 있다면, 재귀호출
 * 4. 후처리 로직
 * 	현재 알파벳 방문 상태 false로 되돌려 주기 
 * 
 * <main>
 * 1. 입력
 * 	1-1. 세로 칸 수, 가로 칸 수를 입력받는다.
 * 	1-2. 보드의 알파벳을 입력받는다.
 * 2. dfs(0,0,0)
 * 3. 정답 출력
 */

public class Main {
	
	public static BufferedReader br;
	public static StringBuilder sb;
	public static StringTokenizer st;
	
	// 상하좌우 delta 배열
	public static final int[] dr = {-1, 1, 0, 0};
	public static final int[] dc = {0, 0, -1, 1};
	public static boolean[] isVisited;
	
	
	public static int row, col;
	public static char[][] board;
	public static int maxBox; // 이동한 최대 칸 저장
	
	public static void dfs(int currentRowIdx, int currentColIdx, int accumBoxNum) {
		
		// 1. 기저조건
		if (isVisited[board[currentRowIdx][currentColIdx]-'A']) {
			maxBox = Math.max(accumBoxNum, maxBox);
			return;
		}
		// 2. 전처리 로직
		// 현재 알파벳 방문 처리
		isVisited[board[currentRowIdx][currentColIdx]-'A'] = true;
		
		// 3. 재귀호출
		for (int d=0; d<4; d++) {
			int nextRowIdx = currentRowIdx+dr[d];
			int nextColIdx = currentColIdx+dc[d];
			// 다음 인덱스가 범위 안에 있다면
			if (nextRowIdx>=0 && nextRowIdx<row && nextColIdx>=0 && nextColIdx<col) {
				dfs(nextRowIdx, nextColIdx, accumBoxNum+1);
			}
		}
		
		// 4. 후처리 로직
		isVisited[board[currentRowIdx][currentColIdx]-'A'] = false;
		
		
	}

	public static void main(String[] args) throws Exception{
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		// 1. 입력
		// 1-1. 세로칸 수, 가로칸 수를 입력받는다.
		st = new StringTokenizer(br.readLine().trim());
		row = Integer.parseInt(st.nextToken());
		col = Integer.parseInt(st.nextToken());
		// 1-2. 보드의 알파벳을 입력받는다.
		board = new char[row][col];
		for (int inputRowIdx=0; inputRowIdx<row; inputRowIdx++) {
			String line = br.readLine().trim();
			for (int inputColIdx=0; inputColIdx<col; inputColIdx++) {
				board[inputRowIdx][inputColIdx] = line.charAt(inputColIdx);
			}
		}
		
		// 2. 배열 탐색 실행
		isVisited = new boolean[26];
		maxBox = 1;
		dfs(0, 0, 0);
		// 3. 정답 출력
		System.out.println(maxBox);

	}

}
