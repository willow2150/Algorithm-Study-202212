def solution(s):
    n = len(s)
    min_val = n
    for length in range(1, n // 2 + 1):
        min_val = min(min_val, calc(s, length))

    return min_val


def calc(s, length):
    answer = []
    n = len(s)
    comp = s[:length]
    count = 1

    i = length
    while i < n:
        sliced_str = s[i : i + length]
        if comp == sliced_str:
            count += 1
        else:
            if count != 1:  # reset
                answer.append(str(count))
            answer.append(comp)
            count = 1
            comp = sliced_str

        i += length

    if count != 1:  # add the existing count
        answer.append(str(count))
        answer.append(comp)
        i += length

    if i - length < n:
        remaining = s[i - length :]
        answer.append(remaining)

    # print(answer)
    return len("".join(answer))


s = "aabbaccc"
result = 7
sol = solution(s)
assert result == sol, f"expected {result}, but {sol}"


s = "ababcdcdababcdcd"
result = 9
sol = solution(s)
assert result == sol, f"expected {result}, but {sol}"
