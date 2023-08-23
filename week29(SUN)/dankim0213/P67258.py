# DFS로 시도했는데 시간 초과...
# 다익스트라로 풀어야 함
from collections import defaultdict


def solution(n, paths, gates, summits):
    graph = defaultdict(list)
    for path in paths:
        x, y, w = path
        graph[x].append((y, w))
        graph[y].append((x, w))

    answer = [float("inf"), float("inf")]
    visited = [False] * (n + 1)
    for gate in gates:
        visited[gate] = True
        answer = min(
            answer,
            dfs(graph, visited, gate, float("inf"), gates, summits),
            key=lambda x: (x[1], x[0]),
        )
        visited[gate] = False
    return answer


def dfs(graph, visited, idx, max_intensity, gates, summits):
    if idx in summits:
        return [idx, max_intensity]

    max_val = [float("inf"), float("inf")]
    for node in graph[idx]:
        adj, w = node
        if not visited[adj] and adj not in gates:
            visited[adj] = True
            intensity = w
            if max_intensity != float("inf"):
                intensity = max(intensity, max_intensity)

            max_val = min(
                max_val,
                dfs(graph, visited, adj, intensity, gates, summits),
                key=lambda x: (x[1], x[0]),
            )
            visited[adj] = False

    return max_val
