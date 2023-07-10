import sys

sys.setrecursionlimit(10**6)
input = sys.stdin.readline


def dfs(graph, visited, v, parent):
    visited[v] = True
    for neighbor in graph[v]:
        if not visited[neighbor]:
            if dfs(graph, visited, neighbor, v):
                return True
        elif parent != neighbor:
            return True
    return False


def count_trees(graph, n):
    visited = [False] * (n + 1)
    count = 0
    for v in range(1, n + 1):
        if not visited[v]:
            if not dfs(graph, visited, v, -1):
                count += 1
    return count


def solve():
    case = 1
    while True:
        n, m = map(int, input().split())
        if n == 0 and m == 0:
            break

        graph = [[] for _ in range(n + 1)]
        for _ in range(m):
            u, v = map(int, input().split())
            graph[u].append(v)
            graph[v].append(u)

        num_trees = count_trees(graph, n)

        if num_trees == 0:
            print(f"Case {case}: No trees.")
        elif num_trees == 1:
            print(f"Case {case}: There is one tree.")
        else:
            print(f"Case {case}: A forest of {num_trees} trees.")

        case += 1


solve()
