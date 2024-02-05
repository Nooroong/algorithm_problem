import java.io.*;
import java.util.*;
/**
 * @author JiYeon Sin
 * 중간 삽입이 자주 일어나므로 연결 리스트를 사용한다.
 * 1. 원본 암호문의 길이를 입력받는다.
 * 2. 원본 암호문을 입력받아 연결 리스트에 저장한다.
 * 3. 명령어의 개수를 입력받는다.
 * 4. 명령어를 입력받는다.
 * 5. 4단어씩 읽어들이며 적절히 처리한다.
 * 6. 완성된 암호문을 앞의 10개만 출력한다.
 */
public class Solution {
	public static StringBuilder sb;
	public static StringTokenizer st;
	public static BufferedReader br;

	
	public static int cipherLen; // 암호문 길이
	public static List<Integer> cipherList; // 암호문을 저장하는 리스트
	public static int cmdLen; // 명령어 길이
	public static List<String> cmdList; // 읽어들인 명령어가 저장되는 리스트
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		int testCase = 10;
		
		for(int tc = 1; tc <= testCase; tc++) {
			cipherLen = Integer.parseInt(br.readLine().trim());
			
			// 2. 원본 암호문을 입력받아 연결 리스트에 저장한다.
			cipherList = new LinkedList<Integer>();
			st = new StringTokenizer(br.readLine().trim());
			while(st.hasMoreTokens()) {
				cipherList.add(Integer.parseInt(st.nextToken()));
			}
			
			// 3. 명령어의 개수를 입력받는다.
			cmdLen = Integer.parseInt(br.readLine().trim());
			
			// 4. 명령어를 입력받는다.
			cmdList = new ArrayList<String>();
			st = new StringTokenizer(br.readLine().trim());
			while(st.hasMoreTokens()) {
				cmdList.add(st.nextToken());
				
			}
			
			
			// 5. 4단어씩 읽어들이며 적절히 처리한다.
			int x, y; // 삽입할 위치, 삽입할 개수
			int idx = 0;
			while(idx < cmdList.size()) {
				if(cmdList.get(idx).equals("I")) {
					x = Integer.parseInt(cmdList.get(++idx));
					y = Integer.parseInt(cmdList.get(++idx));

					// 다음번 명렁어가 나올 때까지
					// 암호문에 숫자들을 삽입한다.
					while(y > 0) {
						cipherList.add(x, Integer.parseInt(cmdList.get(++idx)));
						x++;
						y--;
					}
					
				}
				idx++;
			}
			
			// 6. 완성된 암호문을 앞의 10개만 출력한다.
			sb.append("#").append(tc).append(" ");
			for(idx = 0; idx < 10; idx++) {
				sb.append(cipherList.get(idx)).append(" ");
			}
			
			System.out.println(sb);
			sb.setLength(0);
			
		}
	}
}
