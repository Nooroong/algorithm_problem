#include <stdio.h>
#include <stdlib.h>
#include <string.h>

/*
배열과 int형 변수를 이용해 스택을 표현한다.
*/

int main(void)
{
    int stack[10000];
    int top = -1;
    int pushNum;

    int n;
    char command[20];

    scanf("%d\n", &n); // 밑에서 gets를 쓰므로 이스케이프 문자 꼭 써주기.

    while(n > 0)
    {
        gets(command);

        if(!strncmp(command, "push", 4))
        {
            // 문자열 파싱
            strtok(command, " ");
            pushNum = atoi(strtok(NULL, " "));

            top = (top<0) ? 0 : top+1;
            stack[top] = pushNum;
        }
        else if(!strcmp(command, "pop"))
        {
            if(top < 0) printf("-1 \n");
            else printf("%d \n", stack[top--]);
        }
        else if(!strcmp(command, "size"))
            printf("%d \n", (top<0) ? 0 : top+1);
        else if(!strcmp(command, "empty"))
            printf("%d \n", (top<0) ? 1 : 0);

        else if(!strcmp(command, "top"))
            printf("%d \n", (top<0) ? -1 : stack[top]);

        n--;
    }

    return 0;
}