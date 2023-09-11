#include <stdio.h>
#include <stdlib.h>

void MergeSort(int arr[], int left, int right);
void MergeTwoArea(int arr[], int left, int mid, int right);
void Swap(int * num1, int * num2);

int main(void)
{
    int n;
    int i;
    scanf("%d", &n);
    
    int arr[n];
    for(i = 0; i < n; i++)
        scanf("%d", &arr[i]);
    
    MergeSort(arr, 0, n-1);
    
    for(i = 0; i < n; i++)
        printf("%d \n", arr[i]);
    
    return 0;
}


void MergeSort(int arr[], int left, int right)
{
	int mid;
	
	// 배열을 나눌 수 있다면,
	if(left < right) {
		// 배열을 반으로 나누고,
		mid = (left + right) / 2;
		
		// 정렬하여,
		MergeSort(arr, left, mid);
		MergeSort(arr, mid+1, right);
		
		// 합친다.
		MergeTwoArea(arr, left, mid, right);
	}
	
}

void MergeTwoArea(int arr[], int left, int mid, int right)
{
	int fIdx = left; // 왼쪽 배열 
	int rIdx = mid + 1; // 오른쪽 배열
	int i;
	
	// 두 배열의 원소를 임시로 정렬하기 위해 필요한 변수
	int * sortArr = (int*)malloc(sizeof(int)*(right+1)); 
	int sIdx = left; // sortArr 
	
	// 합쳐야 할 두 배열이 있다.
	// 두 배열의 첫번째 요소부터 서로 비교를 하여
	// 작은 값부터 순서대로 sortArr에 임시로 정렬하여 저장한다.
	while(fIdx <= mid && rIdx <= right)
	{
		if(arr[fIdx] < arr[rIdx]) sortArr[sIdx] = arr[fIdx++];
		else sortArr[sIdx] = arr[rIdx++];
		
		sIdx++;
	}
	
	// 한 쪽 배열의 원소를 다 꺼냈으면
	// 다른 배열의 원소를 그대로 쭉 sortArr에 집어넣으면 된다.
	if(fIdx > mid)
	{
		for(i = rIdx; i <= right; sIdx++, i++)
			sortArr[sIdx] = arr[i];
	}
	else
	{
		for(i = fIdx; i <= mid; sIdx++, i++)
			sortArr[sIdx] = arr[i];
	}
		
	for(i = left; i <= right; i++)
		arr[i] = sortArr[i];
		
	free(sortArr);
}

void Swap(int * num1, int * num2)
{
    int tmp = *num1;
    *num1 = *num2;
    *num2 = tmp;
}