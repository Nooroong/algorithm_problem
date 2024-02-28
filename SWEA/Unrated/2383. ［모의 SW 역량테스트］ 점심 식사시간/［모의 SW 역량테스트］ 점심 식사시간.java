import java.io.*;
import java.util.*;

/**
 * 
 * @author SSAFY
 * 테케 50개 3초
 * 
 * 2개의 계단이 존재
 * 사람은 둘 중 어느 계단을 갈 것인지 선택해야 함(2^N. 사람 수는 최대 10명)
 * 부분집합??
 * 
 * 계단을 선택했으면 사람과 계단간의 이동거리를 계산
 * 계단과의 거리를 기준으로 우선순위 큐에 삽입을 한다.
 * 
 * 시간을 증가시키면서 
 */


public class Solution {
	public static BufferedReader br;
	public static StringTokenizer st;
	public static StringBuilder sb;
	
	public static int testCase;
	
	public static int mapSize;
	public static int[][] map;
	
	public static final int HUMAN = 1;
	public static final int STAIR_COUNT = 2;
	
	public static int humanCount; 
	public static List<Human> humanList;
	public static boolean[] elementUsedList;
	
	public static Stair[] stairList;
	public static int stairIndex;
	
	public static Queue<Integer> stair1pq;
	public static Queue<Integer> stair2pq;
	public static int stair1PersonCount;
	public static int stair2PersonCount;
	
	public static int minMoveTime;
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		testCase = Integer.parseInt(br.readLine().trim());
		
		
		for(int tc = 1; tc <= testCase; tc++) {
			humanList = new ArrayList<>();
			stairList = new Stair[STAIR_COUNT];
			stairIndex = 0;
			minMoveTime = Integer.MAX_VALUE;
			humanCount = 0;
			
			mapSize = Integer.parseInt(br.readLine().trim());
			
			map = new int[mapSize][mapSize];
			
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


			elementUsedList = new boolean[humanCount];
			powerSet(0);
			
			
			sb.append("#").append(tc).append(" ").append(minMoveTime);
			System.out.println(sb);
			sb.setLength(0);
		}
	}
	
	
	public static void powerSet(int selectIdx) {
		if(selectIdx == humanCount) {
			// 이동 완료 시간을 계산한다.
//			System.out.println(Arrays.toString(elementUsedList));
			int moveTime = calcMoveTime();
			minMoveTime = (moveTime < minMoveTime) ? moveTime : minMoveTime;
			return;
		}
		
		// 1번 계단을 선택한 경우
		elementUsedList[selectIdx] = true;
		powerSet(selectIdx + 1);
		
		// 2번 계단을 선택한 경우
		elementUsedList[selectIdx] = false;
		powerSet(selectIdx + 1);
	}
	
	
	
	public static int calcMoveTime() {
//		System.out.println("==============");
		// 큐는 계단 대기열
		stair1pq = new PriorityQueue<>();
		stair2pq = new PriorityQueue<>();
		
		// 두 집단은 elementUsedList가 true/false인 집단으로 구분할 수 있다.
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
		
		
		// 시간을 증가시키면서 계산
		int time = 0;
//		int stair1 = 0, stair2 = 0; // 0번째 비트는 계단 입구
		Queue<Integer> stair1 = new ArrayDeque<>();
		Queue<Integer> stair2 = new ArrayDeque<>();
		for (int i = 0; i < stairList[0].len; i++) {
			stair1.offer(0);
		}
		for (int i = 0; i < stairList[1].len; i++) {
			stair2.offer(0);
		}
		stair1PersonCount = stair2PersonCount = 0;
		
		int addPerson = 0;
		
		while(!stair1pq.isEmpty() || !stair2pq.isEmpty() || stair1PersonCount > 0 || stair2PersonCount > 0) {
			time++;
			
			
			
			// 계단 내려가기
			// 계단을 다 내려온 사람이 있다면
//			System.out.println("stair1 minus: " + (stair1/(int)Math.pow(10, stairList[0].len))%10);
//			System.out.println("stair2 minus: " + (stair2/(int)Math.pow(10, stairList[1].len))%10);
			stair1PersonCount -= stair1.poll();
			stair2PersonCount -= stair2.poll();
			
			

			// 계단에 도달
			addPerson = 0;
			while(!stair1pq.isEmpty()) {
				if(stair1PersonCount == 3) break;
				
				
				if(stair1pq.peek()+1 <= time) {
					addPerson++; // 계단에 들어온다.
					stair1PersonCount++;
					stair1pq.poll();
				} else break;
				
				
				
			}
			stair1.offer(addPerson);
			
			
			addPerson = 0;
			while(!stair2pq.isEmpty()) {
				if(stair2PersonCount == 3) break;
				
				if(stair2pq.peek()+1 <= time) {
					addPerson++; // 계단에 들어온다.
					stair2PersonCount++;
					stair2pq.poll();
				} else break;
				
				
				
			}
			stair2.offer(addPerson);
			

//			System.out.println(time + "m: " + stair1 + ", " + stair2);
//			System.out.println("person cnt: " + stair1PersonCount + ", " + stair2PersonCount);
		}

//		System.out.println("time: " + time);
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

