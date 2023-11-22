T = int(input())

for test_case in range(1, T+1):
    n = int(input()) # 플레이어 수. 주경이는 n번.
    call = []
    k = 1 # 지목 횟수
    findFlag = 0
    idx = 0 # call 리스트의 인덱스. 현재 사람의 번호 저장.

    # 입력받기
    for _ in range(n):
        call.append(int(input()))
    
    # 사람이 n명 있으므로 n번의 지목 횟수를 채우면
    # 더이상 탐색을 할 필요가 없어진다.
    while k <= n:
        # 현재 사람이 지목한 사람이 주경이가 아니라면
        if call[idx] != n:
            k += 1 # 지목횟수 증가
            idx = call[idx] - 1 # 다음 사람으로 넘어감
        else:
            findFlag = 1 # 주경이가 걸렸음을 의미
            break

    if findFlag == 0:
        print("0")
    else:
        print(k)