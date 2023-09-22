#include <stdio.h>

int main(void)
{
    int n;
    int input, cute;
    
    scanf("%d", &n);
    
    cute = 0;
    while(n>0)
    {
        scanf("%d", &input);
        cute += input==1 ? 1 : -1;
        n--;
    }
    
    printf("Junhee is %scute!", cute>0 ? "" : "not ");;
    
    return 0;
}