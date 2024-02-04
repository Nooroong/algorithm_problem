import java.io.*;
import java.util.*;

/**
 * 
 * @author JiYeon Sin
 * 에라토스테네스의 체로 소수 구하기 -> 메모리 제한 때문에 사용할 수 없다.
 * 1자리수부터 시작하여 n자리수까지 확장이 될 때,
 * 각 자리마다 올 수 있는 숫자는 한정적이라는 것을 이용한다.
 * 어떤 수가 소수가 될 수 있는지 일일이 찾기 보다는
 * 소수를 만들어나가는 느낌으로 문제를 해결한다.
 * 이는 재귀함수를 이용해 구현한다.
 * 
 * 1. 자리수를 입력받는다.
 * 2. 1자리일 때 올 수 있는 수는 2, 3, 5, 7밖에 없다.
 *    반복을 돌며 하나씩 숫자를 채워준다.
 * 3. 그다음 자리에는 1, 3, 7, 9만 올 수 있다.
 *    짝수와 5가 끝자리에 있으면 무조건 합성수가 되기 때문이다.
 *    해당 숫자들을 대입해주고 소수가 아닌지 판별한다.
 * 4. 숫자를 확장해나가다가 소수가 아니라고 판별된 경우에는
 *    바로 탐색을 중단한다.
 * 5. 탐색의 끝에 도달한 경우에 해당 숫자는 소수이므로 출력을 해준다.
 */
public class Main {
	public static BufferedReader br;
	
	public static int[] firstNum = { 2, 3, 5, 7}; // 첫번쨰 자리에 올 수 있는 수
	public static int [] anotherNum = { 1, 3, 7, 9 }; // 나머지 자리에 올 수 있는 수
	public static int n; // 자리수
	
	
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		
		
		// 1. 자리수를 입력받는다.
		n = Integer.parseInt(br.readLine().trim());
		
		
		// 2. 1자리일 때 올 수 있는 수는 2, 3, 5, 7밖에 없다.
		// 반복을 돌며 하나씩 숫자를 채워준다.
		for(int idx = 0; idx < firstNum.length; idx++) {
			makePrimeNum(firstNum[idx], 1);
		}
		
	}
	
	
	
	static void makePrimeNum(int num, int depth) {
		// 1. 종료조건: 자리수를 모두 채우면
		if(depth == n) {
			System.out.println(num);
			return;
		}
		
		// 2-1. 전 처리: 새로운 자리에 추가할 숫자를 골라만든다.
		for(int idx = 0; idx < anotherNum.length; idx++) {
			int newNum = num * 10 + anotherNum[idx];
			
			// 2-2. 전처리: 새로 만든 숫자가 소수인지 확인한다.
			if(isPrime(newNum)) {
				// 3. 재귀호출: 소수라면 함수를 재귀호출한다.
				makePrimeNum(newNum, depth+1);
			}
		}
	}
	
	
	// num이 소수인지 판별하여 결과를 리턴하는 함수
	static boolean isPrime(int num) {
		if(num < 2) return false;
		
		for(int i = 2; i*i <= num; i++) {
			if(num%i == 0) return false;
		}
		
		return true;
	}
		
}
