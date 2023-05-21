class Solution {
	public int solution(int alp, int cop, int[][] problems) {
		int targetAlp = alp;
		int targetCop = cop;

		for (int[] problem : problems) {
			if (targetAlp < problem[0]) {
				targetAlp = problem[0];
			}
			if (targetCop < problem[1]) {
				targetCop = problem[1];
			}
		}

		int[][] dp = new int[targetAlp + 1][targetCop + 1];

		for (int algorithm = alp; algorithm <= targetAlp; algorithm++) {
			for (int coding = cop; coding <= targetCop; coding++) {
				dp[algorithm][coding] = Integer.MAX_VALUE;
			}
		}

		dp[alp][cop] = 0;

		for (int algorithm = alp; algorithm <= targetAlp; algorithm++) {
			for (int coding = cop; coding <= targetCop; coding++) {
				int currentValue = dp[algorithm][coding];
				if (algorithm < targetAlp) {
					dp[algorithm + 1][coding] = Math.min(
							dp[algorithm + 1][coding],
							currentValue + 1
					);
				}
				if (coding < targetCop) {
					dp[algorithm][coding + 1] = Math.min(
							dp[algorithm][coding + 1],
							currentValue + 1
					);
				}

				for (int[] problem : problems) {
					if (algorithm < problem[0] || coding < problem[1]) {
						continue;
					}
					int nextAlp = Math.min(targetAlp, algorithm + problem[2]);
					int nextCop = Math.min(targetCop, coding + problem[3]);
					dp[nextAlp][nextCop] = Math.min(
							dp[nextAlp][nextCop],
							currentValue + problem[4]
					);
				}
			}
		}

		return dp[targetAlp][targetCop];
	}
}
