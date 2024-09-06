import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 백준 1926 (그림)
 * bfs로 해보자
 */
public class Main {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static StringTokenizer st;

	private static int rowSize, colSize, paper[][],
		deltaRow[] = {-1, 0, 1, 0},
		deltaCol[] = {0, 1, 0, -1},
		drawCount = 0, maxDrawArea = 0;

	private static boolean visited[][];

	private static Queue<int[]> queue = new ArrayDeque<>();

	public static void main(String[] args) throws IOException {
		// 입력 받기
		st = new StringTokenizer(br.readLine().trim());
		rowSize = Integer.parseInt(st.nextToken());
		colSize = Integer.parseInt(st.nextToken());
		paper = new int[rowSize][colSize];

		for (int rowIdx = 0; rowIdx < rowSize; rowIdx++) {
			st = new StringTokenizer(br.readLine().trim());

			for (int colIdx = 0; colIdx < colSize; colIdx++) {
				paper[rowIdx][colIdx] = Integer.parseInt(st.nextToken());
			}
		}

		// 탐색에 필요한 배열 할당
		visited = new boolean[rowSize][colSize];

		// 탐색하기
		for (int rowIdx = 0; rowIdx < rowSize; rowIdx++) {
			for (int colIdx = 0; colIdx < colSize; colIdx++) {
				if (paper[rowIdx][colIdx] == 1 && !visited[rowIdx][colIdx]) {
					drawCount++;
					bfs(rowIdx, colIdx);
				}
			}
		}

		System.out.println(drawCount + "\n" + maxDrawArea);

	}

	private static void bfs(int rowIdx, int colIdx) {
		int size = 1;
		queue.offer(new int[] {rowIdx, colIdx});
		visited[rowIdx][colIdx] = true;

		while (!queue.isEmpty()) {
			int[] cur = queue.poll();

			for (int dir = 0; dir < deltaRow.length; dir++) {
				int newRow = cur[0] + deltaRow[dir];
				int newCol = cur[1] + deltaCol[dir];

				if (isValid(newRow, newCol) && paper[newRow][newCol] == 1 && !visited[newRow][newCol]) {
					queue.offer(new int[] {newRow, newCol});
					visited[newRow][newCol] = true;
					size++;
				}
			}
		}

		if (size > maxDrawArea)
			maxDrawArea = size;
	}

	private static boolean isValid(int rowIdx, int colIdx) {
		return rowIdx >= 0 && rowIdx < rowSize && colIdx >= 0 && colIdx < colSize;
	}
}