import java.io.*;
import java.util.*;

/**
 * 
 * @author JiYeon Sin
 * 연산자 노드는 자식 노드 2개를 가져야 한다.
 * 피연산자 노드는 자식 노드를 가지면 안 된다.(리프노드)
 * 
 * 1. 총 테스트 케이스 개수만큼 반복한다.
 * 2. 노드의 수 n을 입력받는다.
 * 3. 계산 가능 여부를 저장하는 플래그 변수를 초기화한다.
 * 4. n만큼 반복하며 각 노드의 정보를 순서대로 읽어들인다.
 * 5. 연산자 노드인데 자식 노드가 없거나, 피연산자 노드인데 자식 노드를 갖고 있다면
 *    플래그 값을 false로 바꾸고 나머지 입력값들을 흘려버린다.
 * 6. 입력받기가 끝나면 플래그의 값에 따라 결과를 적절히 출력한다. 
 */
public class Solution {
	public static StringBuilder sb;
	public static StringTokenizer st;
	public static BufferedReader br;
	
	public static final int TEST_CASE = 10; // 총 테스트 케이스 개수
	
	public static int numOfNode; // 노드의 수
	public static boolean canCalculate; // 계산 가능 여부
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		
		// 1. 총 테스트 케이스 개수만큼 반복한다.
		for(int tc = 1; tc <= TEST_CASE; tc++) {
			
			// 2. 노드의 수를 입력받는다.
			numOfNode = Integer.parseInt(br.readLine().trim());
			
			// 3. 계산 가능 여부를 저장하는 플래그 변수를 초기화한다.
			canCalculate = true;
			
			
			
			// 4. n만큼 반복하며 각 노드의 정보를 순서대로 읽어들인다.
			// 노드의 정보들
			int nodeNum; // 노드 번호
			char nodeData; // 노드의 값
			int leftChild, rightChild; // 왼쪽 자식의 번호, 오른쪽 자식의 번호
			
			while(numOfNode > 0) {
				st = new StringTokenizer(br.readLine().trim());
				
				nodeNum = Integer.parseInt(st.nextToken());
				nodeData = st.nextToken().charAt(0);
				
				// 자식 노드 정보가 없을 수도 있기 때문에 여기서 입력받으면 안된다.
//				leftChild = Integer.parseInt(st.nextToken());
//				rightChild = Integer.parseInt(st.nextToken());
				
				
				// 5. 연산자 노드인데 자식 노드가 없거나, 피연산자 노드인데 자식 노드를 갖고 있다면
				if(((nodeData == '+' || nodeData == '-' || nodeData == '*' || nodeData == '/') && !st.hasMoreTokens()) ||
				   ((nodeData >= '0' && nodeData <= '9') && st.hasMoreTokens())) {
					canCalculate = false; // 플래그 값을 false로 바꾸고
					
					// 나머지 입력값들을 흘려버린다.
					numOfNode--; // 다음번 입력부터
					while(numOfNode > 0) {
						br.readLine().trim(); // 죄다 날려버린다.
						numOfNode--;
					}
					break; // 출력으로 넘어간다.
				}
				
				numOfNode--;
			}
			
			
			// 6. 입력받기가 끝나면 플래그의 값에 따라 결과를 적절히 출력한다. 
			sb.append("#").append(tc).append(" ");
			sb.append(canCalculate ? "1" : "0");
			System.out.println(sb);
			sb.setLength(0);
		}
	}

}
