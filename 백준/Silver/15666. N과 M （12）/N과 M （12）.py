# 아래 조건을 만족하는 길이가 M인 수열
# 1) 같은 수를 여러번 골라도 됨.
# 2) 수열은 비내림차순.(모든 인덱스에 대해 이전값 <= 다음값)

def bt(arr, idx, seqlimit, curSeq):
    global seqs

    # 수열 길이를 seqlen과 curlen 매개변수로 체크
    # seqlen은 문제에서 제시된 수열 길이 제한,
    # curlen은 지금까지 만든 수열의 길이를 의미한다.
    if seqlimit == len(curSeq):
        if curSeq not in seqs:
            seqs.append(curSeq)
        return

    # 밑에서 주어진 수를 오름차순으로 정렬했으므로
    # 여기서 반복을 돌 때는 idx부터 돌면
    # 자연스럽게 비내림차순 수열이 만들어진다.
    for i in range(idx, len(arr)):
        bt(arr, i, seqlimit, curSeq+[arr[idx]])


n, m = map(int, input().split())  # 주어지는 수의 개수, 수열의 길이
num = list(map(int, input().split()))

# 여기서 주어진 수에 대해 정렬을 하면,
# 백트래킹 과정에서 비내림차순이기 위한 조건을 신경쓸 필요가 없어짐
num.sort()

seqs = []  # 조건을 만족하는 수열을 담는 리스트

for i in range(n):
    curseq = []
    bt(num, i, m, curseq)


for i in range(len(seqs)):
    print(*seqs[i])
