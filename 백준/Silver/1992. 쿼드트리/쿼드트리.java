import java.io.*;
import java.util.*;

/**
 * 
 * @author JiYeon Sin
 * 탐색 대상에 들어가는 범위 전체의 값아 동일하지 않은 경우
 * 범위를 1/4로 쪼개고 재귀적으로 탐색하여 압축 결과를 출력한다.
 *
 */



public class Main {
	public static BufferedReader br;
	public static StringBuilder sb;

	public static int videoSize; // 흑백 영상 데이터의 사이즈
	public static int[][] video; // 흑백 영상의 데이터를 저장하는 배열
	
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		videoSize = Integer.parseInt(br.readLine().trim());
		
		
		video = new int[videoSize][videoSize];
		for(int rowIdx = 0; rowIdx < videoSize; rowIdx++) {
			String line = br.readLine().trim();
			for(int colIdx = 0; colIdx < videoSize; colIdx++) {
				video[rowIdx][colIdx] = line.charAt(colIdx) - '0';
			}
		}
		
//		for(int rowIdx = 0; rowIdx < videoSize; rowIdx++) {
//			System.out.println(Arrays.toString(video[rowIdx]));
//		}
		
		printCompressionData(0, 0, videoSize);
		
		System.out.println(sb);
		sb.setLength(0);
	}
	
	
	
	
	public static void printCompressionData(int curRow, int curCol, int searchSize) {
		
		
		int checkData = video[curRow][curCol]; // 검사 영역에서 확인해야 할 값
//		System.out.println(preData);
		boolean canCompress = true;

		for(int rowIdx = curRow; rowIdx < curRow+searchSize; rowIdx++) {
			for(int colIdx = curCol; colIdx < curCol+searchSize; colIdx++) {
				if(video[rowIdx][colIdx] != checkData) {
					canCompress = false;
					break;
				}
			}
			
			if(!canCompress) break;
		}
		
		if(!canCompress) {
			sb.append("(");
			int halfSize = searchSize / 2;
			printCompressionData(curRow, curCol, halfSize);
			printCompressionData(curRow, curCol+halfSize, halfSize);
			printCompressionData(curRow+halfSize, curCol, halfSize);
			printCompressionData(curRow+halfSize, curCol+halfSize, halfSize);
			sb.append(")");
		} else {
			sb.append(checkData);
		}
		
		
	}
}
