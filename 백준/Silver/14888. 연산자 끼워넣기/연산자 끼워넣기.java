import java.io.*;
import java.sql.Array;
import java.util.*;

public class Main {
    private static BufferedReader br;
    private static StringTokenizer st;

    private static int operandCount,
                        operandList[],
                        operationList[],
                        minResult = Integer.MAX_VALUE, maxResult = Integer.MIN_VALUE,
                        selectedElementList[];

    private static boolean usedOperationList[];

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));

        operandCount = Integer.parseInt(br.readLine().trim());

        st = new StringTokenizer(br.readLine().trim());
        operandList = new int[operandCount];
        for(int idx = 0; idx < operandCount; idx++) {
            operandList[idx] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine().trim());
        operationList = new int[operandCount-1];
        usedOperationList = new boolean[operandCount-1];

        for(int operationIdx = 0, arrIdx = 0; operationIdx < 4; operationIdx++) {

            int operationCount = Integer.parseInt(st.nextToken());

            for(int count = 0; count < operationCount; count++, arrIdx++)
                operationList[arrIdx] = operationIdx;
        }


        selectedElementList = new int[operandCount - 1];
        Permutation(0);


        System.out.println(maxResult + "\n" + minResult);
    }

    private static void Permutation(int selectedElementIdx) {
        // 기저조건
        if(selectedElementIdx == operandCount-1) {
            
            // 계산
            int result = calc();

            // 최소, 최대값 갱신
            if(result < minResult) minResult = result;
            if(result > maxResult) maxResult = result;

            return;
        }

        for(int idx = 0; idx < operationList.length; idx++) {
            if(usedOperationList[idx])
                continue;

            usedOperationList[idx] = true;

            selectedElementList[selectedElementIdx] = operationList[idx];
            Permutation(selectedElementIdx + 1);

            usedOperationList[idx] = false;
        }
    }

    private static int calc() {
        int result = operandList[0];

        for(int idx = 0; idx < selectedElementList.length; idx++) {
            switch(selectedElementList[idx]) {
                case 0:
                    result += operandList[idx+1];
                    break;
                case 1:
                    result -= operandList[idx+1];
                    break;
                case 2:
                    result *= operandList[idx+1];
                    break;
                case 3:
                    // 음수를 양수로 나누기
                    if(result < 0) {
                        result *= -1;
                        result /= operandList[idx+1];
                        result *= -1;
                    } else {
                        result /= operandList[idx+1];
                    }
                    break;
            }
        }

        return result;
    }

}