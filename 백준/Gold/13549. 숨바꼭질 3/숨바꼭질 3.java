import java.io.*;
import java.util.*;

/**
 * 백준 13549 (숨바꼭질 3)
 *
 * 0-1 bfs로 동생을 잡을 수 있는 최단 시간 구하기
 * 이동 방법이 여러가지니까 말되원(1600) 느낌으로
 * 이동 방법에 따라서 방문 처리를 해줘야 할듯?
 *
 * 반례:
 * 4 6
 * 정답) 1
 * 출력) 2
 *
 * 이유?:
 * 기존 코드에서는 동생을 잡으면 탐색을 바로 종료해버렸다.
 * 손으로 직접 써보면 한 레벨 안에서 동생을 잡는 경우가
 * 여러 개 나올 수 있음을 알 수 있다.
 * 따라서 레벨 단위로 탐색을 하도록 하고 최소 시간을 갱신하도록 해야 한다.
 * 특정 레벨에서 동생을 잡았다면 다음 레벨은 탐색할 필요가 없으므로(시간이 무조건 같거나 증가)
 * 탐색을 종료시켜 버린다.
 */

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));;
    private static StringTokenizer st;


    private static int soobinPos, dongsaengPos, shortestTime = Integer.MAX_VALUE;
    private static boolean[][] visited = new boolean[200001][3];;

    public static void main(String[] args) throws IOException {

        // 입력받기
        st = new StringTokenizer(br.readLine().trim());
        soobinPos = Integer.parseInt(st.nextToken());
        dongsaengPos = Integer.parseInt(st.nextToken());


        // 동생 잡기
        catchDongsaeng();


        // 최단 시간 출력
        System.out.println(shortestTime);

    }

    private static void catchDongsaeng() {
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[] { soobinPos, 0 }); // 수빈이의 위치, 이동 시간

        while(!queue.isEmpty()) {
            int size = queue.size();

            while(size-- > 0) {
                int[] curPos = queue.poll();
                // System.out.println(Arrays.toString(curPos));

                // 동생을 잡으면 최소 시간인지 확인 후 갱신
                if(curPos[0] == dongsaengPos) {
                    if(curPos[1] < shortestTime) shortestTime = curPos[1];
                }


                // 다음 위치로 이동
                if(curPos[0] * 2 <= 200000 && !visited[curPos[0] * 2][0]) {
                    visited[curPos[0] * 2][0] = true;
                    queue.offer(new int[] { curPos[0] * 2, curPos[1] });
                }

                if(curPos[0] - 1 >= 0 && !visited[curPos[0] - 1][2]) {
                    visited[curPos[0] - 1][2] = true;
                    queue.offer(new int[] { curPos[0] - 1, curPos[1] + 1 });
                }
                
                if(curPos[0] + 1 <= 200000 && !visited[curPos[0] + 1][1]) {
                    visited[curPos[0] + 1][1] = true;
                    queue.offer(new int[] { curPos[0] + 1, curPos[1] + 1 });
                }

                
            }

            // System.out.println();
            // 이번 레벨에서 동생을 잡았다면 탐색을 중단한다.
            if(shortestTime != Integer.MAX_VALUE)
                break;
        }
    }
}