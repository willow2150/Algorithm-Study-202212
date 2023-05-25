import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Baekjoon_22116 {
	// 1. 이분탐색 + bfs 사용.
	// 2. 경사의 최소와 최대값을 각각 시작과 끝으로 잡고 중간값을 mid로 잡아서 mid 값을 기준으로 이분탐색 진행.
	// 3. 해당 경로가 끝까지 갈 수 있는 경로인지 bfs로 탐색을 진행한다.
	// 4. 경로가 끝까지 갈 수 있는 경로면 end값을 줄여 탐색을 다시 진행.
	// 5. 3,4번 작업을 반복한다.

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

		int start = 0;
		int end = 1000000000;
		int mid;
		int result = 0;

		while (start <= end) {
			mid = (start + end) / 2;

			if (findPath(map, mid)) {
				result = mid;
				end = mid - 1;
			} else {
				start = mid + 1;
			}
		}

		System.out.println(result);
	}

	private static boolean findPath(final int[][] map, final int mid) {
		final boolean[][] isVisited = new boolean[map.length][map.length];
		final Queue<Point> priorityQueue = new ArrayDeque<>();

		final int[] dx = {1, 0, -1, 0};
		final int[] dy = {0, 1, 0, -1};

		priorityQueue.add(new Point(0, 0));
		isVisited[0][0] = true;

		while (!priorityQueue.isEmpty()) {
			final Point point = priorityQueue.poll();

			if (point.x == map.length - 1 && point.y == map.length - 1) {
				return true;
			}

			for (int i = 0; i < dx.length; i++) {
				int nextX = point.x + dx[i];
				int nextY = point.y + dy[i];

				if (nextX < 0 || nextY < 0 || nextX >= map.length || nextY >= map.length) {
					continue;
				}

				if (isVisited[nextX][nextY]) {
					continue;
				}

				final int slope = Math.abs(map[point.x][point.y] - map[nextX][nextY]);

				if (slope <= mid) {
					isVisited[nextX][nextY] = true;
					priorityQueue.add(new Point(nextX, nextY));
				}
			}
		}

		return false;
	}
}