import java.util.*;
import java.io.*;

public class Main {
	static BufferedReader br;
	static StringBuilder sb;
	
	static int[][] sudoku; // 입력
	static final int SIZE = 9;
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		// 입력을 초기화한다.
		sudoku = new int [SIZE][SIZE];
		
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
		
		if(index == SIZE*SIZE) return true;
		
		if(sudoku[curRow][curCol] > 0) {
			return findMinValue(index+1);
			
		}
		
		// 각 빈칸에 대해 들어올 수 있는 최소값을 찾아서 넣는다.
		int check = 0;
		
		check |= rowCheck(curRow); // 행에 대해 검사
		check |= colCheck(curCol); // 열에 대해 검사
		check |= miniCheck(curRow, curCol); // 3*3에 대해 검사
		
//		System.out.println(curRow + "," + curCol + "," + check);
		for(int i = 1; i <= SIZE; i++) {
			if((check & (1 << i)) == 0) {
				sudoku[curRow][curCol] = i;
				if(findMinValue(index+1))
					return true;
			}
		}
		
		// 여기로 오면 이전으로 돌아가야 함
		sudoku[curRow][curCol] = 0;
		return false;
	}
	
	static int rowCheck(int rowIdx) {
		int check = 0;
		for(int colIdx = 0; colIdx < SIZE; colIdx++) {
			check |= (1 << sudoku[rowIdx][colIdx]);
		}

		return check;
	}
	
	static int colCheck(int colIdx) {
		int check = 0;
		for(int rowIdx = 0; rowIdx < SIZE; rowIdx++) {
			check |= (1 << sudoku[rowIdx][colIdx]);
		}
		
		return check;
	}
	
	static int miniCheck(int rowIdx, int colIdx) {
		int check = 0;
		int startRow = rowIdx - (rowIdx%3);
		int startCol = colIdx - (colIdx%3);
		
		for(int row = startRow; row < startRow+3; row++) {
			for(int col = startCol; col < startCol+3; col++) {
				check |= (1 << sudoku[row][col]);
			}
		}
		
		return check;
	}

}
