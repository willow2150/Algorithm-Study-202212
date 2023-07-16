from collections import deque


def solve(word):
    dq = deque([word[0]])

    for w in word[1:]:
        el = dq.popleft()
        dq.appendleft(el)
        if w <= el:
            dq.appendleft(w)
        else:
            dq.append(w)

    print("".join(dq))


word = input()
solve(word)
