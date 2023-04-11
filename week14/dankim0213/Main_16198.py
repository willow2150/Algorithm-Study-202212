import sys
input = sys.stdin.readline

def dfs(lst: list[int], ans: int) -> int:
    if len(lst) == 2:
        return ans
    
    _max: int = 0
    for i in range(1, len(lst)-1):
        newList = lst.copy()
        newList.pop(i);
        _max = max(_max, dfs(newList, ans + lst[i-1]*lst[i+1]))
    return _max

n, lst = int(input()), list(map(int, input().split(" ")))
print(dfs(lst, 0))