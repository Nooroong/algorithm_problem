/*
 * 1. 문자열을 한 줄 입력 받는다.
 * 2. 입력받은 문자열을 한 문자씩 검사한다.
 * 3-1. 대문자라면 다음 문자로 넘어간다.
 * 3-2. 소문자라면 대문자로 변환한다.(-32)
 * 4. 문자열의 모든 문자에 대한 처리가 끝나면 결과를 출력한다.
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Solution {
	static BufferedReader br;

	public static void main(String[] args) throws IOException {
		// 소문자가 대문자보다 32만큼 더 크다.
		// 소문자 a의 아스키코드 값은 97
		br = new BufferedReader(new InputStreamReader(System.in));
		String headLine;
		char oneChar; // 문자열의 문자 하나를 담는다.
		
		headLine = br.readLine().trim(); // 1. 문자열 입력받기
		
		// 2. 한 문자씩 검사한다.
		for(int i = 0; i < headLine.length(); i++) {
			oneChar = headLine.charAt(i);
			// 3. 문자가 소문자라면
			if(oneChar >= 'a' && oneChar <= 'z') {
				headLine = headLine.replace(oneChar, (char)(oneChar-32));  // 대문자로 변환한다.
			}
		}
		
		// 4. 결과를 출력한다.
		System.out.println(headLine);

	}

}
