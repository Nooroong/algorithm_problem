T = int(input())
# 여러개의 테스트 케이스가 주어지므로, 각각을 처리합니다.
for test_case in range(1, T + 1):
    S = input()
    xCnt = 0

    for s in S:
        if s == 'x': xCnt += 1

    print(f"#{test_case} ", end='')
    print("YES" if xCnt <= 7 else "NO")