#include <stdio.h>
#include <stdlib.h>

/*
그냥 어렵게 생각할 필요없고 반복문으로 풀어도 된다.
구현문제라고 생각하고 그냥 지명한 숫자대로 따라가면 된다.
*/

int main(void)
{
    int T;
    int n; // 주경이는 n번이다.
    int * call;
    int i, j;

    int callCnt; // 지명 횟수.
    int callIdx; // 지명권한을 가지고 있는 사람번호 - 1
    int findFlag;

    
    scanf("%d", &T);

    for(i = 0; i < T; i++)
    {
        scanf("%d", &n);

        call = (int*)malloc(sizeof(int) * n);
        for(j = 0; j < n; j++)
            scanf("%d", &call[j]);

        callCnt = 1; // 무조건 한 번은 지명하므로 1부터 시작.
        callIdx = 0;
        findFlag = 0;

 
        while(callCnt <= n)
        {
            if(call[callIdx] != n)
            {
                callCnt++;
                callIdx = call[callIdx] - 1; // 지명 권한(?)이 다음 사람에게 넘어감.
            }
            else
            {
                findFlag = 1;
                break;
            }
        }

        printf("%d\n", findFlag==1 ? callCnt : 0);
        
        free(call);
    }
    

    return 0;
}