#include <stdio.h>

/*
3중 중첩 for문이므로 O(n^3).
*/

int main(void)
{
    long long int n;
    
    scanf("%lld", &n);
    
    printf("%lld \n", n*n*n);
    printf("3");


	return 0;
}