import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 
 * @author SSAFY
 * (0, 0)부터 시작해서 막다른 길
 * (배열의 범위 바깥 or 0이 아닌 숫자가 들어있는 칸)
 * 을 만나면 방향을 전환한다.
 * 방향은 우, 하, 좌, 상을 반복한다.
 * 
 * 0. 테스트 케이스를 입력받는다.
 * 1. 달팽이의 크기(n)를 입력받는다.
 * 2. 달팽이 배열을 만든다.
 * 3. 각 칸에 들어갈 수를 담을 변수 count를 선언한다.
 * 4. 방향과 관련된 변수들을 선언해준다.
 * 5. (0, 0)부터 시작하여 배열을 돈다.
 * 6. count의 값을 배열에 넣어주고 count를 증가시킨다.
 * 7. 다음으로 이동할 칸이 배열 범위 바깥이거나, 0이 아닌 숫자가 들어있다면
 * 	    방향을 전환한다.
 * 8. 정해진 방향을 바탕으로 다음칸으로 이동한다.
 * 9. count가 n*n이 될 때까지 반복한다.
 * 10. 배열의 값을 출력한다. 
 *
 */

public class Solution {
	public static BufferedReader br;
	public static StringBuilder sb;
	
	static int snailSize; // 달팽이의 크기
	static int[][] snail; // 달팽이 배열
	static int count; // 각 칸에 들어갈 숫자
	
	// 이동과 관련된 변수들(우, 하, 좌, 상)
	static int[] deltaRow = { 0, 1, 0, -1 };
	static int[] deltaCol = { 1, 0, -1, 0 };
	static int direction = 0;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		
		
		int testCase = Integer.parseInt(br.readLine().trim()); // 테스트 케이스의 수를 입력받는다.
		
		
		for(int tc = 1; tc <= testCase; tc++) {
			snailSize = Integer.parseInt(br.readLine().trim()); // 달팽이 크기 입력받기
			snail = new int[snailSize][snailSize]; // 달팽이 배열 만들기
			count = 1; // 칸에 들어갈 숫자는 매 테스트 케이스마다 0으로 초기화
			direction = 0; // 방향도 항상 초기화
			
			
			
			int curRowIdx = 0;
			int curColIdx = 0;
			int nextRowIdx = 0;
			int nextColIdx = 0;
			
			// 반복은 count가 n*n이 될 때까지
			while(count <= snailSize*snailSize) {
				snail[curRowIdx][curColIdx] = count++; // 일단 현재 칸에 값을 넣어주고 count를 1증가.
				
				// 다음으로 이동할 좌표를 구한다.
				nextRowIdx = curRowIdx + deltaRow[direction];
				nextColIdx = curColIdx + deltaCol[direction];
				
				// 다음으로 이동할 칸이 배열 범위 바깥이거나, 0이 아닌 숫자가 들어있다면
				if((nextRowIdx < 0 || nextRowIdx >= snailSize || nextColIdx < 0 || nextColIdx >= snailSize) ||
					(snail[nextRowIdx][nextColIdx] != 0)) {
					direction = (direction + 1) % deltaRow.length; // 방향을 전환해준다.
				}
				
				// 정해진 방향을 바탕으로 다음칸으로 이동한다.
				curRowIdx += deltaRow[direction];
				curColIdx += deltaCol[direction];
			}
			
			// 배열의 값을 출력한다.
			sb.append("#").append(tc).append("\n");
			for(int rowIdx = 0; rowIdx < snailSize; rowIdx++) {
				for(int colIdx = 0; colIdx < snailSize; colIdx++) {
					sb.append(snail[rowIdx][colIdx]).append(" ");
				}
				sb.append("\n");
			}
			System.out.print(sb);
			sb.setLength(0);
		}
	}
}
