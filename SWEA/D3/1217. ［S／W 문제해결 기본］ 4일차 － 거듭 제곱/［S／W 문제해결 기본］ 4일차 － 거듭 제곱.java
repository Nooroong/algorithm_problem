// 이 문제에서 입력 받기는 스캐너만 되는듯...
import java.util.Scanner;


/**
 * 
 * @author JiYeon Sin
 * 
 * 10번 반복
 * 1. 테스트 케이스의 번호를 입력받는다.
 * 2. 밑과 지수를 입력받는다.
 * 3. 재귀함수를 호출한다.
 *  3-1. 지수가 1이라면 밑을 반환하여 함수를 종료한다.
 *  3-2. 지수가 1보다 크다면 밑*pow(밑, 지수-1)을 호출한다.
 * 4. 호출 결과를 출력한다.  
 *
 */
public class Solution {
	public static int testCase; // 테스트 케이스의 개수
	
	public static int base, index; // 밑, 지수
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		for(int tc = 1; tc <= 10; tc++) {
			// 1. 테스트 케이스의 번호를 입력받는다.
			testCase = sc.nextInt();
			
			// 2. 밑과 지수를 입력받는다.
			base = sc.nextInt();
			index = sc.nextInt();
			
			// 4. 호출 결과를 출력한다.
			System.out.println(String.format("#%d %d", tc, pow(base, index)));
		}
	}
	
	public static int pow(int base, int index) {
		if(index == 1) return base; // 3-1. 지수가 1이라면 밑을 반환하여 함수를 종료한다.
		else return base*pow(base, index-1); // 3-2. 지수가 1보다 크다면 밑*pow(밑, 지수-1)을 호출한다.
	}
}
