from sys import stdin


def recursion(size):
    if size == 2:
        return 0
    size -= 1
    max_value = 0

    for index in range(1, last_index):
        if weight_list[index] == 0:
            continue
        left_index = index - 1
        right_index = index + 1
        while 0 < left_index and weight_list[left_index] == 0:
            left_index -= 1
        while right_index < last_index and weight_list[right_index] == 0:
            right_index += 1
        temp = weight_list[index]
        weight_list[index] = 0
        max_value = max(
            max_value,
            weight_list[left_index] * weight_list[right_index] + recursion(size)
        )
        weight_list[index] = temp
    return max_value


N = int(stdin.readline())
weight_list = list(map(int, stdin.readline().split()))
last_index = len(weight_list) - 1
print(recursion(len(weight_list)))
