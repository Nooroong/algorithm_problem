import java.util.*;
import java.io.*;

/**
 * 
 * @author JiYeon Sin
 * 6개의 국가가 한 번 씩 경기를 치루므로 총 경기 수는 15번이다.
 * 각 경기에 대해 모든 경기의 수를 따져 해당 대진 결과가 가능한 결과인지 판별한다.
 */
public class Main {
	public static BufferedReader br;
	public static StringTokenizer st;
	public static StringBuilder sb;
	
	public static final int TOTAL_MATCHES = 15; // 총 경기수 6C2
	public static final int NUMBER_OF_COUNTRIES = 6; // 국가의 수
	public static final int TEST_CASE = 4;
	
	public static int[][] worldCupResult; // 월드컵 경기 후 각 팀의 승패 결과
	
	public static boolean disableResult;
	
	public static final int WIN = 0;
	public static final int DRAW = 1;
	public static final int LOSS = 2;
	
	// 대진표(?)
	public static final int[][] MATCH = { {0, 0, 0, 0, 0, 1, 1, 1, 1, 2, 2, 2, 3, 3, 4}, {1, 2, 3, 4, 5, 2, 3, 4, 5, 3, 4, 5, 4, 5, 5} };
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		
		
		for(int tc = 1; tc <= TEST_CASE; tc++) {
			worldCupResult = new int[NUMBER_OF_COUNTRIES][3];
			disableResult = false;
			
			st = new StringTokenizer(br.readLine().trim());
			for(int idx = 0; idx < NUMBER_OF_COUNTRIES; idx++) {
				worldCupResult[idx][0] = Integer.parseInt(st.nextToken());
				worldCupResult[idx][1] = Integer.parseInt(st.nextToken());
				worldCupResult[idx][2] = Integer.parseInt(st.nextToken());
				
				
				// 한 국가의 승, 패, 무 합이 국가수-1과 일치하지 않다 == 유효하지 않은 경기 결과
				if(worldCupResult[idx][0]+worldCupResult[idx][1]+worldCupResult[idx][2] != NUMBER_OF_COUNTRIES-1) {
					disableResult = true;
					break;
				}
			}

			// 경기 결과가 가능한지 확인
			if(disableResult) {
				sb.append("0 ");
			} else {
				disableResult = true;
				bt(0);
				sb.append(disableResult ? "0 " : "1 ");
			}
		}
		
		
		System.out.println(sb);
	}
	
	
	public static void bt(int round) {
		if(!disableResult) {
			return;
		}
		
		if(round == TOTAL_MATCHES) {
			disableResult = false;
			return;
		}
		
		int team1 = MATCH[0][round];
		int team2 = MATCH[1][round];
		
		
		// 승-패의 경우
		if(worldCupResult[team1][WIN] > 0 && worldCupResult[team2][LOSS] > 0) {
			worldCupResult[team1][WIN]--;
			worldCupResult[team2][LOSS]--;
			bt(round+1);
			worldCupResult[team1][WIN]++;
			worldCupResult[team2][LOSS]++;
		}
		
		// 무-무의 경우
		if(worldCupResult[team1][DRAW] > 0 && worldCupResult[team2][DRAW] > 0) {
			worldCupResult[team1][DRAW]--;
			worldCupResult[team2][DRAW]--;
			bt(round+1);
			worldCupResult[team1][DRAW]++;
			worldCupResult[team2][DRAW]++;
		}
		
		// 패-승의 경우
		if(worldCupResult[team1][LOSS] > 0 && worldCupResult[team2][WIN] > 0) {
			worldCupResult[team1][LOSS]--;
			worldCupResult[team2][WIN]--;
			bt(round+1);
			worldCupResult[team1][LOSS]++;
			worldCupResult[team2][WIN]++;
		}
	}
}
