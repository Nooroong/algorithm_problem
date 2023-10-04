#include <stdio.h>
#include <string.h>

/*
길이가 5인 배열을 이용해 M, O, B, I, S 문자가 나왔는지 체크.
*/

enum { M, O, B, I, S };

int main(void)
{
    char str[101];
    int mobis[5] = {0};
    int i;

    scanf("%s", str);

    for(i = 0; i < strlen(str); i++)
    {
        switch(str[i])
        {
            case 'M':
                mobis[M] = 1;
                break;
            case 'O':
                mobis[O] = 1;
                break;
            case 'B':
                mobis[B] = 1;
                break;
            case 'I':
                mobis[I] = 1;
                break;
            case 'S':
                mobis[S] = 1;
                break;
        }
    }

    for(i = 0; i < 5; i++)
    {
        if(mobis[i] == 0)
        {
            printf("NO");
            return 0;
        }
    }

    printf("YES");

    return 0;
}