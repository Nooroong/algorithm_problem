#include <stdio.h>

void quickSort(int arr[], int start, int end);
void swap(int * num1, int * num2);

int main(void)
{
    int n, k;
    int i;
    
    scanf("%d %d", &n, &k);
    
    int score[n];
    for(i = 0; i < n; i++)
    	scanf("%d", &score[i]);
    
    
    // 퀵 정렬로 오름차순 정렬
    quickSort(score, 0, sizeof(score)/sizeof(int) - 1);
    
    printf("%d", score[k-1]);
    
    
    return 0;
}

void quickSort(int arr[], int start, int end)
{
	int pivot;
	int left = start, right = end;
	
	pivot = arr[(left+right)/2];
	
	while(left <= right)
	{
		while(arr[left] > pivot)
			left++;
		while(arr[right] < pivot)
			right--;
		
		if(left <= right)
		{
			swap(&arr[left], &arr[right]);
			left++;
			right--;
		}	
	}
	
	if(left < end)
		quickSort(arr, left, end);
	if(right > start)
		quickSort(arr, start, right);
	
}

void swap(int * num1, int * num2)
{
	int tmp = *num1;
	*num1 = *num2;
	*num2 = tmp;
}