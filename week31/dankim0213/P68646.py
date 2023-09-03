# Two pointers
def solution(a):
    result = [False] * len(a)
    min_front, min_rear = float("inf"), float("inf")
    for i in range(len(a)):
        if min_front > a[i]:
            min_front = a[i]
            result[i] = True

        if min_rear > a[-1 - i]:
            min_rear = a[-1 - i]
            result[-1 - i] = True

    return sum(map(int, result))
