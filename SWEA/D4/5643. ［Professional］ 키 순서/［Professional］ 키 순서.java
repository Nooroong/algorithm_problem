import java.io.*;
import java.util.*;

/**
 * 
 * @author JiYeon Sin
 * 주어진 정보를 바탕으로 문제 예시처럼 방향 그래프를 구성한다.
 * 자신의 키 순서를 알 수 있다 -> (조상 노드의 수 + 자손 노드의 수 == 학생수-1)
 * 학생수가 최대 500명이므로 인접 리스트를 사용.
 * 
 * 조상 노드의 수는 문제에서 준 정보를 그대로 이용하고 타고타고 올라가면 된다.
 * 자손 노드는 역방향을 저장하는 인접 리스트를 하나 만들고 이를 이용한다.
 * 
 *
 */
public class Solution {
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;
	
	static int testCase;
	
	static int studentCount, compareCount; // 학생의 수, 키 비교횟수
	
	static List<Integer>[] adjList, reverseAdjList;
	
	static Queue<Integer> queue;
	static boolean[] visited;
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		testCase = Integer.parseInt(br.readLine().trim());
		for(int tc = 1; tc <= testCase; tc++) {
			// 초기화, 입력 처리
			input();
			
			int answer = 0;
			
			// 모든 노드에 대해 조상의 수와 자손의 수를 구한다.
			for(int idx = 0; idx < studentCount; idx++) {				
				int sum = getAncesterCount(idx) + getDescendantCount(idx);
				
				// 조상 + 자손 == 학생수-1 이라면 answer++
				if(sum == studentCount-1)
					answer++;
			}
				
			
			sb.append("#").append(tc).append(" ").append(answer);
			System.out.println(sb);
			sb.setLength(0);
			
		}
		
	}
	
	
	static void input() throws IOException {
		studentCount = Integer.parseInt(br.readLine().trim());
		compareCount = Integer.parseInt(br.readLine().trim());
		
		// 인접 리스트 초기화
		adjList = new List[studentCount];
		reverseAdjList = new List[studentCount];
		for(int idx = 0; idx < studentCount; idx++) {
			adjList[idx] = new ArrayList<Integer>();
			reverseAdjList[idx] = new ArrayList<Integer>();
		}
		
		
		// 비교 정보를 바탕으로 인접 리스트를 구성한다.
		for(int c = 0; c < compareCount; c++) {
			// 문제에서 학생 번호는 1부터 시작함에 유의한다.
			st = new StringTokenizer(br.readLine().trim());
			int from = Integer.parseInt(st.nextToken()) - 1;
			int to = Integer.parseInt(st.nextToken()) - 1;
			
			
			adjList[from].add(to);
			reverseAdjList[to].add(from); // 역방향
		}
		
		queue = new ArrayDeque<>();
		visited = new boolean[studentCount];
	}
	
	
	
	
	static int getAncesterCount(int studentIdx) {
		// 순방향 인접 리스트를 이용하여 BFS
		int count = 0;
		Arrays.fill(visited, false);
		
		queue.offer(studentIdx);
		visited[studentIdx] = true;
		
		while(!queue.isEmpty()) {
			int curStudent = queue.poll();
			count++;
			
			for(int idx = 0; idx < adjList[curStudent].size(); idx++) {
				int nextStudent = adjList[curStudent].get(idx);
				
				if(visited[nextStudent]) continue;
				
				queue.offer(nextStudent);
				visited[nextStudent] = true;
			}
		}
		
		// 자기 자신은 빼야한다.
		return count-1;
	}
	
	
	static int getDescendantCount(int studentIdx) {
		// 역방향 인접 리스트를 이용하여 BFS
		int count = 0;
		Arrays.fill(visited, false);
		
		queue.offer(studentIdx);
		visited[studentIdx] = true;
		
		while(!queue.isEmpty()) {
			int curStudent = queue.poll();
			count++;
			
			for(int idx = 0; idx < reverseAdjList[curStudent].size(); idx++) {
				int nextStudent = reverseAdjList[curStudent].get(idx);
				
				if(visited[nextStudent]) continue;
				
				queue.offer(nextStudent);
				visited[nextStudent] = true;
			}
		}
		
		
		return count-1;
	}
}
