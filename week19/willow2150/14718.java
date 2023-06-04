import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
		int N = Integer.parseInt(tokenizer.nextToken());
		int K = Integer.parseInt(tokenizer.nextToken());
		int[][] statTable = new int[N][];
		int minStatSum = Integer.MAX_VALUE;

		for (int n = 0; n < N; n++) {
			tokenizer = new StringTokenizer(reader.readLine());
			int strStat = Integer.parseInt(tokenizer.nextToken());
			int agiStat = Integer.parseInt(tokenizer.nextToken());
			int intStat = Integer.parseInt(tokenizer.nextToken());
			statTable[n] = new int[] {strStat, agiStat, intStat};
		}

		Arrays.sort(statTable, (statA, statB) -> {
			if (statA[0] == statB[0]) {
				return 0;
			}
			return statA[0] < statB[0] ? -1 : 1;
		});

		for (int agiIdx = 0; agiIdx < N; agiIdx++) {
			int agiStat = statTable[agiIdx][1];
			for (int intIdx = 0; intIdx < N; intIdx++) {
				int intStat = statTable[intIdx][2];
				int numOfWins = 0;
				for (int strIdx = 0; strIdx < N; strIdx++) {
					int[] statArray = statTable[strIdx];
					if (statArray[1] <= agiStat && statArray[2] <= intStat) {
						numOfWins++;
						if (numOfWins == K) {
							minStatSum = Math.min(
									minStatSum,
									statArray[0] + agiStat + intStat
							);
							break;
						}
					}
				}
			}
		}
		System.out.print(minStatSum);
	}
}
