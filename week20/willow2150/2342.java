import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws Exception {
		final int[][] DIRECTION_BIT_SUM_TABLE = new int[][] {
				{2, 3, 5, 9, 16},
				{3, 6, 10, 18},
				{5, 6, 12, 20},
				{9, 10, 12, 24},
				{17, 18, 20, 24}
		};
		final int[][] MOVING_COST = new int[][] {
				{1, 2, 2, 2, 2},
				{3, 1, 3, 4, 3},
				{3, 3, 1, 3, 4},
				{3, 4, 3, 1, 3},
				{3, 3, 4, 3, 1},
		};

		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String inputStr = reader.readLine();

		int numOfCommands = inputStr.length() >> 1;
		int minUsedForceSum = Integer.MAX_VALUE;

		int[][] dp = new int[numOfCommands + 1][25];
		int prevDir = 0;
		int currentDir;

		if (1 <= numOfCommands) {
			currentDir = inputStr.charAt(0) - '0';
			dp[1][1 | (1 << currentDir)] = 2;
			prevDir = currentDir;
		} else {
			minUsedForceSum = 0;
		}

		for (int cmdIdx = 1; cmdIdx < numOfCommands; cmdIdx++) {
			currentDir = inputStr.charAt(cmdIdx << 1) - '0';
			for (int dirBitSum : DIRECTION_BIT_SUM_TABLE[prevDir]) {
				if (dp[cmdIdx][dirBitSum] == 0) {
					continue;
				}
				for (int dir = 0; dir <= 4; dir++) {
					if ((dirBitSum & (1 << dir)) != 0) {
						int nextDirBitSum = dirBitSum ^ (1 << dir) | (1 << currentDir);
						int usedForce = dp[cmdIdx][dirBitSum] + MOVING_COST[dir][currentDir];
						int nextCmdIdx = cmdIdx + 1;
						if (dp[nextCmdIdx][nextDirBitSum] == 0) {
							dp[nextCmdIdx][nextDirBitSum] = usedForce;
						} else {
							dp[nextCmdIdx][nextDirBitSum] = Math.min(
									dp[nextCmdIdx][nextDirBitSum],
									usedForce
							);
						}
					}
				}
			}
			prevDir = currentDir;
		}

		for (int usedForce : dp[numOfCommands]) {
			if (usedForce != 0) {
				minUsedForceSum = Math.min(minUsedForceSum, usedForce);
			}
		}

		System.out.print(minUsedForceSum);
	}
}
