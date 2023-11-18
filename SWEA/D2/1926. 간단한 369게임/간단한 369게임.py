def is369(num):
    pass

n = int(input())
say = []

for i in range(1, n+1):
    tmp = i
    hipen = ""
    flag = 0 # i에 3, 6, 9가 들어있었는지 기록하는 flag 변수

    while tmp > 0:
        if (tmp % 10) in (3, 6, 9):
            flag = 1
            hipen += "-"
        tmp //= 10

    # i에 3, 6, 9가 하나라도 없었다면
    if flag == 0:
        say.append(str(i))
    else:
        say.append(hipen)

print(*say)