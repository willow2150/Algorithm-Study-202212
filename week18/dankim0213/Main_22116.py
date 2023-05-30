import heapq


def find_minimum_gradient(grid, N):
    # initialize
    distance = [[float("inf")] * N for _ in range(N)]
    distance[0][0] = 0

    # 4 directions: up, down, left, right
    directions = [(-1, 0), (1, 0), (0, -1), (0, 1)]

    # priority queue which pops the minimum value
    pq = [(0, 0, 0)]
    heapq.heapify(pq)

    while pq:
        dist, row, col = heapq.heappop(pq)

        # return the answer if we have reached the destination
        if row == N - 1 and col == N - 1:
            return dist

        for dr, dc in directions:
            new_row, new_col = row + dr, col + dc

            # if the new spot is within the grid
            if 0 <= new_row < N and 0 <= new_col < N:
                gradient = abs(grid[new_row][new_col] - grid[row][col])
                max_gradient = max(dist, gradient)

                # update the distance if it is smaller than the last val
                if max_gradient < distance[new_row][new_col]:
                    distance[new_row][new_col] = max_gradient
                    heapq.heappush(pq, (max_gradient, new_row, new_col))

    # just in case :)
    return -1


N = int(input())
grid = []
for _ in range(N):
    row = list(map(int, input().split()))
    grid.append(row)

ans = find_minimum_gradient(grid, N)
print(ans)
