n, m = map(int, input().split())
tmp = []

def bt():
    if len(tmp) == m:
        print(*tmp)
        return

    # 같은 숫자를 여러번 골라서 수열을 만들 수 없음!
    for i in range(1, n+1):
        if i not in tmp:
            tmp.append(i)
            bt()
            tmp.pop()


bt()