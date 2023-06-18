import sys

sys.setrecursionlimit(10**6)
INF = float("inf")


def get_energy(move, to):
    if move == 0:
        return 2
    elif move == to:
        return 1
    elif abs(move - to) == 2:
        return 4
    else:
        return 3


def solve(steps, i, left, right, dp):
    if i == len(steps) - 1:
        return 0

    if dp[i][left][right] != -1:
        return dp[i][left][right]

    move = steps[i]
    min_energy = INF

    if left == move:
        min_energy = min(
            min_energy, solve(steps, i + 1, move, right, dp) + get_energy(left, move)
        )
    elif right == move:
        min_energy = min(
            min_energy, solve(steps, i + 1, left, move, dp) + get_energy(right, move)
        )
    else:
        min_energy = min(
            min_energy, solve(steps, i + 1, move, right, dp) + get_energy(left, move)
        )
        min_energy = min(
            min_energy, solve(steps, i + 1, left, move, dp) + get_energy(right, move)
        )

    dp[i][left][right] = min_energy
    return min_energy


def solution():
    steps = list(map(int, input().split()))
    dp = [[[-1] * 5 for _ in range(5)] for _ in range(len(steps))]
    result = solve(steps, 0, 0, 0, dp)
    print(result)


solution()
