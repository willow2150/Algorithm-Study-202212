n, m = map(int, input().split())  # Size of the map
k = int(input())  # Number of rest areas
rest_areas = [
    list(map(int, input().split())) for _ in range(k)
]  # Rest area coordinates

dp = [[0] * (m + 1) for _ in range(n + 1)]  # Dynamic programming table
dp[0][0] = 1  # Initialize the starting point

for i in range(n + 1):
    for j in range(m + 1):
        if i > 0:
            if [i - 1, j, i, j] not in rest_areas and [
                i,
                j,
                i - 1,
                j,
            ] not in rest_areas:
                dp[i][j] += dp[i - 1][j]
        if j > 0:
            if [i, j - 1, i, j] not in rest_areas and [
                i,
                j,
                i,
                j - 1,
            ] not in rest_areas:
                dp[i][j] += dp[i][j - 1]

print(dp[n][m])
