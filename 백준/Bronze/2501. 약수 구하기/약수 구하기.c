#include <stdio.h>

/*
N의 최대값이 10,000이라서 그냥 1부터 N까지 탐색해도
시간제한에 걸리지 않을 것 같다.
*/

int main() 
{
	int n, k;
	int i, cnt;
	
	scanf("%d %d", &n, &k);
	
	for(i = 1, cnt = 0; i <= n; i++)
	{
		if(n%i == 0)
			cnt++;
			
		if(cnt == k)
		{
			printf("%d", i);
			return 0;
		}
	}
	
    // return을 만나지 못하고 여기까지 옴
    // == k번째 약수가 없다는 의미
    // == n의 약수의 개수는 k보다 작다는 의미
	printf("0");
    
}