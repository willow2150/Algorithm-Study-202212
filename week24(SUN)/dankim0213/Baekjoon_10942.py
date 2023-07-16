import sys

input = sys.stdin.readline


def is_palindrome(n, numbers):
    # Create a 2D array to store the results of subproblems
    dp = [[False] * n for _ in range(n)]

    # Initialize the base cases where each individual number is a palindrome
    for i in range(n):
        dp[i][i] = True

    # Check for palindromes of length 2 and above
    for length in range(2, n + 1):
        for start in range(n - length + 1):
            end = start + length - 1

            # Check if the current substring is a palindrome
            if length == 2:
                dp[start][end] = numbers[start] == numbers[end]
            else:
                dp[start][end] = (numbers[start] == numbers[end]) and dp[start + 1][
                    end - 1
                ]

    return dp


# Read the number of elements in the sequence
n = int(input())

# Read the sequence of numbers
numbers = list(map(int, input().split()))

# Read the number of queries
m = int(input())

# Process each query
dp = is_palindrome(n, numbers)
for _ in range(m):
    start, end = map(int, input().split())
    start -= 1  # Adjust the 1-indexed query to 0-indexed array
    end -= 1
    if dp[start][end]:
        print(1)
    else:
        print(0)
