import java.util.*;
import java.io.*;

/**
 * 
 * @author JiYeon Sin
 * 5로 나눠떨어지면 5만큼 빼준다.
 * 3으로 나눠떨어지면 3만큼 빼준다.
 * 둘 다 아니라면 5를 빼준다.
 */
public class Main {
	public static BufferedReader br;
	
	public static int sugar; // 상근이가 배달해야 할 설탕의 무게
	public static int bag; // 상근이가 들고가야 할 봉지의 개수
	
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		
		sugar = Integer.parseInt(br.readLine().trim());		
		bag = 0;
		
		while(sugar > 0) {
			if(sugar%5 == 0) {
				bag++;
				sugar -= 5;
			} else if(sugar%3 == 0) {
				bag++;
				sugar -= 3;
			} else {
				sugar -= 5;
				if(sugar >= 0) bag++;
				else bag = -1;
			}
		}
		
		System.out.println(bag);
	}
	
}
