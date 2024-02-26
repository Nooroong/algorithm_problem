import java.io.*;
import java.util.*;

/**
 * 
 * @author JiYeon Sin
 * 가장 최근에 들어온 업무를 지금 바로 처리하기 때문에 스택을 활용한다.
 * 
 * 업무가 들어오면 이를 바로 처리하고 스택에 넣는다.
 * 업무가 들어오지 않았으면 스택의 top이 가리키는 업무를 처리한다.
 * 	만약 해당 업무가 처리완료(남은 시간 0) 됐다면 점수를 합산한다.
 * 
 * 1. 이번 분기가 몇 분인지 입력받는다.
 * 2. 이번 분기동안 열심히 일한다.
 * 3. 해당 시점의 업무 유무를 입력받는다.
 * 4. 새로 들어온 업무가 없다면
 *  4-1. 마지막으로 하고 있던 업무를 계속 수행하고
 *  4-2. 다음 시간으로 넘어간다.
 * 5. 업무가 새로 들어왔다면 업무 정보를 스택에 넣고
 * 	5-1. 해당 업무를 우선으로 처리한다.
 * 6. 김삼성이 얻은 점수 총합을 출력한다.
 */
public class Main {
	public static BufferedReader br;
	public static StringTokenizer st;
	public static StringBuilder sb;
	
	public static int minute; // 이번 분기의 분
	
	public static int workExist; // 해당 시점의 업무 유무
	public static int score; // 해당 업무를 수행하여 얻을 수 있는 점수
	public static int takeMinute; // 해당 업부를 수행하는 데 걸리는 시간
	
	public static Stack<Work> workStack; // 들어온 일을 스택에 쌓는다.
	
	public static int scoreSum; // 점수 총합
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		
		minute = Integer.parseInt(br.readLine().trim()); // 1. 이번 분기가 몇 분인지 입력받는다.
		
		// 각종 초기화
		scoreSum = 0;
		workStack = new Stack<>();
		
		// 2. 이번 분기동안 열심히 일한다.
		for(int m = 0; m < minute; m++) {
			st = new StringTokenizer(br.readLine().trim());
			
			workExist = Integer.parseInt(st.nextToken()); // 3. 해당 시점의 업무 유무를 입력받는다.
			
			// 4. 새로 들어온 업무가 없다면
			if(workExist == 0) {
				processLastWork(); // 4-1. 마지막으로 하고 있던 업무를 계속 수행하고
				continue; // 4-2. 다음 시간으로 넘어간다.
			}
			
			
			// 5. 업무가 새로 들어왔다면 업무 정보를 스택에 넣고
			score = Integer.parseInt(st.nextToken());
			takeMinute = Integer.parseInt(st.nextToken());
			workStack.push(new Work(score, takeMinute));
			
			// 5-1. 해당 업무를 우선으로 처리한다.
			processLastWork();
		}
		
		
		System.out.println(scoreSum); // 6. 김삼성이 얻은 점수 총합을 출력한다.
	}
	
	
	
	/**
	 * 가장 최근에 수행한 업무를 처리한다.
	 */
	public static void processLastWork() {
		// 처음에 업무가 주어지지 않을 수도 있으므로 스택이 빈 경우를 생각해줘야 한다.
		if(!workStack.isEmpty()) {
			
			int topIndex = workStack.size()-1;
			
			workStack.get(topIndex).leftTime -= 1; // 가장 최근에 수행한 업무 처리
			
			// 해당 업무가 완료되었다면
			if(workStack.get(topIndex).leftTime <= 0) {
				scoreSum += workStack.get(workStack.size()-1).score; // 점수를 계산하고
				workStack.pop(); // 해당 업무는 스택에서 빼낸다.
				
			}
		}
	}
	
	
	
	// 업무를 나타내는 클래스
	static class Work {
		int score;
		int leftTime; // 완수까지 남은 시간

		public Work(int score, int leftTime) {
			super();
			this.score = score;
			this.leftTime = leftTime;
		}

		
		@Override
		public String toString() {
			return "Work [score=" + score + ", leftTime=" + leftTime + "]";
		}
	}
}
