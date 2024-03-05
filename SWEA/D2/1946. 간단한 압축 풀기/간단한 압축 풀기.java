import java.io.*;
import java.util.*;

/**
 * 
 * @author JiYeon Sin
 * 
 * 1. 테스트 케이스의 개수를 입력받아 반복한다.
 * 2. 알파벳과 숫자 쌍의 개수를 입력받는다.
 * 3. 알파벳과 개수 정보를 입력받는다.
 * 4. 알파벳의 개수만큼 반복하며 문자를 추가한다.
 *  4-1. 만든 문자열의 길이가 문서 너비의 배수가 되면 개행문자를 추가한다.
 * 5. 만들어진 문자열을 출력한다.
 */
public class Solution {
	public static BufferedReader br;
	public static StringTokenizer st;
	public static StringBuilder sb;
	
	public static int testCase; // 테스트 케이스의 개수
	
	public static final int WIDTH = 10; // 문서의 너비
	
	public static int infoLineNum;
	public static char alphabet; // 알파벳
	public static int repeatNum; // 알파벳 반복 횟수
	public static int charCount; // 출력문에 들어간 문자의 수
	
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		
		// 1. 테스트 케이스의 개수를 입력받아 반복한다.
		testCase = Integer.parseInt(br.readLine().trim());
		
		for(int tc = 1; tc <= testCase; tc++) {
			sb.append("#").append(tc).append("\n");
			charCount = 0;
			
			// 2. 알파벳과 숫자 쌍의 개수를 입력받는다.
			infoLineNum = Integer.parseInt(br.readLine().trim());
			
			// 쌍의 개수만큼 반복
			for(int line = 0; line < infoLineNum; line++) {
				// 3. 알파벳과 개수 정보를 입력받는다.
				st = new StringTokenizer(br.readLine().trim());
				alphabet = st.nextToken().charAt(0);
				repeatNum = Integer.parseInt(st.nextToken());
				
				// 4. 알파벳의 개수만큼 반복하며 문자를 추가한다.
				while(repeatNum-- > 0) {
					sb.append(alphabet);
					charCount++;
					
					// 4-1. 만든 문자열의 길이가 문서 너비의 배수가 되면 개행문자를 추가한다.
					if(charCount % WIDTH == 0)
						sb.append("\n");
				}
			}
			
			
			System.out.println(sb); // 5. 만들어진 문자열을 출력한다.
			sb.setLength(0);
		}
	}
}
