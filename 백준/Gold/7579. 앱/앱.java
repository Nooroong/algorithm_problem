import java.io.*;
import java.util.*;

/**
 * 
 * @author JiYeon Sin
 * 메모리 제한을 지키기 위해 보는 관점을 조금 달리해야 한다.
 * 
 * 메모리 = 가방, 앱 = 물건, 각 앱이 차지하는 메모리 = 무게, 비용 = 물건의 가치
 * 로 보고 dp table을 [앱][메모리]로 구성한다면
 * 메모리는 M~(모든 앱의 메모리 총합)로 구성되어야 한다
 * 이때 최악의 경우 메모리 총합은 10억이므로 시간, 공간에 대해 모두 불리하다.
 * 
 * 비용은 최소 0, 최대 100의 값을 가진다.
 * 따라서 dp table을 [앱][최대 비용]으로 구성한다면
 * 최악의 경우 최대 비요은 100*100 = 10000으로 문제의 조건을 만족할 수 있다.
 * 이때 dp table의 각 요소의 값은 i번째 앱까지 해당 최대 비용에 대한 최대 메모리 총합이 된다.
 *  
 * 1. 활성화된 앱의 개수, 확보해야 할 메모리의 크기를 입력받는다.
 * 2. 각 앱이 차지하는 메모리 바이트 수를 입력받는다.
 * 3. 각 앱의 비용을 입력받는다.
 * 4. [아이템][최대비용]에 대한 정보를 저장하는 dp table을 생성한다.
 * 5. 모든 앱의 모든 비용 제한에 대해 dp table을 채워나간다.
 *  5-1. 현재 앱의 비용이 최대 비용을 넘는 경우 선택될 수 없다.
 *  5-2. 현재 앱의 비용이 최대 비용을 넘지 않는 경우 선택하거나 선택하지 않는 쪽을 계산하여
 *       최적해를 구한다.(이때 dp table에 들어가는 최적해는 최대 메모리 크기)
 * 6. dp table을 모두 채웠다면 마지막 행에 대하여 요소 값(메모리 합)이 M을 넘는 최소 비용을 찾는다.(최소 비용 == 열 인덱스)
 */
public class Main {
	static BufferedReader br;
	static StringTokenizer st;
	
	static int appCount; // 활성화된 앱의 개수
	static int memorySize; // 확보해야 할 메모리 크기
	static App[] appList; // 각 앱의 정보를 담는 배열
	
	static int[][] dpTable; // 각 경우의 최적해를 담는 dp table
	static int dpRowIdx; // array switching
	static final int MAX_COST = 100 * 100;
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		
		// 1. 활성화된 앱의 개수, 확보해야 할 메모리의 크기를 입력받는다.
		st = new StringTokenizer(br.readLine().trim());
		appCount = Integer.parseInt(st.nextToken());
		memorySize = Integer.parseInt(st.nextToken());
		
		// 2. 각 앱이 차지하는 메모리 바이트 수를 입력받는다.
		appList = new App[appCount + 1];
		st = new StringTokenizer(br.readLine().trim()); 
		for(int appIdx = 1; appIdx <= appCount; appIdx++) {
			appList[appIdx] = new App();
			appList[appIdx].memory = Integer.parseInt(st.nextToken());
		}
		
		// 3. 각 앱의 비용을 입력받는다.
		st = new StringTokenizer(br.readLine().trim()); 
		for(int appIdx = 1; appIdx <= appCount; appIdx++) {
			appList[appIdx].cost = Integer.parseInt(st.nextToken());
		}
		
		// 4. [아이템][최대비용]에 대한 정보를 저장하는 dp table을 생성한다.
		dpTable = new int[2][MAX_COST+1];
		
		// 5. 모든 앱의 모든 비용 제한에 대해 dp table을 채워나간다.
		dpRowIdx = 0;
		for(int appIdx = 1; appIdx <= appCount; appIdx++) {
			dpRowIdx = 1 - dpRowIdx; // 행 전환
			
			for(int cost = 0; cost <= MAX_COST; cost++) {
				
				if(appList[appIdx].cost > cost) {
					// 5-1. 현재 앱의 비용이 최대 비용을 넘는 경우 선택될 수 없다.
					dpTable[dpRowIdx][cost] = dpTable[1-dpRowIdx][cost];
				} else {
					// 5-2. 현재 앱의 비용이 최대 비용을 넘지 않는 경우 선택하거나 선택하지 않는 쪽을 계산하여
					//      최적해를 구한다.(이때 dp table에 들어가는 최적해는 최대 메모리 크기)
					int curCost = appList[appIdx].cost;
					int curMemory = appList[appIdx].memory;
					dpTable[dpRowIdx][cost] = max(dpTable[1-dpRowIdx][cost-curCost] + curMemory,
							                      dpTable[1-dpRowIdx][cost]);
				}
			}
		}
		
//		for(int rowIdx = 0; rowIdx < 2; rowIdx++) {
//			System.out.println(Arrays.toString(dpTable[rowIdx]));
//		}
		
		
		// 6. dp table을 모두 채웠다면 마지막 행에 대하여 요소 값(메모리 합)이 M을 넘는 최소 비용을 찾는다.(최소 비용 == 열 인덱스)
		for(int colIdx = 0; colIdx <= MAX_COST; colIdx++) {
			if(dpTable[dpRowIdx][colIdx] >= memorySize) {
				System.out.println(colIdx);
				break;
			}
		}
		
	}
	
	static class App {
		int memory;
		int cost;
	}
	
	private static int max(int a, int b) {
		return (a > b) ? a : b;
	}
}
