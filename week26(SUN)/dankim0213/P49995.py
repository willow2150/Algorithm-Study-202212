def solution(cookie):
    answer = 0
    for i in range(len(cookie) - 1):
        left, right = i, i + 1
        left_sum, right_sum = cookie[i], cookie[i + 1]

        while True:
            if left_sum == right_sum:
                answer = max(answer, left_sum)

            if left_sum <= right_sum and left > 0:
                left -= 1
                left_sum += cookie[left]
            elif left_sum >= right_sum and right < len(cookie) - 1:
                right += 1
                right_sum += cookie[right]
            else:
                break

    return answer


"""
# try1: prefix-sum + binary search
# but failed

def solution(cookie):
    answer = 0
    n = len(cookie)
    prefix_sum = [0] * (n + 1)
    for i in range(1, n + 1):
        prefix_sum[i] = prefix_sum[i - 1] + cookie[i - 1]

    print(prefix_sum)
    if n == 1:
        return 0

    for start in range(1, n):
        for end in range(start + 1, n + 1):
            answer = max(answer, binary_search(prefix_sum, start, end))

    return answer


def binary_search(prefix_sum, at, to):
    print("bs")
    value = 0
    start = at
    end = to
    while start < end:
        mid = (start + end) // 2

        A = prefix_sum[mid] - prefix_sum[at - 1]
        B = prefix_sum[to] - prefix_sum[mid]
        print("start mid end: ", start, mid, end, A, B)
        if A == B:
            value = A
            break

        if A < B:
            start = mid + 1
        else:
            end = mid - 1

    # print("and then: ", start, end, value)
    return value


cookie = [1, 1, 2, 3]
result = solution(cookie)
assert result == 3, f"expected 3, but {result}"

cookie = [1, 1, 3, 3, 8]
result = solution(cookie)
assert result == 8, f"expected 8, but {result}"

"""
