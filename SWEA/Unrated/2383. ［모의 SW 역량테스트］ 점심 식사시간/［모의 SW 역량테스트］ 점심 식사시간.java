import java.io.*;
import java.util.*;

/**
 * 
 * @author SSAFY
 * 모든 사람은 두 계단 중 하나를 선택해야 한다.
 * 계단이 선택되는 경우는 부분집합 코드를 활용하여 구한다.
 * 
 * 계단을 선택했으면 사람과 선택한 계단간의 이동거리를 계산하고
 * 계단과의 거리를 기준으로 우선순위 큐에 삽입을 한다.
 * 
 * 시간을 증가시키면서 문제에서 제시한 조건에 맞춰 사람들을 계단으로 내려보낸다.
 * 이때 계단에 있는 사람들의 정보는 덱을 이용하였다.
 * 덱의 크기는 계단의 길이와 같다.
 */


public class Solution {
	public static BufferedReader br;
	public static StringTokenizer st;
	public static StringBuilder sb;
	
	public static int testCase; // 테스트 케이스의 수
	
	public static int mapSize; // 방의 크기
	public static int[][] map;
	
	public static final int HUMAN = 1; // 사람을 나타내는 상수
	
	public static int humanCount; // 사람의 수
	public static List<Human> humanList; // 사람의 좌표 정보를 담는 리스트
	public static boolean[] elementUsedList; // 부분집합에 사용되는 변수
	
	public static Stair[] stairList; // 계단 정보를 담는 리스트
	public static int stairIndex;
	
	public static Queue<Integer> stair1pq; // 1번 계단 대기열
	public static Queue<Integer> stair2pq; // 2번 계단 대기열
	public static Queue<Integer> stair1State; // 1번 계단에 있는 사람 정보
	public static Queue<Integer> stair2State; // 2번 계단에 있는 사람 정보
	public static int stair1PersonCount; // 1번 계단에 있는 사람의 수
	public static int stair2PersonCount; // 2번 계단에 있는 사람의 수
	
	public static int minMoveTime; // 이동 완료 최소 시간
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		testCase = Integer.parseInt(br.readLine().trim()); // 테스트 케이스를 입력받는다.
		
		
		for(int tc = 1; tc <= testCase; tc++) {
			// 각종 초기화
			humanList = new ArrayList<>();
			stairList = new Stair[2];
			stairIndex = 0;
			minMoveTime = Integer.MAX_VALUE;
			humanCount = 0;
			
			
			// 방의 크기를 입력받고 배열을 할당한다.
			mapSize = Integer.parseInt(br.readLine().trim());
			map = new int[mapSize][mapSize];
			
			// 지도의 정보를 입력받으면서 사람 정보, 계단 정보를 리스트에 추가한다.
			for(int rowIdx = 0; rowIdx < mapSize; rowIdx++) {
				st = new StringTokenizer(br.readLine().trim());
				for(int colIdx = 0; colIdx < mapSize; colIdx++) {
					map[rowIdx][colIdx] = Integer.parseInt(st.nextToken());
					
					if(map[rowIdx][colIdx] == HUMAN) {
						humanList.add(new Human(rowIdx, colIdx));
						humanCount++;
					} else if(map[rowIdx][colIdx] > HUMAN) {
						stairList[stairIndex++] = new Stair(rowIdx, colIdx, map[rowIdx][colIdx]);
					}
				}
			}

			
			elementUsedList = new boolean[humanCount]; // 사람들이 선택한 계단 정보가 담긴다.
			powerSet(0);
			
			
			// 이동 완료 최소 시간을 출력한다.
			sb.append("#").append(tc).append(" ").append(minMoveTime);
			System.out.println(sb);
			sb.setLength(0);
		}
	}
	
	
	public static void powerSet(int selectIdx) {
		// 부분집합이 완성되면
		if(selectIdx == humanCount) {
			int moveTime = calcMoveTime(); // 이동 완료 시간을 계산한다.
			minMoveTime = (moveTime < minMoveTime) ? moveTime : minMoveTime; // 최소값 갱신
			
			return;
		}

		
		// 재귀호출: 1번 계단을 선택한 경우
		elementUsedList[selectIdx] = true;
		powerSet(selectIdx + 1);
		
		// 재귀호출: 2번 계단을 선택한 경우
		elementUsedList[selectIdx] = false;
		powerSet(selectIdx + 1);
	}
	
	
	
	// 선택된 계단 정보를 바탕으로 이동 완료 시간을 계산한다.
	public static int calcMoveTime() {
		// 계단 대기열 생성
		stair1pq = new PriorityQueue<>();
		stair2pq = new PriorityQueue<>();
		
		
		// 두 집단은 elementUsedList가 true/false인 집단으로 구분할 수 있다.
		// 사람과 계단과의 거리를 우선순위 큐에 삽입한다.
		for(int idx = 0; idx < humanCount; idx++) {
			if(elementUsedList[idx]) {
				int distance = Math.abs(humanList.get(idx).row - stairList[0].row) +
							   Math.abs(humanList.get(idx).col - stairList[0].col);
				stair1pq.offer(distance);
			} else {
				int distance = Math.abs(humanList.get(idx).row - stairList[1].row) +
						       Math.abs(humanList.get(idx).col - stairList[1].col);
				stair2pq.offer(distance);
			}
		}
		
		
		// 계단 정보 초기화
		stair1State = new ArrayDeque<>();
		for (int i = 0; i < stairList[0].len; i++)
			stair1State.offer(0);
			
		stair2State = new ArrayDeque<>();
		for (int i = 0; i < stairList[1].len; i++)
			stair2State.offer(0);
		
		stair1PersonCount = stair2PersonCount = 0; // 계단에 남아있는 사람의 수
		
		int addPerson = 0; // 계단에 추가되는 사람의 수
		
		
		
		int time = 0; // 시간을 증가시키면서 계산
		// 대기열이 비고 계단에 남은 사람이 없을 때까지 반복한다.
		while(!stair1pq.isEmpty() || !stair2pq.isEmpty() || stair1PersonCount > 0 || stair2PersonCount > 0) {
			time++;
	
			// 계단을 다 내려온 사람만큼 남은 사람 수에서 빼준다.
			stair1PersonCount -= stair1State.poll();
			stair2PersonCount -= stair2State.poll();
			
			

			// while문을 통해 계단에 새롭게 추가되는 사람들을 구해서 계단에 넣어준다.
			
			// 1번 계단에 사람이 도달한 경우 계산
			addPerson = 0;
			while(!stair1pq.isEmpty()) {
				// 계단에 사람이 꽉 찼으면 추가하지 않는다.
				if(stair1PersonCount == 3) break;
				
				// 바로 계단을 내려갈 수 있는 사람이 있다면
				if(stair1pq.peek()+1 <= time) {
					addPerson++; // 계단에 들어온다.
					stair1PersonCount++;
					stair1pq.poll(); // 이 사람은 대기열에서 빠진다.
				} else { // 계단을 내려갈 수 있는 사람이 없다면
					break;
				}
			}
			stair1State.offer(addPerson); // 새 사람을 추가해주면서 다른 사람들을 한 층씩 내려보낸다.
			
			
			// 위와 동일한 방식
			addPerson = 0;
			while(!stair2pq.isEmpty()) {
				if(stair2PersonCount == 3) break;
				
				if(stair2pq.peek()+1 <= time) {
					addPerson++; // 계단에 들어온다.
					stair2PersonCount++;
					stair2pq.poll();
				} else break;
			}
			stair2State.offer(addPerson);
			

		}

		return time;
	}
	
	
	
	
	static class Human {
		int row;
		int col;

		public Human(int row, int col) {
			super();
			this.row = row;
			this.col = col;
		}

		@Override
		public String toString() {
			return "Human [row=" + row + ", col=" + col + "]";
		}
	}
	
	static class Stair {
		int row;
		int col;
		int len;

		public Stair(int row, int col, int len) {
			super();
			this.row = row;
			this.col = col;
			this.len = len;
		}

		@Override
		public String toString() {
			return "Stair [row=" + row + ", col=" + col + ", len=" + len + "]";
		}
		
	}
}

