import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 백준 2644 (촌수계산)
 * 촌수 == 노드간의 거리
 * 가중치가 없는 그래프
 * bfs로 계산하자(촌수는 bfs 탐색 레벨)
 */
public class Main {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static StringTokenizer st;

	private static int humanCount, chonsooStartIdx, chonsooEndIdx, relationCount, chonsoo;

	private static List<Integer>[] relationList;

	public static void main(String[] args) throws IOException {
		// 입력 받기
		humanCount = Integer.parseInt(br.readLine().trim());

		relationList = new List[humanCount + 1];
		for (int idx = 0; idx < humanCount + 1; idx++)
			relationList[idx] = new ArrayList<Integer>();

		st = new StringTokenizer(br.readLine().trim());
		chonsooStartIdx = Integer.parseInt(st.nextToken());
		chonsooEndIdx = Integer.parseInt(st.nextToken());

		relationCount = Integer.parseInt(br.readLine().trim());
		for (int idx = 0; idx < relationCount; idx++) {
			st = new StringTokenizer(br.readLine().trim());
			int fromIdx = Integer.parseInt(st.nextToken());
			int toIdx = Integer.parseInt(st.nextToken());

			relationList[fromIdx].add(toIdx);
			relationList[toIdx].add(fromIdx);
		}

		// 촌수 계산
		chonsoo = -1;
		calcChonsoo();

		// 결과 출력
		System.out.println(chonsoo);
	}

	private static void calcChonsoo() {
		Queue<Integer> queue = new ArrayDeque<>();
		boolean visited[] = new boolean[humanCount + 1];

		queue.offer(chonsooStartIdx);
		visited[chonsooStartIdx] = true;

		int level = 0;
		while (!queue.isEmpty()) {
			int qSize = queue.size();

			while (qSize-- > 0) {
				int currentIdx = queue.poll();

				if (currentIdx == chonsooEndIdx)
					chonsoo = level;

				for (int idx : relationList[currentIdx]) {
					if (visited[idx])
						continue;

					queue.offer(idx);
					visited[idx] = true;
				}
			}

			level++;
		}
	}
}



