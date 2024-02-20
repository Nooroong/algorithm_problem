import sys
input = sys.stdin.readline

# 입력
N, M = map(int, input().split())
arr = [list(map(int, input().split())) for _ in range(N)]
home = [] # 집 위치 저장
chi = [] # 치킨집 위치 저장
for i in range(N) :
    for j in range(N) :
        if arr[i][j]==1 :
            home.append([i, j])
        elif arr[i][j]==2 :
            chi.append([i, j])
CHI = len(chi) # 총 치킨집 개수

def cal(clst) : # 치킨거리 구하기 : 모든 집~치킨집
    sm = 0
    for hi, hj in home :
        mn = 2*N # 최소값 저장
        for ci, cj in clst :
            mn = min(mn, abs(hi-ci) + abs(hj-cj))
        sm += mn
    return sm

def dfs(n, clst) : # clst : 남을 치킨 집
    global ans
    # 1.종료 조건
    if n==CHI :
        if len(clst)==M :
            ans = min(ans, cal(clst))
        return
    # 2.하부 호출
    dfs(n+1, clst+[chi[n]]) # 폐업X
    dfs(n+1, clst) # 폐업O

ans = 2*N*2*N   # 최대 2N개의 집이 떨어진 경우
dfs(0, [])
print(ans)


