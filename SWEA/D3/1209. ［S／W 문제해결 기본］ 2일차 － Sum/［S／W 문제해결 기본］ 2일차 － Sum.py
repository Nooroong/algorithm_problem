for x in range(10):
    T = int(input())

    _max = -1
    tmp = 0
    arr = []

    for i in range(100):
        arr.append(list(map(int, input().split())))

    # 각 행의 합
    tmp = 0
    for i in range(100):
        tmp = sum(arr[i])
        _max = tmp if tmp > _max else _max

    # 각 열의 합
    for i in range(100):
        tmp = 0
        for j in range(100):
            tmp += arr[j][i]

        _max = tmp if tmp > _max else _max

    # 대각선의 합
    tmp = 0
    for i in range(100):
        tmp += arr[i][i]
    _max = tmp if tmp > _max else _max

    tmp = 0
    for i in range(99, -1, -1):
        tmp += arr[i][i]
    _max = tmp if tmp > _max else _max

    print(f"#{T} {_max}")
