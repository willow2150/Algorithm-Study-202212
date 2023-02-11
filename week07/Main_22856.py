'''
그니까 각각 심사대가 있는데 거기로 사람을 배정해서 가장 짧게(최소)로 이용하는 시간은 얼마인지 구하는 것

각각 심사대에 따라서 하나씩 배정해야하는데 N,M 범위보니까 일일히 하는건 아닌 것 같음
범위 사이즈보니까 이분탐색이긴하네
어떤걸 기준으로 이분탐색해야되나? ==> 시간

'''
import sys
input = sys.stdin.readline

N, M = list(map(int, input().split()))
time = list(int(input()) for _ in range(N))

start = 0
end = max(time) * M
result = 0

while start <= end:
    mid = (start + end) // 2
    temp = 0
    for t in time:
        temp += mid // t

    if temp >= M:
        end = mid - 1
        result = mid
    else:
        start = mid + 1

print(result)