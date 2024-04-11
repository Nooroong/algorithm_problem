import java.io.*;
import java.util.*;

/**
 * 
 * @author JiYeon Sin
 * 중복 순열?
 *
 */
public class Main {
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	
	static boolean elementList[]; // 각 자연수의 수를 기록
	static int elementCount, selectCount;
	static int selectElementList[]; // 선택한 원소들을 담는 배열
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		// 전체 원소의 개수와 선택할 수 있는 원소의 개수를 입력받는다.
		st = new StringTokenizer(br.readLine().trim());
		elementCount = Integer.parseInt(st.nextToken());
		selectCount = Integer.parseInt(st.nextToken());
		
		elementList = new boolean[10001]; // 입력으로 주어지는 수는 10000이하의 자연수
		selectElementList = new int[selectCount];
		
		st = new StringTokenizer(br.readLine().trim());
		for(int idx = 0; idx < elementCount; idx++) {
			int element = Integer.parseInt(st.nextToken());
			elementList[element] = true;
		}
		
		// 사전 순 출력을 위해 정렬
//		Arrays.sort(elementList);
		
		// 원소를 뽑아서 출력한다.
		permutation(0);
		
		System.out.println(sb);
	}

	private static void permutation(int selectIdx) {
		// 기저조건
		if(selectIdx == selectCount) {
			for(int idx = 0; idx < selectCount; idx++)
				sb.append(selectElementList[idx]).append(" ");
			sb.append("\n");
			
			return;
		}
		
		// 다음 원소 고르기
		for(int idx = 0; idx < elementList.length; idx++) {
			if(!elementList[idx]) continue;
			
			// 선택
			selectElementList[selectIdx] = idx;
//			elementList[idx]--;
			
			
			permutation(selectIdx+1);
			
			// 복구
//			elementList[idx]++;
			selectElementList[selectIdx] = 0;
		}
	}

}
