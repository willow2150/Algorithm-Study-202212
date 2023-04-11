import sys


answer = None
_input: list[str] = []


def check(lst: list[str]) -> bool:
    L = len(lst)
    for l_idx in range(L):
        at = lst[l_idx]
        to = "".join([lst[l][l_idx] for l in range(L)])
        if at != to:
            return False

    return True


def dfs(depth: int, idx: int, visited: list[bool], possible_ans: list[str]) -> None:
    if answer is not None and depth == L and check(possible_ans):
        answer = possible_ans.copy()

    for i in range(idx, L):
        visited[i] = True
        possible_ans.append(_input[i])
        dfs(depth + 1, i + 1, visited, possible_ans)
        possible_ans.pop()
        visited[i] = False


L, N = list(map(int, sys.stdin.readline().rstrip().split()))

for _ in range(N):
    _input.append(sys.stdin.readline().rstrip())
_input.sort()

if answer is None:
    print("NONE")
else:
    for i in range(len(answer)):
        print(answer[i])
