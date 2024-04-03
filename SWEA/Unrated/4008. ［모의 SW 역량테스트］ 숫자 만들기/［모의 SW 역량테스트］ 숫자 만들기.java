import java.io.*;
import java.util.*;

/**
 * 
 * @author JiYeon Sin
 * nPn 중복 원소가 있는 경우의 순열
 * 
 * 1. 입력을 받는다.
 * 2. 순열을 통해 피연산자들의 순서를 정해준다.
 * 	2-1. 기저조건: 모든 연산자가 위치를 찾은 경우 (피연산자의 수는 numCount-1)
 * 		2-1-1. 수식을 계산하고 결과를 바탕으로 최대값, 최소값을 갱신한다.
 * 	2-2. 연산자를 다 선택하지 못한 경우 operationList에 들어갈 연산자를 고른다.
 *
 */
public class Solution {
	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;
	
	static int testCase;
	
	static int numCount; // 피연산자 개수
	static int[] nums, // 피연산자 배열
				 operationList, // 선택된 연산자 순서롤 저장하는 배열
				 operationCount = new int[4]; // 각 연산자들의 개수
	
	static final int PLUS = 0, MINUS = 1, MULTIFY = 2, DIVISION= 3;
	
	static int maxValue, minValue; // 최댓값, 최솟값
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		testCase = Integer.parseInt(br.readLine().trim());
		for(int tc = 1; tc <= testCase; tc++) {
			// 1. 입력을 받는다.
			numCount = Integer.parseInt(br.readLine().trim()); // 피연산자의 개수
			
			// 각 연산자의 개수 입력받기
			st = new StringTokenizer(br.readLine().trim());
			for(int idx = 0; idx < 4; idx++)
				operationCount[idx] = Integer.parseInt(st.nextToken());
			
			// 피연산자 입력받기
			nums = new int[numCount];
			st = new StringTokenizer(br.readLine().trim());
			for(int idx = 0; idx < numCount; idx++)
				nums[idx] = Integer.parseInt(st.nextToken());
			
			
			// 초기화
			maxValue = Integer.MIN_VALUE;
			minValue = Integer.MAX_VALUE;
			operationList = new int[numCount-1];
			Arrays.fill(operationList, -1);
			
			
			// 2. 순열을 통해 피연산자들의 순서를 정해준다.
			permutation(0);
			
			
			sb.append("#").append(tc).append(" ").append(maxValue - minValue);
			System.out.println(sb);
			sb.setLength(0);
		}
		
		
	}
	
	
	static void permutation(int selectIdx) {
		// 2-1. 기저조건: 모든 연산자가 위치를 찾은 경우 (피연산자의 수는 numCount-1)
		if(selectIdx == numCount-1) {
			// 2-1-1. 수식을 계산하고 결과를 바탕으로 최대값, 최소값을 갱신한다.
			int result = calc();
			
//			System.out.println(Arrays.toString(operationList));
//			System.out.println("calc result: " + result);
			
			maxValue = Integer.max(result, maxValue);
			minValue = Integer.min(result, minValue);
			
			return;
		}
		
		
		// 2-2. 연산자를 다 선택하지 못한 경우 operationList에 들어갈 연산자를 고른다.
		// 각 원소의 사용 처리는 남은 개수로 한다.
		for(int opIdx = 0; opIdx < 4; opIdx++) {
			// 해당 연산자를 선택할 수 없는 경우 pass
			if(operationCount[opIdx] == 0) continue;
			
			// 현재 연산자를 사용
			operationCount[opIdx]--;
			operationList[selectIdx] = opIdx;
			
			permutation(selectIdx+1); // 다음 위치에 들어갈 연산자를 선택한다.
			
			// 되돌리기
			operationCount[opIdx]++;
			operationList[selectIdx] = -1;
		}
	}
	
	
	
	
	static int calc() {
		// 첫번째 피연산자를 결과에 대입해놓는다.
		int result = nums[0];
		
		// 연산자의 갯수만큼 차례대로 연산을 진행한다.
		for(int opIdx = 0; opIdx < numCount-1; opIdx++) {
			int curOp = operationList[opIdx];
			
			switch(curOp) {
				case PLUS:
					result += nums[opIdx+1];
					break;
				case MINUS:
					result -= nums[opIdx+1];
					break;
				case MULTIFY:
					result *= nums[opIdx+1];
					break;
				case DIVISION:
					result /= nums[opIdx+1];
			}
		}
		
		return result;
	}
}
