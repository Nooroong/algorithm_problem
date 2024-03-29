import java.io.*;
import java.util.*;

/**
 * 
 * @author JiYeon Sin
 * 언제 어떤 열을 선택하느냐에 따라 결과가 다 달라지므로
 * 순열을 통해 파괴할 열의 순서를 골라준다.
 * 이때 같은 열을 여러번 선택할 수 있으므로 '중복 순열'임에 주의한다.
 * 
 * 1. 입력을 받으면서 초기 블록의 개수를 센다.
 * 2. 중복 순열로 파괴 순서를 고른다.
 * 3. 파괴 순서를 다 골랐으면 선택한 열에 대해 순서대로 파괴를 하며 파괴블록 수를 구한다.
 * 4. 초기 블록 수 - 최대 파괴수를 출력한다.(출력은 남은 블록의 수!!!)
 *
 */
public class Solution {
	public static BufferedReader br;
	public static StringTokenizer st;
	public static StringBuilder sb;
	
	public static int testCase; // 테스트 케이스의 개수
	
	public static int initBlockCount; // 초기 블록의 수
	public static int ballCount; // 구슬의 개수
	public static int maxBlockCount; // 최대로 부순 블록의 개수
	
	public static int width, height; // 공간 전체의 크기
	public static int[][] map;
	public static int[][] copyMap;
	
	public static final int BLANK = 0;
	
	public static int[] deltaRow = { -1, 0, 1, 0 };
	public static int[] deltaCol = { 0, 1, 0, -1};
	
	public static int[] selectElementList; // 중복 순열로 고른 열 순서 
	
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		testCase = Integer.parseInt(br.readLine().trim());
		
		for(int tc = 1; tc <= testCase; tc++) {
			// 1. 입력을 받으면서 초기 블록의 개수를 센다.
			input();
			
			// 초기화
			maxBlockCount = Integer.MIN_VALUE;
			copyMap = new int[height][width];
			
			
			// 2. 중복 순열로 파괴 순서를 고른다.
			selectElementList = new int[ballCount];
			permutation(0);
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                           
			
			// 4. 초기 블록 수 - 최대 파괴수를 출력한다.(출력은 남은 블록의 수!!!)
			sb.append("#").append(tc).append(" ").append(initBlockCount - maxBlockCount);
			System.out.println(sb);
			sb.setLength(0);
		}
	}
	
	
	
	static void permutation(int selectIdx) {
		// 기저조건: 파괴할 인덱스 순서를 다 뽑은 경우
		// 3. 파괴 순서를 다 골랐으면 선택한 열에 대해 순서대로 파괴를 하며 파괴블록 수를 구한다.
		if(selectIdx == ballCount) {
			int blockCount = getDistroyCount(); // 선택한 순서대로 블록을 파괴했을 때의 파괴한 블록 개수를 구한다.
			maxBlockCount = Integer.max(blockCount, maxBlockCount); // 최대값을 갱신한다.
			
			return;
		}		
		
		
		// 다음 순서를 고른다.(중복 가능)
		for(int elementIdx = 0; elementIdx < width; elementIdx++) {
			selectElementList[selectIdx] = elementIdx;
			permutation(selectIdx+1);
			
		}
	}
	
	
	
	
	
	// 문제 입력받기
	static void input() throws IOException {
		initBlockCount = 0;
		
		st = new StringTokenizer(br.readLine().trim());
		
		ballCount = Integer.parseInt(st.nextToken());
		width = Integer.parseInt(st.nextToken());
		height = Integer.parseInt(st.nextToken());
		
		map = new int[height][width];
		for(int rowIdx = 0; rowIdx < height; rowIdx++) {
			st = new StringTokenizer(br.readLine().trim());
			
			for(int colIdx = 0; colIdx < width; colIdx++) {
				map[rowIdx][colIdx] = Integer.parseInt(st.nextToken());
				
				if(map[rowIdx][colIdx] > BLANK) initBlockCount++;
			}
				
		}
	}
	


	// 선택한 열에 대해 총 파괴 개수를 구한다.
	static int getDistroyCount() {
		copyMapInit(); // 파괴는 map 복사본에다가 한다.
		int distroySum = 0;
		
		for(int colIdx : selectElementList) {			
			distroySum += distroy(colIdx);
		}
		
		return distroySum;
	}
	
	
	// 해당 열에 대해 파괴 시도
	static int distroy(int colIdx) {
		// 블록은 연쇄적으로 파괴될 수 있다. 큐로 대기열을 관리한다.
		Queue<Point> queue = new ArrayDeque<>();
		int count = 0;
		
		// 일단 해당 열의 제일 윗 블록부터 파괴
		for(int rowIdx = 0; rowIdx < height; rowIdx++) {
			if(copyMap[rowIdx][colIdx] > BLANK) {
				// 블록을 연쇄 파괴 대기열에 넣는다.
				queue.offer(new Point(rowIdx, colIdx, copyMap[rowIdx][colIdx]));
				
				count++; // 파괴한 블록 개수 증가
				copyMap[rowIdx][colIdx] = BLANK; // 블록 파괴 처리
				break;
			}
		}
		
		// 연쇄 파괴를 시작한다.
		while(!queue.isEmpty()) {
			Point center = queue.poll();
			
			// 중심으로 부터 상, 하, 좌, 우에 대해 블록값-1만큼 파괴를 한다.
			// dist: center로부터 파괴 대상 거리
			for(int dist = 1; dist < center.value; dist++) {
				for(int dir = 0; dir < deltaRow.length; dir++) {
					int newRow = center.row + deltaRow[dir]*dist;
					int newCol = center.col + deltaCol[dir]*dist;
					
					// 배열 범위를 벗어간 경우와 빈칸은 패스
					if(!isAvailable(newRow, newCol)) continue;
					if(copyMap[newRow][newCol] == BLANK) continue;
					
					// 연쇄 파괴 대상도 대기열에 넣는다.
					queue.offer(new Point(newRow, newCol, copyMap[newRow][newCol]));
					copyMap[newRow][newCol] = BLANK;
					count++;
				}
			}
		}
		

		cleanBlank(); // 빈 공간을 정리해준다.
		
		return count;
	}
	
	
	// 블록 사이의 빈 공간 정리
	static void cleanBlank() {
		for(int colIdx = 0; colIdx < width; colIdx++) {
			for(int rowIdx = height-1; rowIdx >= 0; rowIdx--) {
				
				if(copyMap[rowIdx][colIdx] == BLANK) {
					// 빈 칸을 만나면 위로 쭉 올라가면서 유효한 값이 나오는 칸을 찾는다.
					for(int tempRow = rowIdx-1; tempRow >= 0; tempRow--) {
						// 유효한 다음 값을 찾으면 땡기기
						if(copyMap[tempRow][colIdx] > BLANK) {
							copyMap[rowIdx][colIdx] = copyMap[tempRow][colIdx];
							copyMap[tempRow][colIdx] = BLANK;
							break;
						}
					}
				}
				
			}
		}
	}
	
	
	// clone 메서드로 원본 배열의 값을 deep clone
	static void copyMapInit() {
		for(int rowIdx = 0; rowIdx < height; rowIdx++)
			copyMap[rowIdx] = map[rowIdx].clone();
	}
	
	
	static void mapPrint() {
		sb.setLength(0);
		for(int rowIdx = 0; rowIdx < height; rowIdx++) {
			for(int colIdx = 0; colIdx < width; colIdx++) {
				sb.append(map[rowIdx][colIdx]).append(" ");
			}
			sb.append("\n");
			
		}
		
		System.out.println(sb);
	}

	
	static boolean isAvailable(int row, int col) {
		return (row >= 0 && row < height && col >= 0 &&col < width);
	}
	
	static class Point {
		int row, col;
		int value;

		public Point(int row, int col, int value) {
			this.row = row;
			this.col = col;
			this.value = value; // 블록의 값 == 파괴 거리
		}
		
		
	}
}
