import java.util.*;
import java.io.*;

/**
 * 
 * @author JiYeon Sin
 * 자신보다 높이가 높거나 같은 탑 중 가장 가까운 탑의 번호를 알면 된다.
 * 2중 for문을 통해서도 이를 구할 수는 있지만 많은 시간이 걸린다.
 * 스택을 이용하면 탑의 번호를 빠르게 알아낼 수 있다.
 * 다음(오른쪽) 탑 들의 신호가 부딪힐 가능성이 있는 탑들을 스택에 삽입한다.
 * 
 * 1. 탑의 개수를 입력받는다.
 * 2. 탑의 높이를 순서대로 입력받는다.
 * 3. 자신의 높이와 스택의 top 원소의 높이를 비교한다.
 * 4. 자신보다 낮다면 top 원소를 pop한다.
 *    그 탑은 이후 탑들의 신호를 받을 일이 없기 때문이다.
 * 5. 자신보다 높다면 자신의 정보를 스택에 push한다.
 * 6. 탑 높이를 다 읽어들일 때까지 반복한다.
 * 
 */

public class Main {
	public static BufferedReader br;
	public static StringTokenizer st;
	public static StringBuilder sb;
	
	public static int numOfTop; // 탑의 수
	public static Stack<Integer> topStack = new Stack<Integer>(); // 탑의 높이를 저장
	public static Stack<Integer> indexStack = new Stack<Integer>(); // 탑의 인덱스를 저장
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		
		// 1. 탑의 개수를 입력받는다.
		numOfTop = Integer.parseInt(br.readLine().trim());
		
			
		st = new StringTokenizer(br.readLine().trim());

		
		// 2. 탑의 높이를 순서대로 입력받는다.
		for(int index = 0; index < numOfTop; index++) {
			int curHeight = Integer.parseInt(st.nextToken()); // 현재 탑의 높이
			
			if(topStack.isEmpty() ) {
				sb.append("0 ");
			} else {
				while(true) {
					if(topStack.peek() > curHeight) {
						sb.append(indexStack.peek() + " ");
						break;
					} else {
						topStack.pop();
						indexStack.pop();
					}
					
					if(topStack.isEmpty()) {
						sb.append("0 ");
						break;
					}
				}
			}
			
			topStack.push(curHeight);
			indexStack.push(index+1);
		}
		
		System.out.println(sb);
	}
}
