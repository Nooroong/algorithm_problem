#include <stdio.h>
#include <stdlib.h>

/*
28m 13s
가로로 연결되어 있는 -뭉탱이의 개수와,
세로로 연결되어 있는 |뭉탱이의 개수를 구하면 된다.
*/

int RowDFS(char ** arr, int row, int col, int rowSize, int colSize);
int ColDFS(char ** arr, int row, int col, int rowSize, int colSize);

int main(void)
{
    int n, m;
    char ** arr;
    int cnt = 0;
    int i, j;
    
    scanf("%d %d\n", &n, &m);

    arr = (char**)malloc(sizeof(char*) * n);
    for(i = 0; i < n; i++)
        arr[i] = (char*)malloc(sizeof(char) * m);
    
    for(i = 0; i < n; i++)
    {
        for(j = 0; j < m; j++)
            scanf("%1c", &arr[i][j]);
        getchar(); // 한 줄마다 엔터 처리. (%c를 쓰기 때문에 꼭 여기서 엔터를 처리해줘야 함.)
    }


    for(i = 0; i < n; i++)
    {
        for(j = 0; j < m; j++)
        {
            if(RowDFS(arr, i, j, n, m))
                cnt++;
        }
    }

    for(i = 0; i < n; i++)
    {
        for(j = 0; j < m; j++)
        {
            if(ColDFS(arr, i, j, n, m))
                cnt++;
        }
    }

    printf("%d", cnt);

    return 0;
}

int RowDFS(char ** arr, int row, int col, int rowSize, int colSize)
{
    if(row < 0 || row >= rowSize || col < 0 || col >= colSize)
        return 0;
    
    if(arr[row][col] == '-')
    {
        arr[row][col] = '*'; // 방문처리

        // 현재 노드의 좌, 우에 대해 DFS.
        RowDFS(arr, row, col-1, rowSize, colSize);
        RowDFS(arr, row, col+1, rowSize, colSize);
        return 1;
    }

    return 0;
}

int ColDFS(char ** arr, int row, int col, int rowSize, int colSize)
{
    if(row < 0 || row >= rowSize || col < 0 || col >= colSize)
        return 0;
    
    if(arr[row][col] == '|')
    {
        arr[row][col] = '*'; // 방문처리

        // 현재 노드의 상, 하에 대해 DFS.
        ColDFS(arr, row-1, col, rowSize, colSize);
        ColDFS(arr, row+1, col, rowSize, colSize);
        return 1;
    }

    return 0;
}