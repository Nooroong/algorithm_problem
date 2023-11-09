# 조건 1: 2종류의 문자만 등장하는가
# 조건 2: 한 종류당 문자가 두 번 나오는가
# 문자를 정렬한 뒤,
# 조건 1에 따라 1번 요소와 2번 요소가 다른지 비교.
# 조건 2에 따라 0번과 1번, 2번과 3번이 같은지 비교.

T = int(input())
# 여러개의 테스트 케이스가 주어지므로, 각각을 처리합니다.
for test_case in range(1, T + 1):
    S = input()
    
    S = sorted(S)
    if S[1] != S[2] and S[0] == S[1] and S[2] == S[3]:
        print(f"#{test_case} Yes")
    else:
        print(f"#{test_case} No")
   