from collections import deque


def solution(board):
    n = len(board)

    dx = [-1, 1, 0, 0]
    dy = [0, 0, -1, 1]
    queue = deque([(0, 1, 0, 0), (0, 3, 0, 0)])  # (cost, dir, x, y)
    visited = [[[False] * 4 for _ in range(n)] for _ in range(n)]
    visited[0][0][0] = True
    visited[0][0][1] = True
    visited[0][0][2] = True
    visited[0][0][3] = True

    answer = float("inf")
    while queue:
        cost, direction, x, y = queue.popleft()

        if x == n - 1 and y == n - 1:
            answer = min(answer, cost)

        for i in range(4):
            nx, ny = x + dx[i], y + dy[i]
            if 0 <= nx < n and 0 <= ny < n and board[nx][ny] != 1:
                w = 100 if direction == i else 600
                if not visited[nx][ny][i] or cost + w <= board[nx][ny]:
                    visited[nx][ny][i] = True
                    queue.append((cost + w, i, nx, ny))
                    board[nx][ny] = cost + w

    return answer
