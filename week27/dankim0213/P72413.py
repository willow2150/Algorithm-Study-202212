from collections import defaultdict
import heapq

# It takes O(n) + O(n*logn)  * 3
# init a graph + search for dest (where to go together + A + B)


def solution(n, s, a, b, fares):
    graph = defaultdict(list)
    for fare in fares:
        start, end, f = fare
        graph[start].append((end, f))
        graph[end].append((start, f))

    min_val = float("inf")
    for together in range(1, n + 1):
        total = (
            dijkstra(graph, s, together, n)
            + dijkstra(graph, together, a, n)
            + dijkstra(graph, together, b, n)
        )
        min_val = min(min_val, total)

    return min_val


def dijkstra(graph, src, to, n):
    # They do not take a bus together
    if src == to:
        return 0

    dist = [float("inf")] * (n + 1)
    dist[src] = 0
    pq = [(0, src)]

    while pq:
        val, at = heapq.heappop(pq)
        if at == to:
            return dist[to]

        for node in graph[at]:
            adj, fare = node

            if dist[adj] > dist[at] + fare:
                dist[adj] = dist[at] + fare
                heapq.heappush(pq, (dist[adj], adj))

    return float("inf")


"""
n, s, a, b = 6, 4, 6, 2
fares = [
    [4, 1, 10],
    [3, 5, 24],
    [5, 6, 2],
    [3, 1, 41],
    [5, 1, 24],
    [4, 6, 50],
    [2, 4, 66],
    [2, 3, 22],
    [1, 6, 25],
]
result = solution(n, s, a, b, fares)
assert result == 82, f"expected 82, but {result}"


n, s, a, b = 7, 3, 4, 1
fares = [[5, 7, 9], [4, 6, 4], [3, 6, 1], [3, 2, 3], [2, 1, 6]]
result = solution(n, s, a, b, fares)
assert result == 14, f"expected 14, but {result}"

"""

n, s, a, b = 6, 4, 5, 6
fares = [
    [2, 6, 6],
    [6, 3, 7],
    [4, 6, 7],
    [6, 5, 11],
    [2, 5, 12],
    [5, 3, 20],
    [2, 4, 8],
    [4, 3, 9],
]
result = solution(n, s, a, b, fares)
assert result == 18, f"expected 18, but {result}"
