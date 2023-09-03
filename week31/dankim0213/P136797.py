from collections import defaultdict
import sys

sys.setrecursionlimit(10**6)

# [num_from][num_to]
W = [
    [1, 7, 6, 7, 5, 4, 5, 3, 2, 3],  # 0
    [7, 1, 2, 4, 2, 3, 5, 4, 5, 6],  # 1
    [6, 2, 1, 2, 3, 2, 3, 5, 4, 5],  # 2
    [7, 4, 2, 1, 5, 3, 2, 6, 5, 4],  # 3
    [5, 2, 3, 5, 1, 2, 4, 2, 3, 5],  # 4
    [4, 3, 2, 3, 2, 1, 2, 3, 2, 3],  # 5
    [5, 5, 3, 2, 4, 2, 1, 5, 3, 2],  # 6
    [3, 4, 5, 6, 2, 3, 5, 1, 2, 4],  # 7
    [2, 5, 4, 5, 3, 2, 3, 2, 1, 2],  # 8
    [3, 6, 5, 4, 5, 3, 2, 4, 2, 1],  # 9
]


def solution(numbers):
    dp = defaultdict(dict)

    def move(i, left, right):
        if i == len(numbers):
            return 0

        if (left, right) in dp[i]:
            return dp[i][(left, right)]

        w = float("inf")
        num = numbers[i]
        if num != right:  # 왼손을 움직이자
            w = min(w, move(i + 1, num, right) + W[left][num])

        if num != left:  # 오른손을 움직이자
            w = min(w, move(i + 1, left, num) + W[right][num])

        dp[i][(left, right)] = w
        return w

    numbers = list(map(int, numbers))
    return move(0, 4, 6)
