import java.util.*;
import java.io.*;

/**
 * 
 * @author JiYeon Sin
 * 정렬시켜놓고 과일들 차례대로 먹으면 되는거 아님????
 * 
 * 1. 과일의 개수와 스네이크버드의 길이를 입력받는다.
 * 2. 과일의 높이를 입력받는다.
 * 3. 과일의 높이들을 오름차순으로 정렬한다.
 * 4. 자신보다 높이가 낮거나 같은 과일들을 하나씩 먹으면서 길이를 1씩 늘린다.
 * 5. 자신보다 높이가 더 높은 과일을 만나면 그만한다.
 * 6. 마지막의 스네이크버드의 높이를 출력한다.
 */
public class Main {
	public static BufferedReader br;
	public static StringTokenizer st;
	
	public static int numOfFruit; // 과일의 개수
	public static int snakeBirdLen; // 스네이크 버드의 길이
	public static int[] fruitHeight; // 과일의 높이
	
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		
		// 1. 과일의 개수와 스네이크버드의 길이를 입력받는다.
		st = new StringTokenizer(br.readLine().trim());
		numOfFruit = Integer.parseInt(st.nextToken());
		snakeBirdLen = Integer.parseInt(st.nextToken());
		
		
		// 2. 과일의 높이를 입력받는다.
		fruitHeight = new int[numOfFruit];
		st = new StringTokenizer(br.readLine().trim());
		for(int index = 0; index < numOfFruit; index++) {
			fruitHeight[index] = Integer.parseInt(st.nextToken());
		}
		
		
		// 3. 과일의 높이들을 오름차순으로 정렬한다.
		Arrays.sort(fruitHeight);
		
		// 4. 자신보다 높이가 낮거나 같은 과일들을 하나씩 먹으면서 길이를 1씩 늘린다.
		for(int idx = 0; idx < numOfFruit; idx++) {
			if(fruitHeight[idx] <= snakeBirdLen) {
				snakeBirdLen++;
			} else { // 5. 자신보다 높이가 더 높은 과일을 만나면 그만한다.
				break;
			}
			
		}
		
		// 6. 마지막의 스네이크버드의 높이를 출력한다.
		System.out.println(snakeBirdLen);
	}

}
