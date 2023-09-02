#include <stdio.h>

/*
맨 아래의 정사각형이 n개일 때, 전체 도형은 n층을 이루고 있다.
좌/우변의 길이는 각각 도형 높이만큼.
하단 변의 길이는 정사각형의 개수와 같음.
위쪽의 볼록한 부분도 정사각형의 개수와 같음.
*/

int main(void)
{
    unsigned int n; // 입력되는 값의 범위에 주의!!
    
    scanf("%u", &n);
    
    printf("%u", 4*n);
    
	return 0;
}