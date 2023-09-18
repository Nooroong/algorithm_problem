#include <stdio.h>
#include <stdlib.h>

/*
'딸->초->바'를 반복. 스타트는 딸기.
*/

int main(void)
{
	int n;
	int * milk;
	int milkState = -1; 
	int i, cnt;
	
	
	scanf("%d", &n);
	milk = (int*)malloc(sizeof(int)*n);

	for(i = 0; i < n; i++)
		scanf("%d", &milk[i]);
		
	// 0은 딸기, 1은 초코, 2는 바나나.
	milkState = -1; // 직전에 마신 우유의 종류를 저장하는 변수
	cnt = 0; // 우유를 마실 수 있는 횟수
	for(i = 0; i < n; i++)
	{
		// 아직 아무 우유도 마시지 못한 경우
		if(milkState == -1)
		{
			if(milk[i] == 0)
			{
				milkState = 0;
				cnt++;
			}
		}
		else // 우유를 한 번이라도 마신 경우
		{
			// 내가 다음에 먹을 우유가 우유가게에서 팔고있다면
			if((milkState+1)%3 == milk[i])
			{
				milkState = milk[i];
				cnt++;
			}
		}		
	}
	
	
	printf("%d", cnt);
	
	
	return 0;
}