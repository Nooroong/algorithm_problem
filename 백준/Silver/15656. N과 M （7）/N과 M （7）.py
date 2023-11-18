n, m = map(int, input().split())
natural = sorted(map(int, input().split())) # 사전 수 출력
seq = []

def bt():
    if len(seq) == m:
        print(*seq)
        return

    # 같은 수를 여러 번 골라도 된다.
    for i in natural:
        seq.append(i)
        bt()
        seq.pop()

bt()