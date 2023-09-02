#include <stdio.h>

int main(void)
{
    int angle1, angle2, angle3;
    
    scanf("%d\n%d\n%d", &angle1, &angle2, &angle3);
    
    if(angle1 + angle2 + angle3 != 180)
        printf("Error");
    else
    {
        if(angle1 == angle2 && angle2 == angle3)
            printf("Equilateral");
        else if(angle1 == angle2 || angle2 == angle3 || angle3 == angle1)
            printf("Isosceles");
        else
            printf("Scalene");
    }


	return 0;
}