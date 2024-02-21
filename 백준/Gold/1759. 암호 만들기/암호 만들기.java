import java.io.*;
import java.util.*;

/**
 * 
 * @author JiYeon Sin
 * 
 * 암호문의 문자들은 오름차순으로 정렬되어야 한다. 출력도 사전순으로 해야한다.
 * 따라서 사용할 수 있는 문자들을 입력받고 그것들을 오름차순으로 정렬해준다.
 * 약간 순열 비스무리하게 풀면 될듯?
 * 원소를 뽑을 때마다 사용한 모음 개수, 자음 개수를 세어준다.
 * 원소를 다 뽑으면 출력을 한다.
 * 
 * 
 */

public class Main {
	public static BufferedReader br;
	public static StringTokenizer st;
	public static StringBuilder sb;
	
	public static final int MIN_VOWEL_COUNT = 1;
	public static final int MIN_CON_COUNT = 2;
	
	public static int selectCount; // 사용해야하는 문자의 수
	public static int elementCount; // 주어진 문자의 수
	public static boolean[] elementUsedList; // 사용한 문자 정보
	public static char[] selectElementList; // 만든 암호문
	
	
	public static char[] elementList; // 암호문에 사용할 수 있는 문자들
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		st = new StringTokenizer(br.readLine().trim());
		
		// 암호문에 사용해야하는 문자의 수, 주어진 문자의 수를 입력받는다.
		selectCount = Integer.parseInt(st.nextToken());
		elementCount = Integer.parseInt(st.nextToken());
		
		// 각종 할당 및 초기화
		elementUsedList = new boolean[elementCount];
		selectElementList = new char[selectCount];
		
		// 암호문에 사용할 수 있는 문자들을 입력받는다.
		elementList = new char[elementCount];
		st = new StringTokenizer(br.readLine().trim());
		for(int idx = 0; idx < elementCount; idx++) {
			elementList[idx] = st.nextToken().charAt(0);
		}
		
		Arrays.sort(elementList); // 사용가능한 문자들을 오름차순으로 정렬한다.
		
		makeCipher(0, 0, 0, 0);
		
		System.out.println(sb);
	}
	
	
	public static void makeCipher(int selectIdx, int elementIdx, int vowelCount, int conCount) {
		// 기저조건: 모든 원소를 뽑은 경우
		if(selectIdx == selectCount) {
			// 모음, 자음 최수 개수를 만족하면
			if(vowelCount >= MIN_VOWEL_COUNT && conCount >= MIN_CON_COUNT) {
				for(int idx = 0; idx < selectCount; idx++) {
					// 출력
					sb.append(selectElementList[idx]);
				}
				sb.append("\n");
			}
			return;
		}
		
		
		
		// 다음 원소를 고른다.
		for(int idx = elementIdx; idx < elementCount; idx++) {
			if(elementUsedList[idx]) {
				continue;
			}
			
			elementUsedList[idx] = true;
			char nextChar = elementList[idx]; // 다음에 고르게 될 문자
			
			selectElementList[selectIdx] = nextChar;
			if(nextChar == 'a' || nextChar == 'i' || nextChar == 'o' || nextChar == 'u' || nextChar == 'e') {
				makeCipher(selectIdx+1, idx, vowelCount+1, conCount);
			} else {
				makeCipher(selectIdx+1, idx, vowelCount, conCount+1);
			}
			
			elementUsedList[idx] = false;
			
		}
		
	}
}
