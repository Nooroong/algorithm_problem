import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

/*
 * n+1번의 출력이 반복됨을 볼 수 있다.
 * 또한 "라고~"가 리턴되는 것처럼 보인다.
 * 
 * 호출 횟수를 i라고 하자,
 * 첫 호출시에만 "어느 한~"이 출력되고
 * 이후의 출력에는 앞에 ____가 i번 붙는다.
 * 
 * 근데 재귀함수의 인자로는 n부터 -1씩해서 넣을 것이다.
 * 따라서 ____는 Static.n - n번 출력된다.
 */


public class Main {
	static BufferedReader bf;
	static StringBuilder sb;
	static int n; // 재귀 횟수
	
	public static void ChatBot(int n) {
		int i;
		
		if(Main.n == n) { // 처음에만 출력되는 문장.
			sb.append("어느 한 컴퓨터공학과 학생이 유명한 교수님을 찾아가 물었다.\n");
		}
		
		if(n > 0) { // n+1이므로 >=를 넣어준다.
			for(i = 0; i < Main.n - n; i++) sb.append("____");
			sb.append("\"재귀함수가 뭔가요?\"\n");
			for(i = 0; i < Main.n - n; i++) sb.append("____");
			sb.append("\"잘 들어보게. 옛날옛날 한 산 꼭대기에 이세상 모든 지식을 통달한 선인이 있었어.\n");
			for(i = 0; i < Main.n - n; i++) sb.append("____");
			sb.append("마을 사람들은 모두 그 선인에게 수많은 질문을 했고, 모두 지혜롭게 대답해 주었지.\n");
			for(i = 0; i < Main.n - n; i++) sb.append("____");
			sb.append("그의 답은 대부분 옳았다고 하네. 그런데 어느 날, 그 선인에게 한 선비가 찾아와서 물었어.\"\n");
		} else if(n==0) { // 0에 도달하면 진실을 답해준다.
			for(i = 0; i < Main.n - n; i++) sb.append("____");
			sb.append("\"재귀함수가 뭔가요?\"\n");
			for(i = 0; i < Main.n - n; i++) sb.append("____");
			sb.append("\"재귀함수는 자기 자신을 호출하는 함수라네\"\n");		
		} else { // 재귀함수 종료 조건(n이 0미만인 경우)
			return;
		}
		
		// "~물었어." 다음에 다시 질문과 대답이 반복해서 나온다. 
		ChatBot(n-1);
		
		// 답변이 끝나면 "라고~"가 나온다.
		for(i = 0; i < Main.n - n; i++) sb.append("____");
		sb.append("라고 답변하였지.\n");
		
	}
	
	public static void main(String[] args) throws IOException {
		bf = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		Main.n = Integer.parseInt(bf.readLine().trim());
		
		ChatBot(Main.n);
		System.out.print(sb);
	}
}