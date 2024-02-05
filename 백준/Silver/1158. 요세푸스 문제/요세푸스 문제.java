import java.io.*;
import java.util.*;

/**
 * 
 * @author JiYeon Sin
 * 큐를 이용하여 해결해본다.
 * 
 * 1. 사람 수 numOfPerson을 입력받는다.
 * 2. 삭제해야 하는 인덱스 delIdx를 입력받는다.
 * 3. 큐에 delIdx부터 1까지의 숫자를 넣는다.
 * 4. 큐에서 원소를 꺼내서 출력한다.
 * 5. delIdx-1 개 만큼의 원소를 꺼내고 다시 넣는다.
 * 6. 4~5의 과정을 큐가 빌 때까지 반복한다.
 * 7. 결과를 적절히 출력한다.  
 *
 */
public class Main {
	public static BufferedReader br;
	public static StringBuilder sb;
	public static StringTokenizer st;
	
	public static int numOfPerson; // 사람 수
	public static int delIdx; // 제거해야하는 대상
	public static Queue queue; // 사람들을 큐에 넣어 관리한다.
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		st = new StringTokenizer(br.readLine().trim());
		numOfPerson = Integer.parseInt(st.nextToken()); // 1. 사람 수 numOfPerson을 입력받는다. 
		delIdx = Integer.parseInt(st.nextToken()); // 2. 삭제해야 하는 인덱스 delIdx를 입력받는다.
		
		
		// 3. 큐에 delIdx부터 1까지의 숫자를 넣는다.
		queue = new ArrayDeque<Integer>();
		for(int idx = delIdx-1, cnt = 1; cnt <= numOfPerson; cnt++, idx++) {
				queue.offer(idx % (numOfPerson) + 1);
		}
		
//		while(!queue.isEmpty() ) {
//			System.out.print(queue.poll() + " ");
//		}
		
		// 6. 4~5의 과정을 큐가 빌 때까지 반복한다.  
		sb.append("<");
		while(!queue.isEmpty() ) {
			// 4. 큐에서 원소를 꺼내서 출력한다.
			sb.append(queue.poll());
			if(queue.size() >= 1) { // 마지막 원소 때는 , 를 출력하지 않는다.
				sb.append(", ");
			}
			
			
			// 5. delIdx-1 개 만큼의 원소를 꺼내고 다시 넣는다.
			for(int idx = 1; idx <= delIdx-1; idx++ ) {
				if(!queue.isEmpty()) {
					queue.offer(queue.poll());
				}
			}
		}
		
		
		// 7. 결과를 적절히 출력한다.
		sb.append(">");		
		System.out.println(sb);
	}
}
