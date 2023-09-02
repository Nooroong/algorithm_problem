#include <stdio.h>

int main(void)
{
    int side1, side2, side3;
    
    scanf("%d %d %d", &side1, &side2, &side3);
    
    // 삼각형이 되기위한 세 변의 길이
    // : (가장 긴 변의 길이) < (나머지 두 변의 길이의 합)
    
    // 일단 변의 길이에 대해 내림차순으로 버블 정렬
    if(side1 < side2) swap(&side1, &side2);
    if(side2 < side3) swap(&side2, &side3);
    if(side1 < side2) swap(&side1, &side2);
    
    if(side1 < (side2 + side3)) // 입력값 그대로 삼각형을 만들 수 있음
        printf("%d", side1 + side2 + side3);
    else // 최대 길이로 만들어야 하므로, 나머지 두 변 + (나머지 두 변의 길이 - 1)로 맞춰야 한다.
        printf("%d", (side2 + side3)*2 - 1);

	return 0;
}

void swap(int * num1, int * num2)
{
    int tmp;
    
    tmp = *num1;
    *num1 = *num2;
    *num2 = tmp;
}