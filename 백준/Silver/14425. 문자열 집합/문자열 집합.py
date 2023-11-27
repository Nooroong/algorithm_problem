# 최악의 경우 10000 * 10000 * 500 = 500억이 되는건가?
# 그렇다면 이진탐색을 이용한다.
# 문자열에 대해 비교 연산자는
# c언어 string 라이브러리의 strcmp 함수처럼 동작하는듯?

n, m = map(int, input().split()) # s의 길이, finds의 길이
s = [ input() for _ in range(n) ] # 탐색의 대상
finds = [ input() for _ in range(m) ]
count = 0

s.sort()

for find in finds:
    start = 0
    end = n - 1

    while start <= end:
        mid = (start + end) // 2
        
        if find == s[mid]:
            count += 1
            break
        elif find < s[mid]:
            end = mid - 1
        else:
            start = mid + 1

print(count)