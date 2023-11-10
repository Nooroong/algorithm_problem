# 우, 하, 좌, 상의 방향으로 반복하며 움직인다.
# 우에서 하로, 좌에서 상으로 방향이 바뀔 때
# 이동하는 칸의 수가 1씩 줄어든다.

T = int(input())

# 우, 하, 좌, 상
dRow = [0, 1, 0, -1]
dCol = [1, 0, -1, 0]

# 여러개의 테스트 케이스가 주어지므로, 각각을 처리합니다.
for test_case in range(1, T + 1):
    print(f"#{test_case}")

    n = int(input())
    snail = [[0 for col in range(n)] for row in range(n)]

    move = n  # 한 방향에서 이동하는 칸 수
    direction = 0  # 0, 1, 2, 3 == 우, 하, 좌, 상

    # 좌표
    row = 0
    col = -1

    i = 1  # 각 칸에 기록해야 할 수
    while i <= n * n:
        for j in range(move):  # 각 방향마다(?) 반복
            row += dRow[direction]
            col += dCol[direction]
            snail[row][col] = i
            i += 1

        if direction == 0 or direction == 2:
            move -= 1

        direction = (direction + 1) % 4  # 뱡향전환

    for i in range(n):
        [print(snail[i][j], end=' ') for j in range(n)]
        print()
