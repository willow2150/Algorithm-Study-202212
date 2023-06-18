import sys

input = sys.stdin.readline


def calculate_max_profit(prices):
    n = len(prices)
    max_profit = 0
    max_price = 0

    for i in range(n - 1, -1, -1):
        if prices[i] > max_price:
            max_price = prices[i]
        else:
            max_profit += max_price - prices[i]

    return max_profit


def solution():
    t = int(input())

    for _ in range(t):
        n = int(input())
        prices = list(map(int, input().split()))
        max_profit = calculate_max_profit(prices)
        print(max_profit)


solution()
