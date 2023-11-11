T = int(input())
# 여러개의 테스트 케이스가 주어지므로, 각각을 처리합니다.
for test_case in range(1, T + 1):
    # 리스트에 각 점수 빈도를 기록
    score = [0] * 101
    
    print("#%d" % int(input()), end=' ')
    students = map(int, input().split())
    
    for s in students:
        score[s] += 1
    
    # 최빈수가 여러 개 일 때에는 가장 큰 점수를 출력해야 하므로  reversed함수를 이용
    score = list(reversed(score))
    print(abs(score.index(max(score))-100)) # 인덱스가 뒤집어졌므로 100을 빼줌
    
    
    
    
    
    
