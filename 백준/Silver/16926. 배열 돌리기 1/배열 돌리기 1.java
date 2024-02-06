import java.io.*;
import java.util.*;

/**
 * 
 * @author JiYeon Sin
 * 바깥쪽에서 안쪽으로 들어가면서 회전을 반복한다.
 * 회전의 시작점은 (0, 0), (1, 1), (2, 2), …과 같은 방식으로 증가한다.
 * 다만 무한정으로 안쪽으로 들어가면 안 된다.
 * 새로운 시작점의 좌표가 각각 rowSize/2, colSize/2 미만이어야 한다.
 * (double로 계산하여 비교. 요점은 회전 시작점이 중앙을 넘어가면 안 된다는 것이다.)
 * 
 * 매 회전마다 이동해야하는 거리는 2씩 감소한다.
 * 초기 거리는 rowSize-1, colSize-1이다.
 * 
 * 1. delta 배열을 선언한다.
 *    이동은 하, 우, 상, 좌의 순서로 이뤄진다.
 * 2. 배열의 크기, 회전 수를 입력받는다.
 * 3. 배열을 할당하고 원소를 입력받는다.
 * 4. 시작좌표, 이동거리, 방향 등을 초기화한다.
 * 5. 회전 수만큼 반복한다. (아래 과정이 1회전)
 * 	6. 시작 좌표가 각각 rowSize/2, colSize/2 미만인지 확인한다.
 * 	7. 4방향으로 이동한다.
 * 	8. 현재 위치의 진짜 값(원래 회전 전에 있던 값)을 구한다.
 *     현재 위치가 시작점이라면 값을 그대로 사용하면 된다.
 *     아니라면, 이전에 구한 다음 요소의 값이 현재 위치의 진짜 값이다.
 * 	9. 다음 칸으로 이동한다.
 * 	10. 다음 칸의 값을 임시 저장한다.
 * 	11. 현재 위치의 값을 다음 칸에 넣는다.
 * 	12. 한 방향의 작업이 끝나면 방향을 전환한다.
 * 	13. 4방향 이동이 끝나면 행 이동거리, 열 이동거리를 2 감소시킨다.
 *  14. 시작 좌표를 1씩 증가시켜 안쪽으로 들어간다.
 * 15. 배열을 출력한다.
 */
public class Main {
	public static BufferedReader br;
	public static StringTokenizer st;
	public static StringBuilder sb;
	
	// 1. delta 배열을 선언한다.
	// 이동은 하, 우, 상, 좌의 순서로 이뤄진다.
	public static final int[] DELTA_ROW = { 1, 0, -1, 0 };
	public static final int[] DELTA_COL = { 0, 1, 0, -1 };
	public static final int DIRECTION_COUNT = 4; // 방향의 수
	
	public static int rowSize; // 배열의 행의 크기
	public static int colSize; // 배열의 열의 크기
	public static int rotationCount; // 회전 수
	
	public static int[][] array; // 회전대상이 되는 배열
	
	public static int startRow; // 회전 시작 행
	public static int startCol; // 회전 시작 열
	public static int curRow; // 현재 행의 위치
	public static int curCol; // 현재 열의 위치
	public static int rowMove; // 행 이동거리
	public static int colMove; // 열 이동거리
	
	public static int direction; // 이동방향(하, 우, 상, 좌)
	public static int preData; // 이전 위치의 값 
	public static int curData; // 현재 위치의 값
	public static int nextData; // 다음 위치의 값
	public static boolean isRowMove; // 행을 이동한다면 true, 열을 이동한다면 false
	public static int curMove; // 현재 이동해야하는 수
	
	
	
	
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		
		
		// 2. 배열의 크기, 회전 수를 입력받는다.
		st = new StringTokenizer(br.readLine().trim());
		rowSize = Integer.parseInt(st.nextToken());
		colSize = Integer.parseInt(st.nextToken());
		rotationCount = Integer.parseInt(st.nextToken());
		
		// 3. 배열을 할당하고 원소를 입력받는다.
		array = new int[rowSize][colSize];
		for(int rowIdx = 0; rowIdx < rowSize; rowIdx++) {
			st = new StringTokenizer(br.readLine().trim());
			for(int colIdx = 0; colIdx < colSize; colIdx++) {
				array[rowIdx][colIdx] = Integer.parseInt(st.nextToken());
			}
		}
		
		

		while(rotationCount > 0) {
			// 4. 시작좌표, 이동거리, 방향 등을 초기화한다.
			startRow = startCol = 0;
			rowMove = rowSize-1;
			colMove = colSize-1;
			direction = 0;
			isRowMove = true;
			
			// 5. 회전 수만큼 반복한다.
			rotateArray();
			rotationCount--;
		}
		
		
		// 15. 배열을 출력한다.
		for(int rowIdx = 0; rowIdx < rowSize; rowIdx++) {
			for(int colIdx = 0; colIdx < colSize; colIdx++) {
				sb.append(array[rowIdx][colIdx]).append(" ");
			}
			sb.append("\n");
		}
		System.out.println(sb);
		sb.setLength(0);
		
	}
	
	
	
	// 함수 한 번 호출 == 한 번 회전
	public static void rotateArray() {
		// 6. 시작 좌표가 각각 rowSize/2, colSize/2 미만인지 확인한다.
		// 미만인 동안 안쪽으로 들어가며 회전을 계속한다.
		while( (double)startRow < (double)rowSize/2 &&
		    (double)startCol < (double)colSize/2 ) {
			curRow = startRow;
			curCol = startCol;
			
			// 7. 4방향으로 이동한다.
			for(int move = 0; move < DIRECTION_COUNT; move++) {
				curMove = isRowMove ? rowMove : colMove;
				
				for(int i = 0; i < curMove; i++) {
					// 8. 현재 위치의 진짜 값(원래 회전 전에 있던 값)을 구한다.
					// 현재 위치가 시작점이라면 값을 그대로 사용하면 된다.
					// 아니라면, 이전에 구한 다음 요소의 값이 현재 위치의 진짜 값이다.
					if(curRow == startRow && curCol == startCol) {
						curData = array[curRow][curCol];						
					} else {
						curData = nextData;
					}
					
					// 9. 다음 칸으로 이동한다. 
					curRow += DELTA_ROW[direction];
					curCol += DELTA_COL[direction];
					
					
					nextData = array[curRow][curCol]; // 10. 다음 칸의 값을 임시 저장한다.
					array[curRow][curCol] = curData; // 11. 현재 위치의 값을 다음 칸에 넣는다.
				}
				
				// 12. 한 방향의 작업이 끝나면 방향을 전환한다.
				direction = (direction+1) % DIRECTION_COUNT; // 방향 전환 1
				isRowMove = !isRowMove; // 방향 전환 2
			}
			
			// 13. 4방향 이동이 끝나면 행 이동거리, 열 이동거리를 2 감소시킨다.
			rowMove -= 2;
			colMove -=2;
			
			// 14. 시작 좌표를 1씩 증가시켜 안쪽으로 들어간다.
			startRow++;
			startCol++;
		}
	}

}
