import java.io.*;
import java.util.*;

/**
 * 
 * @author JiYeon Sin
 * 
 * 0. 서로소 집합과 관련된 변수, 메서드를 만든다.
 * 1. 테스트 케이스의 개수를 입력받는다.
 * 2. 원소의 수와 연산의 수를 입력받는다.
 * 3. 서로소 집합에 대해 초기화 작업을 해준다.
 * 4. 연산의 수만큼 반복
 * 5. 연산을 입력받는다.
 * 6. 연산에 따라 각각 처리한다.
 * 7. 결과를 출력한다.
 */
public class Solution {
	public static BufferedReader br;
	public static StringTokenizer st;
	public static StringBuilder sb;
	
	public static int testCase;
	
	
	public static int[] parentList; // 각 원소의 부모 정보
	public static int[] rankList; // 집합의 랭크 정보
	
	public static int elementCount; // 원소의 개수
	public static int operationCount; // 연산의 횟수
	
	public static int operation; // 연산
	public static int element1; // 원소 1
	public static int element2; // 원소 2
	
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		
		// 1. 테스트 케이스의 개수를 입력받는다.
		testCase = Integer.parseInt(br.readLine().trim());
		
		
		for(int tc = 1; tc <= testCase; tc++) {
			sb.append("#").append(tc).append(" ");
			
			// 2. 원소의 수와 연산의 수를 입력받는다.
			st = new StringTokenizer(br.readLine().trim());
			elementCount = Integer.parseInt(st.nextToken());
			operationCount = Integer.parseInt(st.nextToken());
			
			
			// 원소의 번호가 1번부터 시작함에 주의한다.
			parentList = new int[elementCount + 1];
			rankList = new int[elementCount + 1];
			make(); // 3. 서로소 집합에 대해 초기화 작업을 해준다.
			
			
			// 4. 연산의 수만큼 반복
			for(int op = 0; op < operationCount; op++) {
				// 5. 연산을 입력받는다.
				st = new StringTokenizer(br.readLine().trim());
				operation = Integer.parseInt(st.nextToken());
				element1 = Integer.parseInt(st.nextToken());
				element2 = Integer.parseInt(st.nextToken());
				
				/// 6. 연산에 따라 각각 처리한다.
				switch(operation) {
					case 0: // 합집합
						union(element1, element2);
						break;
					case 1: // 포함된 집합 비교
						boolean compareResult = isSetEqual(element1, element2);
						sb.append(compareResult ? "1" : "0");
				}
			}
			
			// 7. 결과를 출력한다.
			System.out.println(sb);
			sb.setLength(0);
		}
	}
	
	
	
	public static void make() {
		// 나 자신을 부모로 설정하고 rank를 0으로 초기화해준다.
		for(int idx = 0; idx < elementCount+1; idx++) {
			parentList[idx] = idx;
			rankList[idx] = 0;
		}
	}
	
	
	// element가 속해있는 집합의 대표자를 반환한다.
	public static int find(int element) {
		if(parentList[element] == element) return element;
		return parentList[element] = find(parentList[element]);
	}
	
	
	// 두 원소가 속한 집합을 합친다.
	public static void union(int element1, int element2) {
		int e1Parent = find(element1);
		int e2Parent = find(element2);
		
		// 두 원소가 속한 집합이 같으면 pass
		if(e1Parent == e2Parent) return;
		
		if(rankList[e1Parent] > rankList[e2Parent]) {
			parentList[e2Parent] = e1Parent;
			return;
		}
		
		parentList[e1Parent] = e2Parent;
		
		if(rankList[e1Parent] == rankList[e2Parent]) {
			rankList[e2Parent]++;
		}
	}
	
	
	// 두 원소가 포함된 집합이 같은지 비교하여 결과를 반환한다.
	public static boolean isSetEqual(int element1, int element2) {
		return find(element1) == find(element2);
	}
}
