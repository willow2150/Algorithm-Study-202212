import sys

sys.setrecursionlimit(10**6)
input = sys.stdin.readline


def find_parent(parent, x):
    if parent[x] != x:
        parent[x] = find_parent(parent, parent[x])
    return parent[x]


# Read input values
n, m = map(int, input().split())
grid = [input() for _ in range(n)]

parent = [i for i in range(n * m)]  # Initialize the parent array

# Perform union operations for adjacent cells
for i in range(n):
    for j in range(m):
        cell = i * m + j
        next_cell = None

        if grid[i][j] == "U":
            next_cell = (i - 1) * m + j
        elif grid[i][j] == "D":
            next_cell = (i + 1) * m + j
        elif grid[i][j] == "L":
            next_cell = i * m + (j - 1)
        elif grid[i][j] == "R":
            next_cell = i * m + (j + 1)

        if next_cell is not None:
            parent_cell = find_parent(parent, cell)
            parent_next_cell = find_parent(parent, next_cell)

            if parent_cell != parent_next_cell:
                parent[parent_next_cell] = parent_cell

# Count the number of distinct groups
group_count = len(set(find_parent(parent, i) for i in range(n * m)))

# Print the result
print(group_count)
