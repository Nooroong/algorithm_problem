T = int(input())
# 여러개의 테스트 케이스가 주어지므로, 각각을 처리합니다.
for test_case in range(1, T + 1):
    # n명의 사람이 가게로 온다.
    # 0초부터 시작, m초의 시간을 들여 k개의 붕어빵을 만들 수 있음.
    n, m, k = map(int, input().split())
    customer = sorted(list(map(int, input().split())))

    time = 0
    ppang = 0
    possible = 1

    while customer:
        # time을 1씩 증가시키면서 탐색하면 당연히 시간초과가 걸린다.
        # m의 간격만큼 시각을 증가시키며 검토를 해야한다.
        # 현재 시각에서 다음 붕어빵이 만들어질 시각 전까지
        # (예: m이 2라면 0초부터 1초까지의 시간 동안)
        # 도착하는 사람에 대해 붕어빵의 소모를 계산
        while customer and customer[0] <= (time+m-1):
            ppang -= 1
            customer.pop(0)
    
            # 붕어빵이 모자라서 모든 사람에게 줄 수 없는 경우
            if ppang < 0:
                possible = 0 # 불가능!
                break

        time += m
        ppang += k

    if possible == 1:
        print(f"#{test_case} Possible")
    else:
        print(f"#{test_case} Impossible")
