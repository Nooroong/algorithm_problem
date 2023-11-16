def dfs(edge, depth, v, visited):
    global maxLen
	
    if visited[v] == 1:
        return
    
    if depth > maxLen: maxLen = depth

    visited[v] = 1

    for i in edge[v]:
        dfs(edge, depth + 1, i, visited)
    
    # 더이상 방문할 노드가 없다면 되돌아와야 한다!
    visited[v] = 0


T = int(input())
# 여러개의 테스트 케이스가 주어지므로, 각각을 처리합니다.
for test_case in range(1, T + 1):
    maxLen = 0  # 최장 경로의 길이
    n, m = map(int, input().split())  # 정점은 n번까지, 간선의 개수는 m개

    # 정점의 개수만큼 리스트를 생성
    # n+1인 이유: 계산 편하게 하려고
    # 리스트 안에는 연결된 정점 번호가 들어간다.
    edge = [[] for _ in range(n + 1)]

    for _ in range(m):
        v1, v2 = map(int, input().split())
        # 무방향 그래프
        edge[v1].append(v2)
        edge[v2].append(v1)

    for i in range(1, (n+1)):
        visited = [0] * (n+1) # 조건 중 '경로에는 같은 정점의 번호가 2번 이상 등장할 수 없으며'
        dfs(edge, 1, i, visited)

    print(f"#{test_case} {maxLen}")
