#include <stdio.h>

/*
666부터 시작하여 1씩 더해간다.
해당 숫자가 6이 연속으로 3개 이상 들어가는지 본다.
이는 나눗셈, 나머지 연산, 카운트 변수를 이용하면 된다.
*/

int main(void)
{
    int n;
   	int i, num, numTmp, sixCnt; 
    
    scanf("%d", &n);
    
    i = 0;
    while(1)
	{
		// 666부터 시작하여 1씩 더해간다.
		sixCnt= 0;
		num = numTmp = 666+i;
		
		while(numTmp > 0)
		{
			// 해당 숫자가 6이 연속으로 3개 이상 들어가는지 본다.
			sixCnt = (numTmp%10 == 6)? sixCnt+1 : 0;
			
			// 6이 연속으로 3개 발견되면, n을 1만큼 감소시킨다.
			// n이 0이 되면 n번째로 작은 숫자를 찾은 것을 의미한다.
			// 예를 들어 n이 1인 경우, num이 666일 때 아래 if문 안으로 들어오게 된다.
			// 그리고 n이 1 감소하여 0이 되고, 1번째로 작은 종말의 수를 찾게 됨을 알 수 있게된다.
			if(sixCnt == 3)
			{
				n--;
				break; // 일단 종말의 수를 찾았으므로 빠져나옴.
			}
			else
				numTmp /= 10;
		}
		
		// n번째로 작은 종말의 수를 찾은 경우
		if(n == 0)
		{
			printf("%d", num);
			break;
		}
		
		i++;
	}
    
    return 0;
}