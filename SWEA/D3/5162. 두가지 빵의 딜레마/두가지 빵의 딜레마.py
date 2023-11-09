# 금액이 더 적은 빵만 왕창 산다.

T = int(input())
# 여러개의 테스트 케이스가 주어지므로, 각각을 처리합니다.
for test_case in range(1, T + 1):
    bread = 0
    
    a, b, c = map(int, input().split())
    
    if a < b:
        bread += c // a
        # 이때의 잔돈은 b보다 작을 것이므로
        # b에 대해서는 계산하지 않아도 될듯
    else:
        bread += c // b
        
    print(f"#{test_case} {bread}")
