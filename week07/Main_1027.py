# 올만에 파이썬으로 해봄
# 이거 모든칸에 대해서 그냥 모든 경우를 다 봐야할 거 같은데??


import math

row, col = list(map(int, input().split()))
data = []
for _ in range(row):
    data.append(input())

res = -1
for x in range(row):
    for y in range(col):
        for i in range(-row, row):
            for j in range(-col, col):
                if i == 0 and j == 0:
                    continue

                temp = []
                n = x
                m = y

                while(0<=n<row) and (0<=m<col):
                    temp.append(data[n][m])

                    v = math.sqrt(int("".join(temp)))

                    if v == int(v):
                        res = max(res, int(v)**2)
                    n += i
                    m += j
print(res)