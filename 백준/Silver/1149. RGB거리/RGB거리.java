import java.io.*;
import java.util.*;

/**
 * 
 * @author JiYeon Sin
 * 이전 집과 색이 달라야 한다.
 * 각 경우에 대해 최소값을 고른다고 전체 비용이 최소 비용이 되는 것이 아니다.
 * 따라서 처음에 어떤 집을 선택했는지에 따라 비용을 각각 계산하여 최소값을 출력한다.
 * 
 * 1. 집의 수를 입력받는다.
 * 2. 집의 비용을 입력받는다.
 * 3. 처음에 고를 수 있는 3가지 경우에 대한 최소값을 각각 구한다.
 * 4. 마지막 3개의 합계 중 최소값을 출력한다.
 */
public class Main {
	public static BufferedReader br;
	public static StringTokenizer st;

	public static int houseCount; // 집의 수
	
	public static int[][] house; // 집의 비용을 저장하는 배열
	public static int[][] dpTable;
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		
		// 1. 집의 수를 입력받는다.
		houseCount = Integer.parseInt(br.readLine().trim());
		
		
		// 초기화
		house = new int[houseCount+1][3];
		dpTable = new int[houseCount+1][3];
		
		
		// 2. 집의 비용을 입력받는다.
		for(int idx = 1; idx <= houseCount; idx++) {
			st = new StringTokenizer(br.readLine().trim());
			
			house[idx][0] = Integer.parseInt(st.nextToken());
			house[idx][1] = Integer.parseInt(st.nextToken());
			house[idx][2] = Integer.parseInt(st.nextToken());
		}
		
		
		// 3. 처음에 고를 수 있는 3가지 경우에 대한 최소값을 각각 구한다.
		for(int idx = 1; idx <= houseCount; idx++) {
			dpTable[idx][0] = Min(dpTable[idx-1][1], dpTable[idx-1][2]) + house[idx][0];
			dpTable[idx][1] = Min(dpTable[idx-1][0], dpTable[idx-1][2]) + house[idx][1];
			dpTable[idx][2] = Min(dpTable[idx-1][0], dpTable[idx-1][1]) + house[idx][2];
		}
		
		
		// 4. 마지막 3개의 합계 중 최소값을 출력한다.
		System.out.println(Min(Min(dpTable[houseCount][0], dpTable[houseCount][1]), dpTable[houseCount][2]));
	}
	
	
	
	// 두 파라미터 중 작은 값을 반환한다.
	public static int Min(int a, int b) {
		return (a < b) ? a : b; 
	}
}
