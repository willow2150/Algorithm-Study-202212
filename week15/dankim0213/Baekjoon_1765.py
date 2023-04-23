import sys

input = sys.stdin.readline


def process(data: list[str]) -> tuple[dict, list]:
    enemies: dict[int, set[int]] = {}
    friends: list[set[int]] = []
    for data_input in data:
        input_type, input_a, input_b = data_input.split()
        a, b = int(input_a), int(input_b)
        if input_type == "E":
            if a in enemies:
                enemy = enemies.get(a)
                if enemy is not None:
                    enemy.add(b)
            else:
                enemies.update({a: {b}})
        else:  # "F"
            is_occurred = False
            for friend in friends:
                if a in friend:
                    friend.add(b)
                    is_occurred = True
                    break
                elif b in friend:
                    friend.add(a)
                    is_occurred = True
                    break
            if not is_occurred:
                friends.append({a, b})

    return enemies, friends


def solve(enemies: dict[int, set[int]], friends: list[set[int]]) -> int:
    # print("in: ", enemies, friends)
    for key in enemies:
        vals = enemies.get(key)
        if vals is None:
            continue

        is_key_occurred = False
        is_occurred = False
        for val in vals:
            if is_key_occurred and is_occurred:
                break
            for friend in friends:
                if not is_occurred and val in friend:
                    # friend.union(vals)
                    friend.update(vals)
                    is_occurred = True
                if not is_key_occurred and key in friend:
                    is_key_occurred = True

        if is_key_occurred and not is_occurred:
            friends.append(vals)
        elif not is_key_occurred and is_occurred:
            friends.append({key})
        elif not is_key_occurred and not is_occurred:
            friends.append({key})
            friends.append(vals)

    # print("out: ", friends)
    return len(friends)


n, m = int(input()), int(input())
data = [input() for _ in range(m)]
enemies, friends = process(data)
print(solve(enemies, friends))
