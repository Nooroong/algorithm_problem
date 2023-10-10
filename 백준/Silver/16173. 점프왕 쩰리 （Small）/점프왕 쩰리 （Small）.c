#include <stdio.h>
#include <stdlib.h>

/*
38m 46s
DFS를 이용한다.
이유: 해당 칸에서 오른쪽으로 가보고 안 되면 아래쪽으로 가보는 방식을 쓰면 되기 때문.
*/

int DFS(int ** arr, int row, int col, int rowSize, int colSize);

int main(void)
{
    int n;
    int ** arr;
    int cnt = 0;
    int i, j;
    int successFlag = 0; // 한 번이라도 성공했는지에 대한 flag 변수.
    
    scanf("%d", &n);

    arr = (int**)malloc(sizeof(int*) * n);
    for(i = 0; i < n; i++)
        arr[i] = (int*)malloc(sizeof(int) * n);
    
    for(i = 0; i < n; i++)
    {
        for(j = 0; j < n; j++)
            scanf("%d", &arr[i][j]);
    }

    // ‘쩰리’의 출발점은 항상 정사각형의 가장 왼쪽, 가장 위의 칸이다.
    DFS(arr, 0, 0, n, n);

    // DFS가 실행되는 동안에 프로그램이 종료되지 못하고 여기까지 왔다는 것은,
    // ‘쩰리’가 끝 점에 도달할 수 없다는 의미가 되므로 Hing을 출력한다.
    printf("Hing");

    return 0;
}

int DFS(int ** arr, int row, int col, int rowSize, int colSize)
{
    // 이 부분을 어떻게 써야할지 잘 모르겠음…

    // 주어진 영역을 벗어나면 패배.
    if(row < 0 || row >= rowSize || col < 0 || col >= colSize)
        return 0;

    // 일단 목표 지점에 도달하면 메시지를 출력하고 프로그램 자체를 종료하는 방식으로 작성.
    if(arr[row][col] == -1)
    {
        printf("HaruHaru");
        exit(0);
    }
    else if(arr[row][col] == 0) // 이동 거리가 0인 경우를 처리하지 않으면 무한 루프에 빠질 수 있음!
        return 0; // 이전 칸으로 돌아간다.
    

    // 우측, 하측 방향에 대해서 칸에 써진 숫자만큼 이동.
    DFS(arr, row, col + arr[row][col], rowSize, colSize); // 우측
    DFS(arr, row + arr[row][col], col, rowSize, colSize); // 하측

    return 0;
}