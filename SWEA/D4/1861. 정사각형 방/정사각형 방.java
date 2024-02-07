import java.io.*;
import java.util.*;

/**
 * 
 * @author JiYeon Sin
 * DFS를 통해 배열의 각 요소의 이동 거리를 구한다.
 * 탐색이 끝나면 최대 이동 거리와 요소의 인덱스를 갱신한다.
 * 
 * 1. 테스트 케이스 개수를 입력받는다.
 * 2. 테스트 케이스만큼 반복한다.
 * 3. 방의 크기를 입력받는다.
 * 4. 방의 숫자를 저장하는 배열을 생성하고 입력받는다.
 * 5. (0, 0)부터 (n, n)까지 배열의 모든 요소에 대해 반복을 돈다.
 * 6. dfs를 통해 각 요소에 대한 최대 이동거리를 구한다.(distance를 저장한다.)
 *  7. 상, 하, 좌, 우의 요소를 본다.
 *  8. 현재 위치가 배열의 범위를 벗어났거나 이전 위치의 값보다 1만큼 더 크지 않다면.
 *     distance-1과 maxDistance를 비교하고 maxDistance와 startRoomNum을 갱신하고 돌아간다.
 *     단, startRoomNum은 제일 작은 값이 들어가야 한다.
 * 9. 최대 이동거리를 가지는 좌표를 적절히 출력한다.
 */
public class Solution {
	public static BufferedReader br;
	public static StringBuilder sb;
	public static StringTokenizer st;
	
	public static int testCase; // 테스트 케이스의 수
	
	public static int roomSize; // 방의 크기 n
	public static int[][] room; // 방의 정보를 저장하는 배열
	public static int preRoomVal; // 이전에 방문한 방이 갖는 값
	
	
	public static int maxDistance; // 최대 이동 거리
	public static int curStartRoomNum; // 현재 시점에서 시작하는 방의 번호
	public static int maxDistanceStartRoomNum; // 최대 이동거리를 갖는 출발 방의 번호
	
	// 상, 하, 좌, 우
	public static int[] deltaRow = { -1, 1, 0, 0 };
	public static int[] deltaCol = { 0, 0, -1, 1 };
	
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		

		
		testCase = Integer.parseInt(br.readLine().trim()); // 1. 테스트 케이스 개수를 입력받는다.

		// 2. 테스트 케이스만큼 반복한다.
		for(int tc = 1; tc <= testCase; tc++) {
			maxDistance = Integer.MIN_VALUE;
			maxDistanceStartRoomNum = Integer.MAX_VALUE;
			
			
			roomSize = Integer.parseInt(br.readLine().trim()); // 3. 방의 크기를 입력받는다.
			
			// 4. 방의 숫자를 저장하는 배열을 생성하고 입력받는다.
			room = new int[roomSize][roomSize];
			for(int rowIdx = 0; rowIdx < roomSize; rowIdx++) {
				st = new StringTokenizer(br.readLine().trim());
				for(int colIdx = 0; colIdx < roomSize; colIdx++) {
					room[rowIdx][colIdx] = Integer.parseInt(st.nextToken());
				}
			}
			
			// 5. (0, 0)부터 (n, n)까지 배열의 모든 요소에 대해 반복을 돈다.
			for(int rowIdx = 0; rowIdx < roomSize; rowIdx++) {
				for(int colIdx = 0; colIdx < roomSize; colIdx++) {
					// 6. dfs를 통해 각 요소에 대한 최대 이동거리를 구한다.(distance를 저장한다.)
					curStartRoomNum = room[rowIdx][colIdx];
					preRoomVal = room[rowIdx][colIdx] - 1;
					
					dfs(rowIdx, colIdx, 1);
				}
			}
			
			// 9. 최대 이동거리를 가지는 좌표를 적절히 출력한다.
			sb.append("#").append(tc).append(" ").append(maxDistanceStartRoomNum).append(" ").append(maxDistance);
			System.out.println(sb);
			sb.setLength(0);
		}
	}
	
	
	// 파라미터: 현재 행 번호, 현재 열 번호, 지금까지 이동한 거리
	public static void dfs(int row, int col, int curDistance) {
		// 8. 현재 위치가 배열의 범위를 벗어났거나 이전 위치의 값보다 1만큼 더 크지 않다면.
		//    distance-1과 maxDistance를 비교하고 maxDistance와 startRoomNum을 갱신하고 돌아간다.
		if( (row < 0 || row >= roomSize || col < 0 || col >= roomSize) ||
			(room[row][col] != preRoomVal+1) ) {
			
			// 지금의 거리는 하나 더 넘어온 것이기에 1을 빼줘야 한다.
			// 최대 거리가 갱신된 경우에 시작점은 현재 위치가 된다.
			if(curDistance-1 > maxDistance) { 
				maxDistance = curDistance-1; 
				maxDistanceStartRoomNum = curStartRoomNum;
			} else if(curDistance-1 == maxDistance) {
				// 거리가 같을 때는 값이 작은 쪽이 저장된다.
				maxDistanceStartRoomNum = (curStartRoomNum < maxDistanceStartRoomNum) ? curStartRoomNum : maxDistanceStartRoomNum;
			}
			
			return;
		}
		
		
		// 7. 상, 하, 좌, 우의 요소를 본다.		
		for(int direction = 0; direction < 4; direction++ ) {
			preRoomVal = room[row][col];
			dfs(row+deltaRow[direction], col+deltaCol[direction], curDistance + 1);
		}
	}
}
