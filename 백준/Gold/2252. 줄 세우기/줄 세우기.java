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
 * 
 * 
 * 1. 학생의 수(n), 비교 횟수(m)을 입력받는다.
 * 2. 각 학생의 정렬 처리 여부를 기록하는 길이가 n+1인 배열을 만든다.
 * 3. 뒤에 오는 학생들의 정보를 기억하는 길이가 m 배열을 만든다.
 * 4. m번 반복하여 키 비교 정보를 입력받는다.
 * 5. 앞에 오는 학생은 그대로 출력하고 처리 결과를 기록한다.
 * 6. 뒤에 오는 학생은 배열에 차곡차곡 저장한다.
 * 7. 뒤에 오는 학생 배열을 차례대로 출력한다.
 * 	7-1. 정렬처리가 되지 않은 학생의 번호만 출력
 * 8. 전체 정렬된 결과를 출력한다.
 */


public class Main {
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
	
	public static Node[] orderGraph;
	public static int[] inputDegree;
	public static Queue<Integer> queue;
	
	public static int sortedCount;
	public static boolean[] isSorted; // 각 학생의 정렬여부를 담는 배열
	
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		
		// 1. 학생의 수(n), 비교 횟수(m)을 입력받는다.
		st = new StringTokenizer(br.readLine().trim());
		numberOfStudents = Integer.parseInt(st.nextToken());
		compareCount = Integer.parseInt(st.nextToken());
		
		// 각종 초기화
		orderGraph = new Node[numberOfStudents + 1];
		inputDegree = new int[numberOfStudents + 1];
		queue = new ArrayDeque<>();
		sortedCount = 0;
		
		// 2. 각 학생의 정렬 처리 여부를 기록하는 길이가 n+1인 배열을 만든다.
		isSorted = new boolean[numberOfStudents + 1];
		

		
		// 4. m번 반복하여 키 비교 정보를 입력받는다.
		while(compareCount > 0) {
			st = new StringTokenizer(br.readLine().trim());
			
			int frontStudentNubmer = Integer.parseInt(st.nextToken()); 
			int backStudentNumber = Integer.parseInt(st.nextToken());
			
			// 입력받은 선후 관계를 바탕으로 인접 리스트를 만들어나간다.
			orderGraph[frontStudentNubmer] = new Node(backStudentNumber, orderGraph[frontStudentNubmer]);
			inputDegree[backStudentNumber]++; // 진입 차수도 계산해준다.
			
			
			compareCount--;
		}

		
		// 모든 학생들이 정렬 될 때가지 반복한다.
		while(sortedCount < numberOfStudents) {
			// 진입 차수가 0인 노드를 큐에 모두 넣는다.
			for(int idx = 1; idx <= numberOfStudents; idx++) {
				if(inputDegree[idx] == 0 && !isSorted[idx]) {
					queue.offer(idx);
					isSorted[idx] = true;
				}
			}
			
			
			// 큐에서 집입 차수가 0인 노드를 꺼내어 자신과 인접한 노드의 간선을 제거한다.
			if(!queue.isEmpty()) {
				sortedCount++;
				int stdNumber = queue.poll();
				sb.append(stdNumber).append(" ");
				
				// 자신과 인접한 노드를 찾는다.
				for(Node neighbor = orderGraph[stdNumber]; neighbor != null; neighbor = neighbor.next) {
					inputDegree[neighbor.to]--; // 진입 차수를 1 감소시킨다.
//					System.out.println("degree: " + inputDegree[neighbor.to]);
				}
			}
		}
		
		System.out.println(sb); // 8. 전체 정렬된 결과를 출력한다.
	}

}
