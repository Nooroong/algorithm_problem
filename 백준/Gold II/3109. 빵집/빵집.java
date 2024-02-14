import java.io.*;
import java.util.*;

/**
 * 
 * @author JiYeon Sin
 * 1. 배열의 크기를 입력받는다.
 * 2. 배열의 정보를 입력받는다.
 * 3. 
 *
 */
public class Main {
	public static BufferedReader br;
	public static StringTokenizer st;
	
	// /, -, \ 파이프에 따른 이동 방향
	public static int[] deltaRow = { -1, 0, 1 };
	public static int[] deltaCol = { 1, 1, 1 };
	public static char[] pipe = { '/', '-', '\\' };
	public static final int DIRECTION = 3; // 이동 방향의 수
	
	public static int rowSize; // 배열의 행 크기
	public static int colSize; // 배열의 열 크기
	public static char[][] array; // 빵집 사이의 정보를 담는 배열
	
	public static boolean connectFlag; // 파이프 연결 성공 플래그
	public static int curPipeLineCnt; // 파이프 라인의 수
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		
		// 배열의 크기를 입력받는다.
		st = new StringTokenizer(br.readLine().trim());
		rowSize = Integer.parseInt(st.nextToken());
		colSize = Integer.parseInt(st.nextToken());
		
		
		// 배열의 정보를 입력받는다.
		array = new char[rowSize][colSize];
		for(int rowIdx = 0; rowIdx < rowSize; rowIdx++) {
			String line = br.readLine().trim();
			for(int colIdx = 0; colIdx < colSize; colIdx++) {
				array[rowIdx][colIdx] = line.charAt(colIdx);
			}
		}
		
		
		
		// 0행부터 r-1행까지 0열의 위치부터 파이프를 놓기 시작한다.
		curPipeLineCnt = 0;
		for(int rowIdx = 0; rowIdx < rowSize; rowIdx++) {
			connectFlag = false;
			setPipe(rowIdx, 0);
			
			if(connectFlag) curPipeLineCnt++;
		}
		
//		for(int rowIdx = 0; rowIdx < rowSize; rowIdx++) {
//			System.out.println(Arrays.toString(array[rowIdx]));
//		}
		
		System.out.println(curPipeLineCnt); // 파이프 라인의 수를 출력한다.
	}
	
	
	
	// 파이프를 한 줄을 놓는 함수
	// 파라미터: 현재 위치
	public static void setPipe(int curRow, int curCol) {		
		// 파이프를 원웅이네 빵집까지 놓은 경우, 다음 행의 0열부터 새롭게 파이프를 놓기 시작한다.
		if(curCol == colSize - 1) {
			array[curRow][curCol] = '-';
			connectFlag = true; // 파이프 라인 설치에 성공했다는 의미
			return;
		}
		
		
		
		int newRow, newCol; // 새롭게 이동할 위치
		for(int dir = 0; dir < DIRECTION; dir++) {
			if(connectFlag) {
				break;
			}
			// 현재 위치에 파이프를 놓는다.
			array[curRow][curCol] = pipe[dir];
			
			
			// 파이프를 따라 새롭게 이동하게 될 위치
			newRow = curRow + deltaRow[dir];
			newCol = curCol + deltaCol[dir];

			// 해당 위치가 유효하면서 파이프를 놓을 수 있다면
			if((newRow >= 0 && newRow < rowSize && newCol >= 0 && newCol < colSize) &&
			   (array[newRow][newCol] == '.')) {
				
				// 새로운 위치로 이동한다.
				// 파이프를 쭉 연결하여 원웅이네 빵집에 연결이 성공한 경우
				// 다른 종류의 파이프를 놓지 않고 현재 위치의 파이프를 고정시킨다.
				setPipe(newRow, newCol);
			}
		}
		
//		array[curRow][curCol] = '.';
//		return false; // 끝까지 파이프를 연결하지 못한경우
	}
}

