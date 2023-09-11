#include <stdio.h>

/*
여기서도 카운팅 정렬을 이용할 수 있다.
10진수가 입력되므로 사실상 데이터의 범위는 0~9이다.
크기가 10인 배열을 선언하여 카운팅 정렬을 이용하면 된다.
*/

int main(void)
{
    unsigned long long int n; // 입력되는 수가 매우 크므로 long long 자료형을 이용.
    int arr[10] = {0};
    int i, j;
    
    scanf("%d", &n);
    
    // 나누기 연산과 나머지 연산을 이용하여
    // 가장 낮은 자리수부터 정렬을 해나간다.
    while(n > 0)
    {
        arr[n%10] += 1;
        n /= 10;
    }
    
    for(i = 9; i >= 0; i--)
    {
        if(arr[i] != 0)
        {
            for(j = 0; j < arr[i]; j++)
                printf("%d", i);
        }
    }
    
    return 0;
}