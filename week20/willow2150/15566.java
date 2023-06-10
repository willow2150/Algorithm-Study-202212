import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder output = new StringBuilder();
		StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
		int N = Integer.parseInt(tokenizer.nextToken());
		int M = Integer.parseInt(tokenizer.nextToken());

		int[][] interestAmountTable = new int[N + 1][];
		for (int frog = 1; frog <= N; frog++) {
			String interestInfo = reader.readLine();
			interestAmountTable[frog] = new int[] {
					0,
					interestInfo.charAt(0) - '0',
					interestInfo.charAt(2) - '0',
					interestInfo.charAt(4) - '0',
					interestInfo.charAt(6) - '0',
			};
		}

		int[] favoriteLotusBitSums = new int[N + 1];
		for (int frog = 1; frog <= N; frog++) {
			tokenizer = new StringTokenizer(reader.readLine());
			favoriteLotusBitSums[frog] |= (1 << Integer.parseInt(tokenizer.nextToken()));
			favoriteLotusBitSums[frog] |= (1 << Integer.parseInt(tokenizer.nextToken()));
		}

		int[][] logSubjectTable = new int[N + 1][N + 1];
		for (int logIdx = 0; logIdx < M; logIdx++) {
			tokenizer = new StringTokenizer(reader.readLine());
			int lotusA = Integer.parseInt(tokenizer.nextToken());
			int lotusB = Integer.parseInt(tokenizer.nextToken());
			int subject = Integer.parseInt(tokenizer.nextToken());
			logSubjectTable[lotusA][lotusB] = logSubjectTable[lotusB][lotusA] = subject;
		}

		int[] placedFrogsInLotuses = new int[N + 1];
		boolean isPossible = placeFrogsOnLotuses(
				interestAmountTable, logSubjectTable,
				favoriteLotusBitSums, placedFrogsInLotuses, 1, N
		);

		if (isPossible) {
			output.append("YES").append('\n');
			for (int lotus = 1; lotus <= N; lotus++) {
				output.append(placedFrogsInLotuses[lotus]).append(' ');
			}
		} else {
			output.append("NO");
		}

		System.out.print(output);
	}

	public static boolean placeFrogsOnLotuses(
			int[][] interestAmountTable, int[][] logSubjectTable,
			int[] favoriteLotusBitSums, int[] placedFrogsInLotuses, int frog, int N
	) {
		if (N < frog) {
			boolean isPossible = true;

			for (int lotusA = 1; lotusA <= N; lotusA++) {
				int frogA = placedFrogsInLotuses[lotusA];
				for (int lotusB = 1; lotusB <= N; lotusB++) {
					int logSubject = logSubjectTable[lotusA][lotusB];
					if (logSubject == 0) {
						continue;
					}
					int frogB = placedFrogsInLotuses[lotusB];
					if (interestAmountTable[frogA][logSubject]
							!= interestAmountTable[frogB][logSubject]
					) {
						isPossible = false;
						break;
					}
				}
				if (!isPossible) {
					break;
				}
			}
			return isPossible;
		}

		int favoriteLotusBitSum = favoriteLotusBitSums[frog];

		while (favoriteLotusBitSum != 0) {
			int favoriteLotusBit = favoriteLotusBitSum & -favoriteLotusBitSum;
			int lotus = 0;
			favoriteLotusBitSum ^= favoriteLotusBit;
			for (; 1 < favoriteLotusBit; favoriteLotusBit >>= 1) {
				lotus++;
			}
			if (placedFrogsInLotuses[lotus] != 0) {
				continue;
			}
			placedFrogsInLotuses[lotus] = frog;
			if (
					placeFrogsOnLotuses(
							interestAmountTable, logSubjectTable,
							favoriteLotusBitSums, placedFrogsInLotuses, frog + 1, N
					)
			) {
				return true;
			}
			placedFrogsInLotuses[lotus] = 0;
		}
		return false;
	}
}
