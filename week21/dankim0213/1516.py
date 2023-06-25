from collections import deque


def read_input():
    n = int(input())  # Number of buildings
    building_times = [0] * (n + 1)  # List to store the time required for each building
    graph = [
        [] for _ in range(n + 1)
    ]  # Graph to represent the dependencies between buildings
    indegree = [0] * (n + 1)  # List to store the indegree of each building

    for i in range(1, n + 1):
        info = list(map(int, input().split()))
        building_times[i] = info[0]  # Time required for building i

        for x in info[1:-1]:
            graph[x].append(i)  # Add the dependency from building x to building i
            indegree[i] += 1  # Increment the indegree of building i

    return n, building_times, graph, indegree


def perform_topological_sorting(n, building_times, graph, indegree):
    queue = deque()

    earliest_completion_time = [0] * (
        n + 1
    )  # List to store the earliest completion time for each building

    # Find the initial buildings with indegree 0 and add them to the queue
    for i in range(1, n + 1):
        if indegree[i] == 0:
            earliest_completion_time[i] += building_times[i]
            queue.append(i)

    while queue:
        building = queue.popleft()

        for next_building in graph[building]:
            indegree[next_building] -= 1  # Decrement the indegree of the next building
            earliest_completion_time[next_building] = max(
                earliest_completion_time[next_building],
                earliest_completion_time[building] + building_times[next_building],
            )

            if indegree[next_building] == 0:
                queue.append(next_building)

    return earliest_completion_time


def print_earliest_completion_time(earliest_completion_time):
    for i in range(1, len(earliest_completion_time)):
        print(earliest_completion_time[i])


n, building_times, graph, indegree = read_input()
earliest_completion_time = perform_topological_sorting(
    n, building_times, graph, indegree
)
print_earliest_completion_time(earliest_completion_time)
