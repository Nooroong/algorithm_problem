/*
참고링크: https://m.blog.naver.com/PostView.naver?isHttpsRedirect=true&blogId=sooftware&logNo=221516440423
배열을 기반으로 구현한 원형 큐에서 추가 기능을 덧붙임.
원형 큐 기반 deque에서 front는 항상 빈 요소를 가리킨다.
한 칸을 비우는 이유는 포화상태와 공백상태를 구분하기 위해서이다.(front==rear)
실제 앞부분의 데이터는 front의 다음 인덱스에 저장되어 있다.
*/
#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <math.h>

#define SIZE 10000

int main(void)
{
    int n;
    int deque[SIZE];
    int front, rear, size;
    int tmp;
    char command[20];

    front = 0;
    rear = 0;
    size = 0;

    scanf("%d\n", &n);

    while(n > 0)
    {
        gets(command);

        if(!strncmp(command, "push_front", 10))
        {
            strtok(command, " ");
            tmp = atoi(strtok(NULL, " "));
            deque[front] = tmp;
            front = (front-1)<0 ? SIZE-1 : front-1;
            size++;
        }
        else if(!strncmp(command, "push_back", 9))
        {
            strtok(command, " ");
            tmp = atoi(strtok(NULL, " "));
            rear = (rear+1)%SIZE;
            deque[rear] = tmp;
            size++;
        }
        else if(!strcmp(command, "pop_front"))
        {
            if(front == rear)
                printf("-1 \n");
            else
            {
                printf("%d \n", deque[(front+1)%SIZE]);
                front = (front+1)%SIZE;
                size = (size-1)<0 ? 0 : size-1;
            }
        }
        else if(!strcmp(command, "pop_back"))
        {
            if(front == rear)
                printf("-1 \n");
            else
            {
                printf("%d \n", deque[rear]);
                rear = (rear-1)<0 ? SIZE-1 : rear-1;
                size = (size-1)<0 ? 0 : size-1;
            }
        }
        else if(!strcmp(command, "size"))
        {
            printf("%d \n", size);
        }
        else if(!strcmp(command, "empty"))
        {
            printf("%d \n", (front == rear) ? 1 : 0);
        }
        else if(!strcmp(command, "front"))
        {
            printf("%d \n", (front==rear) ? -1 : deque[(front+1)%SIZE]);
        }
        else if(!strcmp(command, "back"))
        {
            printf("%d \n", (front==rear) ? -1 : deque[rear]);
        }

        n--;
    }

    return 0;
}