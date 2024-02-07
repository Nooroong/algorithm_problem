import java.util.*;
import java.io.*;

/**
 * 
 * @author JiYeon Sin
 * 정렬기준은 다음과 같다.
 * 	1) 절댓값을 기준으로 정렬한다.
 * 	2) 절댓값이 같으면 실제 값을 기준으로 정렬한다.
 * 
 * 1. 연산의 개수를 입력받는다.
 * 2. 정수들을 저장할 우선순위 큐를 만든다.
 *    우선순의 큐의 정렬 기준은 위를 참고하여 작성한다.
 * 3. 연산의 개수만큼 반복하며 연산을 입력받는다.
 * 4. 입력된 값이 0인 경우.
 * 	4-1. 우선순위 큐가 비어있으면 0을 출력한다.
 * 	4-2. 비어있지 않다면 값을 뽑아내고 출력한다.
 * 5. 입력된 값이 0이 아닌 경우.
 * 	5-1. 값을 우선순위 큐에 넣어준다.
 * 
 */

public class Main {
	public static BufferedReader br;
	public static StringBuilder sb;
    
	public static PriorityQueue<Integer> minHeap; // 숫자들을 저장할 우선순위 큐
	
	public static int operationCnt; // 연산의 개수(횟수)
	public static int operation; // 입력된 연산
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
		
		
		// 1. 연산의 개수를 입력받는다.
		operationCnt = Integer.parseInt(br.readLine().trim());
		
		// 2. 정수들을 저장할 우선순위 큐를 만든다.
		minHeap = new PriorityQueue<Integer>(new Comparator<Integer>() {
			
			@Override
			public int compare(Integer o1, Integer o2) {
				// 1) 절댓값을 기준으로 정렬한다.
				if(Math.abs(o1) > Math.abs(o2)) return 1;
				else if(Math.abs(o1) < Math.abs(o2)) return -1;
				else { // 2) 절댓값이 같으면 실제 값을 기준으로 정렬한다.
					return (o1 > 0) ? 1 : -1;
				}
			}
		});
		
		
		
		// 3. 연산의 개수만큼 반복하며 연산을 입력받는다.
		while(operationCnt > 0) {
			operation = Integer.parseInt(br.readLine().trim());
			
			if(operation == 0) { // 4. 입력된 값이 0인 경우.
				if(minHeap.isEmpty()) { // 4-1. 우선순위 큐가 비어있으면 0을 출력한다.
					sb.append("0\n");
				} else { // 4-2. 비어있지 않다면 값을 뽑아내고 출력한다.
					sb.append(minHeap.poll()).append("\n");					
				}
				
			} else { // 5. 입력된 값이 0이 아닌 경우.
				minHeap.add(operation); // 5-1. 값을 우선순위 큐에 넣어준다.
			}
			operationCnt--;
		}
        
        System.out.println(sb);
	}
}

