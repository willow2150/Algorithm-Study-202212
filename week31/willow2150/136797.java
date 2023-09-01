import java.util.Arrays;

class Solution {
    public int solution(String numbers) {
        final int INF = Integer.MAX_VALUE;
        int[][][] dp = new int[numbers.length()][10][10];
        int[][] costTable = getCostTable();
        int numbersLen = numbers.length();
        int minWeightSum = Integer.MAX_VALUE;

        for (int[][] minWeightTable : dp) {
            for (int[] minWeights : minWeightTable) {
                Arrays.fill(minWeights, INF);
            }
        }

        int firstNumber = numbers.charAt(0) - '0';
        if (firstNumber == 4 || firstNumber == 6) {
            dp[0][4][6] = 1;
        } else {
            dp[0][firstNumber][6] = costTable[4][firstNumber];
            dp[0][4][firstNumber] = costTable[6][firstNumber];
        }

        for (int idx = 1; idx < numbersLen; idx++) {
            int number = numbers.charAt(idx) - '0';
            int prevIdx = idx - 1;
            for (int left = 0; left <= 9; left++) {
                for (int right = 0; right <= 9; right++) {
                    if (dp[prevIdx][left][right] == INF) {
                        continue;
                    }
                    if (number != right) {
                        dp[idx][number][right] = Math.min(
                                dp[idx][number][right],
                                dp[prevIdx][left][right] + costTable[left][number]
                        );
                    }
                    if (number != left) {
                        dp[idx][left][number] = Math.min(
                                dp[idx][left][number],
                                dp[prevIdx][left][right] + costTable[right][number]
                        );
                    }
                }
            }
        }

        for (int left = 0; left <= 9; left++) {
            for (int right = 0; right <= 9; right++) {
                minWeightSum = Math.min(minWeightSum, dp[numbersLen - 1][left][right]);
            }
        }

        return minWeightSum;
    }

    public int[][] getCostTable() {
        int[][] points = new int[][] {
                {3, 1}, {0, 0}, {0, 1}, {0, 2}, {1, 0},
                {1, 1}, {1, 2}, {2, 0}, {2, 1}, {2, 2}
        };
        int[][] costTable = new int[10][10];

        for (int numberA = 0; numberA <= 9; numberA++) {
            costTable[numberA][numberA] = 1;
            for (int numberB = numberA + 1; numberB <= 9; numberB++) {
                int rowDifference = Math.abs(points[numberA][0] - points[numberB][0]);
                int colDifference = Math.abs(points[numberA][1] - points[numberB][1]);
                int rowAndColDifference = Math.abs(rowDifference - colDifference);
                if (rowDifference == 0 || colDifference == 0) {
                    costTable[numberA][numberB] = 2 * Math.max(rowDifference, colDifference);
                    costTable[numberB][numberA] = costTable[numberA][numberB];
                    continue;
                }
                if (rowAndColDifference == 0) {
                    costTable[numberA][numberB] = 3 * rowDifference;
                    costTable[numberB][numberA] = costTable[numberA][numberB];
                    continue;
                }
                costTable[numberA][numberB] = 3 + 2 * rowAndColDifference;
                costTable[numberB][numberA] = costTable[numberA][numberB];
            }
        }

        return costTable;
    }
}
