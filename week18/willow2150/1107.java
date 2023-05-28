import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(reader.readLine());
		int M = Integer.parseInt(reader.readLine());
		boolean[] brokenButtons = new boolean[10];

		if (0 < M) {
			String inputLine = reader.readLine();
			for (int i = 0; i < M; i++) {
				brokenButtons[inputLine.charAt(i << 1) - '0'] = true;
			}
		}

		System.out.print(findMinNumOfButtonPresses(brokenButtons, N));
	}

	public static int findMinNumOfButtonPresses(boolean[] brokenButtons, int N) {
		int minNumOfButtonPresses = Math.abs(N - 100);
		boolean isSelectable;

		if (!brokenButtons[0]) {
			minNumOfButtonPresses = Math.min(minNumOfButtonPresses, N + 1);
		}

		for (int channel = 1; channel <= 900000; channel++) {
			int temp = channel;
			int numOfDigits = 0;
			isSelectable = true;
			while (0 < temp) {
				if (brokenButtons[temp % 10]) {
					isSelectable = false;
					break;
				}
				temp /= 10;
				numOfDigits++;
			}
			if (isSelectable) {
				minNumOfButtonPresses = Math.min(
						minNumOfButtonPresses, Math.abs(N - channel) + numOfDigits
				);
			}
		}

		return minNumOfButtonPresses;
	}
}
