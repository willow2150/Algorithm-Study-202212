import java.util.Arrays;

public class 파괴되지않은건물 {
    // 구현으로 풀면 효율성 통과 못함.
    // skill 에서 일일히 더해주는 과정을 O(1)로 감소시켜야 할듯

    public static void main(String[] args) {
//        int[][] board = {
//                {5, 5, 5, 5, 5},
//                {5, 5, 5, 5, 5},
//                {5, 5, 5, 5, 5},
//                {5, 5, 5, 5, 5}
//        };
//
//        int[][] skill = {
//                {1, 0, 0, 3, 4, 4},
//                {1, 2, 0, 2, 3, 2},
//                {2, 1, 0, 3, 1, 2},
//                {1, 0, 1, 3, 3, 1}
//        };

        int[][] board = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };

        int[][] skill = {
                {1, 1, 1, 2, 2, 4},
                {1, 0, 0, 1, 1, 2},
                {2, 2, 0, 2, 0, 100}
        };

        System.out.println(solution(board, skill));
    }

    public static int solution(int[][] board, int[][] skill) {
        countDamage(board, attackAndRecover(board, skill));

        return countSurviveBuildings(board);
    }

    public static void countDamage(final int[][] board, final int[][] prefixSum) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                board[i][j] = board[i][j] + prefixSum[i][j];
            }
        }

    }

    public static int[][] attackAndRecover(final int[][] board, final int[][] skill) {
        int[][] prefixSum = new int[board.length + 1][board[0].length + 1];

        for (int i = 0; i < skill.length; i++) {
            int type = skill[i][0];
            int row1 = skill[i][1];
            int col1 = skill[i][2];
            int row2 = skill[i][3];
            int col2 = skill[i][4];
            int degree = skill[i][5];

            if (type == 1) {
                prefixSum[row1][col1] -= degree;
                prefixSum[row1][col2 + 1] += degree;
                prefixSum[row2 + 1][col1] += degree;
                prefixSum[row2 + 1][col2 + 1] -= degree;
            } else {
                prefixSum[row1][col1] += degree;
                prefixSum[row1][col2 + 1] -= degree;
                prefixSum[row2 + 1][col1] -= degree;
                prefixSum[row2 + 1][col2 + 1] += degree;
            }
        }

        for (int i = 1; i < prefixSum.length; i++) {
            for (int j = 0; j < prefixSum[0].length; j++) {
                prefixSum[i][j] = prefixSum[i - 1][j] + prefixSum[i][j];
            }
        }

        for (int i = 0; i < prefixSum.length; i++) {
            for (int j = 1; j < prefixSum[0].length; j++) {
                prefixSum[i][j] = prefixSum[i][j - 1] + prefixSum[i][j];
            }
        }

        return prefixSum;
    }

    public static int countSurviveBuildings(final int[][] board) {
        int answer = 0;

        for (final int[] boardLine : board) {
            for (int j = 0; j < board[0].length; j++) {
                if (boardLine[j] <= 0) {
                    continue;
                }

                answer++;
            }
        }

        return answer;
    }

}
