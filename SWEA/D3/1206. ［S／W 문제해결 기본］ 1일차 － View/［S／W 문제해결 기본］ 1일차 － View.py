# 순서가 i인 건물에 대해서
# 순서가 i-1, i-2, i+1, i+2인 건물의 높이가 i번째 건물보다 모두 낮아야 한다.(미만)
# max(i-1, i-2, i+1, i+2) < i 여야하고, 이때 조망권이 확보된 세대 수는
# i - max(i-1, i-2, i+1, i+2)가 된다.

T = 10
# 여러개의 테스트 케이스가 주어지므로, 각각을 처리합니다.
for test_case in range(1, T + 1):
    n = int(input())  # 건물의 개수
    building = [0] * n
    result = 0  # 조망권이 확보된 세대 수

    building = list(map(int, input().split()))

    for i, b in enumerate(building):
        if b != 0:
            neighborMax = max([building[i - 2], building[i - 1], building[i + 1], building[i + 2]])
            result += b - neighborMax if b > neighborMax else 0

    print(f"#{test_case} {result}")
