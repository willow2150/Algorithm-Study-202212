from itertools import product

dy = [-1, 1, 0, 0, 0]
dx = [0, 0, -1, 1, 0]


def solution(clockHands):
    answer = float("inf")
    n = len(clockHands)

    for case in product(range(4), repeat=n):
        grid = [i[:] for i in clockHands]

        for y in range(n):
            rotate(0, y, case[y], grid)

        result = sum(case)

        for x in range(1, n):
            for y in range(n):
                if grid[x - 1][y] != 0:
                    remaining = 4 - grid[x - 1][y]
                    rotate(x, y, remaining, grid)
                    result += remaining

        if sum(grid[n - 1]) != 0:
            continue

        answer = min(answer, result)
    return answer


def rotate(x, y, turn, grid):
    n = len(grid)
    for k in range(5):
        nx, ny = x + dx[k], y + dy[k]
        if 0 <= nx < n and 0 <= ny < n:
            grid[nx][ny] = (grid[nx][ny] + turn) % 4
