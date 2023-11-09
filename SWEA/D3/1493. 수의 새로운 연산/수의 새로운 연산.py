# list를 반환
def Num2Point(n):
    point = [0, 0]
    line = 0
    
    # 해당 수가 좌표에서 몇번째 대각선에 속해있는지 구하기
    # 대각선의 시작점은 line*(line-1)/2 + 1 이다.
    # line번째 대각선 시작점 ~ line+1번째 대각선 시작점 안에 속해있는지 본다.
    for i in range(1, 150):
        if i * (i-1) <= 2*(n-1) and 2*(n-1) < i * (i+1):
            line = i
            break
    
    startNum = line * (line-1) / 2 + 1 # 찾아낸 대각선의 시작점
    
    # 시작점에서 얼마나 떨어져있는지 계산하여 좌표를 알아낸다.
    point[0] = 1 + (n-startNum)
    point[1] = line - (n-startNum)
    
    return point
        
def Point2Num(x, y):
    line = x+y-1 # 점이 몇번째 대각선에 속해있는지 구하기
    startNum = line * (line-1) / 2 + 1 # 해당 대각선의 첫 숫자(가장 작은 수)
    
    return int(startNum + x - 1)

T = int(input())
# 여러개의 테스트 케이스가 주어지므로, 각각을 처리합니다.
for test_case in range(1, T + 1):
    p, q = map(int, input().split())
    
    # 두 수를 좌표로 변환
    p = Num2Point(p)
    q = Num2Point(q)

    # 두 좌표에 대해 덧셈 연산
    rtn = [p[i] + q[i] for i in range(len(p))]
    
    # 새로 구해진 좌표를 수로 변환
    rtn = Point2Num(rtn[0], rtn[1])
    
    print(f"#{test_case} {rtn}")
