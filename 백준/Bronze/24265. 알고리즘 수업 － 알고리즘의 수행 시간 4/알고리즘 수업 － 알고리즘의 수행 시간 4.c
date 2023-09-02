#include <stdio.h>

/*
바깥 for문은 n-1번 반복, 안쪽 for문은 n-(i+1)+1 = n-i번 반복.
(n-1)*(n-i) = n^2-(i+1)n+i. 빅-오는 O(n^2).
*/

int main(void)
{
    long long int n;
    
    scanf("%lld", &n);
    
    /*
    코드 1의 수행 횟수 함수 f(n) = n * (n-1) * 0.5
    i가 1일 때부터 n-1일 때까지 내부 for문은
    n-1번, n-2번, n-3번, … , 1번 반복하게 된다.
    즉 전체 반복 횟수는 1부터 n-1까지의 합을 구하면 된다.
    1부터 x까지의 합은 (x+1)*x/2 이니 이 식에 대입하면 함수를 구할 수 있다.
    */
    printf("%lld \n", n*(n-1)/2);
    printf("2"); // 최고차항은 위에서 구함.

	return 0;
}