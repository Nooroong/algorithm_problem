import java.util.*;
import java.io.*;

/**
 * 
 * @author JiYeon Sin
 * 0. 테스트케이스의 길이를 입력받는다.
 * 1. 문자열을 입력받아 저장한다.(brackets)
 * 2. 문자열에서 문자들을 하나씩 살펴본다.
 * 3. 여는 괄호를 만나면 스택에 push
 * 4. 닫는 괄호를 만나면 스택에서 pop
 * 	4-1. 현재 보고 있는 닫는 괄호와 스택에서 꺼낸 여는 괄호를 비교한다.
 * 	4-2. 쌍이 맞으면 유효하다고 보고 다음 문자로 넘어간다.
 * 	4-3. 쌍이 맞지않으면 유효하지 않다고 보고 반복을 중단한다. (flag 이용)
 * 	4-4. 문자열의 문자를 다 봤는데 스택에 원소가 남아있다면 유효하지 않다고 보고 스택을 비워준다.
 * 5. 마지막에 결과를 적절히 출력한다.
 *
 */
public class Solution {
	static BufferedReader br;
	static StringBuilder sb;
	
	static final int TEST_CASE = 10; // 총 테스트 케이스의 수
	static int lenOfTestCase; // 테스트케이스 길이
	static String brackets; // 괄호 문자열
	static Stack<Character> stack = new Stack<Character>(); // 여는 괄호들을 넣을 스택
	static boolean flag = true; // 괄호쌍이 유요한지에 대한 flag
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		

		for(int tc = 1; tc <= TEST_CASE; tc++) {
			flag = true;
			
			// 0. 테스트케이스의 길이를 입력받는다.
			lenOfTestCase = Integer.parseInt(br.readLine().trim());
			
			// 1. 문자열을 입력받아 저장한다.(brackets)
			brackets = br.readLine().trim();
			
			
			
			// 2. 문자열에서 문자들을 하나씩 살펴본다.
			for(int idx = 0; idx < lenOfTestCase; idx++) {
				
				char curChar = brackets.charAt(idx); // 현재 보고 있는 문자
				char popChar; // 스택에서 꺼낸 문자
				
				// 3. 여는 괄호를 만나면 스택에 push
				if(curChar == '(' || curChar == '[' || curChar == '{' || curChar =='<') {
					stack.push(curChar);
				} else {
					// 4. 닫는 괄호를 만나면 스택에서 pop
					popChar = stack.pop();
					
					// 4-1. 현재 보고 있는 닫는 괄호와 스택에서 꺼낸 여는 괄호를 비교한다.
					if((curChar == ')' && popChar == '(') ||
						(curChar == ']' && popChar == '[') ||
						(curChar == '}' && popChar == '{') ||
						(curChar == '>' && popChar == '<')) {
						// 4-2. 쌍이 맞으면 유효하다고 보고 다음 문자로 넘어간다.
						continue;
						
					} else {
						// 4-3. 쌍이 맞지않으면 유효하지 않다고 보고 반복을 중단한다. (flag 이용)
						flag = false;
						break;
					}
				}
			}
			
			// 4-4. 문자열의 문자를 다 봤는데 스택에 원소가 남아있다면 유효하지 않다고 보고 스택을 비워준다.
			if(!stack.empty()) {
				flag = false;
				stack.clear();
			}
			
			
			// 5. 마지막에 결과를 적절히 출력한다.
			sb.append("#").append(tc).append(" ").append(flag ? "1" : "0");
			System.out.println(sb);
			sb.setLength(0);
		}

	}

}
