n, m = map(int, input().split())
seq = []

# cur: 마지막으로 추가한 수를 기억하기 위한 매개변수
def bt(cur):
    if len(seq) == m:
        print(*seq)
        return

    # 비내림차순: 마지막으로 추가한 수 ~ n까지의 수에 대해
    # 수열을 만들어보면 된다.
    for i in range(cur, n+1):
        seq.append(i)
        bt(i)
        seq.pop()


bt(1)