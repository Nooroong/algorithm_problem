# 일단 리스트에서 최고가를 찾는다.
# 최고가가 되기 전까지 물건을 다 사놓는다.
# 최고가에서 물건을 판다.
# 그 이후의 날에 대해 다시 최고가를 찾는다.
# 날이 끝날 때까지 위 과정을 반복한다.

T = int(input())

for test_case in range(1, T + 1):
    
    n = int(input())
    cost = list(map(int, input().split()))
    
    earned = 0 # 총 수익
    
    while len(cost) > 0:
        highestIdx = cost.index(max(cost)) # 남은 리스트에서 고점의 인덱스를 찾음
        heighestPrice = cost[highestIdx]
        
        # 고점 전에는 물건을 전부 사고 고점에서 판 가격을 계산
        for i in range(len(cost[ :highestIdx])):
            earned  += heighestPrice - cost[i]
        
        cost = cost[highestIdx+1: ] # 이후 기간에서 새로운 고점을 찾기 위해 범위 조정
        
    print(f"#{test_case} {earned}")
    
