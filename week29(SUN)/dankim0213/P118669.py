# Two-pointer
# 앞으로, 뒤로 재귀적으로 반복하려 했지만 실패함 :(


def solution(gems):
    search_gems = {key: 0 for key in set(gems)}
    for i in range(len(search_gems)):
        search_gems[gems[i]] += 1

    length = len(search_gems)  # target length
    start, end = search(0, 0, gems, search_gems, length)
    # print("answer: ", start + 1, end)
    return [start + 1, end]


def is_valid(search_gems):
    for key in search_gems:
        if search_gems[key] <= 0:
            return False
    return True


def search(src, direction, gems, search_gems, length):
    L = len(gems)  # two-pointer length, total length
    start, end = src, src + length

    if is_valid(search_gems):
        return [src, end]

    if direction == 0:
        for i in range(end, L):
            if search_gems[gems[start]] > 0:
                search_gems[gems[start]] -= 1
            search_gems[gems[i]] += 1
            start += 1

            # print("debug: ", search_gems)
            if is_valid(search_gems):
                return [start, i + 1]

        # print("debug1: ", start, L, search_gems, direction)
        search_gems[gems[start - 1]] += 1
        return search(start - 1, -1, gems, search_gems, length + 1)
    else:
        for i in range(start - 1, -1, -1):
            if search_gems[gems[end - 1]] > 0:
                search_gems[gems[end - 1]] -= 1
            search_gems[gems[i]] += 1
            end -= 1

            # print("debug: ", search_gems)
            if is_valid(search_gems):
                return [i, end]

        # print("debug2: ", start, end, search_gems, direction)
        search_gems[gems[end]] += 1
        return search(start, 0, gems, search_gems, length + 1)
