#include <stdio.h>
#include <malloc.h>

/*
바깥 for문은 0~N-8, 안쪽 for문은 0~M-8만큼 돌면 되나?
8*8만큼의 요소를 한 칸씩 옮겨가며 살펴본다.
*/

int main(void)
{
    int n, m;
    int minRePaint = 65; // 다시 칠해야 하는 정사각형 개수의 최솟값
    int rePaintB, rePaintW; // 다시 칠한 검정색의 수, 하얀색의 수.
    int i, j, k, l;
    int flag; // 정답 체스판을 만들기 위한 플래그 변수
    
    
    scanf("%d %d", &n, &m); // 행, 열
    
    // 체스판 생성 및 입력받기
    char chess[n][m];
    for(i = 0; i < n; i++)
            scanf("%s", chess[i]);
    
	
	// 대조를 위한 정답 체스판 2개를 만든다.
	char chessW[8][8]; // W로 시작함. 
	char chessB[8][8];
    
    flag = 1; // 1, -1을 반복한다.
    
    // 정답 체스판 내용을 채운다.
    for(i = 0; i < 8; i++)
	{
		for(j = 0; j < 8; j++)
		{
			if(flag == 1)
			{
				chessW[i][j] = 'W';
				chessB[i][j] = 'B';
			}
			else
			{
				chessW[i][j] = 'B';
				chessB[i][j] = 'W';
			}
			flag *= -1;
		}
		flag *= -1; // 행을 넘어갈 때는 색이 변하지 않는다.
	}
    
    // 8*8만큼 잘라서 본다. 한 칸씩 옮겨간다.
    for(i = 0; i <= n-8; i++)
    {
        for(j = 0; j <= m-8; j++)
        {
            rePaintB = rePaintW = 0;
            
            // 여기서 8*8의 내용을 본다.
            for(k = 0; k < 8; k++)
            {
            	for(l = 0; l < 8; l++)
            	{
            		// 정답 체스판과 비교를 하여
            		// 하얀색으로 칠해야하는 경우와, 검정색으로 칠해야하는 경우를 각각 본다.
            		if(chess[i+k][j+l] != chessW[k][l]) rePaintW++;
            		if(chess[i+k][j+l] != chessB[k][l]) rePaintB++;
				}
			}

            // 새로운 최소값을 발견하면 갱신
            if(minRePaint > rePaintW) minRePaint = rePaintW;
            if(minRePaint > rePaintB) minRePaint = rePaintB;
        }
    }

    printf("%d", minRePaint);
    
    return 0;
}