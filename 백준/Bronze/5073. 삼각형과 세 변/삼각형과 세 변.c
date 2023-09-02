#include <stdio.h>

void swap(int * num1, int * num2);

int main(void)
{
    int side1, side2, side3;
    
    while(1)
    {
    	scanf("%d %d %d", &side1, &side2, &side3);
    	
        if(side1 == 0 && side2 == 0 && side3 == 0)
            return 0;
        
        // 세 변을 내림차순으로 정렬(버블 정렬)
        if(side1 < side2) swap(&side1, &side2);
        if(side2 < side3) swap(&side2, &side3);
        if(side1 < side2) swap(&side1, &side2);
        
        // 삼각형인지 판별
        if(side1 >= (side2 + side3))
            printf("Invalid\n");
        else
        {
            if(side1 == side2 && side2 == side3)
                printf("Equilateral\n");
            else if(side1 == side2 || side2 == side3 || side3 == side1)
                printf("Isosceles\n");
            else
                printf("Scalene\n");
        }
    }

	return 0;
}

void swap(int * num1, int * num2)
{
    int tmp;
    
    tmp = *num1;
    *num1 = *num2;
    *num2 = tmp;
}