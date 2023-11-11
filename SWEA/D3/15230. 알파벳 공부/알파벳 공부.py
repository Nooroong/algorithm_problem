T = int(input())
# 여러개의 테스트 케이스가 주어지므로, 각각을 처리합니다.
for test_case in range(1, T + 1):
    string = list(input())
    
    i = 1
    cnt = 0
    findFlag = 0
    n = len(string)
    
    if string[0] == 'a':
        cnt = 1
        while i < n:
            if ord(string[i-1])+1 == ord(string[i]):
                    cnt += 1
            else:
                break

            i += 1
    
    print(f"#{test_case} {cnt}")
