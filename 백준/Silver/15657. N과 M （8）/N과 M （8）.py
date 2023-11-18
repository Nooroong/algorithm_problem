n, m = map(int, input().split())
natural = sorted(map(int, input().split())) # 비내림차순
seq = []

def bt(idx):
    if len(seq) == m:
        print(*seq)
        return

    for i in range(idx, n):
        seq.append(natural[i])
        bt(i)
        seq.pop()

bt(0)