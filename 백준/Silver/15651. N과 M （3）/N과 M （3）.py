import sys

n, m = map(int, input().split())
tmp = []

def bt():
    if len(tmp) == m:
        for t in tmp:
            sys.stdout.write(f"{t} ")
        sys.stdout.write("\n")
        return



    for i in range(1, n+1):
        tmp.append(i)
        bt()
        tmp.pop()

bt()