import java.io.*;
import java.util.*;

/**
 * 
 * @author JiYeon Sin
 * 같은 값이 여러 개 들어오는 건 신경쓸 필요 없고
 * 중복을 뺀 뒤 중복 순열을 만들면 된다.
 *
 */
public class Main {
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	
	static int elementList[]; // 원소들을 기록
	static int elementCount, selectCount;
	static int selectElementList[]; // 선택한 원소들을 담는 배열
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		// 전체 원소의 개수와 선택할 수 있는 원소의 개수를 입력받는다.
		st = new StringTokenizer(br.readLine().trim());
		elementCount = Integer.parseInt(st.nextToken());
		selectCount = Integer.parseInt(st.nextToken());
		
		elementList = new int[elementCount]; // 입력으로 주어지는 수는 자연수
		selectElementList = new int[selectCount];
		
		// elementList에는 중복된 원소를 넣지 않는다.
		int element; // 입력으로 들어온 원소 하나
		boolean newElement; // 지금 입력된 원소가 새로운 원소인가?
		st = new StringTokenizer(br.readLine().trim());
		
		for(int idx = 0; idx < elementCount; idx++) {
			element = Integer.parseInt(st.nextToken());
			newElement = true; 
			
			// 앞에 중복된 값이 있는지 확인(n이 최대 7이라서 시간상 문제 x)
			for(int preIdx = 0; preIdx < idx; preIdx++) {
				if(element == elementList[preIdx]) {
					newElement = false;
					break;
				}
			}
			
			// 새로운 값이라면 원소 목록에 추가
			if(newElement)
				elementList[idx] = element;
		}
		
		// 사전 순 출력을 위해 정렬
		Arrays.sort(elementList);
		
		// 원소를 뽑아서 출력한다.
		permutation(0);
		
		System.out.println(sb);
	}

	private static void permutation(int selectIdx) {
		// 기저조건: 원하는 갯수만큼 원소들을 고른 경우
		if(selectIdx == selectCount) {
			// 출력
			for(int idx = 0; idx < selectCount; idx++)
				sb.append(selectElementList[idx]).append(" ");
			sb.append("\n");
			
			// 다른 원소 뽑으러 돌아가기
			return;
		}
		
		
		// 현재 위치(selectIdx)의 원소 고르기
		for(int idx = 0; idx < elementList.length; idx++) {
			// 0은 유효한 원소가 아니다.
			if(elementList[idx] == 0) continue;
			
			selectElementList[selectIdx] = elementList[idx]; // 선택

			permutation(selectIdx+1);
			
			selectElementList[selectIdx] = 0; // 다음 선택을 위해 현 선택 복구
		}
	}

}
