#include <stdio.h>

/*
bi와 ai을 비교하면서 bi > ai라면 차이나는 자전거의 수가 몇개인지 저장하면 된다.
(bi < ai인 경우에 bi > ai인 부분에서 채워주므로 굳이 셀 필요가 없음.)
*/

int main(void)
{
    int n;
    scanf("%d", &n);
    
    int a[n];
    int b[n];
    int i, cnt;
    
    for(i = 0; i < n; i++)
        scanf("%d", &a[i]);
    for(i = 0; i < n; i++)
        scanf("%d", &b[i]);
    
    cnt = 0;
    for(i = 0; i < n; i++)
    {
        if(b[i] > a[i])
            cnt += b[i] - a[i];
    }
    
    printf("%d", cnt);
}