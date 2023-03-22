from sys import stdin


def find_magic_square(permutation, selected_idx_set) -> bool:
    def is_magic_square() -> bool:
        for row in range(L):
            for col in range(row + 1, L):
                if magic_square[row][col] == magic_square[col][row]:
                    continue
                return False
        return True

    if len(permutation) == L:
        return is_magic_square()

    for word, idx in words:
        if idx in selected_idx_set:
            continue
        permutation.append(word)
        selected_idx_set.add(idx)
        if find_magic_square(permutation, selected_idx_set):
            return True
        selected_idx_set.remove(idx)
        permutation.pop()
    return False


if __name__ == '__main__':
    L, N = map(int, stdin.readline().split())
    words = [[stdin.readline().rstrip(), idx] for idx in range(N)]
    words.sort()
    magic_square = []
    print("\n".join(magic_square) if find_magic_square(magic_square, set()) else "NONE")
