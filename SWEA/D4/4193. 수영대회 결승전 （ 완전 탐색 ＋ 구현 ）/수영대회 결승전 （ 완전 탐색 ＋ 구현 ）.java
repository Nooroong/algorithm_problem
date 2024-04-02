import java.io.*;
import java.util.*;

/**
 * 
 * @author JiYeon Sin
 * 1. 입력을 받는다.
 * 2. 시간을 0으로 초기화하고 bfs를 돌린다.
 * 3. 큐의 크기만큼 while문을 돌리면서 시간을 계산한다.
 * 	3-1. 목적지에 도착하면 true를 return하고 종료
 * 	3-2. 아니라면 네 방향을 탐색한다.
 * 		3-2-1. 해당 방향으로 이동할 수 없는 경우는 pass
 * 		3-2-2. 소용돌이가 사라질 때까지 기다리고 가로지를 때 최단 거리를 찾을 수도 있다.
 * 		                소용돌이가 아직 사라지지 않은 경우 해당 위치에서 기다린다. == 큐에 현재 위치를 넣는다.
 * 		3-2-3. 그냥 빈 칸인 경우 or 소용돌이를 건너갈 수 있는 경우에는 방문을 시도한다.
 *	3-3. 목적지에 도달할 수 없는 경우 false를 return
 * 4. 탐색에 성공하면 걸린 시간을 출력, 실패하면 -1을 출력한다.
 */
public class Solution {
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	
	static int testCase; // 테스트 케이스의 개수
	
	static int size; // 수영장의 크기
	static int[][] pool; // 수영장
	static boolean[][] visited; // 방문 처리
	
    // 이동 방향
	static int[] deltaRow = { -1, 0, 1, 0 },
				 deltaCol = { 0, 1, 0, -1 };
	
	static final int BLANK = 0,
					 ISLAND = 1,
					 WHIRLPOOL = 2;
	
    // 출발지점, 목적지점 좌표
	static int startRow, startCol,
			   goalRow, goalCol;
	
	static int time; // 골인까지 걸리는 시간
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		testCase = Integer.parseInt(br.readLine().trim());
		for(int tc = 1; tc <= testCase; tc++) {
			// 1. 입력을 받는다.
			
			size = Integer.parseInt(br.readLine().trim()); // 수영장 크기
			
			// 배열 생성
			pool = new int[size][size];
			visited = new boolean[size][size];
			
			// 수영장 정보 입력받기
			for(int rowIdx = 0; rowIdx < size; rowIdx++) {
				st = new StringTokenizer(br.readLine().trim());
				
				for(int colIdx = 0; colIdx < size; colIdx++)
					pool[rowIdx][colIdx] = Integer.parseInt(st.nextToken());
			}
			
            // 출발지 정보 입력받기
			st = new StringTokenizer(br.readLine().trim());
			startRow = Integer.parseInt(st.nextToken());
			startCol = Integer.parseInt(st.nextToken());
			
			// 도착지 정보 입력받기
			st = new StringTokenizer(br.readLine().trim());
			goalRow = Integer.parseInt(st.nextToken());
			goalCol = Integer.parseInt(st.nextToken());
			
			
			
			
			// 2. 시간을 0으로 초기화하고 bfs를 돌린다.
			time = 0;
			boolean result = bfs();
			
			// 4. 탐색에 성공하면 걸린 시간을 출력, 실패하면 -1을 출력한다.
            sb.append("#").append(tc).append(" ").append(result ? time : -1); // 도착하지 못하는 경우네는 -1을 출력해야 한다.
			System.out.println(sb);
            sb.setLength(0);
			
		}
	}
	
	static boolean bfs() {
		Queue<Point> queue = new ArrayDeque<>();
		queue.offer(new Point(startRow, startCol)); // 출발점 처리
		
		while(!queue.isEmpty()) {
            // 3. 큐의 크기만큼 while문을 돌리면서 시간을 계산한다.
			int size = queue.size(); 
			
			while(size-- > 0) {
                // 현재 방문 위치
				Point curPoint = queue.poll();
				int curRow = curPoint.row;
				int curCol = curPoint.col;
				
				//System.out.println(curPoint.toString());				
				
                // 3-1. 목적지에 도착하면 true를 return하고 종료
				if(curRow == goalRow && curCol == goalCol) {
					return true;
				}
				
				// 3-2. 아니라면 네 방향을 탐색한다.
				for(int dir = 0; dir < deltaRow.length; dir++) {
					int newRow = curRow + deltaRow[dir];
					int newCol = curCol + deltaCol[dir];
					
                    // 3-2-1. 해당 방향으로 이동할 수 없는 경우는 pass
                    // (범위 밖, 이미 방문한 곳, 장애물)
					if(!isAvailable(newRow, newCol)) continue;
					if(visited[newRow][newCol]) continue;
					if(pool[newRow][newCol] == ISLAND) continue;
                    
                    // 3-2-2. 소용돌이가 사라질 때까지 기다리고 가로지를 때 최단 거리를 찾을 수도 있다.
                    // 소용돌이가 아직 사라지지 않은 경우 해당 위치에서 기다린다. == 큐에 현재 위치를 넣는다.
                    if(pool[newRow][newCol] == WHIRLPOOL && time%3 != 2) {
                        queue.offer(new Point(curRow, curCol)); 
                        
                    } else {
                        // 3-2-3. 그냥 빈 칸인 경우 or 소용돌이를 건너갈 수 있는 경우에는 방문을 시도한다.
                        visited[newRow][newCol] = true;
						queue.offer(new Point(newRow, newCol));
                    }
				}		
			}
			
			time++;
		}
		
        // 3-3. 목적지에 도달할 수 없는 경우 false를 return
        return false;
	}

	
	// 좌표가 배열 범위를 벗어났는지 체크
	static boolean isAvailable(int row, int col) {
		return (row >= 0 && row < size && col >= 0 && col < size);
	}
	
	
	static class Point {
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
		
		
	}
}
