#include <stdio.h>
#include <math.h>

int isPrimeNum(int num);

int main(void)
{
    int n;
	int nTmp; // 실직적으로 소인수 분해가 진행되는 변수
    int i;
    
    scanf("%d", &n);
    
    if(n==1)
        return 0;
    
    // n이 소수인 경우
    if(isPrimeNum(n) == 1)
    {
    	printf("%d", n);
    	return 0;
	}
    
    
    // 소수가 아닌 경우
    for(i = 2, nTmp = n; i <= n && nTmp != 1; i++)
    {
        if(isPrimeNum(i) == 1) // i가 소수라면
        {
            // n을 i로 나눌 수 있을 때까지 계속 나눈다.
            // && nTmp != 1: 소인수분해가 끝나면 nTmp는 1이 된다.
			// nTmp가 1이 되면 모든 반복을 중단해야 한다.
            while(nTmp >= i && nTmp%i == 0 && nTmp != 1)
            {
                printf("%d\n", i);
                nTmp /= i;
            }
        }
        
        if(isPrimeNum(nTmp) == 1)
        {
        	printf("%d\n", nTmp);
        	return 0;
		}
    }
    
	return 0;
}

// num이 소수라면 1, 아니라면 0을 반환.
int isPrimeNum(int num)
{
    int i;
    
    if(num==1)
    	return 0;
    else if(num==2 || num==3)
        return 1;
    
    // n^(0.5)까지만 검사해도 됨.
    for(i = 2; i <= (int)sqrt(num); i++)
    {
        if(num%i == 0)
            return 0;
    }
    
    return 1;
}