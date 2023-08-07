from collections import defaultdict, deque


def solution(n, roads, sources, destination):
    graph = defaultdict(list)
    for road in roads:
        start, end = road
        graph[start].append(end)
        graph[end].append(start)

    # start from destination to sources
    answer = bfs(graph, destination, sources, n)

    return answer


def bfs(graph, src, dests, n):
    visited = [-1] * (n + 1)
    visited[src] = 0
    queue = deque([src])

    while queue:
        at = queue.popleft()

        for adj in graph[at]:
            if visited[adj] == -1:
                visited[adj] = visited[at] + 1
                queue.append(adj)

    return [visited[dest] for dest in dests]


n, roads, sources, destination, result = 3, [[1, 2], [2, 3]], [2, 3], 1, [1, 2]
sol = solution(n, roads, sources, destination)
assert result == sol, f"expected {result}, but {sol}"

n, roads, sources, destination, result = (
    5,
    [[1, 2], [1, 4], [2, 4], [2, 5], [4, 5]],
    [1, 3, 5],
    5,
    [2, -1, 0],
)
sol = solution(n, roads, sources, destination)
assert result == sol, f"expected {result}, but {sol}"
