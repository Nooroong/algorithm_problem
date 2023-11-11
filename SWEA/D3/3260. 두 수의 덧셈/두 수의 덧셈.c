#include <stdio.h>
#include <string.h>

/*
문자열로 입력받고 가장 끝부분부터 한 자리씩 계산해나간다.
*/

int main(void)
{
    int T, test_case;
	char num1[102] = {0};
    char num2[102]= {0};
    int result[102] = {0};
    int i, j, rIdx, tmp, resultLen;
	
    scanf("%d", &T);
    
	for (test_case = 1; test_case <= T; ++test_case)
	{
		scanf("%s %s", num1, num2);
        memset(result, 0, sizeof(int)*102);
        resultLen = 0;
        
		for(i = strlen(num1)-1, j = strlen(num2)-1, rIdx = 0; i >= 0 && j >= 0; i--, j--, rIdx++)
        {
            result[rIdx] += (num1[i] + num2[j] - 2*48);
            resultLen = rIdx;
            if(result[rIdx] >= 10)
            {
                result[rIdx+1] += result[rIdx]/10;
            	result[rIdx] %= 10;
                resultLen = rIdx+1;
            }
        }
        
        while(i>=0)
        {
            result[rIdx] += (num1[i]-48);
            resultLen = rIdx;
            if(result[rIdx] >= 10)
            {
                result[rIdx+1] += result[rIdx]/10;
            	result[rIdx] %= 10;
                resultLen = rIdx+1;
            }
            rIdx++;
            i--;
        }
        
        while(j>=0)
        {
            result[rIdx] += (num2[j]-48);
            resultLen = rIdx;
            if(result[rIdx] >= 10)
            {
                result[rIdx+1] += result[rIdx]/10;
            	result[rIdx] %= 10;
                resultLen = rIdx+1;
            }
            rIdx++;
            j--;
        }
        
        
        printf("#%d ", test_case); 
        for(i=resultLen; i >= 0 ; i--)
            printf("%d", result[i]);
        printf("\n");
	}
    
	return 0; //정상종료시 반드시 0을 리턴해야 합니다.
}