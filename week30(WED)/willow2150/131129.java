class Solution {
    final int BULL = 50;
    // final int SINGLE = 1;
    final int DOUBLE = 2;
    final int TRIPLE = 3;
    final int MIN_NUMBER = 1;
    final int MAX_NUMBER = 20;

    public int[] solution(int target) {
        int[][] dp = new int[target + 1][2];

        for (int[] array : dp) {
            array[0] = array[1] = Integer.MAX_VALUE;
        }

        dp[0][0] = dp[0][1] = 0;

        for (int score = 1; score <= target; score++) {
            if (BULL <= score) {
                setIfBetterCase(dp, score, BULL, true);
            }
            for (int number = MIN_NUMBER; number <= MAX_NUMBER; number++) {
                if (number <= score) {
                    setIfBetterCase(dp, score, number, true);
                }
                if (number * DOUBLE <= score) {
                    setIfBetterCase(dp, score, number * DOUBLE, false);
                }
                if (number * TRIPLE <= score) {
                    setIfBetterCase(dp, score, number * TRIPLE, false);
                }
            }
        }

        return dp[target];
    }

    public void setIfBetterCase(
            int[][] dp, int score, int thisScore, boolean isBullOrSingle
    ) {
        if (dp[score - thisScore][0] + 1 < dp[score][0]) {
            dp[score][0] = dp[score - thisScore][0] + 1;
            dp[score][1] = dp[score - thisScore][1] + (isBullOrSingle ? 1 : 0);
            return;
        }
        if (dp[score - thisScore][0] + 1 == dp[score][0]) {
            dp[score][1] = Math.max(
                    dp[score][1],
                    dp[score - thisScore][1] + (isBullOrSingle ? 1 : 0)
            );
        }
    }
}
