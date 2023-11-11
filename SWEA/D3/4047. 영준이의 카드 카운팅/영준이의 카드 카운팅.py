T = int(input())
# 여러개의 테스트 케이스가 주어지므로, 각각을 처리합니다.
for test_case in range(1, T + 1):
    own = list(input())
    
    flag = 0
    i = 0
    n = len(own)
    cards = [ [0]*13 for _ in range(4) ]
    kind = 0
	
    print(f"#{test_case}", end=' ')
    
    while i < n:
        # 문자 하나
        if own[i] == 'S': kind = 0
        elif own[i] == 'D': kind = 1
        elif own[i] == 'H': kind = 2
        elif own[i] == 'C': kind = 3
            
        # 숫자 두 개
        cardNum = int(own[i+1])*10 + int(own[i+2]) - 1
        if cards[kind][cardNum] == 0:
            cards[kind][cardNum] = 1
        else:
            print("ERROR")
            flag = 1
            break
            
        i += 3
    
    if flag != 1:
        for i in range(4):
            print(13-sum(cards[i]), end=' ')
        print()
    
        