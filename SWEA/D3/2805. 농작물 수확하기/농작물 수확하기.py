T = int(input())
# 여러개의 테스트 케이스가 주어지므로, 각각을 처리합니다.
for test_case in range(1, T + 1):
    n = int(input())
    crop = [ list(input()) for _ in range(n) ]

    sum = 0 # 수익
    m = n//2 # 가운데

    # 넓어진다.
    # n//2부터 시작해서 좌, 우로 한 칸씩 넓어진다.
    for i in range(0, m + 1):
        for j in range(m-i, m+i+1):
            sum += int(crop[i][j])

    # 좁아진다.
    # 가운데 행을 기준으로 m-x, m+x만큼의 범위를 돈다고 하면,
    # x는 0~m행 까지는 행 번호를 그대로 쓰면 되고,
    # 그 이후의 행에 대해서는 (n-x-1)로 표현할 수 있다.
    for i in range(m+1, n):
        for j in range(m-(n-i-1), m+(n-i-1)+1):
            sum += int(crop[i][j])


    print(f"#{test_case} {sum}")