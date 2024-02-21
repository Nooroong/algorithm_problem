import java.util.*;
import java.io.*;

/**
 * 
 * @author JiYeon Sin
 * 
 * 어느 한 사람에서부터 시작해서 관계를 따라 이어나갔을 때,
 * 5명의 사람이 이어질 수 있는지를 판별해야 한다.
 * 예제 입력 1에서는 0-1-2-3-4,
 * 예제 입력 2에서는 4-1-0-3-2,
 * 예제 입력 4에서는 1-7-4-3-5
 * 와 같은 형태가 만들어지므로 문제의 조건을 만족한다고 볼 수 있다.
 * 하지만 예제 3의 경우 아무리 길게 표현해도 0-1, 0-2, 0-3, 0-4, 0-5와 같이 표현될 뿐이다.
 * 
 * 친구 관계를 바탕으로 그래프를 표현한다.
 * 모든 사람을 대상으로 DFS를 시도한다.
 * 탐색의 깊이가 4가 되는 순간이 되면 1을 출력하고 문제를 종료한다.
 * 또한 관계의 수는 적어도 4개가 되어야 한다. 이를 만족하지 못하면 0을 출력하고 문제를 종료한다.
 * 
 * 
 * 1. 사람의 수와 관계의 수를 입력받는다.
 * 2. 사람간의 관계를 표현할 연결 리스트 배열을 만든다.
 * 3. 관계의 수가 일정 수 미만이면 0을 출력하고 종료한다.
 * 4. 관계를 입력받으면서 리스트에 적절히 추가한다.(무방향 그래프임에 주의)
 * 5. 0번부터 n-1번까지 각각의 사람에 대해 DFS를 시도한다.
 * 6. 각 반복마다 방문 여부 배열을 초기화해준다.
 * 7. 적절히 출력한다.
 */
public class Main {
	public static BufferedReader br;
	public static StringTokenizer st;
	
	public static int humanCount; // 사람의 수
	public static int relationCount; // 관계의 수
	
	public static final int RELATION_COUNT_CONDITION = 4; // 문제에서 요구하는 관계의 수
	
	public static List<Integer>[] relation; // 관계를 표현하는 그래프
	public static boolean[] visited; // 방문 여부
	public static boolean conditionPass; // 문제의 조건을 만족하는가
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		
		
		// 1. 사람의 수와 관계의 수를 입력받는다.
		st = new StringTokenizer(br.readLine().trim());
		humanCount = Integer.parseInt(st.nextToken());
		relationCount = Integer.parseInt(st.nextToken());
		
		
		// 2. 사람간의 관계를 표현할 연결 리스트 배열을 만든다.
		relation = new ArrayList[humanCount];
		for(int idx = 0; idx < humanCount; idx++) {
			relation[idx] = new ArrayList<Integer>();
		}
		
		
		// 3. 관계의 수가 일정 수 미만이면 0을 출력하고 종료한다.
		if(relationCount < RELATION_COUNT_CONDITION) {
			System.out.println("0");
			System.exit(0);
		}
		
		
		
		// 4. 관계를 입력받으면서 리스트에 적절히 추가한다.(무방향 그래프임에 주의)
		for(int idx = 0; idx < relationCount; idx++) {
			st = new StringTokenizer(br.readLine().trim());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			
			// 무방향 그래프임
			relation[from].add(to);
			relation[to].add(from);
		}
		
		
		
		// 5. 0번부터 n-1번까지 각각의 사람에 대해 DFS를 시도한다.
		conditionPass = false;
		
		for(int idx = 0; idx < humanCount; idx++) {
			visited = new boolean[humanCount]; // 6. 각 반복마다 방문 여부 배열을 초기화해준다.
			
			dfs(idx, 0);
			
			// 이미 조건을 만족함을 알았으면 다른 사람에 대한 탐색을 이어나갈 필요가 없다.
			if(conditionPass) {
				break;
			}
		}
		
		// 7. 적절히 출력한다.
		System.out.println(conditionPass ? "1" : "0");
	}
	
	
	public static void dfs(int currentPerson, int depth) {
		// 현재 노드를 방문처리 한다.
		visited[currentPerson] = true;
		
		// 관계 조건을 만족하면 탐색 종료
		if(depth == RELATION_COUNT_CONDITION) {
			conditionPass = true;
			return;
		}
		
		
		// 아니라면 주변의 다른 친구들을 찾는다.
		for(int idx = 0; idx < relation[currentPerson].size(); idx++) {
			int nextPerson = relation[currentPerson].get(idx);
			if(!visited[nextPerson]) {
				dfs(nextPerson, depth+1);
				visited[nextPerson] = false;
			}
		}
	}
}
