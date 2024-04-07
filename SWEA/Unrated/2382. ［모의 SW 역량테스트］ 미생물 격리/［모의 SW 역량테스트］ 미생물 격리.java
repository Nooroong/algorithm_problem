import java.io.*;
import java.util.*;

/**
 * 
 * @author JiYeon Sin
 * 리스트와 id로 미생물 군집을 관리한다.
 * 합치기는 모든 미생물 군집의 이동이 끝난 후에 진행되어야 한다.
 * 모든 군집이 동시에 이동하는 것이 아닌, 하나씩 이동이 이뤄지기 때문에
 * 실제로는 겹치지 않는 군집이 코드 상에서는 겹치는 경우가 생기기 때문이다.
 * 
 * 1. 구역의 크기, 격리시간, 군집의 수를 입력받는다.
 * 2. 미생물 군집 정보를 입력받아 리스트에 하나씩 추가한다. 군집의 id는 행, 열 정보로 생성.
 * 3. 주어진 격리 시간 만큼 작업을 반복을 한다.
 * 	3-1. 각 군집은 이동방향에 따라 다음 셀로 이동
 * 		3-1-1. 이동 후 약품에 닿은 군집은 수가 반으로 줄어든다.
 * 		3-1-2. 수가 0이 되면 미생물 군집이 사라진다.
 * 	3-2. 군집들을 위치, 수를 기준으로 정렬
 * 	3-3. 위치에 따라 합치기 처리
 * 		3-3-1. 현재 군집과 다음 군집의 위치가 같다면 현재 군집이 다음 군집을 흡수한다.
 * 4. 남아있는 미생물 수의 총합을 구하고 출력한다.
 */
public class Solution {
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	
	static int testCase;
	static int mapSize;
	
	static int time; // 격리 시간
	static int microbeCount; // 미생물 군집의 수
	static List<Microbe> microbeList; // 미생물 군집 목록
	
	// 상, 우, 하, 좌(방향 전환을 편하게 하기 위해 이렇게 저장한다.)
	static int[] deltaRow = { -1, 0, 1, 0 };
	static int[] deltaCol = { 0, 1, 0, -1 };
	
	static int[][] cost;
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		testCase = Integer.parseInt(br.readLine().trim());
		for(int tc = 1; tc <= testCase; tc++) {
			// 1. 구역의 크기, 격리시간, 군집의 수를 입력받는다.
			st = new StringTokenizer(br.readLine().trim());
			mapSize = Integer.parseInt(st.nextToken());
			time = Integer.parseInt(st.nextToken());
			microbeCount = Integer.parseInt(st.nextToken());

			// 리스트 생성			
			microbeList = new ArrayList<>();
			
			// 군집 수 만큼 각각의 정보를 입력받고 리스트에 추가한다.
			int rowIdx, colIdx, amount, dir;
			for(int idx = 0; idx < microbeCount; idx++) {
				st = new StringTokenizer(br.readLine().trim());
				rowIdx = Integer.parseInt(st.nextToken());
				colIdx = Integer.parseInt(st.nextToken());
				amount = Integer.parseInt(st.nextToken());
				dir = Integer.parseInt(st.nextToken());
				
				// 입력된 방향을 작성된 코드에 맞게 조정(상, 우 조정)
				if(dir == 1) dir = 0;
				else if(dir == 4) dir = 1;
				
				// 2. 미생물 군집 정보를 입력받아 리스트에 하나씩 추가한다. 군집의 id는 행, 열 정보로 생성.
				microbeList.add(new Microbe(rowIdx*mapSize + colIdx, rowIdx, colIdx, amount, dir));
			}
			
			
			
			// 3. 주어진 격리 시간 만큼 작업을 반복을 한다.
			while(time-- > 0) {
				moveMicrobe(); // 3-1. 각 군집은 이동방향에 따라 다음 셀로 이동
				Collections.sort(microbeList); // 3-2. 군집들을 위치, 수를 기준으로 정렬
				combineMicrobe(); // 3-3. 위치에 따라 합치기 처리
			}
			

			// 4. 남아있는 미생물 수의 총합을 구하고 출력한다.
			sb.append("#").append(tc).append(" ").append(totalMicrobeAmount());
			System.out.println(sb);
			sb.setLength(0);
			
		}
	}
	
	
	private static void moveMicrobe() {
		// 각 미생물 군집을 이동방향대로 이동시킨다.
		for(int mIdx = 0; mIdx < microbeList.size(); mIdx++) {
			Microbe curM = microbeList.get(mIdx);
			
			// 미생물이 새롭게 이동할 위치
			curM.row += deltaRow[curM.dir];
			curM.col += deltaCol[curM.dir];
			curM.id = curM.row * mapSize + curM.col;
			
			
			// 3-1-1. 이동 후 약품에 닿은 군집은 수가 반으로 줄어든다.
			if(curM.row == 0 || curM.row == (mapSize-1) || curM.col == 0 || curM.col == (mapSize-1)) {
				curM.amount /= 2; // 수를 절반으로 감소시킨다.
				curM.dir = (curM.dir + 2) % deltaRow.length; // 방향 전환
				
				// 3-1-2. 수가 0이 되면 미생물 군집이 사라진다.
				if(curM.amount == 0) {
					microbeList.remove(mIdx);
					mIdx--;
				}
			}
		}
		

	}
	
	
	private static void combineMicrobe() {
		for(int mIdx = 0; mIdx < microbeList.size() - 1; mIdx++) {
			Microbe curM = microbeList.get(mIdx);
			Microbe nextM = microbeList.get(mIdx+1);
			
			
			// 미생물 군집 리스트는 좌표를 바탕으로 생성한 id를 기준으로, id가 같다면 미생물 수를 기준으로 내림차순 정렬되어 있다.
			// 따라서 현재 군집과 다음 군집의 id가 같다면(== 위치가 같다면),
			// 다음번 군집의 미생물을 현재 군집이 흡수하면 된다.
			if(curM.id == nextM.id) {
				curM.amount += nextM.amount;
				microbeList.remove(mIdx+1); // 흡수 당한 군집을 리스트에서 삭제
				mIdx--; // 중간의 요소가 삭제됐으므로 인덱스 1 감소
			}
		}
	}

	// 리스트에 남은 전체 미생물 수를 반환하는 함수
	static int totalMicrobeAmount() {
		int total = 0;
		
		for(Microbe microbe : microbeList) {
			total += microbe.amount;
		}
		
		return total;
	}
	
	
	// 미생물 정보를 저장하는 클래스
	static class Microbe implements Comparable<Microbe> {
		int id;
		int row;
		int col;
		int amount;
		int dir;
		
		public Microbe(int id, int row, int col, int amount, int dir) {
			this.id = id;
			this.row = row;
			this.col = col;
			this.amount = amount;
			this.dir = dir;
		}

		@Override
		public int compareTo(Microbe o) {
			// 좌표 순서상을 기준으로 정렬. 좌표가 같다면 미생물 수를 기준으로 정렬.
			if(this.id == o.id) return (o.amount-this.amount);
			return (this.id - o.id);
		}
	}
}
