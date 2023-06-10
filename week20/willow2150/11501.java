import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		final int MAX_N = 1_000_000;
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder output = new StringBuilder();

		long[] stocks = new long[MAX_N];

		int T = Integer.parseInt(reader.readLine());
		for (int tC = 1; tC <= T; tC++) {
			int N = Integer.parseInt(reader.readLine());
			StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
			long totalProfit = 0L;
			long profit = 0L;
			
			for (int day = 0; day < N; day++) {
				stocks[day] = Long.parseLong(tokenizer.nextToken());
			}
			for (int day = N - 1; 0 <= day; day--) {
				if (profit <= stocks[day]) {
					profit = stocks[day];
				}
				totalProfit += profit - stocks[day];
			}
			output.append(totalProfit).append('\n');
		}
		System.out.print(output);
	}
}
