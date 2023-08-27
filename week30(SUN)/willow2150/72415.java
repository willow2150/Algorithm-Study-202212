import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

class Solution {
    private final int VALID_MAX_CARD_NUMBER = 6;
    private final int BLANK = 0;

    public int solution(int[][] board, int r, int c) {
        int[][][] points = new int[VALID_MAX_CARD_NUMBER + 1][2][];
        int bitSum = 0;

        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board.length; col++) {
                int cardNumber = board[row][col];
                if (0 < cardNumber) {
                    bitSum |= (1 << cardNumber);
                    if (points[cardNumber][0] == null) {
                        points[cardNumber][0] = new int[] {row, col};
                        continue;
                    }
                    points[cardNumber][1] = new int[] {row, col};
                }
            }
        }

        return findNumOfMinTotalKeystrokes(board, r, c, points, bitSum);
    }

    public int findNumOfMinTotalKeystrokes(
            int[][] board, int r, int c, int[][][] points, int bitSum
    ) {
        if (bitSum == 0) {
            return 0;
        }

        int numOfMinTotalKeystrokes = Integer.MAX_VALUE;

        for (int cardNumber = 1; cardNumber <= VALID_MAX_CARD_NUMBER; cardNumber++) {
            if (((1 << cardNumber) & bitSum) == 0) {
                continue;
            }
            int rowA = points[cardNumber][0][0];
            int colA = points[cardNumber][0][1];
            int rowB = points[cardNumber][1][0];
            int colB = points[cardNumber][1][1];
            int nextBitSum = bitSum ^ (1 << cardNumber);

            int numOfMinKeystrokes = 0;
            numOfMinKeystrokes += calcNumOfMinKeystrokes(board, r, c, rowA, colA);
            board[rowA][colA] = BLANK;
            numOfMinKeystrokes += calcNumOfMinKeystrokes(board, rowA, colA, rowB, colB);
            board[rowB][colB] = BLANK;

            numOfMinTotalKeystrokes = Math.min(
                    numOfMinTotalKeystrokes,
                    2 + numOfMinKeystrokes + findNumOfMinTotalKeystrokes(
                            board, rowB, colB, points, nextBitSum
                    )
            );

            board[rowA][colA] = cardNumber;
            board[rowB][colB] = cardNumber;

            numOfMinKeystrokes = 0;
            numOfMinKeystrokes += calcNumOfMinKeystrokes(board, r, c, rowB, colB);
            board[rowB][colB] = BLANK;
            numOfMinKeystrokes += calcNumOfMinKeystrokes(board, rowB, colB, rowA, colA);
            board[rowA][colA] = BLANK;

            numOfMinTotalKeystrokes = Math.min(
                    numOfMinTotalKeystrokes,
                    2 + numOfMinKeystrokes + findNumOfMinTotalKeystrokes(
                            board, rowA, colA, points, nextBitSum
                    )
            );

            board[rowA][colA] = cardNumber;
            board[rowB][colB] = cardNumber;
        }

        return numOfMinTotalKeystrokes;
    }

    public int calcNumOfMinKeystrokes(
            int[][] board, int depRow, int depCol, int arrRow, int arrCol
    ) {
        Queue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] stateA, int[] stateB) {
                if (stateA[0] == stateB[0]) {
                    return 0;
                }
                return stateA[0] < stateB[0] ? -1 : 1;
            }
        });

        pq.add(new int[] {0, depRow, depCol});
        while (!pq.isEmpty()) {
            int[] state = pq.poll();
            int numOfKeystrokes = state[0];
            int row = state[1];
            int col = state[2];
            if (row == arrRow && col == arrCol) {
                pq.clear();
                return numOfKeystrokes;
            }

            if (0 < row) {
                int nr = row - 1;
                while (0 < nr && board[nr][col] == BLANK) {
                    nr--;
                }
                pq.add(new int[] {numOfKeystrokes + 1, nr, col});
                pq.add(new int[] {numOfKeystrokes + 1, row - 1, col});
            }
            if (0 < col) {
                int nc = col - 1;
                while (0 < nc && board[row][nc] == BLANK) {
                    nc--;
                }
                pq.add(new int[] {numOfKeystrokes + 1, row, nc});
                pq.add(new int[] {numOfKeystrokes + 1, row, col - 1});
            }
            if (row < 3) {
                int nr = row + 1;
                while (nr < 3 && board[nr][col] == BLANK) {
                    nr++;
                }
                pq.add(new int[] {numOfKeystrokes + 1, nr, col});
                pq.add(new int[] {numOfKeystrokes + 1, row + 1, col});
            }
            if (col < 3) {
                int nc = col + 1;
                while (nc < 3 && board[row][nc] == BLANK) {
                    nc++;
                }
                pq.add(new int[] {numOfKeystrokes + 1, row, nc});
                pq.add(new int[] {numOfKeystrokes + 1, row, col + 1});
            }
        }

        return -1;
    }
}
