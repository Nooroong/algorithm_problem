import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

/**
 * 
 * @author JiYeon Sin
 * 1. 사다리 정보를 입력할 10*10 크기의 배열을 선언한다.
 * 2. 테스트 케이스를 입력받는다.
 * 3. 사다리 정보를 입력받아 배열에 저장한다.
 * 4. 0열부터 99열까지 모든 열에 대해 탐색을 한다.(== 사다리를 탄다.)
 * 5. 0행 i열의 값이 1이라면 행번호를 +1한다.
 * 	  아니라면 다음 열로 넘어간다.
 * 6. '방향'에 따라 다르게 행동해줘야 한다.
 * 7-1. 방향이 아래쪽이라면 좌, 우에 1이 있는지 확인한다.
 * 	7-1. 1이 없다면 방향을 유지하며 나아간다.
 * 	7-2. 1이 있다면 방향을 전환하고 나아간다.
 * 8-1. 방향이 좌나 우라면 해당 방향으로 옆에 1이 있는지 확인한다.
 * 	8-2. 1이 있다면 방향을 유지하며 나아간다.
 * 	8-3. 1이 없다면 방향을 아래로 전환하고 나아간다.
 * 9. 7~8의 과정을 계속 반복한다.
 * 10. 마지막 행에 도달했다면 i열에 대한 사다리 타기를 종료한다.
 * 11. 이때 요소의 값이 2라면(X표시) 출발한 열의 번호를 출력하고
 * 	     반복문을 종료한다.
 * 
 */

public class Solution {
	static BufferedReader br;
	static StringTokenizer st;
	
	static int ladderSize = 100; // 사다리의 사이즈
	static int[][] ladder = new int[ladderSize][ladderSize]; // 사다리의
	
	// 하, 좌, 우 방향에 따른 이동 거리(?) 정의
	static int[] dRow = new int[] {1, 0, 0};
	static int[] dCol = new int[] {0, -1, 1};
	static int direction = 0; // 기본 방향은 하
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		
		int testCase = 10;
		
		
		for(int tc = 1; tc <= testCase; tc++) {
			br.readLine(); // 테스트 케이스를 입력받는다.
			
			// 사다리 사이즈에 맞게 사다리 정보를 입력받아 배열에 저장한다.
			for(int row = 0; row < ladderSize; row++) {
				st = new StringTokenizer(br.readLine().trim());
				for(int col = 0; st.hasMoreTokens(); col++) {
					ladder[row][col] = Integer.parseInt(st.nextToken());
				}
			}
			
			
			// 0열부터 99열까지 모든 열에 대해 탐색을 한다.
			// colIdx는 현재 '출발한' 열의 번호를 의미한다.
			for(int colIdx = 0; colIdx < ladderSize; colIdx++) {
				int curRow = 0; // 현재 행의 위치
				int curCol = colIdx; // 현재 열의 위치
				
				// 해당 열의 시작점이 0이라면 다음 열로 넘어간다.(내려갈 수 없는 경우) 
				if(curRow == 0 && ladder[curRow][colIdx] == 0) {
					continue;
				}
				
				
				// 내려갈 수 있다면
				// 현재 위치에서 현재 방향을 바탕으로 적절히 행동한다.
				// 언제까지?? 끝에 도달할 때까지
				while(curRow < (ladderSize - 1)) {
					
					if(direction == 0) { // 방향이 '하'라면
						// 일단 왼쪽이나 오른쪽으로 갈 수 있는지 확인한다.
						if(curCol - 1 >= 0 && ladder[curRow][curCol-1] == 1) { // 좌에 1이 있다면
							direction = 1; // 왼쪽으로 방향을 전환
						} else if(curCol + 1 < ladderSize && ladder[curRow][curCol+1] == 1) { // 우에 1이 있다면
							direction = 2; // 오른쪽으로 뱡향을 전환
						}
					} else if(direction == 1) { // 방향이 '좌'라면
						// 더이상 왼쪽으로 갈 수 없거나, 왼쪽으로 갈 수 있어도 그쪽에 1이 없다면
						if((curCol - 1 < 0) || 
							(curCol - 1 >= 0 && ladder[curRow][curCol-1] != 1)) {
							direction = 0; // 아래쪽으로 방향을 전환
						}
					} else { // 방향이 '우'라면
						// 더이상 오른쪽으로 갈 수 없거나, 오른쪽 갈 수 있어도 그쪽에 1이 없다면
						if((curCol + 1 >= ladderSize) ||
							(curCol + 1 < ladderSize && ladder[curRow][curCol+1] != 1)) {
							direction = 0; // 아래쪽으로 방향을 전환
						}
					}
					
					
					// 전환된 방향을 바탕으로 한 칸 이동한다.
					curRow += dRow[direction];
					curCol += dCol[direction];
				}
				
				
				
				// while문이 끝나 바깥으로 나왔다는 것은
				// 현재 나의 위치가 맨 마지막 행이라는 것이다.
				
				// 만약 도착지점에 도달한 경우
				if(ladder[curRow][curCol] == 2) {
					System.out.println("#" + tc + " " + colIdx); // 현재 반복에서 출발한 열의 위치를 적절히 출력하고.
					break; // 해당 테스트 케이스를 종료한다.
				}
			}
		}
	}

}
