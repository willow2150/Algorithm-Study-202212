from collections import deque


def solution(p):
    if not p:
        return p

    u, v = separate_u_v(p)
    if check_alright(u):
        return u + solution(v)
    else:
        answer = "("
        answer += solution(v)
        answer += ")"

        for pp in u[1 : len(u) - 1]:
            if pp == "(":
                answer += ")"
            else:
                answer += "("
    return answer


def check_alright(u):
    stack = deque()
    for i in u:
        if i == "(":
            stack.append(i)
        else:
            if not stack:
                return False
            stack.pop()
    return True if not stack else False


def separate_u_v(p):
    num_open, num_close = 0, 0
    for i in range(len(p)):
        if p[i] == "(":
            num_open += 1
        else:
            num_close += 1
        if num_open == num_close:
            return p[: i + 1], p[i + 1 :]  # u, v
