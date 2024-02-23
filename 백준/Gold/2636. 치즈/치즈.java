import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;
	
	static int boardRowSize, boardColSize;
	static int[][] board;
	static int cheese; // 치즈가 다 녹기전 치즈 양을 기록해두는 변수
	
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	static boolean[][] isVisited;
	
	public static void inputTestCase() throws IOException{
		// 치즈가 놓인 찬의 세로, 가로 길이를 입력받는다.
		st = new StringTokenizer(br.readLine().trim());
		boardRowSize = Integer.parseInt(st.nextToken());
		boardColSize = Integer.parseInt(st.nextToken());
		
		// 판의 상태를 입력받는다.
		board = new int[boardRowSize][boardColSize];
		cheese = 0;
		for (int rowIdx=0; rowIdx<boardRowSize; rowIdx++) {
			st = new StringTokenizer(br.readLine().trim());
			for (int colIdx=0; colIdx<boardColSize; colIdx++) {
				board[rowIdx][colIdx] = Integer.parseInt(st.nextToken());
				// 치즈가 있는 칸의 수 세기
				if (board[rowIdx][colIdx]==1)
					cheese++;
			}
		}
	}
	
	public static void searchCheese() {
		Queue<int[]> q = new ArrayDeque<>();
		q.offer(new int[] {0,0});
		isVisited[0][0] = true;
		
		while(!q.isEmpty()) {
			int[] current = q.poll();
			for (int d=0; d<4; d++) {
				int nextRowIdx = current[0]+dr[d];
				int nextColIdx = current[1]+dc[d];
				
				// 범위 안에 있고 아직 방문하지 않았다면
				if (nextRowIdx>=0 && nextColIdx>=0 && nextRowIdx<boardRowSize && nextColIdx<boardColSize
						&& !isVisited[nextRowIdx][nextColIdx]) {
					isVisited[nextRowIdx][nextColIdx] = true;
					// 2-1-1. 치즈라면 -> 제거 (0으로 바꿔주기)
					if (board[nextRowIdx][nextColIdx] == 1) {
						board[nextRowIdx][nextColIdx] = 0;
						cheese--;
					}
					// 2-1-2. 치즈가 아니라면 -> 큐에 삽입해 근처의 치즈 탐색
					else {
						q.offer(new int[] {nextRowIdx, nextColIdx});
					}
				}		
			}
		}
	}
	
	public static void main(String[] args) throws Exception{

		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		// 1. 테스트케이스를 입력받는다.
		inputTestCase();
		
		// 2. while: 치즈의 개수가 없어질때까지 무한반복
		int cheeseSaved = 0;
		int time = 0;
		while (cheese!=0) {
			// 2-1. bfs 실행 전 치즈의 개수 저장
			cheeseSaved = cheese;
			// 2-2. (0,0)부터 bfs로 board를 탐색한다
			isVisited = new boolean[boardRowSize][boardColSize];
			searchCheese();
			// 2-3. time 증가
			time++;
		}
		
		System.out.println(time);
		System.out.println(cheeseSaved);
	}

}
