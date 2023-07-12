from collections import deque
import sys

input = sys.stdin.readline


def bfs(graph, starts):
    queue = deque()
    believers = [0] * (N + 1)
    times = [-1] * (N + 1)

    time = 0
    time_connected = []
    for node in starts:
        times[node] = 0
        queue.append((0, node))
        for adj in graph[node]:
            time_connected.append(adj)

    while queue:
        minute, at = queue.popleft()

        if time == minute:
            while time_connected:
                believers[time_connected.pop()] += 1
            time += 1

        for node in graph[at]:
            if times[node] == -1 and believers[node] * 2 >= len(graph[node]):
                queue.append((minute + 1, node))
                times[node] = minute + 1  # check 1
                for adj in graph[node]:
                    time_connected.append(adj)
    return times


N = int(input())
graph = {i: [] for i in range(1, N + 1)}
for i in range(1, N + 1):
    data = list(map(int, input().split()))
    graph[i].extend(data[:-1])

M = int(input())
starts = map(int, input().split())

result = bfs(graph, starts)
print(" ".join(map(str, result[1:])))
