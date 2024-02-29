import java.io.*;
import java.util.*;

/**
 * 
 * @author JiYeon Sin
 * 출발점부터 시작해서 BFS로 모든 방향(원숭이 이동, 말 이동)으로 이동한다.
 * BFS로 이미 방문했던 노드라고 그냥 지나치면 안 될듯???????
 * 
 * 
 */
public class Main {
	public static BufferedReader br;
	public static StringTokenizer st;
	
	public static int horseMoveCount;
	
	public static int width, height; // 격자판의 크기
	public static int[][] map;
	
	public static int monkeyRow, monkeyCol;
	
	public static Queue<Point> queue;
	public static boolean[][][] visited;
	
	public static int[] monkeyDeltaRow = { -1, 0, 1, 0 };
	public static int[] monkeyDeltaCol = { 0, 1, 0, -1 };
	public static final int MONKEY_DIRECTION = 4;
	
	public static int[] horseDeltaRow = { -1, -2, -2, -1, 1, 2, 2, 1 };
	public static int[] horseDeltaCol = { -2, -1, 1, 2, 2, 1, -1, -2 };
	public static final int HORSE_DIRECTION = 8;
	
	public static int moveCount;
	public static int minMoveCount;
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		
		horseMoveCount = Integer.parseInt(br.readLine().trim());
		
		st = new StringTokenizer(br.readLine().trim());
		width = Integer.parseInt(st.nextToken());
		height = Integer.parseInt(st.nextToken());
		map = new int[height][width];
		
		for(int rowIdx = 0; rowIdx < height; rowIdx++) {
			st = new StringTokenizer(br.readLine().trim());
			for(int colIdx = 0; colIdx < width; colIdx++) {
				map[rowIdx][colIdx] = Integer.parseInt(st.nextToken());
			}
		}
		
		monkeyRow = monkeyCol = 0;
		queue = new ArrayDeque<>();
		visited = new boolean [height][width][horseMoveCount+1];
		minMoveCount = Integer.MAX_VALUE;
		
		queue.offer(new Point(monkeyRow, monkeyCol, 0, horseMoveCount));
		
		BFS();

	}
	
	
	public static void BFS() {
		int level = 1; // while 안에서 현재 레벨을 가진다.
		visited[0][0][horseMoveCount] = true;
		
		while(!queue.isEmpty()) {
			int size = queue.size();
			
			while(size > 0) {
				size--;
				
				Point cur = queue.poll();
				int curRow = cur.row;
				int curCol = cur.col;
				int leftMove = cur.leftMoveCount;
				
				// 도착 지점에 온 경우
				if(curRow == height-1 && curCol == width-1) {
					System.out.println(level-1);
					return;
				}
//				
				// 말 움직임으로 방문하는 경우, 원숭이 움직임으로 방문하는 경우가 있다.
				// 0은 말. 1은는 원숭이
				
//				if(visited[cur.horse][curRow][curCol]) continue;
				
				
				// 원숭이가 갈 수 있는 방향을 방문
				if(leftMove > 0) {
					for (int dir = 0; dir < HORSE_DIRECTION; dir++) {
						int newRow = curRow + horseDeltaRow[dir];
						int newCol = curCol + horseDeltaCol[dir];
						
						if(newRow < 0 || newRow >= height || newCol < 0 || newCol >= width)
							continue;
						if(map[newRow][newCol] == 1) continue; // 장애물이 있는 곳에는 갈 수 없다.
						if(visited[newRow][newCol][leftMove-1]) continue;
						
						visited[newRow][newCol][leftMove-1] = true;
						queue.offer(new Point(newRow, newCol, 0, leftMove-1));
					}
				}
				
				for(int dir = 0; dir < MONKEY_DIRECTION; dir++) {
					int newRow = curRow + monkeyDeltaRow[dir];
					int newCol = curCol + monkeyDeltaCol[dir];
					
					if(newRow < 0 || newRow >= height || newCol < 0 || newCol >= width)
						continue;
					if(map[newRow][newCol] == 1) continue; // 장애물이 있는 곳에는 갈 수 없다.
					if(visited[newRow][newCol][leftMove]) continue;
					
					visited[newRow][newCol][leftMove] = true;
					queue.offer(new Point(newRow, newCol, 1, leftMove));
				}
				
			}
			
			
			level++;
		}
		System.out.println(-1);
	}
	
	

	
	static class Point {
		int row;
		int col;
		int horse;
		int leftMoveCount;
		
		public Point(int row, int col, int horse, int leftMoveCount) {
			super();
			this.row = row;
			this.col = col;
			this.horse = horse;
			this.leftMoveCount = leftMoveCount;
		}

		@Override
		public String toString() {
			return "Point [row=" + row + ", col=" + col + ", horse=" + horse + ", leftMoveCount=" + leftMoveCount + "]";
		}
	}
}
