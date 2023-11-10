T = 10
# 여러개의 테스트 케이스가 주어지므로, 각각을 처리합니다.
for test_case in range(1, T + 1):
    cipherLen = int(input())
    cipher = list(input().split())
    cmdNum = int(input())
    cmd = list(input().split())

    i = 0
    cnt = 1
    result = ""

    while cnt <= cmdNum:
        if cmd[i] == "I":
            x = int(cmd[i + 1])
            y = int(cmd[i + 2])

            for n in range(y):
                cipher.insert(x + n, cmd[i + 3 + n])

            i += (3 + y)

        elif cmd[i] == "D":
            x = int(cmd[i + 1])
            y = int(cmd[i + 2])

            del cipher[x:x + y]

            i += 3

        cnt += 1


    print(f"#{test_case} ", end='')
    for i in range(10):
        print(cipher[i], end=' ')
    print()
