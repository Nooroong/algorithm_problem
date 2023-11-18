n, m = map(int, input().split())
nums = sorted(list(map(int, input().split()))) # 수열은 오름차순으로 출력
seq = []
def bt():
    if len(seq) == m:
        print(*seq)
        return

    for num in nums:
        if num not in seq:
            seq.append(num)
            bt()
            seq.pop()

bt()