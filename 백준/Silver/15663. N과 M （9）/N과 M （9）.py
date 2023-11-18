# 이전 문제와의 차이점: 주어지는 숫자에 '중복'이 있을 수 있다.
# 중복으로 인해 같은 수열이 여러번 출력될 수 있다.
# 이를 주의하며 코드를 작성해야 한다.

n, m = map(int, input().split())
natural = sorted(list(map(int, input().split())))
seq = []
seqIdx = []

def bt():
    if len(seq) == m:
        print(*seq)
        return

    # remember에 들어가는 값: 다음 단계에 추가될 값
    # remember의 역할:
    # 주어진 자연수에 중복된 값이 있는 경우
    # 중복값에 대해서는 추가적으로 탐색을 하지 않게 하기 위함.
    # 예를 들어 예제 2번(4 2\n9 7 9 1)의 경우,
    remember = 0

    for i in range(n):
        # 한 번 들어간 값은 들어가지 않도록한다.
        # 중복되는 값이 있으므로 인덱스로 관리한다.
        if i not in seqIdx:
            num = natural[i]
            if remember != num:
                remember = num
                seq.append(num)
                seqIdx.append(i)
                bt()
                seq.pop()
                seqIdx.pop()

bt()