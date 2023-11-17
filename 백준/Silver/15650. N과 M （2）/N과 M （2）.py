import sys

n, m = map(int, input().split())
tmp = []

def bt(idx):
    if len(tmp) == m:
        for t in tmp:
            sys.stdout.write(str(t) + ' ')
        sys.stdout.write("\n")
        return
    
    for i in range(idx+1, n+1):
        if i not in tmp: # 수열 안에 중복된 값은 들어갈 수 없다.
            tmp.append(i)
            bt(i)
            tmp.pop()

bt(0)