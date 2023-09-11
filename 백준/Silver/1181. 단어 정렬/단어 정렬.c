#include <stdio.h>
#include <string.h>

/*
11650 문제의 답을 약간 바꾸면 된다. 
*/

void MergeSort(char arr[][51], int left, int right);
void MergeTwoArea(char arr[][51], int left, int mid, int right);

int main(void)
{
	int n;
	int i;
	
	scanf("%d", &n);
	
	char word[n][51];
	
	for(i = 0; i < n; i++)
		scanf("%s", word[i]);
		
	MergeSort(word, 0, n-1);
	
	for(i = 0; i < n; i++)
	{
		if(strcmp(word[i], "\0"))
			printf("%s\n", word[i]);
	}
		
    return 0;
}

void MergeSort(char arr[][51], int left, int right)
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

void MergeTwoArea(char arr[][51], int left, int mid, int right)
{
	int fIdx = left;
	int rIdx = mid+1;
	
	char sortArr[right+1][51];
	int sIdx = left;
	
	int i;
	
	while(fIdx <= mid && rIdx <= right)
	{
		// 정렬 조건 1: 길이가 짧은 것부터(strlen)
		if(strlen(arr[fIdx]) < strlen(arr[rIdx]))
			strcpy(sortArr[sIdx], arr[fIdx++]);
		else if (strlen(arr[fIdx]) > strlen(arr[rIdx]))
			strcpy(sortArr[sIdx], arr[rIdx++]);
		else // 정렬 조건 2: 길이가 같으면 사전 순으로(strcmp)
		{
			if(strcmp(arr[fIdx], arr[rIdx]) < 0)
				strcpy(sortArr[sIdx], arr[fIdx++]);
			else if(strcmp(arr[fIdx], arr[rIdx]) > 0)
				strcpy(sortArr[sIdx], arr[rIdx++]);
			else // 정렬 조건3: 중복된 단어는 하나만 남기고 제거해야 한다.
			{
				// 왼쪽 부분 배열의 원소는 그대로 sortArr에 넣고
				// 오른쪽 부분 배열의 원소는 넣지않고 sortArr에 \0을 넣고 넘겨버린다.
				// 오른쪽 부분 배열을 그냥 패스해버리면 길이가 맞지 않아 문제가 생기게 된다.
				strcpy(sortArr[sIdx++], arr[fIdx++]);
				strcpy(sortArr[sIdx], "\0");
				rIdx++;
			}
		}
		
		sIdx++;
	}
	
	
	if(fIdx > mid)
	{
		for(i = rIdx; i <= right; i++, sIdx++)
			strcpy(sortArr[sIdx], arr[i]);			
	}
	else
	{
		for(i = fIdx; i <= mid; i++, sIdx++)
			strcpy(sortArr[sIdx], arr[i]);
	}
	
	
	for(i = left; i <= right; i++)
		strcpy(arr[i], sortArr[i]);
}