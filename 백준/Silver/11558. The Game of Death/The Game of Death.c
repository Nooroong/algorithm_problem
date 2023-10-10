#include <stdio.h>
#include <stdlib.h>

/*
49m 00s

DFS 함수에서 depth 매개변수를 통해 희현이부터 주경이까지 이동(?)하기 위한 횟수를 기억한다.

사람이 n명일 때, 조건에 따라 생성될 수 있는 간선의 수는 n개.
함수를 n번 호출했는데도 원하는 노드를 찾지 못했다면,
내가 원하는 노드는 해당 사이클 내에 없고 떨어진 다른 사이클에 있다는 의미.
그러므로 depth의 크기를 보고 프로그램을 종료할 순간을 찾는다.
(이 부분에 대해 신경쓰지 않으면 무한루프에 빠지게 된다.)

0을 출력하게 되는 테스트 케이스
1
7
4
5
2
2
3
7
6
*/

void DFS(int arr[], int idx, int len, int depth);

int main(void)
{
    int t;
    int n;
    int i, j, tmp;

    int * edge; // 지명한 사람의 숫자 정보가 저장됨.
    int * visited; // 방문 정보

    scanf("%d", &t);
    edge = (int *)malloc(sizeof(int) * (n+1));

    for(i = 0 ; i < t; i++)
    {
        scanf("%d", &n);

        for(j = 0; j < n; j++)
        {
            scanf("%d", &tmp);
            edge[j+1] = tmp; // 편의를 위해 1번 인덱스부터 사용.
        }

        DFS(edge, 1, n, 0);
    }

    return 0;
}

void DFS(int arr[], int idx, int len, int depth)
{
    
    if(depth > len)
    {
        printf("0\n");
        return;
    }


    if(idx == len) // 주경이를 찾으면 최소 숫자 k 출력.
    {
        printf("%d\n", depth);
        return;
    }
    else
        DFS(arr, arr[idx], len, depth+1);
    
}