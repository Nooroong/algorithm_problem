import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * BOJ
 * @author eunwoo.lee
 * 
 * 1. 테스트 케이스를 입력받는다. 
 * 2. 조합으로 폐업하지 않을 치킨집을 뽑는다.
 * 	2-1. 최대 치킨집 개수를 다 선택하면, 치킨 거리를 계산한다.
 * 3. 각 집에 대하여 선택된 치킨 집 중 거리가 최소인 치킨집과의 거리를 누적해 더한다.
 * 4. 답을 출력한다.
 *
 */

class Point{
	int rowIdx;
	int colIdx;
	
	Point(int rowIdx, int colIdx) {
		this.rowIdx = rowIdx;
		this.colIdx = colIdx;
	}
}

public class Main {
	
	static BufferedReader br;
	static StringTokenizer st;
	
	static int citySize, maxChikenNum;
	static int[][] city; // 도시 정보 저장
	static ArrayList<Point> homeList, chickenList; // 집과 치킨집 정보 저장
	static int minChickenDistance, minChickenDistanceSum;
	static int[] selectedChickenList; // 조합에서 선택한 치킨집 인덱스 저장
	
	public static int calChickenDistance(int[] selectedChickenList) {
	
		minChickenDistanceSum = 0;
		
		// 각 집마다 제일 가까운 치킨집 거리 구하기
		for (int homeIdx=0; homeIdx<homeList.size(); homeIdx++) {
			int minChickenDistanceByHome=4*citySize*citySize;
			for (int chickenIdx=0; chickenIdx<maxChikenNum; chickenIdx++) {
				minChickenDistanceByHome = Math.min(minChickenDistanceByHome, 
						Math.abs(homeList.get(homeIdx).rowIdx-chickenList.get(selectedChickenList[chickenIdx]).rowIdx)+Math.abs(homeList.get(homeIdx).colIdx-chickenList.get(selectedChickenList[chickenIdx]).colIdx));
			}
			minChickenDistanceSum += minChickenDistanceByHome;
		}
		return minChickenDistanceSum;
	}

	public static void combination(int selectIdx, int chickenIdx) {
		// 1. 기저 조건
		// 다 선택함
		if (selectIdx==maxChikenNum) {
			minChickenDistance = Math.min(minChickenDistance, calChickenDistance(selectedChickenList));
			return;
		}
		// 모든 원소를 살펴봤다.
		if (chickenIdx==chickenList.size()) {
			return;
		}
		
		// 2. 해당 원소 사용
		selectedChickenList[selectIdx] = chickenIdx;
		combination(selectIdx+1, chickenIdx+1);
		
		// 3. 해당 원소를 사용하지 않을때
		selectedChickenList[selectIdx] = 0;
		combination(selectIdx, chickenIdx+1);
	}
	
	
	public static void inputTestCase() throws IOException{
		// 도시의 크기와 치킨집의 최대 개수를 입력받는다.
		st = new StringTokenizer(br.readLine().trim());
		citySize = Integer.parseInt(st.nextToken());
		maxChikenNum = Integer.parseInt(st.nextToken());
		
		// 도시의 정보를 입력받는다.
		city = new int[citySize][citySize];
		homeList = new ArrayList<>();
		chickenList = new ArrayList<>();
		
		for (int cityRowIdx=0; cityRowIdx<citySize; cityRowIdx++) {
			st = new StringTokenizer(br.readLine().trim());
			for (int cityColIdx=0; cityColIdx<citySize; cityColIdx++) {
				city[cityRowIdx][cityColIdx] = Integer.parseInt(st.nextToken());
				// 각 집과 치킨집의 좌표를 arrayList에 저장
				if (city[cityRowIdx][cityColIdx]==1) {
					homeList.add(new Point(cityRowIdx, cityColIdx));
				}else if (city[cityRowIdx][cityColIdx]==2) {
					chickenList.add(new Point(cityRowIdx, cityColIdx));
				}
			}
		}
	}

	public static void main(String[] args) throws Exception{
		br = new BufferedReader(new InputStreamReader(System.in));
		
		// 테스트 케이스를 입력받는다.
		inputTestCase();
		
		// 조합으로 폐업하지 않을 치킨집을 선택한다.
		minChickenDistance = Integer.MAX_VALUE;
		selectedChickenList = new int[maxChikenNum];
		combination(0, 0);
		
		// 출력
		System.out.println(minChickenDistance);
	}

}
