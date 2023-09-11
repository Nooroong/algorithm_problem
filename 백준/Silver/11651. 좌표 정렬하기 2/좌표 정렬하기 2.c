#include <stdio.h>

/*
11650 문제의 답을 약간 바꾸면 된다. 
*/

void MergeSort(int arr[][2], int left, int right);
void MergeTwoArea(int arr[][2], int left, int mid, int right);

int main(void)
{
	int n, x, y;
	int i;
	
	scanf("%d", &n);
	
	int coordinate[n][2];
	
	for(i = 0; i < n; i++)
		scanf("%d %d", &coordinate[i][0], &coordinate[i][1]);

	
	MergeSort(coordinate, 0, n-1);
	
	for(i = 0; i < n; i++)
		printf("%d %d \n", coordinate[i][0], coordinate[i][1]);
    
    return 0;
}

void MergeSort(int arr[][2], int left, int right)
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

void MergeTwoArea(int arr[][2], int left, int mid, int right)
{
	int fIdx = left;
	int rIdx = mid+1;
	
	int sortArr[right+1][2];
	int sIdx = left;
	
	int i;
	
	while(fIdx <= mid && rIdx <= right)
	{
		if(arr[fIdx][1] < arr[rIdx][1])
		{
			sortArr[sIdx][0] = arr[fIdx][0];
			sortArr[sIdx][1] = arr[fIdx][1];
			fIdx++;
		}
		else if (arr[fIdx][1] > arr[rIdx][1])
		{
			sortArr[sIdx][0] = arr[rIdx][0];
			sortArr[sIdx][1] = arr[rIdx][1];
			rIdx++;
		}
		else // y좌표가 같으면 x좌표가 증가하는 순서로 정렬
		{
			if(arr[fIdx][0] < arr[rIdx][0])
			{
				sortArr[sIdx][0] = arr[fIdx][0];
				sortArr[sIdx][1] = arr[fIdx][1];
				fIdx++;
			}
			else
			{
				sortArr[sIdx][0] = arr[rIdx][0];
				sortArr[sIdx][1] = arr[rIdx][1];
				rIdx++;
			}
		}
		
		sIdx++;
	}
	
	
	if(fIdx > mid)
	{
		for(i = rIdx; i <= right; i++, sIdx++)
		{
			sortArr[sIdx][0] = arr[i][0];
			sortArr[sIdx][1] = arr[i][1];
		}
	}
	else
	{
		for(i = fIdx; i <= mid; i++, sIdx++)
		{
			sortArr[sIdx][0] = arr[i][0];
			sortArr[sIdx][1] = arr[i][1];
		}
	}
	
	
	for(i = left; i <= right; i++)
	{
		arr[i][0] = sortArr[i][0];
		arr[i][1] = sortArr[i][1];
	}
	
}