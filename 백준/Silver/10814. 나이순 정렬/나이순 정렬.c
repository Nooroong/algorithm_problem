#include <stdio.h>
#include <string.h>

/*
시간 제한을 고려해야 하는 정렬문제에서는 가능하면 병합정렬을 사용한다.
기수정렬보다 구현이 간단하며, 대부분의 상황에서 O(n*logn)이 보장된다. 
*/

typedef struct _member
{
	int age;
	char name[101];
} Member;


void MergeSort(Member arr[], int left, int right);
void MergeTwoArea(Member arr[], int left, int mid, int right);


int main(void)
{
	int n;
	int i;
	
	scanf("%d", &n);
	
	Member baekjoon[n];


	for(i = 0; i < n; i++)
		scanf("%d %s", &baekjoon[i].age, baekjoon[i].name);
		
	MergeSort(baekjoon, 0, n-1);
	
	
	for(i = 0; i < n; i++)
		printf("%d %s\n", baekjoon[i].age, baekjoon[i].name);
	
	
    return 0;
}

void MergeSort(Member arr[], int left, int right)
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

void MergeTwoArea(Member arr[], int left, int mid, int right)
{
	int fIdx = left;
	int rIdx = mid+1;
	
	Member sortArr[right+1];
	int sIdx = left;
	
	int i;
	
	while(fIdx <= mid && rIdx <= right)
	{
		// 정렬 조건 1: 회원들을 나이가 증가하는 순으로
		if(arr[fIdx].age < arr[rIdx].age)
		{
			sortArr[sIdx].age = arr[fIdx].age;
			strcpy(sortArr[sIdx].name, arr[fIdx].name);
			fIdx++;
		}
		else if (arr[fIdx].age > arr[rIdx].age)
		{
			sortArr[sIdx].age = arr[rIdx].age;
			strcpy(sortArr[sIdx].name, arr[rIdx].name);
			rIdx++;
		}
		else // 정렬 조건 2: 나이가 같으면 먼저 가입한 사람이 앞에 오는 순서로 정렬
		{
			// 인덱스를 비교하면 된다?
			if(fIdx <= rIdx)
			{
				sortArr[sIdx].age =  arr[fIdx].age;
				strcpy(sortArr[sIdx].name, arr[fIdx].name);
				fIdx++;
			}
			else
			{
				sortArr[sIdx].age =  arr[rIdx].age;
				strcpy(sortArr[sIdx].name, arr[rIdx].name);
				rIdx++;
			}
		}
		
		sIdx++;
	}
	
	
	if(fIdx > mid)
	{
		for(i = rIdx; i <= right; i++, sIdx++)
		{
			sortArr[sIdx].age =  arr[i].age;
			strcpy(sortArr[sIdx].name, arr[i].name);
		}
	}
	else
	{
		for(i = fIdx; i <= mid; i++, sIdx++)
		{
			sortArr[sIdx].age =  arr[i].age;
			strcpy(sortArr[sIdx].name, arr[i].name);
		}
	}
	
	
	for(i = left; i <= right; i++)
	{
		arr[i].age = sortArr[i].age;
		strcpy(arr[i].name, sortArr[i].name);
	}
}