# (확률 * 급여)의 총합을 구하면 되는듯

T = int(input())
# 여러개의 테스트 케이스가 주어지므로, 각각을 처리합니다.
for test_case in range(1, T + 1):
    salary = 0.0
    n = int(input())
    
    for i in range(n):
        p, x = map(float, input().split())
        salary += p * x
    
    print(f"#{test_case} {salary}")
