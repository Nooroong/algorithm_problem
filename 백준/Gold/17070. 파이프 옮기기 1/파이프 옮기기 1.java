import java.io.*;
import java.util.*;

/**
 * 
 * @author JiYeon Sin
 * (N, N)까지 오는 동안 어떻게 파이프를 배치했는 가에 따라 경우가 달라지게 된다.
 * 그러므로 기본적으로 완탐으로 해결해야 한다.
 * 이때 주어진 제한 시간이 1초 이므로 dp로 접근을 한다??
 * 
 * 1. 입력 및 초기화
 * 2. 0행에는 가로방향 파이프를 한 가지의 경우로만 놓울 수 있다.
 * 	2-1. 중간에 벽을 만나는 경우에는 더이상 파이프를 이동시킬 수 없다.
 * 3. 0열로는 파이프를 이동시킬 수 없다. 따라서 [1][1]부터 시작한다.
 * 	3-1. 현재 칸에 대각선 방향으로 파이프를 옮길 수 있다면
 * 		3-1-1. 대각선 윗칸의 가로 경우 수+세로 경우 수+대각선 경우 수를 저장 
 * 	3-2. 현재 칸에 가로, 세로 방향으로 파이프를 옮길 수 있다면
 * 		3-2-1. 가로: 왼쪽 칸의 가로 경우 수+대각선 경우 수를 저장
 * 		3-2-2. 세로: 윗칸의 세로 경우 수+대각선 경우 수를 저장
 * 4. 도착 지점의 가로 경우 수+세로 경우수+대각선 경우 수를 출력한다.
 */
public class Main {
	static BufferedReader br;
	static StringTokenizer st;
	
	static int houseSize, house[][];
	
	// 각 좌표에 놓을 수 있는 가로, 세로, 대각선 파이프의 수
	static int dpTable[][][];
	static final int HORIZONTAL = 0, VERTICAL = 1, DIAGONAL = 2;
	
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		
		// 1. 입력 및 초기화
		// 집의 크기를 입력받고 배열을 생성.
		houseSize = Integer.parseInt(br.readLine().trim());
		house = new int[houseSize][houseSize];
		dpTable = new int[houseSize][houseSize][3];
		
		// 집의 정보를 입력받는다.
		for(int rowIdx = 0; rowIdx < houseSize; rowIdx++) {
			st = new StringTokenizer(br.readLine().trim());
			
			for(int colIdx = 0; colIdx < houseSize; colIdx++) {
				house[rowIdx][colIdx] = Integer.parseInt(st.nextToken());
			}
		}
		
		
		// 2. 0행에는 가로방향 파이프를 한 가지의 경우로만 놓울 수 있다.
		for(int colIdx = 1; colIdx < houseSize; colIdx++) {
			if(house[0][colIdx] == 1) break; // 2-1. 중간에 벽을 만나는 경우에는 더이상 파이프를 이동시킬 수 없다.
			dpTable[0][colIdx][HORIZONTAL] = 1;
		}
		
		
		// 3. 0열로는 파이프를 이동시킬 수 없다. 따라서 [1][1]부터 시작한다.
		for(int rowIdx = 1; rowIdx < houseSize; rowIdx++) {
			for(int colIdx = 1; colIdx < houseSize; colIdx++) {
				
				// 3-1. 현재 칸에 대각선 방향으로 파이프를 옮길 수 있다면
				// (왼쪽칸, 윗칸, 대각선 윗칸이 빈 공간이라면)
				if(house[rowIdx-1][colIdx] == 0 && house[rowIdx][colIdx-1] == 0 && house[rowIdx][colIdx] == 0) {
					// 3-1-1. 대각선 윗칸의 가로 경우 수+세로 경우 수+대각선 경우 수를 저장 
					// (파이프 이동 방법을 보고 필요한 값을 더해줌녀 된다.)
					dpTable[rowIdx][colIdx][DIAGONAL] = dpTable[rowIdx-1][colIdx-1][HORIZONTAL] + 
														dpTable[rowIdx-1][colIdx-1][VERTICAL] + 
														dpTable[rowIdx-1][colIdx-1][DIAGONAL];
				}
				
				// 3-2. 현재 칸에 가로, 세로 방향으로 파이프를 옮길 수 있다면
				// (이전칸을 볼 필요가 없는 이유: 이전칸이 벽이라면 어차피 경우의 수가 0으로 기록되어 있다.)
				if(house[rowIdx][colIdx] == 0) {
					// 3-2-1. 가로: 왼쪽 칸의 가로 경우 수+대각선 경우 수를 저장
					dpTable[rowIdx][colIdx][HORIZONTAL] = dpTable[rowIdx][colIdx-1][HORIZONTAL] + 
															dpTable[rowIdx][colIdx-1][DIAGONAL];
					
					// 3-2-2. 세로: 윗칸의 세로 경우 수+대각선 경우 수를 저장
					dpTable[rowIdx][colIdx][VERTICAL] = dpTable[rowIdx-1][colIdx][VERTICAL] + 
														dpTable[rowIdx-1][colIdx][DIAGONAL];
				}
				
			}
		}
		
		
		// 4. 도착 지점의 가로 경우 수+세로 경우수+대각선 경우 수를 출력한다.
		int answer = dpTable[houseSize-1][houseSize-1][HORIZONTAL] + 
					dpTable[houseSize-1][houseSize-1][VERTICAL] + 
					dpTable[houseSize-1][houseSize-1][DIAGONAL];
		
		System.out.println(answer);
	}

}
