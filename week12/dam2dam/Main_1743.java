import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_1743 {

	static final int FOOD_WASTE = 1;
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};

	static int vertical, horizontal, theNumberOfFoodWaste, row, column, sizeOfFoodWaste, maxFoodWaste;
	static int[][] map;
	static boolean[][] visited;

	public static void main(String[] args) throws Exception {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer;

		stringTokenizer = new StringTokenizer(bufferedReader.readLine());
		vertical = Integer.parseInt(stringTokenizer.nextToken());
		horizontal = Integer.parseInt(stringTokenizer.nextToken());
		theNumberOfFoodWaste = Integer.parseInt(stringTokenizer.nextToken());

		map = new int[vertical + 1][horizontal + 1];
		for (int i = 0; i < theNumberOfFoodWaste; i++) {
			stringTokenizer = new StringTokenizer(bufferedReader.readLine());
			row = Integer.parseInt(stringTokenizer.nextToken());
			column = Integer.parseInt(stringTokenizer.nextToken());
			map[row][column] = FOOD_WASTE;
		}
		maxFoodWaste = 0;
		sizeOfFoodWaste = 0;
		visited = new boolean[vertical + 1][horizontal + 1];
		for (int i = 1; i <= vertical; i++) {
			for (int j = 1; j <= horizontal; j++) {
				if (visited[i][j]) {
					continue;
				}
				if (map[i][j] == FOOD_WASTE) {
					sizeOfFoodWaste = searchTheBiggestFoodWaste(i, j);
					if (maxFoodWaste < sizeOfFoodWaste) {
						maxFoodWaste = sizeOfFoodWaste;
					}
				}
			}
		}
		System.out.println(maxFoodWaste);
		bufferedReader.close();
	}

	private static int searchTheBiggestFoodWaste(int row, int column) {
		int sizeOfFoodWaste = 0;
		Queue<Point> queue = new ArrayDeque<>();
		queue.offer(new Point(row, column, ++sizeOfFoodWaste));
		visited[row][column] = true;

		while (!queue.isEmpty()) {
			Point currentPoint = queue.poll();
			for (int d = 0; d < 4; d++) {
				int nr = currentPoint.row + dr[d];
				int nc = currentPoint.column + dc[d];
				if (nr <= 0 || nr > vertical || nc <= 0 || nc > horizontal
					|| map[nr][nc] != FOOD_WASTE || visited[nr][nc]) {
					continue;
				}
				queue.offer(new Point(nr, nc, ++sizeOfFoodWaste));
				visited[nr][nc] = true;
			}
		}
		return sizeOfFoodWaste;
	}

	static class Point {
		int row, column, size;

		public Point(int row, int column, int size) {
			this.row = row;
			this.column = column;
			this.size = size;
		}

	}

}
