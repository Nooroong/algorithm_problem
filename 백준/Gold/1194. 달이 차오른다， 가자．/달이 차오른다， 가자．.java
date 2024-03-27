import java.io.*;
import java.util.*;

/**
 * 
 * @author JiYeon Sin
 * 
 * 1. 미로의 크기를 입력받는다.
 * 2. 미로의 정보를 입력받으면서 출발지 위치를 찾는다.
 * 3. BFS로 탐색을 한다.
 *  3-1. 이때 visited는 현재 보유하고 있는 키 정보에 따라 관리해줘야 한다.
 * 4. 탐색 결과를 출력한다.
 */
public class Main {
	public static BufferedReader br;
	public static StringTokenizer st;
	public static StringBuilder sb;

	public static int testCase; // 테스트 케이스의 개수

	public static int[] deltaRow = { -1, 0, 1, 0 };
	public static int[] deltaCol = { 0, 1, 0, -1 };

	public static int rowSize, colSize;
	public static char[][] map;
	public static int startRow, startCol;

	public static final int KEY_COUNT = (int) Math.pow(2, 6);

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		// 1. 미로의 크기를 입력받는다.
		st = new StringTokenizer(br.readLine().trim());
		rowSize = Integer.parseInt(st.nextToken());
		colSize = Integer.parseInt(st.nextToken());
		map = new char[rowSize][colSize];

		
		// 2. 미로의 정보를 입력받으면서 출발지 위치를 찾는다.
		for (int rowIdx = 0; rowIdx < rowSize; rowIdx++) {
			String line = br.readLine().trim();
			for (int colIdx = 0; colIdx < colSize; colIdx++) {
				map[rowIdx][colIdx] = line.charAt(colIdx);

				if (map[rowIdx][colIdx] == '0') {
					startRow = rowIdx;
					startCol = colIdx;
				}
			}
		}
		
		// 4. 탐색 결과를 출력한다.
		System.out.println(BFS(startRow, startCol));
	}

	static int BFS(int startRow, int startCol) {
		// 3-1. 이때 visited는 현재 보유하고 있는 키 정보에 따라 관리해줘야 한다.
		boolean[][][] visited = new boolean[rowSize][colSize][KEY_COUNT+1];
		Queue<Point> queue = new ArrayDeque<>();
		

		// 시작지점 처리
		queue.offer(new Point(startRow, startCol, 0, 0));

		while (!queue.isEmpty()) {
			Point curPoint = queue.poll();
			int curRow = curPoint.row, curCol = curPoint.col;
			int curKey = curPoint.key; // 해당 위치에서 소유하고 있는 키
			int curMove = curPoint.move;
			
			// 가장 가까운 도착지점에 도착했다면 지금까지의 이동거리를 반환한다.
			if(map[curRow][curCol] == '1')
				return curPoint.move;
			
			// 해당 키로 방문한 적이 있다면
			if (visited[curRow][curCol][curKey]) continue;
			
			visited[curRow][curCol][curKey] = true; // 방문처리
			
			// 4 방향에 대해 방문을 시도한다.
			for (int dir = 0; dir < deltaRow.length; dir++) {
				int newRow = curRow + deltaRow[dir];
				int newCol = curCol + deltaCol[dir];

				// 맵의 범위를 벗어나면 안 된다.
				if (newRow < 0 || newRow >= rowSize || newCol < 0 || newCol >= colSize)
					continue;

				if (map[newRow][newCol] == '#') {
					continue; // 벽으로는 이동할 수 없다.
					
				} else if ('a' <= map[newRow][newCol] && map[newRow][newCol] <= 'f') {
					// 열쇠가 있는 위치로 가려고 하는 경우 해당 위치의 키 정보를 갱신한다.
					int newKey = curKey | (1 << (map[newRow][newCol] - 'a'));
					queue.offer(new Point(newRow, newCol, curMove+1, newKey));
					
				} else if ('A' <= map[newRow][newCol] && map[newRow][newCol] <= 'F') {
					// 문에 대응하는 열쇠가 있는 경우에만 이동을 할 수 있다
					if ((curKey & (1 << (map[newRow][newCol] - 'A'))) > 0)
						queue.offer(new Point(newRow, newCol, curMove+1, curKey));
					
				} else {
					queue.offer(new Point(newRow, newCol, curMove+1, curKey));
				}

			}

		}
		
		// 도착지에 도달하지 못한 경우 -1을 반환한다.
		return -1;
	}

	static class Point {
		int row;
		int col;
		int move; // 이동 거리
		int key; // 현재 위치에서 갖고 있는 키들
		
		public Point() {
		}

		public Point(int row, int col, int move, int key) {
			super();
			this.row = row;
			this.col = col;
			this.move = move;
			this.key = key;
		}

		
	}
}
