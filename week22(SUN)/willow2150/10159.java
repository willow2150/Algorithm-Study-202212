import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		final int MEASURABLE = 1;
		final int IMMEASURABLE = 0;
		final int HEAVIER_THAN = 1;
		final int LIGHTER_THAN = -1;
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder output = new StringBuilder();
		int N = Integer.parseInt(reader.readLine());
		int M = Integer.parseInt(reader.readLine());
		int[][] isMeasurable = new int[N + 1][N + 1];

		for (int m = 0; m < M; m++) {
			StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
			int thingA = Integer.parseInt(tokenizer.nextToken());
			int thingB = Integer.parseInt(tokenizer.nextToken());
			isMeasurable[thingA][thingB] = HEAVIER_THAN;
			isMeasurable[thingB][thingA] = LIGHTER_THAN;
		}

		for (int k = 1; k <= N; k++) {
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					if (isMeasurable[i][k] * isMeasurable[k][j] == MEASURABLE) {
						isMeasurable[i][j] = isMeasurable[i][k];
					}
				}
			}
		}

		for (int thing = 1; thing <= N; thing++) {
			int count = -2;
			for (int heavierOrLighter : isMeasurable[thing]) {
				if (heavierOrLighter == IMMEASURABLE) {
					count++;
				}
			}
			output.append(count).append('\n');
		}
		System.out.print(output);
	}
}
