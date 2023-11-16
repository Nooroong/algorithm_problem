# 단조 증가하는 수인지 결과 반환
def isDanjo(num):
    # 가장 낮은 자리의 수부터 확인
    # 다음 자리 수가 현재수와 같거나 작은지 확인
    while num > 0:
        cur = num % 10
        num //= 10 # /와 //를 목적에 따라 잘 사용하자...
        nxt = num % 10
        
        if nxt > cur:
            return False
        
    return True
    
    
T = int(input())
# 여러개의 테스트 케이스가 주어지므로, 각각을 처리합니다.
for test_case in range(1, T + 1):
    maxNum = -1
   
    n = int(input())
    nums = list(map(int, input().split()))
    
    for i in range(n-1):
        for j in range(i+1, n):
            aij = nums[i] * nums[j]
            
            if isDanjo(aij):
                if aij > maxNum:
                    maxNum = aij
    
    print(f"#{test_case} {maxNum}")
