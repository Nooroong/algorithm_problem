import java.io.*;
import java.util.*;

/**
 * 
 * @author JiYeon Sin
 * 출력: 최대 코어 연결 시 전선길이의 최소합
 * 
 * 전선은 상, 하, 좌, 우 중 한 방향으로만 설치할 수 있다.
 * 전선이 교차하면 안 된다 -> 전선 정보를 배열에 표시해야 함
 * 코어 하나당 선택지는 5개(4방향 중 하나, 전선을 놓지 않는 경우) -> 조합
 * 
 * 완탐으로 시도하는 경우 O(5^n)... 이라고 하기에는 전선을 놓을 때마다
 * 다음 코어의 경우의 수가 줄어들게 되므로 실제로는 경우의 수가 더 줄어들게 될 것이다.
 * 
 * 기저조건: 모든 코어를 다 살펴본 경우 -> 연결된 코어수, 전선 수 확인 후 갱신 필요
 * 전선을 놓는 경우
 *  4방향으로 시도
 *   해당방향으로 가능한지 시도하여 가능하면 전선을 놓고 다음 코어로 넘어간다.
 *   불가능하면 다음방향을 시도한다.
 *  다음 코어 처리 후 돌아온 경우 전선을 지워줘야 한다.
 * 전선을 놓지 않는 경우
 *  다음 코어로 넘어간다.
 */
public class Solution {
	public static BufferedReader br;
	public static StringTokenizer st;
	public static StringBuilder sb;
	
	public static int testCase;
	
	public static int mapSize;
	public static int[][] map;
	
	public static final int CORE = 1;
	public static final int LINE = 2;
	public static final int BLANK = 0;
	
	public static int totalCoreCnt; // 안쪽에 있는 코어의 수
	public static int maxCoreCnt, minLineLen; // 최대 코어 수, 최소 전선 길이
	public static List<int[]> coreList; // 안쪽에 있는 코어들에 대한 리스트
	
	// 전선을 놓을 수 있는 방향
	public static int[] deltaRow = { -1, 0, 1, 0 };
	public static int[] deltaCol = { 0, 1, 0, -1 };
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		testCase = Integer.parseInt(br.readLine().trim());
		for(int tc = 1; tc <= testCase; tc++) {
			mapSize = Integer.parseInt(br.readLine().trim());
			
			// 각종 할당 및 초기화
			map = new int[mapSize][mapSize];
			coreList = new ArrayList<>();
			totalCoreCnt = 0;
			maxCoreCnt = 0;
			minLineLen = Integer.MAX_VALUE;
			
			for(int rowIdx = 0; rowIdx < mapSize; rowIdx++) {
				st = new StringTokenizer(br.readLine().trim());
				for(int colIdx = 0; colIdx < mapSize; colIdx++) {
					map[rowIdx][colIdx] = Integer.parseInt(st.nextToken());
					
					// 안쪽에 있는 코어라면 리스트에 저장한다.
					if(rowIdx > 0 && rowIdx < mapSize-1 && colIdx > 0 && colIdx < mapSize-1
					   && map[rowIdx][colIdx] == CORE) {
						coreList.add(new int[] {rowIdx, colIdx});
						totalCoreCnt++;
					}
				}
			}
			
			
			tryCoreConnect(0, 0, 0);
			
			
			sb.append("#").append(tc).append(" ").append(minLineLen);
			System.out.println(sb);
			sb.setLength(0);
		}
	}
	
	
	// 현재 코어에서 전선처리를 시도한다.
	// 현재 코어의 인덱스, 처리한 코어의 수, 전선 길이 합
	public static void tryCoreConnect(int index, int pCoreCnt, int lineSum) {
		// 가지치기: 앞으로 남은 코어를 다 처리하더라도 최대 처리 코어수를 넘을 수 없다면 종료
		if(pCoreCnt + (totalCoreCnt-index) < maxCoreCnt) return;
		
		// 기저조건: 모든 코어를 다 처리한 경우
		if(index == totalCoreCnt) {
			// 더 많은 코어를 연결한 경우
			if(pCoreCnt > maxCoreCnt) {
				maxCoreCnt = pCoreCnt;
				minLineLen = lineSum;
			} else if(pCoreCnt == maxCoreCnt) {
				// 지금 연결한 코어 수가 기존의 최대 연결 수와 같으면
				// 전선 최소 길이를 갱신한다.
				if(lineSum < minLineLen) {
					minLineLen = lineSum;
				}
			}
			
			return;
		}
		
		
		int[] curCore = coreList.get(index);
		int curRow = curCore[0];
		int curCol = curCore[1];
		
		
		// 전선을 놓는 경우: 4방향으로 시도
		for(int dir = 0; dir < 4; dir++) {
			if(isAvailable(curRow, curCol, dir)) {
				// 전선을 놓을 수 있는 경우 선을 깔고 다음 코어로 넘어간다.
				int line = setStatus(curRow, curCol, dir, LINE);
				tryCoreConnect(index+1, pCoreCnt+1, lineSum + line);
				
				setStatus(curRow, curCol, dir, BLANK); // 전선 지우기
			}
		}
		
		
		// 전선을 놓지 않는 경우
		tryCoreConnect(index+1, pCoreCnt, lineSum);
	}
	
	
	
	// (row, col)위치에서 dir 방향으로 전선을 놓을 수 있는가?
	public static boolean isAvailable(int row, int col, int dir) {
		int newRow = row;
		int newCol = col;
		
		while(true) {
			newRow += deltaRow[dir];
			newCol += deltaCol[dir];
			
			
			// map의 끝범위까지 왔다면 전선을 놓을 수 있다.
			if(newRow < 0 || newRow >= mapSize || newCol < 0 || newCol >= mapSize)
				return true;
			
			
			// 해당 위치에 코어나 전선이 있다면
			if(map[newRow][newCol] == CORE || map[newRow][newCol] == LINE)
				return false;
			
		}
	}
	
	
	
	// (row, col) 위치에서 dir 방향으로 set값으로 map을 설정
	// dir 방향으로 전선을 놓을 수 있는 경우에만 호출할 것이므로 여기서는 코어 여부 등을 체크할 필요가 없다.
	public static int setStatus(int row, int col, int dir, int set) {
		int newRow = row;
		int newCol = col;
		int lineCnt = 0; // 전선의 수
		
		while(true) {
			newRow += deltaRow[dir];
			newCol += deltaCol[dir];
			
			// map 범위를 벗어나면 중단.
			if(newRow < 0 || newRow >= mapSize || newCol < 0 || newCol >= mapSize)
				break;
			
			map[newRow][newCol] = set; // 전선을 놓거나 제거
			lineCnt++;
		}
		
		return lineCnt;
	}

}
