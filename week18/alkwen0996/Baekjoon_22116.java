import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Baekjoon_22116 {

	private static int result;

	public static void main(String[] args) throws IOException {
		final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		final int size = Integer.parseInt(bufferedReader.readLine());
		final int[][] map = new int[size][size];

		StringTokenizer stringTokenizer;

		for (int i = 0; i < size; i++) {
			stringTokenizer = new StringTokenizer(bufferedReader.readLine());

			for (int j = 0; j < size; j++) {
				map[i][j] = Integer.parseInt(stringTokenizer.nextToken());
			}
		}

		result = 0;
		findPath(map);
		System.out.println(result);
	}

	private static void findPath(final int[][] map) {
		final boolean[][] isVisited = new boolean[map.length][map.length];
		final Queue<Node> queue = new PriorityQueue<>();

		final int[] dx = {1, 0, -1, 0};
		final int[] dy = {0, 1, 0, -1};

		queue.add(new Node(0, 0, 0));
		isVisited[0][0] = true;

		while (!queue.isEmpty()) {
			final Node node = queue.poll();

			isVisited[node.x][node.y] = true;
			result = Math.max(result, node.slope);

			if (node.x == map.length - 1 && node.y == map.length - 1) {
				return;
			}

			for (int i = 0; i < dx.length; i++) {
				int nextX = node.x + dx[i];
				int nextY = node.y + dy[i];

				if (nextX < 0 || nextY < 0 || nextX >= map.length || nextY >= map.length) {
					continue;
				}

				if (isVisited[nextX][nextY]) {
					continue;
				}

				queue.add(new Node(nextX, nextY, Math.abs(map[node.x][node.y] - map[nextX][nextY])));
			}
		}
	}

	static class Node implements Comparable<Node> {
		private int x;
		private int y;
		private int slope;

		public Node(final int x, final int y, final int slope) {
			this.x = x;
			this.y = y;
			this.slope = slope;
		}

		@Override
		public int compareTo(final Node node) {
			return slope - node.slope;
		}
	}
}