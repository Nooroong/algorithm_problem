T = int(input())

for test_case in range(1, T + 1):
    # 맵의 높이와 너비(_map의 행, 열)
    h, w = map(int, input().split())
    _map = [ list(input()) for _ in range(h) ]
    n = int(input()) # 사용자의 입력 개수
    com = list(input()) # 사용자가 입력한 명령어
    flag = 0


    # 전차 위치 찾기
    for i in range(h):
        for j in range(w):
            if _map[i][j] == '<' or \
                _map[i][j] == '>' or \
                _map[i][j] == '^' or \
                _map[i][j] == 'v':
                row = i
                col = j
                break

    # 명령어 실행하기
    for c in com:
        if c == 'U':
            _map[row][col] = '^' # 방향 바꾸기

            # 평지라면 이동
            tRow = row-1
            if tRow < 0 : tRow = 0
            if _map[tRow][col] == '.':
                _map[row][col], _map[tRow][col] = _map[tRow][col], _map[row][col]
                row = tRow

        elif c == 'D':
            _map[row][col] = 'v'
            tRow = row + 1
            if tRow >= h: tRow = h-1
            if _map[tRow][col] == '.':
                _map[row][col], _map[tRow][col] = _map[tRow][col], _map[row][col]
                row = tRow

        elif c == 'L':
            _map[row][col] = '<'
            tCol = col - 1
            if tCol < 0: tCol = 0
            if _map[row][tCol] == '.':
                _map[row][col], _map[row][tCol] = _map[row][tCol], _map[row][col]
                col = tCol

        elif c == 'R':
            _map[row][col] = '>'
            tCol = col + 1
            if tCol >= w: tCol = w-1
            if _map[row][tCol] == '.':
                _map[row][col], _map[row][tCol] = _map[row][tCol], _map[row][col]
                col = tCol

        elif c == 'S':
            if _map[row][col] == '^':
                for i in range(row-1, -1, -1):
                    if _map[i][col] == '*':
                        _map[i][col] = '.'
                        break
                    elif _map[i][col] == '#':
                        break


            elif _map[row][col] == 'v':
                for i in range(row+1, h):
                    if _map[i][col] == '*':
                        _map[i][col] = '.'
                        break
                    elif _map[i][col] == '#':
                        break

            elif _map[row][col] == '<':
                for i in range(col-1, -1, -1):
                    if _map[row][i] == '*':
                        _map[row][i] = '.'
                        break
                    elif _map[row][i] == '#':
                        break

            elif _map[row][col] == '>':
                for i in range(col+1, w):
                    if _map[row][i] == '*':
                        _map[row][i] = '.'
                        break
                    elif _map[row][i] == '#':
                        break

    print(f"#{test_case}", end=' ')
    for m in _map:
        for c in m:
            print(c, end='')
        print()