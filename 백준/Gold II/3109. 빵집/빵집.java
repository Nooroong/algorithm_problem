import java.io.*;
import java.util.*;

/**
 * 
 * @author JiYeon Sin
 * 1. 배열의 크기를 입력받는다.
 * 2. 배열의 정보를 입력받는다.
 * 3. 0행부터 r-1행까지 0열부터 파이프 설치를 시도한다.
 * 4. 현재 칸에 파이프를 설치하고 유효한 칸으로 이동한다.
 * 5. 원웅이네 빵집까지 파이프를 설치했다면 플래그를 true로 설정하고 탐색을 종료한다.
 * 6. 한 행에 대한 탐색이 끝나면 파이프 라인 연결 여부를 확인하고 파이프 라인 수를 증가시킨다.
 * 7. 파이프 라인의 수를 출력한다.
 */
public class Main {
	public static BufferedReader br;
	public static StringTokenizer st;
	
	// /, -, \ 파이프에 따른 이동 방향
	public static int[] deltaRow = { -1, 0, 1 };
	public static int[] deltaCol = { 1, 1, 1 };
	public static char[] pipe = { '/', '-', '\\' }; // 최대한 많은 수의 파이프를 설치하기 위해 위쪽 방향부터 설치한다.
	public static final int DIRECTION = 3; // 이동 방향의 수
	
	public static int rowSize; // 배열의 행 크기
	public static int colSize; // 배열의 열 크기
	public static char[][] array; // 빵집 사이의 정보를 담는 배열
	
	public static boolean connectFlag; // 파이프 연결 성공 플래그
	public static int curPipeLineCnt; // 파이프 라인의 수
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		
		// 1. 배열의 크기를 입력받는다.
		st = new StringTokenizer(br.readLine().trim());
		rowSize = Integer.parseInt(st.nextToken());
		colSize = Integer.parseInt(st.nextToken());
		
		
		// 2. 배열의 정보를 입력받는다.
		array = new char[rowSize][colSize];
		for(int rowIdx = 0; rowIdx < rowSize; rowIdx++) {
			String line = br.readLine().trim();
			for(int colIdx = 0; colIdx < colSize; colIdx++) {
				array[rowIdx][colIdx] = line.charAt(colIdx);
			}
		}
		
		
		
		// 3. 0행부터 r-1행까지 0열부터 파이프 설치를 시도한다.
		curPipeLineCnt = 0;
		for(int rowIdx = 0; rowIdx < rowSize; rowIdx++) {
			connectFlag = false;
			setPipe(rowIdx, 0);
			
			// 6. 한 행에 대한 탐색이 끝나면 파이프 라인 연결 여부를 확인하고 파이프 라인 수를 증가시킨다.
			if(connectFlag) curPipeLineCnt++;
		}

		System.out.println(curPipeLineCnt); // 파이프 라인의 수를 출력한다.
	}
	
	
	
	// 파이프를 한 줄을 놓는 함수 (파라미터: 현재 위치)
	public static void setPipe(int curRow, int curCol) {		
		// 5. 원웅이네 빵집까지 파이프를 설치했다면 플래그를 true로 설정하고 탐색을 종료한다.
		// 재귀함수를 처음으로 호출했던 곳(main)으로 돌아오게 된다.
		if(curCol == colSize - 1) {
			array[curRow][curCol] = '-';
			connectFlag = true; // 파이프 라인 설치에 성공했다는 의미
			return;
		}
		
		
		// 4. 현재 칸에 파이프를 설치하고 유효한 칸으로 이동한다.
		int newRow, newCol; // 새롭게 이동할 위치
		for(int dir = 0; dir < DIRECTION; dir++) {
			// 이전 방식으로 파이프 연결에 성공했으면 더이상 다른 종류의 파이프를 연결할 필요가 없다.
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
				setPipe(newRow, newCol); // 새로운 위치로 이동한다.
			}
		}
	}
}

