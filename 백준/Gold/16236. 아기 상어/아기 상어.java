import java.io.*;
import java.util.*;

/**
 * 
 * @author JiYeon Sin
 * 가장 가까운 물고기를 먹는다 -> bfs
 * 
 * 거리가 가까운 물고기가 많다면, 가장 위에 있는 물고기, 그러한 물고기가 여러마리라면, 가장 왼쪽에 있는 물고기를 먹는다.
 * -> 여기서 delta의 이동 순서를 상관이 없다.
 * delta로는 문제에서 요구하는 물고기 먹는 순서를 만족시킬 수 없다.(예제 4번 직접 해보기)
 * 따라서 일단 새로운 탐색 위치에 있는 먹을 수 있는 물고기를 전부 pq에 넣어놓고,
 * 한 level이 끝나면 pq의 root를 꺼내서 문제 조건을 만족하는 가장 가까운 물고기를 찾아야 한다.
 * 
 * 
 * 1. 입력을 받는다.
 * 	1-1. 아기 상어의 위치를 큐에 넣고 초기 위치 방문처리.
 * 2. 아기 상어에 대해 bfs 시작
 * 	2-1. 탐색 level 단위로 반복
 * 		2-1-1. 맵을 벗어나거나, 자신보다 큰 물고기가 있거나, 이미 방문한 곳은 pass
 * 		2-1-2. 이동할 수 있는 곳은 bfs를 위한 queue에 넣고 방문처리.
 * 		2-1-3. 먹을 수 있는 물고기를 만났다면 일단 pq에 넣는다.
 * 	2-2. 한 level이 끝났을 때 먹을 수 있는 물고기가 있는지 확인
 * 		2-2-1. 있다면 가장 가까운 물고기(pq이용)를 먹는다.
 * 		2-2-2. 물고기 크기 증가 체크
 * 		2-2-3. 물고기를 먹었으므로 level만큼 시간을 증가시켜준다.
 * 		2-2-4. 가장 가까운 물고기가 있던 위치에서부터 다시 bfs를 시작할 수 있도록 설정.
 * 
 * 3. 큐가 비어서 bfs가 완전히 끝나면 시간을 출력한다. 
 */

public class Main {
	static BufferedReader br;
	static StringTokenizer st;
	
	static final int BABY_SHARK = 9, BLANK = 0;
	
	// 아기 상어의 크기, 크키가 커지기까지 남은 물고기의 수, 시간
	static int babySharkSize = 2, sizeUpLeftCount = 2, time = 0; 
	
	// 이동은 상, 좌, 우, 하 순서
	static int[] deltaRow = { -1, 0, 0, 1 },
				 deltaCol = { 0, -1, 1, 0 };
	
	static int mapSize, map[][];
	
	static Queue<Point> queue = new ArrayDeque<>();
	static boolean[][] visited;
	
	static Queue<Point> fishQueue = new PriorityQueue<>(); // 먹을 수 있느 물고기 좌표 저장 
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		
		// 1. 입력을 받는다.
		// 바다의 크기 입력받기
		mapSize = Integer.parseInt(br.readLine().trim());
		
		// 배열 생성
		map = new int[mapSize][mapSize];
		visited = new boolean[mapSize][mapSize];
			
		// 바다 정보 입력받기
		for(int rowIdx = 0; rowIdx < mapSize; rowIdx++) {
			st = new StringTokenizer(br.readLine().trim());
			
			for(int colIdx = 0; colIdx < mapSize; colIdx++) {
				map[rowIdx][colIdx] = Integer.parseInt(st.nextToken());
				
				// 1-1. 아기 상어의 위치를 큐에 넣고 초기 위치 방문처리.
				if(map[rowIdx][colIdx] == BABY_SHARK) {
					queue.offer(new Point(rowIdx, colIdx));
					visited[rowIdx][colIdx] = true;
					
					// 아이 상어가 있는 곳은 빈칸.
					// 0으로 처리 안 해주면 이 칸은 영영 못 지나간다.
					map[rowIdx][colIdx] = 0; 
				}
			}
		}
		
		
		// 2. 아기 상어에 대해 bfs 시작
		int moveDistance = 1;
		while(!queue.isEmpty()) {
			int queueSize = queue.size();
			
			// 2-1. 탐색 level 단위로 반복
			while(queueSize-- > 0) {
				Point curPoint = queue.poll();
				int curRow = curPoint.row;
				int curCol = curPoint.col;
				
				// 4방향 탐색
				for(int dir = 0; dir < deltaRow.length; dir++) {
					int newRow = curRow + deltaRow[dir];
					int newCol = curCol + deltaCol[dir];
					
					// 2-1-1. 맵을 벗어나거나, 자신보다 큰 물고기가 있거나, 이미 방문한 곳은 pass
					if(!boundaryCheck(newRow, newCol)) continue;
					if(map[newRow][newCol] > babySharkSize) continue;
					if(visited[newRow][newCol]) continue;
					
					// 2-1-2. 이동할 수 있는 곳은 bfs를 위한 queue에 넣고 방문처리.
					queue.offer(new Point(newRow, newCol));
					visited[newRow][newCol] = true;
					
					// 2-1-3. 먹을 수 있는 물고기를 만났다면 일단 pq에 넣는다.
					if(map[newRow][newCol] != BLANK && map[newRow][newCol] < babySharkSize)
						fishQueue.offer(new Point(newRow, newCol));
				}
			}
			
			// 2-2. 한 level이 끝났을 때 먹을 수 있는 물고기가 있는지 확인
			if(!fishQueue.isEmpty()) {
				// 2-2-1. 있다면 가장 가까운 물고기(pq이용)를 먹는다.
				Point eatFish = fishQueue.poll(); // 아기 상어가 먹을 물고기의 위치
				
				// 물고기를 먹는다.
				map[eatFish.row][eatFish.col] = BLANK;
				sizeUpLeftCount--; 
				
				// 2-2-2. 물고기 크기 증가 체크
				if(sizeUpLeftCount <= 0) {
					babySharkSize++;
					sizeUpLeftCount = babySharkSize;
				}
				
				// 2-2-3. 물고기를 먹었으므로 level만큼 시간을 증가시켜준다.
				// 물고기를 먹었을 때만 시간 증가를 해줘야 한다.
				time += moveDistance;
				moveDistance = 0; // 바깥에 moveDistance++이 있어서 여기서는 0으로 초기화

				
				// 2-2-4. 가장 가까운 물고기가 있던 위치에서부터 다시 bfs를 시작할 수 있도록 설정.
				fishQueue.clear();
				
				queue.clear();
				queue.offer(new Point(eatFish.row, eatFish.col));
				
				initVisited();
				visited[eatFish.row][eatFish.col] = true;
			}
			
			
			moveDistance++;
		}
		
		
		// 3. 큐가 비어서 bfs가 완전히 끝나면 시간을 출력한다.
		System.out.println(time);
	}
	
	
	
	static boolean boundaryCheck(int row, int col) {
		return (row >= 0 && row < mapSize && col >= 0 && col < mapSize);
	}
	
	static void initVisited() {
		for(int rowIdx = 0; rowIdx < mapSize; rowIdx++)
			Arrays.fill(visited[rowIdx], false);
	}
	
	
	
	static class Point implements Comparable<Point>{
		int row;
		int col;
		
		public Point(int row, int col) {
			this.row = row;
			this.col = col;
		}

		@Override
		public String toString() {
			return "Point [row=" + row + ", col=" + col + "]";
		}

		/*
		 * 얘는 먹을 물고기와 관련된 부분
		 * 거리가 가까운 물고기가 많다면, 가장 위에 있는 물고기, 그러한 물고기가 여러마리라면, 가장 왼쪽에 있는 물고기를 먹는다.
		 */
		@Override
		public int compareTo(Point o) {
			if(this.row == o.row) return (this.col - o.col);
			return (this.row - o.row);
		}
	}

}
