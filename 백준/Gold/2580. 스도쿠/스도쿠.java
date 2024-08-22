import java.io.*;
import java.util.*;

/**
 * 백준 2580 (스도쿠)
 * 백?트래킹
 */

public class Main {
    private static BufferedReader br;
    private static StringTokenizer st;
    private static StringBuilder sb;

    private static final int SUDOKU_SIZE = 9;

    private static int sudoku[][] = new int[SUDOKU_SIZE][SUDOKU_SIZE];


    private static List<int[]> blankList = new ArrayList<>();


    public static void main(String[] args) throws IOException {
        sb = new StringBuilder();
        br = new BufferedReader(new InputStreamReader(System.in));

        // 스도쿠 입력받기
        int input;
        for(int rowIdx = 0; rowIdx < SUDOKU_SIZE; rowIdx++) {
            st = new StringTokenizer(br.readLine().trim());

           for(int colIdx = 0; colIdx < SUDOKU_SIZE; colIdx++) {
               input = Integer.parseInt(st.nextToken());
               sudoku[rowIdx][colIdx] = input;

               // 빈칸의 위치를 리스트로 관리
               if(input == 0)
                   blankList.add(new int[] {rowIdx, colIdx});
           }
        }

        
        // 찾기
        solveSudoku(0);


        // 완성된 스도쿠 출력
        for(int rowIdx = 0; rowIdx < SUDOKU_SIZE; rowIdx++) {
            for(int colIdx = 0; colIdx < SUDOKU_SIZE; colIdx++) {
                sb.append(sudoku[rowIdx][colIdx]).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);

    }

    private static boolean solveSudoku(int blankIdx) {
        boolean emptyFlag = true;

        // 모든 빈 칸을 채우면 종료
        if(blankIdx == blankList.size())
            return true;


        // 현재 빈칸의 위치를 얻는다.
        int[] curPoint = blankList.get(blankIdx);

        // 현재 칸에 들어갈 수 있는 후보들을 찾는다.
        boolean[] validValueList = findValidValue(curPoint[0], curPoint[1]);


        // 순차적으로 값을 넣어본다.
        for(int idx = 0; idx < validValueList.length; idx++) {
            if(!validValueList[idx]) continue;

            emptyFlag = false; // 뭐라도 값을 넣었음을 체크

            sudoku[curPoint[0]][curPoint[1]] = idx; // 현재 칸에 가능한 값을 넣어보기

            // 다음 빈 칸으로 가기
            if(solveSudoku(blankIdx + 1))
                return true; // 스도쿠가 완성되면 완전 종료
        }

        // 현재 칸에 넣을 수 있는 값이 없다면 직전의 빈 칸으로 돌아가기
        if(emptyFlag) {
            return false;
        }

        // 앞에 잘못된 값을 넣어서 후보값들을 전부 넣을 수 없는 경우
        // 현재 칸을 초기화하고 이전 빈칸으로 돌아간다.
        sudoku[curPoint[0]][curPoint[1]] = 0;
        return false;
    }

    private static boolean[] findValidValue(int row, int col) {
        boolean[] checkUsedValue = new boolean[SUDOKU_SIZE + 1]; // 이미 사용된 값 체크
        boolean[] validValue = new boolean[SUDOKU_SIZE + 1]; // 사용할 수 있는 값 리스트

        // 행 체크
        for(int colIdx = 0; colIdx < SUDOKU_SIZE; colIdx++) {
            checkUsedValue[sudoku[row][colIdx]] = true;
        }

        // 열 체크
        for(int rowIdx = 0; rowIdx < SUDOKU_SIZE; rowIdx++) {
            checkUsedValue[sudoku[rowIdx][col]] = true;
        }

        // 3*3 체크
        for(int rowIdx = row/3*3; rowIdx < row/3*3 +  3; rowIdx++) {
            for(int colIdx = col/3*3; colIdx < col/3*3 + 3; colIdx++) {
                checkUsedValue[sudoku[rowIdx][colIdx]] = true;
            }
        }

        // 사용되지 않은 값만 추려서 리스트에 저장
        for (int idx = 0; idx < checkUsedValue.length; idx++) {
            if(!checkUsedValue[idx]) validValue[idx] = true;
        }


        return validValue;
    }
}