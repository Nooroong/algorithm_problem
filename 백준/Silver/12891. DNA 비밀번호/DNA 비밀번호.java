import java.io.*;
import java.util.*;

/**
 * 
 * @author JiYeon Sin
 * 부분문자열이니까 원 문자열에서 연속으로 뽑아내야 한다.
 * 
 * 1. DNA 문자열의 길이와 부분문자열의 길이를 입력받는다.(dnaStrLen, subDnaStrLen)
 * 2. DNA 문자열을 입력받는다.
 * 3. 부분문자열에 포함되어야 할 최소 개수를 입력받는다.
 * 4. 원 문자열의 0번째 요소부터 dnaStrLen-subDnaStrLen까지 탐색한다.
 * 5. 부분문자열에 들어있는 각 알파벳들의 수를 구한다.(슬라이딩 윈도우)
 * 6. 부분문자열이 조건을 만족하는지 확인한다.
 * 7. 만족한다면 비밀번호의 수를 하나 증가시킨다.
 * 8. 사용가능한 비밀번호 수를 출력한다.
 */
public class Main {
	public static BufferedReader br;
	public static StringTokenizer st;
	
	public static final int numOfAlphabet = 26;
	
	public static int dnaStrLen; // dna 문자열의 길이
	public static int subDnaStrLen; // 부분 문자열의 길이
	public static String dnaStr; // dna 문자열
	public static char[] subDnaStr; // 부분 문자열
	
	public static int[] minDnaChar = new int[numOfAlphabet]; // A, C, G, T의 최소 개수
	public static int[] subDnaChars = new int[numOfAlphabet]; // 현재 부분 문자열의 A, C, G, T 개수
	
	public static int numOfPW = 0; // 사용할 수 있는 비밀번호 수
	
	public static boolean flag = true; // 부분문자열이 사용할 수 있는 비밀번호인지에 대한 정보
	
	
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		
		// 1. DNA 문자열의 길이와 부분문자열의 길이를 입력받는다.
		st = new StringTokenizer(br.readLine().trim());
		dnaStrLen = Integer.parseInt(st.nextToken());
		subDnaStrLen = Integer.parseInt(st.nextToken());
		subDnaStr = new char[subDnaStrLen];
		
		// 2. DNA 문자열을 입력받는다.
		dnaStr = br.readLine().trim();
		
		
		// 3. 부분문자열에 포함되어야 할 최소 개수를 입력받는다.
		st = new StringTokenizer(br.readLine().trim());
		minDnaChar['A' - 65] = Integer.parseInt(st.nextToken());
		minDnaChar['C' - 65] = Integer.parseInt(st.nextToken());
		minDnaChar['G' - 65] = Integer.parseInt(st.nextToken());
		minDnaChar['T' - 65] = Integer.parseInt(st.nextToken());
		

		
		
		// 4. 원 문자열의 0번째 요소부터 dnaStrLen-subDnaStrLen까지 탐색한다.
		
		// 첫번째 경우 처리
		for(int idx = 0; idx < subDnaStrLen-1; idx++) {
			subDnaChars[dnaStr.charAt(idx) - 65]++;
		}
		
		// 그다음 반복된 부분 처리
		for(int startIdx = 0, endIdx = subDnaStrLen-1; startIdx <= dnaStrLen-subDnaStrLen; startIdx++, endIdx++) {
			flag = true;
			
			// 5. 부분문자열에 들어있는 각 알파벳들의 수를 구한다.(슬라이딩 윈도우)
			// 새롭게 보게되는 영역인 endIdx의 정보를 추가해준다.
			subDnaChars[dnaStr.charAt(endIdx) - 65]++;
			
			
			for(int idx = 0; idx < numOfAlphabet; idx++) {
				if(subDnaChars[idx] - minDnaChar[idx] < 0) {
					flag = false;
					break;
				}
			}
	
			// 7. 만족한다면 비밀번호의 수를 하나 증가시킨다.
			if(flag) numOfPW++;		
			
			
			// 다음 단계로 갈때는 지금 부분문자열의 첫문자를 뺴준다.
			subDnaChars[dnaStr.charAt(startIdx) - 65]--;	
		}
		
		
		// 8. 사용가능한 비밀번호 수를 출력한다.
		System.out.println(numOfPW);
	}
	
}
