import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		final int ABOVE = 0;
		final int RIGHT = 1;
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
		int N = Integer.parseInt(tokenizer.nextToken());
		int M = Integer.parseInt(tokenizer.nextToken());
		int K = Integer.parseInt(reader.readLine());

		int nPlusOne = N + 1;
		boolean[][] isUnderConstruction = new boolean[(M + 1) * nPlusOne][2];
		long[][] map = new long[M + 1][nPlusOne];

		for (int i = 0; i < K; i++) {
			tokenizer = new StringTokenizer(reader.readLine());
			int c1 = Integer.parseInt(tokenizer.nextToken());
			int r1 = M - Integer.parseInt(tokenizer.nextToken());
			int c2 = Integer.parseInt(tokenizer.nextToken());
			int r2 = M - Integer.parseInt(tokenizer.nextToken());
			int hashCodeA = r1 * nPlusOne + c1;
			int hashCodeB = r2 * nPlusOne + c2;
			if (hashCodeA + 1 == hashCodeB) {
				isUnderConstruction[hashCodeA][RIGHT] = true;
			} else if (hashCodeB + 1 == hashCodeA) {
				isUnderConstruction[hashCodeB][RIGHT] = true;
			} else if (hashCodeA - N - 1 == hashCodeB) {
				isUnderConstruction[hashCodeA][ABOVE] = true;
			} else {
				isUnderConstruction[hashCodeB][ABOVE] = true;
			}
		}

		map[M][0] = 1L;
		for (int row = M; 0 <= row; row--) {
			for (int col = 0; col <= N; col++) {
				int hashCode = row * nPlusOne + col;
				if (col < N && !isUnderConstruction[hashCode][RIGHT]) {
					map[row][col + 1] += map[row][col];
				}
				if (0 < row && !isUnderConstruction[hashCode][ABOVE]) {
					map[row - 1][col] += map[row][col];
				}
			}
		}

		System.out.print(map[0][N]);
	}
}
