import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * 1. 테스트 케이스의 수를 입력받는다.
 * 2. 각 테스트 케이스를 입력받는다.
 * 3. 문자열을 앞에서부터 살펴보면서 값의 변화가 몇번 일어났는지 확인한다.
 * 4. 앞의 수가 1이라면 횟수를 +1해준다.
 */

public class Solution {
	static BufferedReader br;
	static StringBuilder sb;
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		
		// 테스트 케이스의 수를 입력받는다.
		int T;
		T = Integer.parseInt(br.readLine().trim());
		

		for(int tc = 1; tc <= T; tc++) {
			char []originalMemory = null; // 원래 메모리의 값
			char preBit = '0'; // 이전 비트 값
			char curBit = '0'; // 현재 비트 값
			int changeCnt = 0; // 최소 수정 횟수
			
			originalMemory = br.readLine().trim().toCharArray(); // 테스트 케이스를 입력받는다.
			
			// 문자열을 앞에서부터 살펴본다.
			preBit = originalMemory[0];
			for(int i = 1; i < originalMemory.length; i++) {
				curBit = originalMemory[i];
				
				// 비트가 변경이 발견되면 변경 횟수를 1증가시킨다.
				if(preBit != curBit) {
					changeCnt++;
				}
				
				preBit = curBit; // 현재 비트를 이전 비트로 갱신
			}
			
			// 첫 비트가 1이었다면 변경 횟수를 1증가시킨다.
			if(originalMemory[0] == '1') {
				changeCnt++;
			}
			
			sb.append("#").append(tc).append(" ").append(changeCnt);
			System.out.println(sb);
			sb.setLength(0);
		}
	}
}