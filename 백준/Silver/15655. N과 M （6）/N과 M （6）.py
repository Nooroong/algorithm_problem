n, m = map(int, input().split())
natural = sorted(list(map(int, input().split())))
seq = []

def bt(idx):
    if len(seq) == m:
        print(*seq)
        return

    for i in range(idx+1, n):
        seq.append(natural[i])
        bt(i)
        seq.pop()

bt(-1)
