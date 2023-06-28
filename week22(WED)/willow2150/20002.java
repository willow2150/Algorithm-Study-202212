import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(reader.readLine());
		int[][] prefixSumTable = new int[N + 1][N + 1];

		int maxProfitSum = -1_000;

		for (int row = 1; row <= N; row++) {
			StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
			int[] prefixSumLine = prefixSumTable[row];
			for (int col = 1; col <= N; col++) {
				int profit = Integer.parseInt(tokenizer.nextToken());
				maxProfitSum = Math.max(maxProfitSum, profit);
				prefixSumLine[col] = profit;
			}
		}

		for (int row = 1; row <= N; row++) {
			for (int col = 1; col <= N; col++) {
				prefixSumTable[row][col]
						+= prefixSumTable[row - 1][col]
						+ prefixSumTable[row][col - 1]
						- prefixSumTable[row - 1][col - 1];
			}
		}

		for (int size = 2; size <= N; size++) {
			for (int row = 0; row + size <= N; row++) {
				for (int col = 0; col + size <= N; col++) {
					maxProfitSum = Math.max(
							maxProfitSum,
							prefixSumTable[row + size][col + size]
									- prefixSumTable[row + size][col]
									- prefixSumTable[row][col + size]
									+ prefixSumTable[row][col]
					);
				}
			}
		}

		System.out.print(maxProfitSum);
	}
}
