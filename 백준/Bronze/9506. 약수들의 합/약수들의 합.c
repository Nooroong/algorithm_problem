#include <stdio.h>
#include <math.h>

/*
100,000까지도 브루트 포스를 쓸 수 있을 것 같다.
(시간 제한이 2초나 되므로)
*/

int main(void)
{
    int n;
    int i, sum;
    int divisor[1000] = {0};
    int divIdx = 0; // divisor 배열의 인덱스
    
    while(1)
    {
        sum = 0;
        divIdx = 0;
        
        scanf("%d", &n);
        
        if(n == -1)
            break;
        
        // 1부터 n^(0.5)까지 돌며 약수의 절반을 구한다.
        for(i = 1; i <= (int)sqrt(n); i++)
        {
            if(n%i == 0)
            {
                sum += i;
                divisor[divIdx++] = i;
            }
        }
        
        // for문을 다 돌고 나면 divIdx는
        // 마지막으로 저장된 요소의 다음 칸을 가리키게 된다.
        
        // divisor 배열을 거꾸로 돌며 나머지 약수를 구한다.
        // ex) n이 28인 경우 1, 2, 4까지 위에서 구한 상황,
        // 4, 2 순서로 28/4, 28/2를 계산하여 나머지 약수를 구한다.
        // i가 1일때까지 도는 이유:
        // 완전수를 구해야해서 divisor[0](==1)까지 갈 필요가 없다.
        for(i = divIdx-1; i >= 1; i--)
        {
            sum += n/divisor[i];
            divisor[divIdx++] = n/divisor[i];
        }
        
        
        if(sum == n) // 완전수인 경우
        {
            printf("%d = ", n);
            
            for(i = 0; i < divIdx -1; i++)
                printf("%d + ", divisor[i]);
            
            printf("%d\n", divisor[divIdx-1]); // 마지막 약수는 따로 출력
            
        }
        else
            printf("%d is NOT perfect.\n", n);
        
    }

	return 0;
}