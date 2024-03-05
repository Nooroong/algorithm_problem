import java.io.*;
import java.util.*;

/**
 * 
 * @author JiYeon Sin
 * 
 * 0. 각 달에 해당하는 최대 날짜를 배열에 저장해놓는다.
 * 1. 테스트 케이스 개수를 입력받는다.
 * 2. 8짜리 날짜를 입력받아 String 타입 변수에 담는다. 
 * 3. substring 메서드를 이용하여 년, 월, 일을 구해 string 타입과 int 타입으로 각각 저장해놓는다.
 * 4. 월과 일이 유효하면 형식에 맞게 출력한다.
 * 5. 유효하지 않다면 -1을 출력한다.
 */
public class Solution {
	public static BufferedReader br;
	public static StringTokenizer st;
	public static StringBuilder sb;
	
	public static int testCase; // 테스트 케이스의 개수
	
	public static String date;
	public static String yearStr, monthStr, dayStr;
	public static int monthInt, dayInt;
	
	// 0. 각 달에 해당하는 최대 날짜를 배열에 저장해놓는다.
	public static int[] monthArr = { 0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		
		testCase = Integer.parseInt(br.readLine().trim()); // 1. 테스트 케이스 개수를 입력받는다.
		
		for(int tc = 1; tc <= testCase; tc++) {
			sb.append("#").append(tc).append(" ");
			
			date = br.readLine().trim(); // 2. 8짜리 날짜를 입력받아 String 타입 변수에 담는다. 
			
			
			// 3. substring 메서드를 이용하여 년, 월, 일을 구해 string 타입과 int 타입으로 각각 저장해놓는다.
			yearStr = date.substring(0, 4);
			monthStr = date.substring(4, 6);
			dayStr = date.substring(6, 8);
			
			monthInt = Integer.parseInt(monthStr);
			dayInt = Integer.parseInt(dayStr);
			
			
			// 4. 월과 일이 유효하면 형식에 맞게 출력한다.
			if((monthInt >= 1 && monthInt <= 12) && (dayInt >= 1 && dayInt <= monthArr[monthInt])) {
				sb.append(yearStr).append("/").append(monthStr).append("/").append(dayStr);
			} else {
				// 5. 유효하지 않다면 -1을 출력한다.
				sb.append(-1);
			}
			
			System.out.println(sb);
			sb.setLength(0);
		}
	}
}
