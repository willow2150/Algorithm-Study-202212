import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static class Tree {
		Tree another;
		int row;
		int col;
		int age;

		public Tree(Tree another, int row, int col, int age) {
			this.another = another;
			this.row = row;
			this.col = col;
			this.age = age;
		}
	}

	public static void main(String[] args) throws Exception {
		final int[][] DELTAS = new int[][] {
				{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}
		};
		final int DEFAULT_NUTRIENT = 5;
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
		int N = Integer.parseInt(tokenizer.nextToken());
		int M = Integer.parseInt(tokenizer.nextToken());
		int K = Integer.parseInt(tokenizer.nextToken());

		List<Tree> deadTrees = new ArrayList<>();
		Tree[][] map = new Tree[N][N];
		int[][] nutrientTable = new int[N][N];
		int[][] nutrientMap = new int[N][N];
		for (int row = 0; row < N; row++) {
			int[] nutrientTableLine = nutrientTable[row];
			int[] nutrientMapLine = nutrientMap[row];
			tokenizer = new StringTokenizer(reader.readLine());
			for (int col = 0; col < N; col++) {
				nutrientTableLine[col] = Integer.parseInt(tokenizer.nextToken());
				nutrientMapLine[col] = DEFAULT_NUTRIENT;
			}
		}

		for (int i = 0; i < M; i++) {
			tokenizer = new StringTokenizer(reader.readLine());
			int x = Integer.parseInt(tokenizer.nextToken()) - 1;
			int y = Integer.parseInt(tokenizer.nextToken()) - 1;
			int z = Integer.parseInt(tokenizer.nextToken());
			map[x][y] = new Tree(map[x][y], x, y, z);
		}

		while (0 < K--) {
			spendSpring(deadTrees, map, nutrientMap);
			spendSummer(deadTrees, nutrientMap);
			spendFall(map, DELTAS);
			spendWinter(nutrientTable, nutrientMap);
		}

		System.out.print(countNumOfTrees(map));
	}

	public static void spendSpring(List<Tree> deadTrees, Tree[][] map, int[][] nutrientMap) {
		int mapSize = map.length;
		for (int row = 0; row < mapSize; row++) {
			Tree[] mapLine = map[row];
			int[] nutrientMapLine = nutrientMap[row];
			for (int col = 0; col < mapSize; col++) {
				Tree tree = mapLine[col];
				if (tree != null && nutrientMapLine[col] < tree.age) {
					deadTrees.add(tree);
					mapLine[col] = null;
					continue;
				}
				while (tree != null) {
					nutrientMapLine[col] -= tree.age;
					tree.age++;
					if (tree.another != null && nutrientMapLine[col] < tree.another.age) {
						deadTrees.add(tree.another);
						tree.another = null;
					}
					tree = tree.another;
				}
			}
		}
	}

	public static void spendSummer(List<Tree> deadTrees, int[][] nutrientMap) {
		for (Tree deadTree : deadTrees) {
			int row = deadTree.row;
			int col = deadTree.col;
			while (deadTree != null) {
				nutrientMap[row][col] += (deadTree.age >> 1);
				Tree nextDeadTree = deadTree.another;
				deadTree.another = null;
				deadTree = nextDeadTree;
			}
		}
		deadTrees.clear();
	}

	public static void spendFall(Tree[][] map, int[][] DELTAS) {
		int mapSize = map.length;
		for (int row = 0; row < mapSize; row++) {
			Tree[] mapLine = map[row];
			for (int col = 0; col < mapSize; col++) {
				Tree tree = mapLine[col];
				while (tree != null) {
					if (tree.age % 5 == 0) {
						for (int[] delta : DELTAS) {
							int nr = row + delta[0];
							int nc = col + delta[1];
							if (nr < 0 || nc < 0 || nr == mapSize || nc == mapSize) {
								continue;
							}
							map[nr][nc] = new Tree(map[nr][nc], nr, nc, 1);
						}
					}
					tree = tree.another;
				}
			}
		}
	}

	public static void spendWinter(int[][] nutrientTable, int[][] nutrientMap) {
		int mapSize = nutrientMap.length;
		for (int row = 0; row < mapSize; row++) {
			int[] nutrientMapLine = nutrientMap[row];
			int[] nutrientTableLine = nutrientTable[row];
			for (int col = 0; col < mapSize; col++) {
				nutrientMapLine[col] += nutrientTableLine[col];
			}
		}
	}

	public static int countNumOfTrees(Tree[][] map) {
		int numOfTrees = 0;
		for (Tree[] mapLine : map) {
			for (Tree tree : mapLine) {
				while (tree != null) {
					numOfTrees++;
					tree = tree.another;
				}
			}
		}
		return numOfTrees;
	}
}
