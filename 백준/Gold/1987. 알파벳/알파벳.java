import java.io.*;
import java.util.*;

/**
 * 
 * @author JiYeon Sin
 * 1. 방문한적 있는 알파벳에 대한 정보를 저장하는 배열을 선언
 * 2. 보드의 크기를 입력받는다.
 * 3. 보드의 알파벳 정보를 입력받는다.
 * 4. 0, 0부터 시작한다.
 * 5. 이동거리 1부터 시작하여 dfs로 탐색을 한다.
 * 6. 현재 노드에 대해 방문처리를 한다.
 * 7. 인접한 칸을 살핀다.
 * 8. 보드의 범위를 벗어나지 않으면서 이전에 방문하지 않은 노드라면 방문을 한다.
 * 9. 더이상 방문할 노드가 없다면 최대 이동 거리를 갱신하고 이전 노드로 돌아간다.
 * 10. 탐색이 끝나면 최대 이동 거리를 출력한다.
 */

public class Main {
	public static BufferedReader br;
	public static StringTokenizer st;
	
	// 상, 우, 하, 좌
	public static int[] deltaRow = { -1, 0, 1, 0 };
	public static int[] deltaCol = { 0, 1, 0, -1 };
	public static final int DIRECTION = 4; // 이동할 수 있는 방향의 수
	
	public static char[][] board; // 말이 놓이는 보드
	public static int rowSize; // 보드의 세로 사이즈
	public static int colSize; // 보드의 가로 사이즈
	
	// 4. 0, 0부터 시작한다.
	public static final int START_ROW = 0; // 말의 시작 행 위치
	public static final int START_COL = 0; // 말의 시작 열 위치
	
	public static final int NUM_OF_CHAR = 26; // 보드에 사용되는 문자의 개수
	public static boolean[] visited; // 방문한 알파벳에 대한 정보를 저장하는 배열
	public static int maxDepth; // 최대 이동 거리
	
	
	
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		
		
		// 1. 방문한적 있는 알파벳에 대한 정보를 저장하는 배열을 선언
		visited = new boolean[NUM_OF_CHAR];
		
		
		// 2. 보드의 크기를 입력받는다.
		st = new StringTokenizer(br.readLine().trim());
		rowSize = Integer.parseInt(st.nextToken());
		colSize = Integer.parseInt(st.nextToken());
		
		
		// 3. 보드의 알파벳 정보를 입력받는다.
		board = new char[rowSize][colSize];
		for(int rowIdx = 0; rowIdx < rowSize; rowIdx++) {
			String line = br.readLine().trim();
			for(int colIdx = 0; colIdx < colSize; colIdx++) {
				board[rowIdx][colIdx] = line.charAt(colIdx);
			}
		}
		
	
		
		// 5. 이동거리 1부터 시작하여 dfs로 탐색을 한다.
		maxDepth = 0;
		dfs(0, 0, 1);
		
		
		// 10. 탐색이 끝나면 최대 이동 거리를 출력한다.
		System.out.println(maxDepth);
	}
	
	
	
	
	public static void dfs(int curRow, int curCol, int depth) {
		// 6. 현재 노드에 대해 방문처리를 한다.
		visited[ board[curRow][curCol] - 'A' ] = true;
		
		int newRow, newCol; // 말이 새롭게 이동하게 될 좌표
		
		
		// 7. 인접한 칸을 살핀다.
		for(int dir = 0; dir < DIRECTION; dir++) {
			newRow = curRow + deltaRow[dir];
			newCol = curCol + deltaCol[dir];
			
			
			// 8. 보드의 범위를 벗어나지 않으면서 
			if(newRow >= 0 && newRow < rowSize && newCol >= 0 && newCol < colSize) {
				// 이전에 방문하지 않은 노드라면 방문을 한다.
				if(!visited[ board[newRow][newCol] - 'A' ]) {
					dfs(newRow, newCol, depth + 1); // 방문
				}
			}
		}
		
		// 9. 더이상 방문할 노드가 없다면 최대 이동 거리를 갱신하고 이전 노드로 돌아간다.
		maxDepth = (depth > maxDepth) ? depth : maxDepth;
		visited[ board[curRow][curCol] - 'A' ] = false;
	}
}
