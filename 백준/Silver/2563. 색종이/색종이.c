#include <stdio.h>

// 검은색 색종이가 안 붙은 영역은 0,
// 검은색 색종이가 붙은 영역은 1로 표현한다.

int main(void)
{
    int whitePaper[100][100] = {0};
    int i, j, k;
    int coloredPaperNum;
    int area = 0;
    int x, y;

    scanf("%d", &coloredPaperNum);
    
    for(i = 0; i < coloredPaperNum; i++)
    {
    	// 배열에서는 x가 열의 인덱스고 y가 행의 인덱스다. 
        scanf("%d %d", &x, &y);
        
        for(j = y; j < y+10; j++)
        {
            for(k = x; k < x+10; k++)
                whitePaper[j][k] = 1;
        }
    }
    
    for(i = 0 ; i < 100; i++)
    {
        for(j = 0; j < 100; j++)
        {
            if(whitePaper[i][j] == 1)
                area++;
        }
    }
    
    printf("%d", area);
    
	return 0;
}