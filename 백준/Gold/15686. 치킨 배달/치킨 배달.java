import java.io.*;
import java.util.*;

/**
 * 
 * @author JiYeon Sin
 * '최대' m개의 치킨집을 살려야 한다.
 * 따라서 nC1 ~ nCm에 대해 모두 생각해야 한다.
 * 모든 입력을 받은 뒤 선택할 수 있는 원소의 수를 1씩 늘려가면서 치킨집을 고른다.
 * 치킨집을 다 선택하면 도시의 치킨 거리를 계산한다.
 */

public class Main {
	public static BufferedReader br;
	public static StringTokenizer st;
	
	public static int mapSize; // 도시의 크기
	public static int[][] map; // 도시의 정보를 저장하는 배열
	
	public static int selectCount; // 선택할 수 있는 치킨집의 수
	public static int maxSelectCount; // 선택할 수 있는 치킨집의 최대 수
	public static int[] selectElementList; // 선택한 치킨집의 인덱스가 담긴다.
	
	public static final int HOUSE = 1;
	public static final int CHIKEN = 2;
	
	public static List<int[]> houseList; // 집 목록
	public static List<int[]> chikenList; // 치킨집 목록
	
	public static int minChikenStreet; // 최소 치킨 거리
	
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		
		st = new StringTokenizer(br.readLine().trim());
		
		// 지도의 크기와 고를 수 있는 치킨집의 최대 수를 입력받는다.
		mapSize = Integer.parseInt(st.nextToken());
		maxSelectCount = Integer.parseInt(st.nextToken());
		
		// 각종 초기화
		map = new int[mapSize][mapSize];
		minChikenStreet = Integer.MAX_VALUE;
		chikenList = new ArrayList<>();
		houseList = new ArrayList<>();
		
		// 지도의 정보를 입력받으면서 집 목록, 치킨집 목록에 원소를 추가한다.
		for(int rowIdx = 0; rowIdx < mapSize; rowIdx++) {
			st = new StringTokenizer(br.readLine().trim());
			
			for(int colIdx = 0; colIdx < mapSize; colIdx++) {
				map[rowIdx][colIdx] = Integer.parseInt(st.nextToken());
				
				if(map[rowIdx][colIdx] == CHIKEN) {
					chikenList.add(new int[] {rowIdx, colIdx});
				} else if(map[rowIdx][colIdx] == HOUSE) {
					houseList.add(new int[] {rowIdx, colIdx});
				}
			}
		}
		
		// nC1 ~ nCm
		for(selectCount = 1; selectCount <= maxSelectCount; selectCount++) {
			selectElementList = new int[selectCount];
			selectChiken(0, 0);
		}
		
		// 도시의 치킨 거리의 최솟값을 출력한다.
		System.out.println(minChikenStreet);
		
	}
	
	
	// 조합으로 폐업시키지 않을 치킨집을 고른다.
	public static void selectChiken(int selectIdx, int elementIdx) {
		// 기저조건 1: 치킨집을 다 고른 경우
		if(selectIdx == selectCount) {
			int chikenStreet = calcCityChikenStreet(); // 도시의 치킨 거리를 계산한다.
			if(chikenStreet < minChikenStreet) minChikenStreet = chikenStreet; // 최소값 갱신
			return;
		}
		
		// 기저조건 2: 모든 원소를 다 살핀 경우
		if(elementIdx == chikenList.size()) {
			return;
		}
		
		// 현재 치킨집을 선택하는 경우
		selectElementList[selectIdx] = elementIdx;
		selectChiken(selectIdx+1, elementIdx+1);
		
		// 현재 치킨집을 선택하지 않는 경우
		selectElementList[selectIdx] = 0;
		selectChiken(selectIdx, elementIdx+1);
	}
	
	
	
	
	public static int calcCityChikenStreet() {
		// 모든 집들에 대해 선택된 치킨집과의 거리를 계산한다.
		// 최소값을 찾으면 그 값을 변수에 더하고 결과를 반환한다.
		
		int cityCikenStreet = 0; // 도시의 치킨 거리
		
		// 각 집에 대해 치킨 거리를 찾는다.
		for(int[] house : houseList) {
			int minDistance = Integer.MAX_VALUE;
			
			for(int element : selectElementList) {
				// 각 집의 치킨거리 == 집과 가장 가까운 치킨집 사이의 거리
				int distance = Math.abs(house[0]-chikenList.get(element)[0]) + Math.abs(house[1]-chikenList.get(element)[1]);
				if(distance < minDistance) minDistance = distance;
			}
			
			// 도시의 치킨 거리는 모든 집의 치킨 거리의 합
			cityCikenStreet += minDistance;
		}
		
		return cityCikenStreet;
	}
}
