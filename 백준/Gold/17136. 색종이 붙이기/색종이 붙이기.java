import java.io.*;
import java.util.*;

/**
 * 
 * @author JiYeon Sin
 * 모든 경우를 다 따져본다.
 * 
 */
public class Main {
	static BufferedReader br;
	static StringTokenizer st;
	
	// 크기에 따른 색종이의 개수
	static int[] coloredPaperLeft = { 0, 5, 5, 5, 5, 5 };
	
	static final int SIZE = 10; // 주어진 종이의 크기는 10
	static int[][] paper;
	static int minUsedCPCount; // 최소 색종이 사용 개수
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		
		paper = new int[SIZE][SIZE];
		for(int rowIdx = 0; rowIdx < SIZE; rowIdx++) {
			st = new StringTokenizer(br.readLine().trim());
			for(int colIdx = 0; colIdx < SIZE; colIdx++)
				paper[rowIdx][colIdx] = Integer.parseInt(st.nextToken());
		}
		
		minUsedCPCount = Integer.MAX_VALUE;
		
		// 모든 경우의 수를 생각한다.
		bt(0);
		
		System.out.println((minUsedCPCount == Integer.MAX_VALUE) ? -1 : minUsedCPCount);
	}
	
	
	
	/**
	 * 
	 * @param usedCPCount: 지금단계까지 사용한 색종이의 수
	 * @param paper: 현재 종이의 상태
	 */
	static void bt(int usedCPCount) {
//		System.out.println("cp count: " + usedCPCount);
//		printPaper();
		if(usedCPCount > minUsedCPCount) return; // 가지?치기
		
		if(isFullFilled()) {
			// 종료조건1: 모든 1에 색종이를 덮음
			// 최소 색종이 사용 개수를 갱신한다
			minUsedCPCount = Integer.min(minUsedCPCount, usedCPCount);
			
			return;
			
		} else if(usedCPCount == 25) {
			// 종료조건2: 색종이를 다 덮지 못했는데 색종이를 다 사용한 경우
			return; // 되돌아가서 하나 더 작은 크기의 색종이를 시도한다.
		}
		
		
		
		// 색종이를 붙일 수 있는 위치를 처음부터 찾아본다.
		int startRow = -1, startCol = -1;
		for(int rowIdx = 0; rowIdx < SIZE; rowIdx++) {
			for(int colIdx = 0; colIdx < SIZE; colIdx++) {
				
				if(paper[rowIdx][colIdx] == 1) {
					startRow = rowIdx;
					startCol = colIdx;
					break;
				}
			}
			
			// 색종이를 붙일 위치를 찾은 경우
			if(startRow >= 0 && startCol >= 0) break;
		}
		
		
		
		// 해당 위치에 대해 모든 사이즈를 전부 시도헤본다.
		for(int size = 5; size > 0; size--) {
			// 현재 사이즈에 대한 색종이가 하나도 남지 않은 경우 pass
			if(coloredPaperLeft[size] == 0) continue;
			
			
			if(canGlue(startRow, startCol, size)) {				
				glue(startRow, startCol, size, 0); // 색종이 붙이기
				coloredPaperLeft[size]--;
				
				bt(usedCPCount+1);
				
				coloredPaperLeft[size]++;
				glue(startRow, startCol, size, 1); // 되돌리기
				
			}
			
			// 하나 더 작은 사이즈 시도
		}
	}
	
	
	
	// startRow, startCol부터 시작하여 size 크기의 색종이를 붙일 수 있는가?
	static boolean canGlue(int startRow, int startCol, int size) {
		int count = 0;
		
		for(int rowIdx = startRow; (rowIdx < startRow+size) && (rowIdx < SIZE); rowIdx++) {
			for(int colIdx = startCol; (colIdx < startCol+size) && (colIdx < SIZE); colIdx++) {
				count += paper[rowIdx][colIdx];
			}
		}
		
		return count == (size*size);
	}
	
	
	// startRow, startCol부터 시작하여 size*size 영역을 value로 채운다.
	// 0으로 채우면 해당 영역에 색종이를 붙인다는 의미. 1로 채우면 원상복구의 의미.
	static void glue(int startRow, int startCol, int size, int value) {
		for(int rowIdx = startRow; rowIdx < (startRow+size); rowIdx++) {
			for(int colIdx = startCol; colIdx < (startCol+size); colIdx++) {
				paper[rowIdx][colIdx] = value;
			}
		}
	}
	
	
	static boolean isFullFilled() {
		int count = 0;
		
		for(int rowIdx = 0; rowIdx < SIZE; rowIdx++) {
			for(int colIdx = 0; colIdx < SIZE; colIdx++) {
				count += paper[rowIdx][colIdx];
			}
		}
		
		return count == 0;
	}

	
	static void printPaper() {
		for(int rowIdx = 0; rowIdx < SIZE; rowIdx++)
			System.out.println(Arrays.toString(paper[rowIdx]));
		System.out.println();
	}
}
