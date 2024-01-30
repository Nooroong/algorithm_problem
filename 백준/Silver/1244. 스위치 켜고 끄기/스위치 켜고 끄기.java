import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 
 * @author JiYeon Sin
 * 1. 스위치의 개수를 입력받는다.
 * 	1-1. 스위치의 개수를 +1 해준다.
 * 		 왜냐하면 문제에서 스위치 번호는 1번부터 시작하기 때문이다.
 * 		 대신 0번째 요소는 상태 전환, 출력에 사용되지 않도록 주의한다. 
 * 2. 스위치의 상태를 입력받는다.
 * 3. 학생의 수를 입력받는다.
 * 4. 학생 정보를 하나씩 확인하면서 성별에 따라 적절히 처리해준다.
 * 5. 남학생이라면 받은 수의 배수를 갖는 스위치의 상태를 전환한다.
 * 6-1. 여학생이라면 자신이 받은 수를 중심으로 좌, 우 스위치의 상태가 동일하면 상태를 전환한다.
 * 		(가운데 스위치 상태도 바뀌어야 한다.)
 * 6-2. 배열의 범위를 넘지 않는 선에서 대칭인 경우, 좌우 범위가 늘어난다.
 * 7. 학생 정보를 모두 확인하고 처리를 다 해줬다면 마지막 스위치 상태를 출력한다.
 * 
 */
public class Main {
	static BufferedReader br;
	static StringTokenizer st;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		
		int numOfSwitchs; // 스위치의 개수
		int []stateOfSwitches; // 스위치의 상태 배열
		int numOfStudents; // 학생의 수
		int genderOfStudent; // 학생 한 명의 성별
		int numOfStudentRecive; // 학생 한 명이 받은 수
		
		
		
		// 스위치의 개수를 입력받는다.
		// +1을 해주는 이유: 문제에서는 스위치 번호가 1번부터 사용되기 때문이다.
		numOfSwitchs = Integer.parseInt(br.readLine().trim()) + 1;
		
		// 스위치의 상태를 입력받는다.(여기서 1번 인덱스부터 유효함에 주의!!!!!!!)
		stateOfSwitches = new int[numOfSwitchs];
		st = new StringTokenizer(br.readLine().trim());
		for(int idx = 1; st.hasMoreTokens(); idx++) {
			stateOfSwitches[idx] = Integer.parseInt(st.nextToken());
		}
		
		numOfStudents = Integer.parseInt(br.readLine().trim()); // 학생의 수를 입력받는다.
		
		// 학생의 정보를 입력받으면서 동시에 처리한다.
		for(int stdCase = 0; stdCase < numOfStudents; stdCase++) {
			st = new StringTokenizer(br.readLine().trim());
			genderOfStudent = Integer.parseInt(st.nextToken()); // 학생의 성별
			numOfStudentRecive = Integer.parseInt(st.nextToken()); // 학생이 받은 수
			
			// 성별에 따라 처리해준다.
			if(genderOfStudent == 1) { // 남학생인 경우
				// 받은 수의 배수를 갖는 스위치의 상태를 전환
				for(int cnt = 1; (numOfStudentRecive * cnt) < numOfSwitchs; cnt++ ){
					stateOfSwitches[numOfStudentRecive * cnt] = (stateOfSwitches[numOfStudentRecive * cnt] + 1) % 2; // 2진수같은 느낌
				}
			} else { // 여학생인 경우
				// 받은 수를 중심으로 좌, 우 스위치의 상태가 동일하면 상태를 전환
				// 가운데 스위치 상태도 바뀌어야 한다.
				stateOfSwitches[numOfStudentRecive] = (stateOfSwitches[numOfStudentRecive] + 1) % 2;
				
				
				int leftIdx = numOfStudentRecive-1; // 받은 수의 왼쪽 스위치 번호
				int rightIdx = numOfStudentRecive+1; // 받은 수의 오른쪽 스위치 번호
				
				// 배열의 범위를 벗어나지 않도록 주의한다.
				// 범위를 벗어나지 않으면서(0번 인덱스는 범위에 들어가지 않음!!!!!!!!!)
				// 좌, 우 스위치의 상태가 동일하면
				while((leftIdx > 0 && rightIdx < numOfSwitchs) &&
						(stateOfSwitches[leftIdx] == stateOfSwitches[rightIdx])) {
					// 상태를 전환하고
					stateOfSwitches[leftIdx] = (stateOfSwitches[leftIdx] + 1) % 2;
					stateOfSwitches[rightIdx] = (stateOfSwitches[rightIdx] + 1) % 2;
					
					// 다음 스위치를 살핀다.
					leftIdx -= 1;
					rightIdx += 1;
				}
			}

		}
		

		// 학생 정보를 모두 확인했다면 스위치 상태를 출력
		for(int idx = 1; idx < numOfSwitchs; idx++) {
			System.out.print(stateOfSwitches[idx] + " ");
			
			// 한 줄에 20개씩 잘라서 출력하라고 되어있다... 문제를 잘 읽자...
			if(idx % 20 == 0) {
				System.out.println();
			}
		}
	}
    
}