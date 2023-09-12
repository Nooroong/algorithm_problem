#include <stdio.h>
#include <string.h>
#include <stdlib.h>

/*
문제가 복잡하게 써졌지만 뜯어보면 의미는 간단하다.
핵심은 'Xi를 줄인 X'i의 값은 Xi > Xj를 만족하는 서로 다른 Xj의 개수와 같아야 한다.'는 부분이다.
즉 Xi > Xj를 만족하는 Xj의 개수를 찾으라는 뜻이고, 더 쉽게 설명하면 배열안에 나(Xi)보다 작은 수가 몇개있냐 이걸 찾으라는 의미이다.
다만 문제 안에 '서로 다른' 이라고 써져있으니 중복된 수를 잘 고려해야겠다. 
*/


void MergeSort(int arr[], int left, int right);
void MergeTwoArea(int arr[], int left, int mid, int right);
int BinarySearch(int arr[], int start, int end, int target);


int main(void)
{
	int n;
	int i, ddIdx;
	
	
	scanf("%d", &n);
	int x[n];
	int xSort[n];
	int xSortDD[n]; // De-Duplication


	for(i = 0; i < n; i++)
	{
		scanf("%d", &x[i]);
		xSort[i] = x[i];
	}
		
	
	// 일단 데이터를 오름차순으로 정렬한다.
	MergeSort(xSort, 0, n-1);
	
	
	// xSort에서 중복을 제거
	// 중복값을 제외하고 앞으로 땡겨와 배열에 저장.(근데 끝에는 중복이 남아있음)
	xSortDD[0] = xSort[0];
	ddIdx = 1;
	
	if(n >= 2)
	{
		for(i = 1; i < n; i++)
		{
			if(xSort[i-1] != xSort[i])
			{
				xSortDD[ddIdx++] = xSort[i];
			}
		}
	}
	
	// xSortDD에서 ddIdx-1까지의 값이 유요한 인덱스이다.
		
	
	// Xi보다 작은 숫자들의 개수를 센다. 
	// xSort에서 해당 값이 위치한 인덱스를 알아내면 된다.(== 탐색)
	// 시간을 고려해 이진 탐색을 이용한다.
	for(i = 0; i < n; i++)
		printf("%d ", BinarySearch(xSortDD, 0, ddIdx-1, x[i]));
		
	
	
    return 0;
}

void MergeSort(int arr[], int left, int right)
{
	int mid;
	
	if(left < right)
	{
		mid = (left+right)/2;
		
		MergeSort(arr, left, mid);
		MergeSort(arr, mid+1, right);
		MergeTwoArea(arr, left, mid, right);
	}
}

void MergeTwoArea(int arr[], int left, int mid, int right)
{
	int fIdx = left;
	int rIdx = mid+1;
	
	int sortArr[right+1];
	int sIdx = left;
	
	int i;
	
	while(fIdx <= mid && rIdx <= right)
	{
		if(arr[fIdx] < arr[rIdx])
			sortArr[sIdx] = arr[fIdx++];
		else 
			sortArr[sIdx] = arr[rIdx++];
		
		sIdx++;
	}
	
	
	if(fIdx > mid)
	{
		for(i = rIdx; i <= right; i++, sIdx++)
			sortArr[sIdx] =  arr[i];
	}
	else
	{
		for(i = fIdx; i <= mid; i++, sIdx++)
			sortArr[sIdx] =  arr[i];
	}
	
	
	for(i = left; i <= right; i++)
		arr[i] = sortArr[i];
}

int BinarySearch(int arr[], int start, int end, int target)
{
	int mid = (start+end)/2;
	
	// target이 배열에 없는 경우
	if(start > end) return -1;
	
	
	if(target == arr[mid])
		return mid; 
	if(arr[mid] < target)
		return BinarySearch(arr, mid+1, end, target);
	else
		return BinarySearch(arr, start, mid-1, target);
}