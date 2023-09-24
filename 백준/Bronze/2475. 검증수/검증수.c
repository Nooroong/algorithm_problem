#include <stdio.h>

int main(void)
{
    int num;
    int verifi;
    int i;

    verifi = 0;

    for(i = 0; i < 5; i++)
    {
        scanf("%d", &num);
        verifi += num * num;
    }

    printf("%d", verifi % 10);

    return 0;
}