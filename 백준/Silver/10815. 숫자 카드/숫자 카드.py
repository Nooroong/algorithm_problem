# 50만개
# 순차 탐색을 이용하면 최악의 경우 O(n*m) = 2500억번 연산
# card를 정렬해서 이분탐색을 쓰는 경우
# (수정: 이진탐색의 시간복잡도는 O(logn)이다. 어쩐지 이상했다…)
# : O(n*logn + m*logn) = O((n+m)*logn) ≈ 100만 * 19 ≈ 1900만번 연산

n = int(input())
card = list(map(int, input().split()))
m = int(input())
nums = list(map(int, input().split()))

card.sort()

for num in nums:
	findFlag = 0

	# card에 대해 이분탐색을 하기 위한 인덱스 정보들
	start = 0
	end = n - 1

	# 이분탐색
	while start <= end:
		target = (start+end) // 2

		if card[target] == num:
			findFlag = 1
			break
		elif num < card[target]:
			end = target - 1
		else:
			start = target + 1

	print("1", end=' ') if findFlag == 1 else print("0", end=' ')