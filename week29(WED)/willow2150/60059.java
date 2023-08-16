class Solution {
    private int M;
    private int N;

    public boolean solution(int[][] key, int[][] lock) {
        M = key.length;
        N = lock.length;

        int centerStartPoint = M - 1;
        int centerBoundaryPoint = M + N - 1;
        int puzzleSize = ((M - 1) << 1) + N;
        int boundary = puzzleSize - M;
        int[][] puzzle = new int[puzzleSize][puzzleSize];
        int[][][] keyCases = getKeyCases(key);

        for (int row = centerStartPoint; row < centerBoundaryPoint; row++) {
            for (int col = centerStartPoint; col < centerBoundaryPoint; col++) {
                puzzle[row][col] = lock[row - centerStartPoint][col - centerStartPoint];
            }
        }

        for (int row = 0; row <= boundary; row++) {
            for (int col = 0; col <= boundary; col++) {
                for (int[][] keyCase : keyCases) {
                    putOrRemoveKey(keyCase, puzzle, row, col);
                    if (isLockOpen(puzzle, centerStartPoint)) {
                        return true;
                    }
                    putOrRemoveKey(keyCase, puzzle, row, col);
                }
            }
        }

        return false;
    }

    public int[][][] getKeyCases(int[][] key) {
        int[][][] keyCases = new int[4][][];
        keyCases[0] = key;
        for (int keyIndex = 1; keyIndex < 4; keyIndex++) {
            keyCases[keyIndex] = getRotatedKey(keyCases[keyIndex - 1]);
        }
        return keyCases;
    }

    public int[][] getRotatedKey(int[][] key) {
        int[][] rotatedKey = new int[M][M];
        int centerStartPoint = M - 1;
        for (int col = 0; col < M; col++) {
            for (int row = centerStartPoint; 0 <= row; row--) {
                rotatedKey[col][centerStartPoint - row] = key[row][col];
            }
        }
        return rotatedKey;
    }

    public void putOrRemoveKey(int[][] key, int[][] puzzle, int startRow, int startCol) {
        int boundaryRow = startRow + M;
        int boundaryCol = startCol + M;
        for (int row = startRow; row < boundaryRow; row++) {
            for (int col = startCol; col < boundaryCol; col++) {
                puzzle[row][col] ^= key[row - startRow][col - startCol];
            }
        }
    }

    public boolean isLockOpen(int[][] puzzle, int startPoint) {
        int boundaryPoint = startPoint + N;
        final int OPEN = 1;
        for (int row = startPoint; row < boundaryPoint; row++) {
            for (int col = startPoint; col < boundaryPoint; col++) {
                if (puzzle[row][col] == OPEN) {
                    continue;
                }
                return false;
            }
        }
        return true;
    }
}
