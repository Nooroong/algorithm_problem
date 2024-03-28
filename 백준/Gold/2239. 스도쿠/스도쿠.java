import java.util.*;
import java.io.*;

public class Main {
	static BufferedReader br;
	static StringBuilder sb;
	
	static int[][] sudoku; // 입력
	static final int SIZE = 9;
	
//	static boolean[] check;
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		
		// 입력, 출력 배열을 초기화한다.
		sudoku = new int [SIZE][SIZE];
//		check = new boolean[SIZE+1];
		
		// 스도쿠 문제를 입력받는다.
		for(int rowIdx = 0; rowIdx < SIZE; rowIdx++) {
			String line = br.readLine().trim();
			for(int colIdx = 0; colIdx < SIZE; colIdx++) {
				sudoku[rowIdx][colIdx] = line.charAt(colIdx) - '0';
			}
		}
		
		

		
		findMinValue(0);
		
		
		for(int rowIdx = 0; rowIdx < SIZE; rowIdx++) {
			for(int colIdx = 0; colIdx < SIZE; colIdx++) {
				sb.append(sudoku[rowIdx][colIdx]);
				
			}
			sb.append("\n");
		}
		
		System.out.print(sb);
	}
	
	
	
	static boolean findMinValue(int index) {
		int curRow = index / SIZE;
		int curCol = index % SIZE;
		// 각 빈칸에 대해 들어올 수 있는 최소값을 찾아서 넣는다.
		boolean[] values = new boolean[SIZE+1];
		
		if(index == SIZE*SIZE) return true;
		
		if(sudoku[curRow][curCol] > 0) {
			return findMinValue(index+1);
			
		}
		
		
		
		rowCheck(curRow, values); // 행에 대해 검사
		colCheck(curCol, values); // 열에 대해 검사
		miniCheck(curRow, curCol, values); // 3*3에 대해 검사
		
		
		for(int i = 1; i <= SIZE; i++) {
			if(!values[i]) {
				sudoku[curRow][curCol] = i;
				if(findMinValue(index+1))
					return true;
			}
		}
		
		// 여기로 오면 이전으로 돌아가야 함
		sudoku[curRow][curCol] = 0;
		return false;
	}
	
	static void rowCheck(int rowIdx, boolean[] check) {
		for(int colIdx = 0; colIdx < SIZE; colIdx++) {
			check[ sudoku[rowIdx][colIdx] ] = true;
		}
	}
	
	static void colCheck(int colIdx, boolean[] check) {
		for(int rowIdx = 0; rowIdx < SIZE; rowIdx++) {
			check[ sudoku[rowIdx][colIdx] ] = true;
		}
	}
	
	static void miniCheck(int rowIdx, int colIdx, boolean[] check) {
		int startRow = rowIdx - (rowIdx%3);
		int startCol = colIdx - (colIdx%3);
		
		for(int row = startRow; row < startRow+3; row++) {
			for(int col = startCol; col < startCol+3; col++) {
				check[ sudoku[row][col] ] = true;
			}
		}
	}

}
