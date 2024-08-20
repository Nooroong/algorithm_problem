import java.io.*;
import java.util.*;

/**
 * 백준 2606 (S3)
 *  네트워크 정보를 linked list로 관리한다.
 */

public class Main {
    public static BufferedReader br;
    public static StringTokenizer st;

    public static int computerCount, computerPareCount, totlaCount;
    public static int fromIdx, toIdx;

    public static List[] network;
    public static boolean[] visited;

    public static long tokaTime, doldolTime, tokaLimitTime;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));

        computerCount = Integer.parseInt(br.readLine().trim());
        computerPareCount = Integer.parseInt(br.readLine().trim());

        visited = new boolean[computerCount + 1];
        network = new List[computerCount + 1];
        for(int idx = 0; idx < computerCount + 1; idx++) {
            network[idx] = new ArrayList<Integer>();
        }


        for(int idx = 0; idx < computerPareCount; idx++) {
            st = new StringTokenizer(br.readLine().trim());

            fromIdx = Integer.parseInt(st.nextToken());
            toIdx = Integer.parseInt(st.nextToken());

            network[fromIdx].add(toIdx);
            network[toIdx].add(fromIdx);
        }

        Queue<Integer> queue = new PriorityQueue<>();
        queue.add(1);
        visited[1] = true;
        totlaCount = 0;

        while(!queue.isEmpty()) {
            int curIdx = queue.poll();

            for(int nIdx = 0; nIdx < network[curIdx].size(); nIdx++) {
                if(!visited[(int)network[curIdx].get(nIdx)]) {
                    queue.add((int)network[curIdx].get(nIdx));
                    visited[(int)network[curIdx].get(nIdx)] = true;
                    totlaCount++;
                }
            }
        }

        System.out.println(totlaCount);

    }
}