import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * BOJ
 * @author eunwoo.lee
 * 
 * 1. 입력
 * 	1-1. 암호의 길이와 문자의 종류 수를 입력받는다.
 * 	1-2. 문자의 종류를 입력받는다.
 * 2. 조합을 통해 가능한 알파벳 조합을 만든다.
 * 	2-1. 기저조건을 만족하면 자음, 모음 개수 검사해서 만족할시 출력
 *
 */

public class Main {
	
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;
	
	static int pwLength, pwAlphaNum;
	static char[] pwAlpha;
	
	static char[] selectedElementList;
	
	public static void combination(int selectIdx, int elementIdx) {
		// 1. 기저조건
		// 다 선택했다
		if (selectIdx==pwLength) {
			int vowel=0;
			int consonant=0;
			// 여기서 자음, 모음 개수 검사
			for (int idx=0; idx<pwLength; idx++) {
				if (selectedElementList[idx]=='a' || selectedElementList[idx]=='e' || selectedElementList[idx]=='i' ||
						selectedElementList[idx]=='o' || selectedElementList[idx]=='u')
					vowel++;
				else
					consonant++;
			}
			if (vowel>=1 && consonant>=2)
				System.out.println(new String(selectedElementList));
			return;
		}
		// 원소의 끝까지 다 살펴봤다
		if (elementIdx==pwAlphaNum)
			return;
		
		// 현재 원소 선택했을때
		selectedElementList[selectIdx] = pwAlpha[elementIdx];
		combination(selectIdx+1, elementIdx+1);
		// 현재 원소 선택X
		selectedElementList[selectIdx] = ' ';
		combination(selectIdx, elementIdx+1);
	}
	

	public static void main(String[] args) throws Exception{
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		// 1. 입력
		// 1-1. 암호의 길이와 문자 종류 수를 입력받는다.
		st = new StringTokenizer(br.readLine().trim());
		pwLength = Integer.parseInt(st.nextToken());
		pwAlphaNum = Integer.parseInt(st.nextToken());
		// 1-2. 문자의 종류를 입력받는다.
		pwAlpha = new char[pwAlphaNum];
		st = new StringTokenizer(br.readLine().trim());
		for (int idx=0; idx<pwAlphaNum; idx++) {
			pwAlpha[idx] = st.nextToken().charAt(0);
		}
		// 알파벳 순을 거스르지 않으므로 정렬
		Arrays.sort(pwAlpha);
		
		// 2. 조합으로 가능한 암호를 뽑는다.
		selectedElementList = new char[pwLength];
		combination(0,0);

	}

}
