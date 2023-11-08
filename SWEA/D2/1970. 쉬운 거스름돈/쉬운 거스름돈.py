T = int(input())

# 각 화폐단위들이 배수 관계이므로 그리디로 해결한다.
money = [ 50000, 10000, 5000, 1000, 500, 100, 50, 10 ]

# 여러개의 테스트 케이스가 주어지므로, 각각을 처리합니다.
for test_case in range(1, T + 1):
    print("#" + str(test_case))
    n = int(input())
    
    for m in money:
        print(n // m, end=' ')
        n %= m
    
    print()
        
