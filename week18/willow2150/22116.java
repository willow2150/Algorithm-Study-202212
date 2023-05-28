import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		final int INF = Integer.MAX_VALUE;
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(reader.readLine());
		int[][] map = new int[N][N];
		int[][] memoization = new int[N][N];

		for (int row = 0; row < N; row++) {
			StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
			for (int col = 0; col < N; col++) {
				map[row][col] = Integer.parseInt(tokenizer.nextToken());
				memoization[row][col] = INF;
			}
		}

		System.out.print(getMinSlope(map, memoization, --N));
	}

	public static int getMinSlope(int[][] map, int[][] memoization, int N) {
		final int[][] DELTAS = new int[][] {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
		Queue<int[]> pq = new PriorityQueue<>((statesA, statesB) -> {
			if (statesA[2] != statesB[2]) {
				return statesA[2] < statesB[2] ? -1 : 1;
			}
			return 0;
		});

		memoization[0][0] = 0;
		pq.add(new int[] {0, 0, 0});

		while (!pq.isEmpty()) {
			int[] states = pq.poll();
			int row = states[0];
			int col = states[1];
			int cost = states[2];
			if (row == N && col == N) {
				break;
			}
			for (int[] delta : DELTAS) {
				int nr = row + delta[0];
				int nc = col + delta[1];
				if (nr < 0 || nc < 0 || N < nr || N < nc) {
					continue;
				}
				int nextCost = Math.max(Math.abs(map[nr][nc] - map[row][col]), cost);
				if (nextCost < memoization[nr][nc]) {
					memoization[nr][nc] = nextCost;
					pq.add(new int[] {nr, nc, nextCost});
				}
			}
		}
		return memoization[N][N];
	}
}
