T = 10
# 여러개의 테스트 케이스가 주어지므로, 각각을 처리합니다.
for test_case in range(1, T + 1):
    dump = int(input())
    box = list(map(int, input().split()))
    
    while dump > 0:
        maxIdx = box.index(max(box))
        minIdx = box.index(min(box))
        box[maxIdx] -= 1
        box[minIdx] += 1
        
        dump -= 1
        
    print(f"#{test_case} {max(box) - min(box)}")
    
    
    