import java.io.*;
import java.util.*;

/**
 * 
 * @author JiYeon Sin
 * 맵 정보까지 다 입력을 받은 뒤, 벽을 세울 위치를 고른다.
 * 벽의 순서는 관계가 없으므로 조합을 통해 위치를 고른다.
 * 세개의 벽을 다 세우면 바이러스틑 퍼트리고 안전 영역의 수를 센다.
 *
 */
public class Main {
	public static BufferedReader br;
	public static StringTokenizer st;
	public static StringBuilder sb;
	
	public static int rowSize, colSize; // 맵 크기
	public static int[][] map; // 연구소
	public static int[][] mapCopy; // 연구소 복사본
	
	public static List<int[]> virusList; // 바이러스들의 위치
	
	// 바이러스가 퍼져나갈 수 있는 방향
	public static int[] deltaRow = { -1, 0, 1, 0 };
	public static int[] deltaCol = { 0, 1, 0, -1 };
	public static final int DIRECTION = 4;
	
	public static final int BLANK = 0;
	public static final int WALL = 1;
	public static final int VIRUS = 2;
	
	public static final int NEW_WALL_COUNT = 3; // 새로 세울 수 있는 벽의 개수
	
	public static int maxSafeZoneCount; // 최대 안전 영역의 수
	
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		// 맵 크기 입력받기
		st = new StringTokenizer(br.readLine().trim());
		rowSize = Integer.parseInt(st.nextToken());
		colSize = Integer.parseInt(st.nextToken());
		
		// 각종 할당 및 초기화
		map = new int[rowSize][colSize];
		virusList = new ArrayList<>();
		maxSafeZoneCount = Integer.MIN_VALUE;
		mapCopy = new int[rowSize][colSize];
		
		
		// 맵 정보 입력받기
		for(int rowIdx = 0; rowIdx < rowSize; rowIdx++) {
			st = new StringTokenizer(br.readLine().trim());
			for(int colIdx = 0; colIdx < colSize; colIdx++) {
				map[rowIdx][colIdx] = Integer.parseInt(st.nextToken());
				
				// 바이러스는 그 위치를 리스트에 추가한다.
				if(map[rowIdx][colIdx] == VIRUS) {
					virusList.add(new int[] {rowIdx, colIdx});
				}
			}
		}
		
		
		// 벽을 세울 위치를 고르고 최대 안전 영역의 크기를 구한다.
		selectWall(0, 0);
		
		// 최대 안전 영역 출력
		System.out.println(maxSafeZoneCount);
	}
	
	
	
	// 조합으로 벽을 세울 위치를 선택한다.
	public static void selectWall(int selectCount, int elementCount) {
		// 기저조건1: 벽을 다 세운 경우
		if(selectCount == NEW_WALL_COUNT) {		
			virusBFS(); // 바이러스를 퍼트리고
			int cnt = countSafeZone(); // 안전 영역의 수를 센다.
			
			// 최대 안전 영역 크기 갱신
			if(cnt > maxSafeZoneCount) maxSafeZoneCount = cnt;
			
			return;
		}
		
		// 기저조건2: 모든 요소를 다 살핀 경우
		if(elementCount == rowSize*colSize) {
			return;
		}
		
		// 좌측상단 요소부터 0번, 다음 요소가 +1일 때 각 요소의 좌표는 아래와 같이 구할 수 있다.
		int curCol = elementCount%colSize;
		int curRow = elementCount/colSize;
		
		
		// 벽을 세울 수 없는 경우 다음 위치로 넘어간다.
		if(map[curRow][curCol] != BLANK) {
			selectWall(selectCount, elementCount+1);
		} else {
			// 현재 위치에 벽을 세우는 경우
			map[curRow][curCol] = WALL;
			selectWall(selectCount+1, elementCount+1);
			
			// 현재 위치에 벽을 세우지 않는 경우
			map[curRow][curCol] = BLANK;
			selectWall(selectCount, elementCount+1);
		}
	}
	
	
	
	// 바이러스틑 퍼트린다.
	public static void virusBFS() {
		// 원본 배열에서 바이러스를 퍼트리면 다음 케이스에 영향을 끼치므로
		// 복사본에다가 바이러스를 퍼트린다.
		for(int rowIdx = 0; rowIdx < rowSize; rowIdx++) {
			mapCopy[rowIdx] = Arrays.copyOf(map[rowIdx], colSize);
		}
		
		// BFS를 위한 큐와 방문 체크 배열 생성
		Queue<int[]> queue = new ArrayDeque<>();
		boolean[][] visited = new boolean[rowSize][colSize];
		
		
		// 각 바이러스에 대해 BFS를 시도한다.
		for(int[] virus : virusList) {
			queue.offer(virus);
			
			while(!queue.isEmpty()) {
				int[] cur = queue.poll();
				
				if(visited[cur[0]][cur[1]]) continue;
				visited[cur[0]][cur[1]] = true;
				
				
				for(int dir = 0; dir < DIRECTION; dir++) {
					int newRow = cur[0] + deltaRow[dir];
					int newCol = cur[1] + deltaCol[dir];
					
					// 배열 범위를 벗어나거나 벽과 만나면 탐색 중단
					if(newRow < 0 || newRow >= rowSize || newCol < 0 || newCol >= colSize)
						continue;
					if(map[newRow][newCol] == WALL) continue;
					
					mapCopy[newRow][newCol] = VIRUS;
					queue.offer(new int[] {newRow, newCol});
				}
			}
		}
		
	}
	
	
	
	// 안전 영역의 수를 세어서 반환한다.
	public static int countSafeZone() {
		int blankCount = 0;
		
		for(int rowIdx = 0; rowIdx < rowSize; rowIdx++) {
			for(int colIdx = 0; colIdx < colSize; colIdx++) {
				if(mapCopy[rowIdx][colIdx] == BLANK)
					blankCount++;
			}
		}
		
		return blankCount;
	}
}
