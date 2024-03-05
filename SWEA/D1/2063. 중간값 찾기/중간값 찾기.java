import java.io.*;
import java.util.*;

/**
 * 
 * @author JiYeon Sin
 * 
 * 1. 숫자들의 개수를 입력받는다.
 * 2. 길이가 n인 배열을 만든다.
 * 3. 숫자들을 공백으로 구분하여 입력받아 배열에 저장한다.
 * 4. 숫자들을 정렬한다.
 * 5. 배열에서 n/2 인덱스의 값을 출력한다.
 * 
 */
public class Solution {
	public static BufferedReader br;
	public static StringTokenizer st;
	public static StringBuilder sb;
	
	public static int numberCount; // 숫자들의 개수
	public static int[] numberArr; // 숫자들을 담는 배열
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		// 1. 숫자들의 개수를 입력받는다.
		numberCount = Integer.parseInt(br.readLine().trim());
		
		numberArr = new int[numberCount]; // 2. 길이가 n인 배열을 만든다.
		
		// 3. 숫자들을 공백으로 구분하여 입력받아 배열에 저장한다.
		st = new StringTokenizer(br.readLine().trim());
		for(int idx = 0; idx < numberCount; idx++) {
			numberArr[idx] = Integer.parseInt(st.nextToken());
		}
		
		
		Arrays.sort(numberArr); // 4. 숫자들을 정렬한다.
		
		
		// 5. 배열에서 n/2 인덱스의 값을 출력한다.
		System.out.println(numberArr[numberCount/2]);
	}
}
