class Solution {
	public int solution(int[] money) {
		int numOfHouses = money.length;
		int[][] dp = new int[2][numOfHouses];

		dp[0][1] = money[1];
		for (int houseIdx = 2; houseIdx < numOfHouses; houseIdx++) {
			dp[0][houseIdx] = Math.max(
					dp[0][houseIdx - 1],
					dp[0][houseIdx - 2] + money[houseIdx]
			);
		}

		dp[1][0] = dp[1][1] = money[0];
		numOfHouses--;
		for (int houseIdx = 2; houseIdx < numOfHouses; houseIdx++) {
			dp[1][houseIdx] = Math.max(
					dp[1][houseIdx - 1],
					dp[1][houseIdx - 2] + money[houseIdx]
			);
		}
		dp[1][numOfHouses] = dp[1][numOfHouses - 1];

		return Math.max(dp[0][numOfHouses], dp[1][numOfHouses]);
	}
}
