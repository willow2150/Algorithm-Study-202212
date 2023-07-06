import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		final char UP = 'U';
		final char DOWN = 'D';
		final char LEFT = 'L';
		final char RIGHT = 'R';

		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
		int N = Integer.parseInt(tokenizer.nextToken());
		int K = Integer.parseInt(tokenizer.nextToken());
		String command = reader.readLine();
		long sum = 1L;
		long number = 1L;
		int row = 0;
		int col = 0;

		N--;

		for (int commandIndex = 0; commandIndex < K; commandIndex++) {
			int diagonal = row + col;
			char direction = command.charAt(commandIndex);
			if (direction == UP) {
				if (((diagonal) & 1) == 1) {
					if (diagonal <= N) {
						number -= (row << 1);
					} else {
						number -= ((N - col) << 1) + 1;
					}
				} else {
					if (diagonal <= N) {
						number -= (col << 1) + 1;
					} else {
						number -= ((N - row) << 1) + 2;
					}
				}
				row--;
			} else if (direction == DOWN) {
				if (((diagonal) & 1) == 1) {
					if (diagonal < N) {
						number += (col << 1) + 1;
					} else {
						number += ((N - row) << 1);
					}
				} else {
					if (diagonal < N) {
						number += (row << 1) + 2;
					} else {
						number += ((N - col) << 1) + 1;
					}
				}
				row++;
			} else if (direction == LEFT) {
				if (((diagonal) & 1) == 1) {
					if (diagonal <= N) {
						number -= (row << 1) + 1;
					} else {
						number -= ((N - col) << 1) + 2;
					}
				} else {
					if (diagonal <= N) {
						number -= (col << 1);
					} else {
						number -= ((N - row) << 1) + 1;
					}
				}
				col--;
			} else {
				if (((diagonal) & 1) == 1) {
					if (diagonal < N) {
						number += (col << 1) + 2;
					} else {
						number += ((N - row) << 1) + 1;
					}
				} else {
					if (diagonal < N) {
						number += (row << 1) + 1;
					} else {
						number += ((N - col) << 1);
					}
				}
				col++;
			}
			sum += number;
		}

		System.out.print(sum);
	}
}
