#include <stdio.h>

int main(void)
{
    int n;
    int i, cnt;
    
    scanf("%d", &n);
    int steeple[n];
    
    for(i = 0; i < n; i++)
        scanf("%d", &steeple[i]);
    
    cnt = 0;
    i = 0;
    while(i < n)
    {
        // 다음 첨탑을 넘길 수 있다면 인덱스를 뒤로 넘기기.
        // i <= n-2: i가 n-1이라면 i+1은 배열 밖을 참조하게 되므로.
        while(steeple[i] > steeple[i+1] && i <= n-2)
            i++;
        cnt++;
        i++;
    }

    printf("%d", cnt);
    
    return 0;
}