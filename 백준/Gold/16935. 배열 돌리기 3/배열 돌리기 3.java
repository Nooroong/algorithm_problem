import java.io.*;
import java.util.*;

/**
 * 
 * @author JiYeon Sin
 * 1. 배열의 크기, 연산의 수를 입력받는다.
 * 2. 입력받은 크기를 바탕으로 배열을 생성한다.
 * 3. 배열의 원소를 입력받아 저장한다.
 * 4. 연산을 저장할 배열을 생성하고 연산을 입력받는다.
 * 5. 연산의 수만큼 반복을 한다.
 * 6. 연산에 따라 각각 작업을 한다.
 * 7. 회전된 배열을 출력한다.
 */
public class Main {
	public static BufferedReader br;
	public static StringBuilder sb;
	public static StringTokenizer st;
	
	public static int[][] array; // 회전할 대상이 되는 배열
	public static int rowSize; // 배열의 행 크기
	public static int colSize; // 배열의 열 크기
	
	public static int operationCnt; // 연산의 수
	public static int[] operations; // 연산들이 저장되는 배열
	
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		// 1. 배열의 크기, 연산의 수를 입력받는다.
		st = new StringTokenizer(br.readLine().trim());
		rowSize = Integer.parseInt(st.nextToken());
		colSize = Integer.parseInt(st.nextToken());
		operationCnt = Integer.parseInt(st.nextToken());
		
		
		
		array = new int[rowSize][colSize]; // 2. 입력받은 크기를 바탕으로 배열을 생성한다.
		
		// 3. 배열의 원소를 입력받아 저장한다.
		for(int rowIdx = 0; rowIdx < rowSize; rowIdx++) {
			st = new StringTokenizer(br.readLine().trim());
			
			for(int colIdx = 0; colIdx < colSize; colIdx++) {
				array[rowIdx][colIdx] = Integer.parseInt(st.nextToken());
			}
		}
		
		
		// 4. 연산을 저장할 배열을 생성하고 연산을 입력받는다.
		operations = new int[operationCnt];
		st = new StringTokenizer(br.readLine().trim());
		for(int idx = 0; idx < operationCnt; idx++) {
			operations[idx] = Integer.parseInt(st.nextToken());
		}
		
		
		// 5. 연산의 수만큼 반복을 한다.
		for(int operation : operations) {
			// 6. 연산에 따라 각각 작업을 한다.
			switch(operation) {
				case 1:
					upNDownInversion();
					break;
				case 2:
					leftNRightInversion();
					break;
				case 3:
					turnRight();
					break;
				case 4:
					turnLeft();
					break;
				case 5:
					splitNTurnRight();
					break;
				case 6:
					splitNTurnLeft();
					break;
				default:
					continue;	
			}
		}
		
		
		
		// 7. 회전된 배열을 출력한다.
		for(int rowIdx = 0; rowIdx < rowSize; rowIdx++) {
			for(int colIdx = 0; colIdx < colSize; colIdx++) {
				sb.append(array[rowIdx][colIdx]).append(" ");
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}
	
	
	
	
	// 배열의 위쪽 끝과 아래쪽 끝에서부터 시작하여 값을 바꾸고 범위를 줄여나간다.
	public static void upNDownInversion() {
		int[] tmp = new int[colSize];
		
		for(int frontIdx = 0, rearIdx = rowSize-1; frontIdx <= rearIdx; frontIdx++, rearIdx--) {
			// front의 행과 rear의 행을 swap
			System.arraycopy(array[frontIdx], 0, tmp, 0, colSize);
			System.arraycopy(array[rearIdx], 0, array[frontIdx], 0, colSize);
			System.arraycopy(tmp, 0, array[rearIdx], 0, colSize);
		}
	}
	
	
	// 배열의 왼쪽 끝과 오른쪽 끝에서부터 시작하여 값을 바꾸고 범위를 줄여나간다.
	public static void leftNRightInversion() {
		int tmp;
		
		for(int rowIdx = 0; rowIdx < rowSize; rowIdx++) {
			for(int frontIdx = 0, rearIdx = colSize-1; frontIdx <= rearIdx; frontIdx++, rearIdx--) {
				tmp = array[rowIdx][frontIdx];
				array[rowIdx][frontIdx] = array[rowIdx][rearIdx];
				array[rowIdx][rearIdx] = tmp;
			}
		}
		
	}
	
	
	// 오른쪽으로 90도 회전
	public static void turnRight() {
		int[][] tmpArray = new int[colSize][rowSize];
		int tmp;
		
		for(int originColIdx = 0, tmpRowIdx = 0; originColIdx < colSize; originColIdx++, tmpRowIdx++) {
			for(int originRowIdx = rowSize - 1, tmpColIdx = 0; originRowIdx >= 0; originRowIdx--, tmpColIdx++) {
				tmpArray[tmpRowIdx][tmpColIdx] = array[originRowIdx][originColIdx];
			}
		}
		array = tmpArray;
		
		// 사이즈를 swap
		tmp = rowSize;
		rowSize = colSize;
		colSize = tmp;
	}
	
	
	// 왼쪽으로 90도 회전
	public static void turnLeft() {
		int[][] tmpArray = new int[colSize][rowSize];
		int tmp;
		
		for(int originColIdx = colSize-1, tmpRowIdx = 0; originColIdx >= 0; originColIdx--, tmpRowIdx++) {
			for(int originRowIdx = 0, tmpColIdx = 0; originRowIdx < rowSize; originRowIdx++, tmpColIdx++) {
				tmpArray[tmpRowIdx][tmpColIdx] = array[originRowIdx][originColIdx];
			}
		}
		array = tmpArray;
		
		// 사이즈를 swap
		tmp = rowSize;
		rowSize = colSize;
		colSize = tmp;
	}
	
	
	
	// 배열을 4등분 후 부분 배열들을 오른쪽으로 돌린다.
	// 몬가... 더 예쁘게 짤 수는 없나...
	public static void splitNTurnRight() {
		// 4등분
		int rowMid = rowSize/2;
		int colMid = colSize/2;
		
		int[][] tmpArr = new int[rowSize][colSize]; // 돌아간 결과를 저장하는 임시 배열
		
		// 돌리기
		// 4 -> 1
		for(int originalRowIdx = rowMid, tmpRowIdx = 0; originalRowIdx < rowSize; originalRowIdx++, tmpRowIdx++) {
			for(int originalColIdx = 0, tmpColIdx = 0; originalColIdx < colMid; originalColIdx++, tmpColIdx++) {
				tmpArr[tmpRowIdx][tmpColIdx] = array[originalRowIdx][originalColIdx];
			}
		}
		
		// 1 -> 2
		for(int originalRowIdx = 0, tmpRowIdx = 0; originalRowIdx < rowMid; originalRowIdx++, tmpRowIdx++) {
			for(int originalColIdx = 0, tmpColIdx = colMid; originalColIdx < colMid; originalColIdx++, tmpColIdx++) {
				tmpArr[tmpRowIdx][tmpColIdx] = array[originalRowIdx][originalColIdx];
			}
		}

		// 2 -> 3
		for(int originalRowIdx = 0, tmpRowIdx = rowMid; originalRowIdx < rowMid; originalRowIdx++, tmpRowIdx++) {
			for(int originalColIdx = colMid, tmpColIdx = colMid; originalColIdx < colSize; originalColIdx++, tmpColIdx++) {
				tmpArr[tmpRowIdx][tmpColIdx] = array[originalRowIdx][originalColIdx];
			}
		}
		
		// 3 -> 4
		for(int originalRowIdx = rowMid, tmpRowIdx = rowMid; originalRowIdx < rowSize; originalRowIdx++, tmpRowIdx++) {
			for(int originalColIdx = colMid, tmpColIdx = 0; originalColIdx < colSize; originalColIdx++, tmpColIdx++) {
				tmpArr[tmpRowIdx][tmpColIdx] = array[originalRowIdx][originalColIdx];
			}
		}

		
		array = tmpArr;
	}
	
	
	// 배열을 4등분 후 부분 배열들을 왼쪽으로 돌린다.
	public static void splitNTurnLeft() {
		// 4등분
		int rowMid = rowSize/2;
		int colMid = colSize/2;
		
		int[][] tmpArr = new int[rowSize][colSize]; // 돌아간 결과를 저장하는 임시 배열
		
		// 돌리기
		// 1 -> 4
		for(int originalRowIdx = 0, tmpRowIdx = rowMid; originalRowIdx < rowMid; originalRowIdx++, tmpRowIdx++) {
			for(int originalColIdx = 0, tmpColIdx = 0; originalColIdx < colMid; originalColIdx++, tmpColIdx++) {
				tmpArr[tmpRowIdx][tmpColIdx] = array[originalRowIdx][originalColIdx];
			}
		}
		
		// 4 -> 3
		for(int originalRowIdx = rowMid, tmpRowIdx = rowMid; originalRowIdx < rowSize; originalRowIdx++, tmpRowIdx++) {
			for(int originalColIdx = 0, tmpColIdx = colMid; originalColIdx < colMid; originalColIdx++, tmpColIdx++) {
				tmpArr[tmpRowIdx][tmpColIdx] = array[originalRowIdx][originalColIdx];
			}
		}

		// 3 -> 2
		for(int originalRowIdx = rowMid, tmpRowIdx = 0; originalRowIdx < rowSize; originalRowIdx++, tmpRowIdx++) {
			for(int originalColIdx = colMid, tmpColIdx = colMid; originalColIdx < colSize; originalColIdx++, tmpColIdx++) {
				tmpArr[tmpRowIdx][tmpColIdx] = array[originalRowIdx][originalColIdx];
			}
		}
		
		// 2 -> 1
		for(int originalRowIdx = 0, tmpRowIdx = 0; originalRowIdx < rowMid; originalRowIdx++, tmpRowIdx++) {
			for(int originalColIdx = colMid, tmpColIdx = 0; originalColIdx < colSize; originalColIdx++, tmpColIdx++) {
				tmpArr[tmpRowIdx][tmpColIdx] = array[originalRowIdx][originalColIdx];
			}
		}

		
		array = tmpArr;
	}
	
}
