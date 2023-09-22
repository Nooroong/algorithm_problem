#include <stdio.h>

/*
조건: 해당 구멍을 만들 수 있는 최소값을 출력하라. 숫자가 0으로 시작하면 안 된다.
구멍 0개: 1 2 3 5 7
구멍 1개: 0 4 6 9
구멍 2개: 8
h가 짝수, 홀수인지 판별하고 이에 맞게 출력을 해주면 될듯.
짝수라면 h/2개 만큼 8을 출력, 홀수라면 4 하나를 출력하고 h/2개 만큼 8을 출력한다.
*/

int main(void)
{
    int h;
    int i;
    scanf("%d", &h);
    
    if(h == 0)
        printf("1");
    else if(h == 1)
        printf("0");
    else if(h%2 == 0)
    {
        for(i = 0; i < h/2; i++)
            printf("8");
    }
    else
    {
        printf("4");
        for(i = 0; i < h/2; i++)
            printf("8");
    }
    
    
}