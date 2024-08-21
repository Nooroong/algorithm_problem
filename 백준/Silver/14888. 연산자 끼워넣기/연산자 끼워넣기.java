import java.io.*;
import java.util.*;

public class Main {
    private static BufferedReader br;
    private static StringTokenizer st;

    private static int operandCount, operandList[], // 피연산자 수, 피연산자 배열
                        operationCountList[] = new int[4], // 각 연산자의 수를 저장하는 배열
                        minResult = Integer.MAX_VALUE, maxResult = Integer.MIN_VALUE;


    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));

        // 입력받기
        operandCount = Integer.parseInt(br.readLine().trim());

        // 피연산자
        st = new StringTokenizer(br.readLine().trim());
        operandList = new int[operandCount];
        for(int idx = 0; idx < operandCount; idx++) {
            operandList[idx] = Integer.parseInt(st.nextToken());
        }

        // 연산자
        st = new StringTokenizer(br.readLine().trim());
        for(int idx = 0; idx < 4; idx++) {
            operationCountList[idx] = Integer.parseInt(st.nextToken());
        }


        // 순열 찾기
        Permutation(0, operandList[0]);


        // 정답 출력
        System.out.println(maxResult + "\n" + minResult);
    }


    private static void Permutation(int selectedElementIdx, int result) {
        // 기저조건
        if(selectedElementIdx == operandCount-1) {
            // 최소, 최대값 갱신
            if(result < minResult) minResult = result;
            if(result > maxResult) maxResult = result;

            return;
        }


        for(int idx = 0; idx < operationCountList.length; idx++) {
            // 현재 연산자를 선택할 수 없는 경우 다음 원소 고르러 가기
            if(operationCountList[idx] == 0)
                continue;


            // 현재 원소 선택
            operationCountList[idx]--;

            // 다음 원소 고르러 가기
            switch(idx) {
                case 0:
                    Permutation(selectedElementIdx + 1, result + operandList[selectedElementIdx+1]);
                    break;
                case 1:
                    Permutation(selectedElementIdx + 1, result - operandList[selectedElementIdx+1]);
                    break;
                case 2:
                    Permutation(selectedElementIdx + 1, result * operandList[selectedElementIdx+1]);
                    break;
                case 3:
                    Permutation(selectedElementIdx + 1, result / operandList[selectedElementIdx+1]);
                    break;
            }

            // 선택 복구
            operationCountList[idx]++;
        }
    }
}