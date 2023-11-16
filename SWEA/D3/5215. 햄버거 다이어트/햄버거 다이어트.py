# 모든 조합의 경우를 생각해야함 ->  DFS
# 이때 지금까지 더해온 맛의 총합을 기억하기 위해 taste,
# 지금까지 소모하고(?) 남은 칼로리를 기억하기 위해 cal 매개변수를 쓴다.

def dfs(arr, idx, taste, cal):
    global maxTaste

    # 제한 칼로리를 맞춰야 한다.
    if cal > limitCal: return

    # maxTaste는 매 순간의 최대 칼로리를 저장하고 있다.
    if taste > maxTaste: maxTaste = taste
        
    # 마지막 재료까지 다 거쳐온 경우,
    if idx == ingreNum: return

    # 다음번 재료를 넣는 경우, 넣지 않는 경우.
    dfs(arr, idx + 1, taste + arr[idx][0], cal + arr[idx][1])
    dfs(arr, idx + 1, taste, cal)


T = int(input())

for test_case in range(1, T + 1):
    ingreNum, limitCal = map(int, input().split())
    score = []  # [맛, 칼로리] 점수

    # 맛, 칼로리 입력받기
    for i in range(ingreNum):
        score.append(list(map(int, input().split())))

    # 탐색
    maxTaste = 0
    dfs(score, 0, 0, 0)

    print(f"#{test_case} {maxTaste}")