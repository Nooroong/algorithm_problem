import java.io.*;
import java.util.*;

/**
 * 
 * @author JiYeon Sin
 * 50*50
 * 최단 경로 찾기 -> BFS
 * S에서 D로 *를 피해서 가야한다.
 * 
 * 물이 찰 예정은 고슴도치가 이동할 수 없다.
 * -> 그냥 도치를 이동할 수 있는 곳으로 다 이동시키고 물에 빠지는 도치는 물로 덮어버린다.
 *    어차피 목적저는 물에 잠기지 않으니 이렇게 해도 괜찮을지도?
 *
 */
public class Main {
	static BufferedReader br;
	static StringTokenizer st;
	
	static int[] deltaRow = { -1, 0, 1, 0 };
	static int[] deltaCol = { 0, 1, 0, -1 };
	
	static final char BLANK = '.',
					  WATER = '*',
					  ROCK = 'X',
					  GOAL = 'D',
					  DOCHI = 'S';
	
	static int dochiRow, dochiCol,
			   goalRow, goalCol;
	
	static List<Point> water = new ArrayList<>();
	
	static int rowSize, colSize;
	static char[][] map;
	
	static Queue<Point> queue = new ArrayDeque<>();
	static boolean[][] waterVisited;
	static boolean[][] dochiVisited;
	static boolean isEnd;
	static int time, dochiCount;
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		
		
		st = new StringTokenizer(br.readLine().trim());
		rowSize = Integer.parseInt(st.nextToken());
		colSize = Integer.parseInt(st.nextToken());
		map = new char[rowSize][colSize];
		
		// map 입력받기 + 각종 오브젝트들 위치 찾기
		for(int rowIdx = 0; rowIdx < rowSize; rowIdx++) {
			String line = br.readLine().trim();
			for(int colIdx = 0; colIdx < colSize; colIdx++) {
				map[rowIdx][colIdx] = line.charAt(colIdx);
				
				if(map[rowIdx][colIdx] == DOCHI) {
					dochiRow = rowIdx;
					dochiCol = colIdx;
				} else if(map[rowIdx][colIdx] == WATER) {
					water.add(new Point(rowIdx, colIdx));
				} else if(map[rowIdx][colIdx] == GOAL) {
					goalRow = rowIdx;
					goalCol = colIdx;
				}
			}
		}
		
		
		
		// 각종 변수 초기화
		time = 0; 
		dochiCount = 1;		
		isEnd = false;
		
		
		// 맵에 남은 도치의 수가 0이거나 어떤 시간을 초과하면 실패로 본다.
		while(dochiCount > 0) {
			time++; // 시간은 1초부터 시작
			
			waterVisited = new boolean[rowSize][colSize];
			dochiVisited = new boolean[rowSize][colSize];
			
			// 도치를 먼저 퍼트린다.만약 목적지에 도착하면 성공.
			if(dochiBFS()) {
				isEnd = true;
				break;
			}
			
			// 물을 퍼트린다.
			waterSpread();
			
			
//			printMap();
//			System.out.println("dochi Count: " + dochiCount);
		}
		
		System.out.println(isEnd ? time : "KAKTUS");
	}
	
	
	static void waterSpread() {
		// 물 시작 위치 처리
		for(int idx = 0; idx < water.size(); idx++)
			queue.offer(water.get(idx));
		
		while(!queue.isEmpty()) {
			Point curPoint = queue.poll();
			int curRow = curPoint.row, curCol = curPoint.col;
			
			if(waterVisited[curRow][curCol]) continue;
			waterVisited[curRow][curCol] = true;
			
			// 물이 갈 수 있는 새로운 위치로 퍼진다.
			// 한 번에 한 칸씩만 퍼질 수 있다.
			if(map[curRow][curCol] == BLANK || map[curRow][curCol] == DOCHI) {
				
				// 물이 퍼질 위치에 도치가 있다면 물로 덮어버리고 도치 수를 1 감소시킨다.
				if(map[curRow][curCol] == DOCHI) dochiCount--;
				
				map[curRow][curCol] = WATER;
				continue;
			}
			
			// 물은 목적지와 돌을 통과할 수 없다.
			if(map[curRow][curCol] == GOAL || map[curRow][curCol] == ROCK)
				continue;
			
			
			
			for(int dir = 0; dir < deltaRow.length; dir++) {
				int newRow = curRow + deltaRow[dir];
				int newCol = curCol + deltaCol[dir];
				
				if(newRow < 0 || newRow >= rowSize || newCol < 0 || newCol >= colSize)
					continue;
				
				queue.offer(new Point(newRow, newCol));
			}
			
		}
		
		return;
		
	}
	
	
	static boolean dochiBFS() {
		// 맵 상에 남아있는 도치들을 찾아 큐에 넣는다.
		for(int rowIdx = 0; rowIdx < rowSize; rowIdx++) {
			for(int colIdx = 0; colIdx < colSize; colIdx++) {
				if(map[rowIdx][colIdx] == DOCHI) {
					queue.offer(new Point(rowIdx, colIdx));
				}
				
			}
		}
		
		// 각 도치들을 상, 하, 좌, 우로 퍼트린다.
		while(!queue.isEmpty()) {
			Point curPoint = queue.poll();
			int curRow = curPoint.row, curCol = curPoint.col;
			
			
			if(dochiVisited[curRow][curCol]) continue;
			dochiVisited[curRow][curCol] = true;
			
			// 도치가 목적지에 도달하면 true 반환
			if(map[curRow][curCol] == GOAL)
				return true;
			
			// 도치가 새롭게 이동할 수 있는 곳을 발견하면 맵에 남은 도치 수를 +1한다.
			// 도치는 한 번에 한 칸만 이동할 수 있음에 주의한다.
			if(map[curRow][curCol] == BLANK) {
				map[curRow][curCol] = DOCHI;
				dochiCount++;
				continue;
			}
			
			// 돌과 물이 있는 곳으로는 이동할 수 없다.
			if(map[curRow][curCol] == ROCK || map[curRow][curCol] == WATER)
				continue;
			
			
			// 상, 하, 좌, 우 이동
			for(int dir = 0; dir < deltaRow.length; dir++) {
				int newRow = curRow + deltaRow[dir];
				int newCol = curCol + deltaCol[dir];
				
				// 맵의 범위를 벗어나지 않도록 한다.
				if(newRow < 0 || newRow >= rowSize || newCol < 0 || newCol >= colSize)
					continue;
				
				queue.offer(new Point(newRow, newCol));
			}
		}
		
		return false;
	}
	
	static void printMap() {
		System.out.println("time: " + time);
		for(int rowIdx = 0; rowIdx < rowSize; rowIdx++)
			System.out.println(Arrays.toString(map[rowIdx]));
	}
	
	
	static class Point {
		int row, col;

		public Point(int row, int col) {
			super();
			this.row = row;
			this.col = col;
		}

		@Override
		public String toString() {
			return "Point [row=" + row + ", col=" + col + "]";
		}
		
		
	}
}
