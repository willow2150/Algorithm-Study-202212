import heapq
import sys

input = sys.stdin.readline


def find_shortest_path(graph, start, end):
    distance = {to: float("inf") for to in graph}
    distance[start] = 0

    pq = [(0, start)]
    heapq.heapify(pq)

    while pq:
        dist, at = heapq.heappop(pq)

        if at == end:
            return dist

        for neighbor, cost in graph[at].items():
            new_dist = dist + cost
            if new_dist < distance[neighbor]:
                distance[neighbor] = new_dist
                heapq.heappush(pq, (new_dist, neighbor))

    return -1


N = int(input())
M = int(input())

graph = {node: {} for node in range(1, N + 1)}
for _ in range(M):
    start, end, cost = list(map(int, input().split()))
    if end in graph[start]:
        graph[start][end] = min(graph[start][end], cost)
    else:
        graph[start][end] = cost

start_city, end_city = map(int, input().split())

result = find_shortest_path(graph, start_city, end_city)
print(result)
