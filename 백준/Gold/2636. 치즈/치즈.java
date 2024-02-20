import java.io.*;
import java.util.*;

/**
 * 
 * @author JiYeon Sin
 *
 */
public class Main {
	static class Node {
		int row;
		int col;
		
		Node(int row, int col) {
			this.row = row;
			this.col = col;
		}
	}
	
	public static BufferedReader br;
	public static StringTokenizer st;
	public static StringBuilder sb;
	
	// 사각형 모양 판의 크기
	public static int rowSize;
	public static int colSize;
	
	public static int[][] plate; // 사각형 모양의 판
	
	public static int leftCheeseCount; // 남은 치즈의 수
	public static int lastLeftCheeseCount; // 모두 녹기 한 시간 전에 남아있는 치즈의 수
	public static int hour; // 치즈가 모두 녹는 데 걸리는 시간
	
	
	public static Queue<Node> queue;
	public static boolean[][] visited;
	
	// 상, 우, 하, 좌 이동 방향
	public static int[] deltaRow = { -1, 0, 1, 0 };
	public static int[] deltaCol = { 0, 1, 0, -1 };
	public static final int DIRECTION = 4; // 이동 방향의 수
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		st = new StringTokenizer(br.readLine().trim());
		
		rowSize = Integer.parseInt(st.nextToken());
		colSize = Integer.parseInt(st.nextToken());
		
		leftCheeseCount = 0;
		queue = new ArrayDeque<>();
		visited = new boolean[rowSize][colSize];
		
		plate = new int[rowSize][colSize];
		for(int rowIdx = 0; rowIdx < rowSize; rowIdx++) {
			st = new StringTokenizer(br.readLine().trim());
			
			for(int colIdx = 0; colIdx < colSize; colIdx++) {
				plate[rowIdx][colIdx] = Integer.parseInt(st.nextToken());
				leftCheeseCount += plate[rowIdx][colIdx];
			}
		}
		
		lastLeftCheeseCount = leftCheeseCount;
		
		
		
		// 배열 전체를 순회하면서 음수인 요소와 맞닿아 있는 치즈를 녹인다.
//		for(int rowIdx = 0; rowIdx < rowSize; rowIdx++) {
//			System.out.println(Arrays.toString(plate[rowIdx]));
//		}
		
		hour = 0;
		while(leftCheeseCount > 0) {
			visited = new boolean[rowSize][colSize];
//			System.out.println(leftCheeseCount);
			lastLeftCheeseCount = leftCheeseCount;
			
			queue.offer(new Node(0, 0)); findOutterAirArea();
			queue.offer(new Node(0, colSize-1)); findOutterAirArea();
			queue.offer(new Node(rowSize-1, 0)); findOutterAirArea();
			queue.offer(new Node(rowSize-1, colSize-1)); findOutterAirArea();
			
			hour++;
		}
		
		
		sb.append(hour).append("\n").append(lastLeftCheeseCount);
		System.out.println(sb);
	}
	
	
	public static void findOutterAirArea() {
		while(!queue.isEmpty()) {
			Node node = queue.poll();
			
			
			
			// 치즈를 만나면 탐색 종료
			if(plate[node.row][node.col] == 1) {
				plate[node.row][node.col] = 0;
				leftCheeseCount--;
				continue;
			}
			
			// 현재 위치가 공기라면 1 감소
			if(plate[node.row][node.col] == 0) {
				plate[node.row][node.col]--;
			}
			
			
			// 다음 위치를 찾는다.
			for(int dir = 0; dir < DIRECTION; dir++) {
				int newRow = node.row + deltaRow[dir];
				int newCol = node.col + deltaCol[dir];
				
				if(newRow < 0 || newRow >= rowSize || newCol < 0 || newCol >= colSize) {
					continue;
				}
				
				if(!visited[newRow][newCol]) {
					visited[newRow][newCol] = true;
					queue.offer(new Node(node.row + deltaRow[dir], node.col + deltaCol[dir]));
				}
			}
			
		}
	}
}
