#include <stdio.h>
#include <math.h>

/*
a 캥거루가 b, c 사이로 뛰어들거나
c 캥거루가 a, b 사이로 뛰어드는 경우만 있다.
b-a, c-b를 구하고 둘 중 간격이 더 큰 부분을 찾는다.
더 넓은 간격을 선택하고 한칸씩 번갈아가며 뛰면 된다.
↳이때 뛸 수 있는 횟수는 (둘의 차 - 1). (캥거루 사이 빈칸의 수)
*/

int main(void)
{
	int a, b, c;

	while(scanf("%d %d %d", &a, &b, &c) != EOF)
		printf("%d\n", (b-a)>(c-b)?b-a-1:c-b-1);

	

}
