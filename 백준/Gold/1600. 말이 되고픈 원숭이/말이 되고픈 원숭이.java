import java.io.*;
import java.util.*;

/**
 * 
 * @author JiYeon Sin
 * 출발점부터 시작해서 BFS로 모든 방향(원숭이 이동, 말 이동)으로 이동한다.
 * 일반적인 BFS와는 방문 처리를 다르게 해줘야 한다.
 * 같은 위치라 하더라도  능력을 사용한 횟수는 다르고 이는 각기 다른 경우로 처리해야 한다.
 * 따라서 visited 배열을 3차원으로 생성해야 한다.
 */
public class Main {
	public static BufferedReader br;
	public static StringTokenizer st;
	
	public static int horseMoveCount; // 능력 사용 가능 횟수
	
	public static int width, height; // 격자판의 크기
	public static int[][] map;
	
	public static int monkeyRow, monkeyCol;
	
	public static Queue<Point> queue;
	public static boolean[][][] visited;
	
	// 원숭이 움직임 방향
	public static int[] monkeyDeltaRow = { -1, 0, 1, 0 };
	public static int[] monkeyDeltaCol = { 0, 1, 0, -1 };
	public static final int MONKEY_DIRECTION = 4;
	
	// 말 움직임 방향
	public static int[] horseDeltaRow = { -1, -2, -2, -1, 1, 2, 2, 1 };
	public static int[] horseDeltaCol = { -2, -1, 1, 2, 2, 1, -1, -2 };
	public static final int HORSE_DIRECTION = 8;
	
	public static int minMoveCount; // 최소 이동 횟수
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		
		// 말 이동 횟수 입력받기
		horseMoveCount = Integer.parseInt(br.readLine().trim());
		
		
		// 맵 크기 및 정보 입력받기
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
		
		
		// 시작 위치 방문처리
		monkeyRow = monkeyCol = 0;
		queue = new ArrayDeque<>();
		visited = new boolean [height][width][horseMoveCount+1];
		minMoveCount = Integer.MAX_VALUE;
		queue.offer(new Point(monkeyRow, monkeyCol, horseMoveCount));
		
		minMoveCount = monkeyBFSMove(); // 원숭이를 bfs로 이동시킨다.  
		
		System.out.println(minMoveCount);
	}
	
	public static int monkeyBFSMove() {
		int level = 0; // 레벨 == 원숭이 이동 횟수
		visited[0][0][horseMoveCount] = true; // 시작 위치 방문 처리
		
		while(!queue.isEmpty()) {
			// 레벨 계산을 위해 큐 사이즈 만큼 원소를 빼낸다.
			int size = queue.size();
			
			while(size > 0) {
				size--;
				
				Point cur = queue.poll();
				int curRow = cur.row;
				int curCol = cur.col;
				int leftMove = cur.leftMoveCount; // 남은 능력 횟수
				
				// 원숭이가 도착 지점에 도달한 경우 이동 횟수를 반환하고 종료 
				if(curRow == height-1 && curCol == width-1) {
					return level;
				}

				
				
				// 말 움직임으로 이동
				if(leftMove > 0) {
					for (int dir = 0; dir < HORSE_DIRECTION; dir++) {
						int newRow = curRow + horseDeltaRow[dir];
						int newCol = curCol + horseDeltaCol[dir];
						
						// 배열 범위를 벗어나지 않도록 한다.
						if(newRow < 0 || newRow >= height || newCol < 0 || newCol >= width)
							continue;
						
						if(map[newRow][newCol] == 1) continue; // 장애물이 있는 곳에는 갈 수 없다.
						if(visited[newRow][newCol][leftMove-1]) continue; // 능력 사용 횟수가 동일한 상태로 방문한 경우 continue
						
						visited[newRow][newCol][leftMove-1] = true;
						queue.offer(new Point(newRow, newCol, leftMove-1));
					}
				}
				
				
				// 원숭이 움직임으로 이동
				for(int dir = 0; dir < MONKEY_DIRECTION; dir++) {
					int newRow = curRow + monkeyDeltaRow[dir];
					int newCol = curCol + monkeyDeltaCol[dir];
					
					// 배열 범위를 벗어나지 않도록 한다.
					if(newRow < 0 || newRow >= height || newCol < 0 || newCol >= width)
						continue;
					
					if(map[newRow][newCol] == 1) continue; // 장애물이 있는 곳에는 갈 수 없다.
					if(visited[newRow][newCol][leftMove]) continue; // 능력 사용 횟수가 동일한 상태로 방문한 경우 continue
					
					visited[newRow][newCol][leftMove] = true;
					queue.offer(new Point(newRow, newCol, leftMove));
				}
				
			}
			
			
			level++;
		}
		
		// 이곳에 도달 == 원숭이가 종료 지점에 갈 수 없다 == -1 출력
		return -1;
	}
	
	

	// 다음 탐색 위치와 남은 능력 수를 관리하는 클래스
	static class Point {
		int row;
		int col;
		int leftMoveCount;
		
		public Point(int row, int col, int leftMoveCount) {
			super();
			this.row = row;
			this.col = col;
			this.leftMoveCount = leftMoveCount;
		}

		@Override
		public String toString() {
			return "Point [row=" + row + ", col=" + col + ", leftMoveCount=" + leftMoveCount + "]";
		}
	}
}
