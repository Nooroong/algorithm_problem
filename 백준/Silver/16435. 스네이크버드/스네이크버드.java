import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * BOJ
 * @author eunwoo.lee
 * 
 * 1. 입력
 * 	1-1. 과일의 개수와 스네이크버드의 초기길이를 입력받는다.
 * 	1-2. 과일의 개수만큼 높이를 입력받는다.
 * 2. 과일의 높이를 정렬한다.
 * 3. while문 (birdLength<=maxHeight)
 * 4-1. 정렬한 다음 과일의 높이가 길이보다 높으면 더이상 먹을 수 없으므로 break
 * 4-2. 먹을 수 있다면 birdLength++
 * 
 *
 */

public class Main {
	
	public static BufferedReader br;
	public static StringBuilder sb;
	public static StringTokenizer st;
	
	public static int fruitNum, birdLength; 
	public static int[] fruitHeight;

	public static void main(String[] args) throws Exception{
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		// 1. 입력
		// 1-1. 과일의 개수와 스네이크버드의 초기길이를 입력받는다.
		st = new StringTokenizer(br.readLine().trim());
		fruitNum = Integer.parseInt(st.nextToken());
		birdLength = Integer.parseInt(st.nextToken());
		
		// 1-2. 과일의 개수만큼 높이를 입력받는다.
		fruitHeight = new int[fruitNum];
		st = new StringTokenizer(br.readLine().trim());
		for (int heightIdx=0; heightIdx<fruitNum; heightIdx++) {
			fruitHeight[heightIdx] = Integer.parseInt(st.nextToken());
		}
		
		// 2. 과일의 높이를 정렬한다.
		Arrays.sort(fruitHeight);
		
		// 3. 과일에 대해 탐색
		for (int fruitIdx=0; fruitIdx<fruitNum; fruitIdx++) {
			// 4-1. 정렬한 다음 과일의 높이가 길이보다 높으면 더이상 먹을 수 없으므로 break			
			if (fruitHeight[fruitIdx]>birdLength) {
				break;
			}
			
			// 4-2. 먹을 수 있다면
			birdLength++;
		}
		System.out.println(birdLength);

	}

}
