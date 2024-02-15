import java.util.*;
import java.io.*;

/**
 * 
 * @author JiYeon Sin
 * 1. 전차의 방향과 방향에 따른 상태를 정의해놓는다.
 * 2. 테스트 케이스의 수를 입력받는다.
 * 3. 맵의 크기를 입력받는다.
 * 4. 맵 배열을 할당하고 맵의 정보를 입력받는다.
 * 5. 맵 정보를 입력받으면서 전차의 위치와 상태를 얻는다.
 * 6. 사용자 입력의 개수를 입력받는다.
 * 7. 사용자 입력을 받는다.
 * 8. 사용자 입력을 하나씩 적절하게 처리한다.
 * 9. 입력을 모두 처리한 결과를 적절히 출력한다.
 *
 */
public class Solution {
	public static BufferedReader br;
	public static StringTokenizer st;
	public static StringBuilder sb;
	
	
	public static int testCase; // 테스트 케이스의 수
	
	public static int height, width; // 게임 맵의 크기
	public static char[][] gameMap; // 게임 맵
	
	public static int userInputNum; // 사용자 입력의 수
	public static String userInput; // 사용자 입력 문자열
	
	public static int tankRow; // 전차의 현재 행 번호
	public static int tankCol; // 전차의 현재 열 번호
	
	
	// 1. 전차의 방향과 방향에 따른 상태를 정의해놓는다.
	// 전차의 방향 정보(상, 우, 하, 좌)
	public static final int UP = 0; 
	public static final int RIGHT = 1;
	public static final int DOWN = 2;
	public static final int LEFT = 3;
	public static int tankDirection; // 전차의 현재 방향
	
	// 상, 우, 하, 좌로의 이동 값 및 전차의 상태
	public static int[] deltaRow = { -1, 0, 1, 0 };
	public static int[] deltaCol = { 0, 1, 0, -1 };
	public static char[] tankState = { '^', '>', 'v', '<' };
	
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		
		testCase = Integer.parseInt(br.readLine().trim()); // 2. 테스트 케이스의 수를 입력받는다.
		
		for(int tc = 1; tc <= testCase; tc++) {
			st = new StringTokenizer(br.readLine().trim());
			
			// 3. 맵의 크기를 입력받는다.
			height = Integer.parseInt(st.nextToken());
			width = Integer.parseInt(st.nextToken());
			
			// 4. 맵 배열을 할당하고 맵의 정보를 입력받는다.
			gameMap = new char[height][width];
			for(int rowIdx = 0; rowIdx < height; rowIdx++) {
				String line = br.readLine().trim();
				for(int colIdx = 0; colIdx < width; colIdx++) {
					gameMap[rowIdx][colIdx] = line.charAt(colIdx);
					
					
					// 5. 맵 정보를 입력받으면서 전차의 위치와 상태를 얻는다.
					for(int dir = 0; dir < 4; dir++) {
						if(gameMap[rowIdx][colIdx] == tankState[dir]) {
							tankRow = rowIdx;
							tankCol = colIdx;
							tankDirection = dir;
							break;
						}
					}
				}
			}
			
			userInputNum = Integer.parseInt(br.readLine().trim()); // 6. 사용자 입력의 개수를 입력받는다.
			userInput = br.readLine().trim(); // 7. 사용자 입력을 받는다.
			
			
			// 8. 사용자 입력을 하나씩 적절하게 처리한다.
			for(int idx = 0; idx < userInput.length(); idx++) {
				switch(userInput.charAt(idx)) {
					case 'U': 
						tankDirection = UP;
						commandMove();
						break;
					case 'D':
						tankDirection = DOWN;
						commandMove();
						break;
					case 'L':
						tankDirection = LEFT;
						commandMove();
						break;
					case 'R':
						tankDirection = RIGHT;
						commandMove();
						break;
					case 'S':
						commandShoot();
				}
			}
			
			
			
			// 9. 입력을 모두 처리한 결과를 적절히 출력한다.
			sb.append("#").append(tc).append(" ");
			for(int rowIdx = 0; rowIdx < height; rowIdx++) {
				for(int colIdx = 0; colIdx < width; colIdx++) {
					sb.append(gameMap[rowIdx][colIdx]);
				}
				sb.append("\n");
			}
			
			System.out.print(sb);
			sb.setLength(0);
		}
	}
	
	
	public static void commandMove() {
		// U, R, D, L 명령어는 방향만 다를 뿐, 수행하는 절차는 유사하다.
		// 따라서 전차의 방향 정보를 바탕으로 동일하게 처리해준다.
		
		
		// 전차가 새롭게 이동할 위치
		int newRow = tankRow + deltaRow[tankDirection];
		int newCol = tankCol + deltaCol[tankDirection];
		
		
		// 바라보는 방향의 다음칸으로 이동할 수 있다면, (배열 범위 주의)
		if( (newRow >= 0 && newRow < height && newCol >= 0 && newCol < width) &&
		    (gameMap[newRow][newCol] == '.') ) {
			gameMap[newRow][newCol] = tankState[tankDirection]; // 이동
			gameMap[tankRow][tankCol] = '.'; // 이전에 있었던 위치는 평지로 바꿔준다.
			
			// 위치 변경
			tankRow = newRow;
			tankCol = newCol;
		} else {
			// 이동할 수 없다면 현재 위치에서 방향만 전환한다.
			gameMap[tankRow][tankCol] = tankState[tankDirection];
		}
	}
	
	
	public static void commandShoot() {
		// 전차가 현재 바라보고 있는 방향으로 포탄을 쏜다.
		
		int shotRow = tankRow, shotCol = tankCol; // 탄환의 위치 
		
		// 탄환이 게임 맵을 벗어날 때까지 반복
		while(shotRow >= 0 && shotRow < height && shotCol >= 0 && shotCol < width) {
			// 포탄이 벽돌 벽에 무딪히면 해당 위치가 평지로 바뀌고 포탄이 소멸한다.
			if(gameMap[shotRow][shotCol] == '*') {
				gameMap[shotRow][shotCol] = '.';
				break;
			}
			// 포탄이 강철 벽에 부딪히면 아무일도 일어나지 않고 포탄이 소멸한다.
			else if(gameMap[shotRow][shotCol] == '#') {
				break;
			}
			
			// 포탄 이동
			shotRow += deltaRow[tankDirection];
			shotCol += deltaCol[tankDirection];
		}
	}
}
