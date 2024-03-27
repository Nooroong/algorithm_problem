import java.io.*;
import java.util.*;

/**
 * 
 * @author JiYeon Sin
 * knapsack문제 <- 동적계획법을 이용하여 해결해보기
 * 
 * 1. 물품의 수와 최대 무게 제한을 입력받는다.
 * 2. n개의 줄에 걸쳐 각 물건의 무게와 가치를 입력받는다.
 * 3. 각 경우에 대한 최적해를 저장할 dp table을 생성한다. 크기는 2*(최대 무게+1). (array switching)
 * 4. 1번째 물건부터 무게 제한이 0~maxWeight인 경우에 대해 최적해를 구한다.
 *  4-1. 물건을 배낭에 넣을 수 없는 경우 이전 물건의 최적해가 답이 된다.
 *  4-2. 물건을 배낭에 넣을 수 있는 경우
 *       물건을 배낭에 넣는 경우와 넣지 않는 경우의 최적해를 구해 더 큰 값을 dpTable에 저장한다.
 * 5. db table을 다 채웠으면 가장 마지막의 값(문제의 최적해)을 출력한다.
 */
public class Main {
	static BufferedReader br;
	static StringTokenizer st;
	
	static int itemCount, maxWeight; // 물품의 수, 준서가 버틸 수 있는 무게
	static Item[] itemList; // 물품 정보를 담는 배열
	
	static int[][] dpTable;
	static int dpTableIdx; // dpTable array를 switching하기 위한 인덱스
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		
		// 1. 물품의 수와 최대 무게 제한을 입력받는다.
		st = new StringTokenizer(br.readLine().trim());
		itemCount = Integer.parseInt(st.nextToken());
		maxWeight = Integer.parseInt(st.nextToken());
		
		// 2. n개의 줄에 걸쳐 각 물건의 무게와 가치를 입력받는다.
		itemList = new Item[itemCount+1];
		for(int idx = 1; idx <= itemCount; idx++) {
			st = new StringTokenizer(br.readLine().trim());
			itemList[idx] = new Item();
			itemList[idx].weight = Integer.parseInt(st.nextToken());
			itemList[idx].value = Integer.parseInt(st.nextToken());
		}
		
		// 3. 각 경우에 대한 최적해를 저장할 dp table을 생성한다. 크기는 2*(최대 무게+1).
		dpTable = new int[2][maxWeight+1];
		
		
		// 4. 1번째 물건부터 무게 제한이 0~maxWeight인 경우에 대해 최적해를 구한다.
		dpTableIdx = 0;
		for(int itemIdx = 1; itemIdx <= itemCount; itemIdx++) {
			dpTableIdx = 1 - dpTableIdx;
			
			for(int weight = 0; weight <= maxWeight; weight++) {
				if(itemList[itemIdx].weight > weight) {
					// 4-1. 물건을 배낭에 넣을 수 없는 경우 이전 물건의 최적해가 답이 된다.
					dpTable[dpTableIdx][weight] = dpTable[1-dpTableIdx][weight];
				} else {
					// 4-2. 물건을 배낭에 넣을 수 있는 경우
					//      물건을 배낭에 넣는 경우와 넣지 않는 경우의 최적해를 구해 더 큰 값을 dpTable에 저장한다.
					int curItmeWeight = itemList[itemIdx].weight;
					int curItemValue = itemList[itemIdx].value;
					dpTable[dpTableIdx][weight] = max(dpTable[1-dpTableIdx][weight-curItmeWeight] + curItemValue,
													dpTable[1-dpTableIdx][weight]);
					
				}
			}
		}
		
		// 5. db table을 다 채웠으면 가장 마지막의 값(문제의 최적해)을 출력한다.
		System.out.println(dpTable[dpTableIdx][maxWeight]);
	}
	
	
	static class Item {
		public int weight; // 각 물건의 무게
		public int value; // 각 물건의 가치
		
		public Item() {
			this.weight = 0;
			this.value = 0;
		}
		
		
	}
	
	static int max(int a, int b) {
		return (a > b) ? a : b;
	}
}
