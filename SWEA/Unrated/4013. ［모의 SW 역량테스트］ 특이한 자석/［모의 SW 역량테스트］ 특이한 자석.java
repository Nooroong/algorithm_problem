import java.io.*;
import java.util.*;

/**
 * 
 * @author JiYeon Sin
 *  
 * 자석을 회전시킬 때, 인접한 자석과 맞닿은 부분의 자성을 확인한다.
 * 만약 인접 자석에 대해 연쇄 회전이 발생하면 그것과 또 인접한 자석들도 회전할 수 있는지 확인한다.
 * -> flood fill
 * 
 * 각 자석의 점수: 2^(자석 index)*(화살표가 가리키는 날의 값)
 * 
 * 자석 회전을 배열 값 이동으로 구현하기에는 복잡하고 연산횟수도 많아진다.
 * 따라서 자석을 돌리지 않고 빨간색 화살표를 이동시킨다.
 * 
 * 1. 입력을 받는다.
 * 2. 주어진 회전 정보를 바탕으로 자석을 돌려본다.
 * 	2-1. BFS를 통해 연쇄 회전 정보를 얻는다.
 * 	2-2. 문제에서 제시한 회전 정보를 큐에 넣는다.(첫 방문 처리)
 * 	2-3. 큐가 빌 때까지 반복(연쇄 회전)
 * 	2-4. 현재 자석에 대해 양 옆의 자석을 확인한다.
 * 	2-5. 범위를 벗어난 경우나 이미 방문한 자석은 pass
 * 	2-6. 현재 자석과 옆에 붙은 자석의 자성을 비교하고 둘이 다르면 옆 자석을 큐에 넣는다.
 * 	     (회전 방향은 현재 자석과 반대(-1*)임에 주의)
 * 	2-7. 큐가 비면 실제로 회전을 적용시킨다.
 * 3. 회전이 다 끝나면 점수 계산하여 출력한다.
 * 	
 */

public class Solution {
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;
	
	static int testCase;
	
	static int[] deltaCol = {-1, 1};
	
	static final int MAGNETIC_COUNT = 4,
					 SAW_COUNT = 8;
	
	static int turnCount; // 총 회전 횟수
	static int[][] turnInfo; // 자석 회전 정보
	
	static int[][] magnetic; // 각 자석의 정보
	static int[] arrowPointIdx; // 각 자석에 대해 빨간색 화살표가 가리키는 톱날 인덱스. 기본은 0
	
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();


		testCase = Integer.parseInt(br.readLine().trim());
		for(int tc = 1; tc <= testCase; tc++) {
			// 1. 입력을 받는다.
			input(); 
			
			// 2. 주어진 회전 정보를 바탕으로 자석을 돌려본다.
			turnMagnetic();
			
			// 3. 회전이 다 끝나면 점수 계산하여 출력한다.
			sb.append("#").append(tc).append(" ").append(returnTotalScore());
			System.out.println(sb);
			sb.setLength(0);
		}
	}
	
	
	
	static void input() throws IOException {
		magnetic = new int[MAGNETIC_COUNT][SAW_COUNT];
		arrowPointIdx = new int[MAGNETIC_COUNT];
		
		// 총 회전 횟수
		turnCount = Integer.parseInt(br.readLine().trim());
		
		// 각 자석의 자성 정보 입력받기
		for(int rowIdx = 0; rowIdx < MAGNETIC_COUNT; rowIdx++) {
			st = new StringTokenizer(br.readLine().trim());
			for(int colIdx = 0; colIdx < SAW_COUNT; colIdx++)
				magnetic[rowIdx][colIdx] = Integer.parseInt(st.nextToken());
		}
		
		// 자석 회전 정보를 입력받는다.
		turnInfo = new int[turnCount][2]; // 0열은 자석 번호, 1열은 회전 방향
		for(int idx = 0; idx < turnCount; idx++) {
			st = new StringTokenizer(br.readLine().trim());
			
			turnInfo[idx][0] = Integer.parseInt(st.nextToken()) - 1; // 문제에서 자석 번호가 1부터 시작함에 주의
			turnInfo[idx][1] = Integer.parseInt(st.nextToken());
		}
	}
	
	
	
	static void turnMagnetic() {
		// 2-1. BFS를 통해 연쇄 회전 정보를 얻는다.
		Queue<Integer> queue = new ArrayDeque<>(); // 큐에는 회전해야 할 자석의 인덱스가 들어간다.
		int[] turnValue = new int[MAGNETIC_COUNT]; // 각 자석들의 회전 방향을 저장하는 배열
		boolean[] visited = new boolean[MAGNETIC_COUNT]; // BFS 방문 배열
		
		// 주어진 회전 횟수만큼 자석을 돌린다.
		for(int turnIdx = 0; turnIdx < turnCount; turnIdx++) {
			Arrays.fill(turnValue, 0); // 회전 정보 초기화
			Arrays.fill(visited, false); // 방문 배열 초기화
			
			// 2-2. 문제에서 제시한 회전 정보를 큐에 넣는다.(첫 방문 처리)
			queue.offer(turnInfo[turnIdx][0]);
			visited[turnInfo[turnIdx][0]] = true;
			turnValue[turnInfo[turnIdx][0]] = turnInfo[turnIdx][1];
			
			// 2-3. 큐가 빌 때까지 반복(연쇄 회전)
			while(!queue.isEmpty()) {
				int curMagnetic = queue.poll(); // 현재 회전할 자석의 인덱스
				int curDirection = turnValue[curMagnetic]; // 현재 자석의 회전 방향
				
				
				// 2-4. 현재 자석에 대해 양 옆의 자석을 확인한다.
				for(int dir = 0; dir < deltaCol.length; dir++) {
					int newMagnetic = curMagnetic + deltaCol[dir];
					
					// 2-5. 범위를 벗어난 경우나 이미 방문한 자석은 pass
					if(!isAvailable(newMagnetic)) continue;
					if(visited[newMagnetic]) continue;
					
					// 2-6. 현재 자석과 옆에 붙은 자석의 자성을 비교하고 둘이 다르면 옆 자석을 큐에 넣는다
					if(isDifferent(curMagnetic, newMagnetic)) {
						queue.offer(newMagnetic);
						visited[newMagnetic] = true;
						turnValue[newMagnetic] = -1*curDirection; // 회전 방향은 현재 자석과 반대(-1*)임에 주의
					}

				}
			}
			
			// 2-7. 큐가 비면 실제로 회전을 적용시킨다.
			// 1은 시계방향을 의미하며, 실제로 화살표가 가리키는 인덱스는 현재 인덱스-1이 된다.
			// -1은 반시계방향을 의미하며, 실제로 화살표가 가리키는 인덱스는 현재 인덱스+1이 된다.
			// 따라서 -1을 곱해준 값을 더해준다.
			for(int idx = 0; idx < MAGNETIC_COUNT; idx++) {
				arrowPointIdx[idx] = mod(arrowPointIdx[idx] + turnValue[idx]*(-1),
										 SAW_COUNT);
			}
			
			
//			System.out.println(Arrays.toString(arrowPointIdx));
		}
	}
	
	
	
	static boolean isAvailable(int idx) {
		return (idx >= 0 && idx < MAGNETIC_COUNT);
	}
	
	
	
	static boolean isDifferent(int mag1, int mag2) {
		int mag1SawIdx, mag2SawIdx; // 맞붙은 톱니 인덱스
		
		if(mag1 < mag2) {
			mag1SawIdx = mod(arrowPointIdx[mag1]+2, SAW_COUNT);
			mag2SawIdx = mod(arrowPointIdx[mag2]-2, SAW_COUNT);
		} else {
			mag1SawIdx = mod(arrowPointIdx[mag1]-2, SAW_COUNT);
			mag2SawIdx = mod(arrowPointIdx[mag2]+2, SAW_COUNT);
		}
		
		
		return magnetic[mag1][mag1SawIdx] != magnetic[mag2][mag2SawIdx];
	}
	
	
	static int returnTotalScore() {
		int score = 0;
		
		for(int mIdx = 0; mIdx < MAGNETIC_COUNT; mIdx++) {
			// 각 자석의 점수: 2^(자석 index)*(화살표가 가리키는 날의 값)
			score += (int)Math.pow(2, mIdx) * magnetic[mIdx][arrowPointIdx[mIdx]];
		}
		
		return score;
	}
	
	
	
	// https://sugoring-it.tistory.com/113
	// 나머지 연산자는 피연산자에 따라 음수를 반환할 수도 있다.
	// 항상  0이상의 정수만 반환하도록 한다.
	static int mod(int num, int div) {
		int temp = num % div;
		if(temp < 0) temp += div;
		return temp;
	}
}
