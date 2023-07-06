import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	private static final int INF = Integer.MAX_VALUE;

	public static void main(String[] args) throws Exception {
		final int BLANK = 0;
		final int INACTIVE_VIRUS = 2;

		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
		int N = Integer.parseInt(tokenizer.nextToken());
		int M = Integer.parseInt(tokenizer.nextToken());
		List<int[][]> combinations = new ArrayList<>();
		List<int[]> virusPoints = new ArrayList<>();
		int[][] map = new int[N][N];
		int numOfBlanks = 0;

		for (int row = 0; row < N; row++) {
			String inputLine = reader.readLine();
			for (int col = 0; col < N; col++) {
				int tile = inputLine.charAt(col << 1) - '0';
				map[row][col] = tile;
				if (tile == INACTIVE_VIRUS) {
					virusPoints.add(new int[] {row, col});
				} else if (tile == BLANK) {
					numOfBlanks++;
				}
			}
		}

		findCombinations(combinations, virusPoints, new int[M][], 0, 0);

		int minTime = (numOfBlanks == 0 ? 0 : INF);
		int marking = 3;

		for (int[][] combination : combinations) {
			minTime = Math.min(minTime, bfs(combination, map, N, marking, numOfBlanks));
			for (int[] virusPoint : virusPoints) {
				map[virusPoint[0]][virusPoint[1]] = INACTIVE_VIRUS;
			}
			marking++;
		}

		System.out.print(minTime == INF ? -1 : minTime);
	}

	public static void findCombinations(
			List<int[][]> combinations, List<int[]> elements,
			int[][] selectedElements, int numOfSelected, int startIndex
	) {
		if (numOfSelected == selectedElements.length) {
			combinations.add(selectedElements.clone());
			return;
		}
		for (int index = startIndex; index < elements.size(); index++) {
			selectedElements[numOfSelected] = elements.get(index);
			findCombinations(
					combinations,
					elements,
					selectedElements,
					numOfSelected + 1,
					index + 1
			);
		}
	}

	public static int bfs(
			int[][] combination, int[][] map, int mapSize, int marking, int numOfBlanks
	) {
		final int[][] DELTAS = new int[][] {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
		final int WALL = 1;
		final int INACTIVE_VIRUS = 2;
		Queue<int[]> pointQueue = new ArrayDeque<>();

		for (int[] point : combination) {
			map[point[0]][point[1]] = marking;
			pointQueue.add(point);
		}

		for (int time = 1; !pointQueue.isEmpty(); time++) {
			int size = pointQueue.size();
			for (int i = 0; i < size; i++) {
				int[] point = pointQueue.poll();
				int row = point[0];
				int col = point[1];
				for (int[] delta : DELTAS) {
					int nr = row + delta[0];
					int nc = col + delta[1];
					if (nr < 0 || nc < 0 || nr == mapSize || nc == mapSize) {
						continue;
					}
					if (map[nr][nc] == marking || map[nr][nc] == WALL) {
						continue;
					}
					if (map[nr][nc] != INACTIVE_VIRUS && --numOfBlanks == 0) {
						return time;
					}
					map[nr][nc] = marking;
					pointQueue.add(new int[] {nr, nc});
				}
			}
		}

		return INF;
	}
}
