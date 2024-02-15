import java.util.*;
import java.io.*;

/**
 * 
 * @author JiYeon Sin
 * 이동할 수 있는 모든 경우의 수를 탐색하며 최소 이동 경로를 찾는다.
 * 가지치기
 * 
 * 1. 좌표 정보를 담는 클래스를 하나 만든다.
 * 2. 테스트 케이스의 개수를 입력받는다.
 * 3. 테스트 케이스의 수 만큼 반복한다.
 * 4. 고객의 수를 입력받는다.
 * 5. 회사, 각 고객의 위치, 집의 위치를 입력받는다.
 * 6. 회사에서부터 시작하여 가능한 모든 경우를 탐색한다.
 * 7. 최소 이동 경로를 출력한다.
 *
 */
public class Solution {
	// 1. 좌표 정보를 담는 클래스를 하나 만든다.
	static class Point {
		int x;
		int y;
		
		Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	public static BufferedReader br;
	public static StringTokenizer st;
	public static StringBuilder sb;
	
	public static int testCase; // 테스트 케이스 개수
	
	public static int numOfCustom; // 고객의 수
	public static Point[] nodeList; // 회사, 집, 고객의 위치 정보를 담는 배열
	public static boolean[] visited; // 각 집의 방문 여부를 기억
	
	public static int minDistance; // 최단 경로 이동거리
	
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		testCase = Integer.parseInt(br.readLine().trim()); // 2. 테스트 케이스의 개수를 입력받는다.
		
		
		// 3. 테스트 케이스의 수 만큼 반복한다.
		for(int tc = 1; tc <= testCase; tc++) {
			numOfCustom = Integer.parseInt(br.readLine().trim()); // 4. 고객의 수를 입력받는다.
			
			
			// 각종 초기화
			minDistance = Integer.MAX_VALUE;
			visited = new boolean[numOfCustom + 2]; // 인덱스 계산하기 귀찮으니까 회사랑 집도 포함
			
			// 5. 회사, 각 고객의 위치, 집의 위치를 입력받는다.
			nodeList = new Point[numOfCustom+2];
			st = new StringTokenizer(br.readLine().trim());
			int idx = 0;
			while(st.hasMoreTokens()) {
				nodeList[idx++] = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			}
			
			// 6. 회사에서부터 시작하여 가능한 모든 경우를 탐색한다.
			bt(0, 0, 0);
			
			
			// 7. 최소 이동 경로를 출력한다.
			sb.append("#").append(tc).append(" ").append(minDistance);
			System.out.println(sb);
			sb.setLength(0);
		}
	}
	
	
	// 파라미터: 현재 방문한 장소의 인덱스, 지금까지 방문한 고객의 수, 지금까지 이동한 총 거리
	public static void bt(int curIdx, int visitCount, int distance) {
		// 지금까지 구한 거리가   minDistance를 초과했다면 계속 탐색을 진행할 필요가 없다.
		// 따라서 탐색을 종료시킨다.
		if(distance > minDistance) {
			return;
		}
		
		// 종료조건: 모든 고객을 다 방문한 경우
		if(visitCount == numOfCustom) {
			// 마지막 고객(curIdx)과 김대리의 집 위치의 거리를 계산한다.
			int addedDistance = calcDistance(nodeList[curIdx], nodeList[1]); // 새롭게 추가되는 거리
			minDistance = Math.min(minDistance, distance+addedDistance);
			return;
		}
		
		
		// 아직 모든 고객을 다 방문하지 않았다면 다음으로 방문할 고객을 찾는다.
		for(int idx = 2; idx < numOfCustom+2; idx++) {
			// 아직 방문하지 않은 고객이 있다면	
			if(!visited[idx]) {
				// 현재 위치와 고객과의 거리를 계산하고
				int addedDistance = calcDistance(nodeList[curIdx], nodeList[idx]); // 새롭게 추가되는 거리

				// 방문을 한다.
				visited[idx] = true;
				bt(idx, visitCount + 1, distance + addedDistance);
				visited[idx] = false;
			}
		}
	}
	
	
	
	// 두 좌표 사이의 거리를 계산하여 반환한다.
	public static int calcDistance(Point p1, Point p2) {
		if(p1 != null && p2 != null) {
			return (int)Math.abs(p1.x - p2.x) + (int)Math.abs(p1.y - p2.y);			
		}
		return -1;
	}
}
