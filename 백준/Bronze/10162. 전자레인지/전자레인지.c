#include <stdio.h>

/*
거스름돈 문제와 똑같은 방식으로 풀면될듯.
1분은 10초의 배수, 5분은 1분의 배수이므로
가장 큰 단위부터 T를 나누면 된다.
*/

int main(void)
{
	int t; // 단위: 초
	int minutes[3] = { 300, 60, 10 }; // 단위를 초로 통일한다.
	int clicks[3] = {0}; // 버튼 누름 횟수
	int i;
	
	scanf("%d", &t);
	
	
	for(i = 0; i < 3; i++)
	{
		// 가장 큰 단위부터 t를 나눔.
		clicks[i] = t/minutes[i];
		t %= minutes[i];
	}
	
	if(t!=0)
		printf("-1");
	else
		printf("%d %d %d", clicks[0], clicks[1], clicks[2]);


	
	return 0;
}