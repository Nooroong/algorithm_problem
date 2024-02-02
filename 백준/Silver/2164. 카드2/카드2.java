import java.io.*;
import java.util.*;

/**
 * 
 * @author JiYeon Sin
 * 위쪽에서는 카드가 제거되고 아래쪽에서는 카드가 제거된다.
 * 따라서 큐를 이용할 수 있다.
 * 
 * 1. 카드의 갯수 n을 입력받는다.(numOfCards)
 * 2. 큐를 만든다.
 * 3. 1~n을 큐에 넣는다.
 * 4. 반복을 돈다.
 * 	5. 큐에 남은 원소가 1개인지 확인한다.(n >= 1)
 *  6. 1개라면 남은 원소의 값을 출력하고 종료.
 *  7. 아니라면 dequeue하고 버린다.
 *  8. 한 번 더 dequeue를 하고 꺼낸 원소를 enqueue한다.
 */

public class Main {
	public static BufferedReader br;
	public static StringTokenizer st;
	
	
	public static int numOfCards; // 카드의 갯수
	public static Queue<Integer> queue;
	
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		
		
		// 1. 카드의 갯수 n을 입력받는다.(numOfCards)
		numOfCards = Integer.parseInt(br.readLine().trim());
		
		
		queue = new ArrayDeque<Integer>(); // 2. 큐를 만든다.
		
		// 3. 1~n을 큐에 넣는다.
		for(int num = 1; num <= numOfCards; num++) {
			queue.offer(num);
		}
		
		
		// 4. 반복을 돈다.
		while(true) {
			// 5. 큐에 남은 원소가 1개인지 확인한다.(n >= 1)
			if(queue.size() == 1) {
				// 6. 1개라면 남은 원소의 값을 출력하고 종료.
				System.out.println(queue.peek());
				return;
			}
			

			queue.poll(); // 7. 아니라면 dequeue하고 버린다.
			
			// 8. 한 번 더 dequeue를 하고 꺼낸 원소를 enqueue한다.
			int tmp = queue.poll();
			queue.offer(tmp);
		}
	}
}
