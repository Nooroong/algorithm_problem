#include <stdio.h>

/*
x좌표의 최대/최소값, y좌표의 최대/최소값을 구하면 된다.
*/

int main(void)
{
    int n;
    int x, y;
    int minX, minY, maxX, maxY;
    int i;
    
    // 항상 입력값의 범위를 생각하자!
    minX = minY = 11111;
    maxX = maxY = -11111;
    
    scanf("%d", &n);
    
    for(i = 0; i < n; i++)
    {
        scanf("%d %d", &x, &y);
        
        minX = (minX < x)?minX:x;
        minY = (minY < y)?minY:y;
        maxX = (maxX > x)?maxX:x;
        maxY = (maxY > y)?maxY:y;
    }
    
    printf("%d", (maxX-minX) * (maxY-minY));

	return 0;
}