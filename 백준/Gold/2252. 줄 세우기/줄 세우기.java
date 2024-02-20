import java.util.*;
import java.io.*;

/**
 * 
 * @author JiYeon Sin
 * 입력된 정보를 바탕으로 그래프를 만들고 그래프 정보를 바탕으로 위상 정렬을 시도한다.
 * 최대 학생 수가 32000명이기 때문에 인접 행렬로 그래프를 표현하는 것은 적절하지 않다.
 * 길이가 n개인 인접 리스트 배열을 이용해 그래프를 표현한다.
 * 
 * 
 * 1. 학생의 수(n), 비교 횟수(m)을 입력받는다.
 * 2. 각종 변수들을 초기화 해준다.
 * 3. m번 반복하여 키 비교 정보를 입력받는다.
 * 4. 입력받은 선후 관계를 바탕으로 인접 리스트를 만들어나가고, 각 학생의 진입 차수도 계산한다.
 * 5. 모든 학생들이 정렬 될 때가지 반복한다.
 * 6. 진입 차수가 0인 노드를 큐에 모두 넣는다.
 * 7. 큐에서 집입 차수가 0인 노드를 꺼낸다.(== 큐가 빌 때까지 노드를 꺼낸다.)
 * 8. 큐에서 꺼낸 학생 번호를 출력 결과에 추가시키고, 정렬된 학생의 수를 1 증가시킨다.
 * 9. 지금 정렬된 학생과 인접한 노드를 찾는다.
 * 10. 인접 노드의 진입 차수를 1 감소시킨다.
 * 11. 전체 결과를 출력한다.
 */


public class Main {
	// 인접 리스트의 노드
	static class Node {
		int to; // 연결된 학생의 정보
		Node next; // 다음 노드를 가리키는 포인터
		
		Node(int to, Node next) {
			this.to = to;
			this.next = next;
		}
		
	}
	
	public static BufferedReader br;
	public static StringTokenizer st;
	public static StringBuilder sb;
	
	
	public static int numberOfStudents; // 학생들의 수
	public static int compareCount; // 키 비교 횟수
	
	public static Node[] orderGraph; // 정렬 정보가 담긴 유향 그래프
	public static int[] inputDegree; // 각 학생들의 진입 차수
	public static Queue<Integer> queue; // 위상 정렬을 위한 큐
	
	public static int sortedStudentCount; // 정렬된 학생의 수
	
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		
		// 1. 학생의 수(n), 비교 횟수(m)을 입력받는다.
		st = new StringTokenizer(br.readLine().trim());
		numberOfStudents = Integer.parseInt(st.nextToken());
		compareCount = Integer.parseInt(st.nextToken());
		
		// 2. 각종 변수들을 초기화 해준다.
		orderGraph = new Node[numberOfStudents + 1];
		inputDegree = new int[numberOfStudents + 1];
		queue = new ArrayDeque<>();
		sortedStudentCount = 0;
		


		
		// 3. m번 반복하여 키 비교 정보를 입력받는다.
		while(compareCount > 0) {
			st = new StringTokenizer(br.readLine().trim());
			
			int frontStudentNubmer = Integer.parseInt(st.nextToken()); 
			int backStudentNumber = Integer.parseInt(st.nextToken());
			
			// 4. 입력받은 선후 관계를 바탕으로 인접 리스트를 만들어나가고, 각 학생의 진입 차수도 계산한다.
			orderGraph[frontStudentNubmer] = new Node(backStudentNumber, orderGraph[frontStudentNubmer]);
			inputDegree[backStudentNumber]++; // 진입 차수 계산
			
			// 문제에서 출력을 보면 학생 번호를 기준으로 내림차순 순서로 인접 리스트에 노드를 넣은 것 같다.
			// 그러나 이는 문제에서 요구하는 사항이 아니기에 편한대로 그냥 head에 노드를 추가한다.
			
			compareCount--;
		}

		
		// 5. 모든 학생들이 정렬 될 때가지 반복한다.
		while(sortedStudentCount < numberOfStudents) {
			// 6. 진입 차수가 0인 노드를 큐에 모두 넣는다.
			for(int stdNum = 1; stdNum <= numberOfStudents; stdNum++) {
				if(inputDegree[stdNum] == 0) {
					inputDegree[stdNum]--; // 해당 학생은 정렬이 되었으므로 차수를 음수로 바꿔준다.
					queue.offer(stdNum); // 큐에 넣기
				}
			}
			
			
			// 7. 큐에서 집입 차수가 0인 노드를 꺼낸다.(== 큐가 빌 때까지 노드를 꺼낸다.)
			while(!queue.isEmpty()) {
				int stdNumber = queue.poll();

				
				// 8. 큐에서 꺼낸 학생 번호를 출력 결과에 추가시키고, 정렬된 학생의 수를 1 증가시킨다.
				sb.append(stdNumber).append(" ");
				sortedStudentCount++;
				
				
				// 9. 지금 정렬된 학생과 인접한 노드를 찾는다.
				for(Node neighbor = orderGraph[stdNumber]; neighbor != null; neighbor = neighbor.next) {
					inputDegree[neighbor.to]--; // 10. 인접 노드의 진입 차수를 1 감소시킨다.
				}
			}
		}
		
		System.out.println(sb); // 11. 전체 결과를 출력한다.
	}
}
