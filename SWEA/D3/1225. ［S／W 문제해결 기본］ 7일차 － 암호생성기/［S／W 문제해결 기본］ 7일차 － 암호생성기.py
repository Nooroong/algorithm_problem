T = 10
for test_case in range(1, T + 1):
    t = input()  # 테스트케이스
    plain = list(map(int, input().split()))
    minus = 0  # 0~4

    while (True):
        tmp = plain[0] - (minus + 1)  # n번째 숫자(그렇지만 항상 맨 앞에 있음)를 n 감소시킴

        # 맨 뒤로 보낸다.
        plain = plain[1:]
        plain.append(tmp)

        if tmp <= 0:  # 감소한 숫자가 0 이하인 경우
            plain[-1] = 0  # 0으로 유지
            break  # 프로그램은 종료

        minus = (minus + 1) % 5

    print(f"#{test_case} ", end='')
    print(*plain)