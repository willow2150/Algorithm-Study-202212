import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Programmers_150366 {
	/*
	 * 각 cell은 하나의 idx로 표현할 수 있음 ((r-1) * 50) + c
	 * 각 cell에 필요한 정보: row, col, value, merged[idx]={parentIdx} // idx가 병합된 부모가 누구인지
	 */

	// 크기 50 * 50으로 고정.
	public static int[] parent = new int[2501];
	public static String[] value = new String[2501];

	public static void main(String[] args) {
		String[] commands = {
			"UPDATE 1 1 a", "UPDATE 1 2 b", "UPDATE 2 1 c", "UPDATE 2 2 d", "MERGE 1 1 1 2", "MERGE 2 2 2 1",
			"MERGE 2 1 1 1", "PRINT 1 1", "UNMERGE 2 2", "PRINT 1 1"};

		final String[] solution = solution(commands);
		System.out.println(Arrays.toString(solution));
	}

	public static String[] solution(String[] commands) {
		makeSet();

		final List<String> result = new ArrayList<>();

		for (final String commandLine : commands) {
			final StringTokenizer stringTokenizer = new StringTokenizer(commandLine);
			String command = stringTokenizer.nextToken();

			if ("UPDATE".equals(command)) {
				if (stringTokenizer.countTokens() == 2) {
					String originalData = stringTokenizer.nextToken();
					String newData = stringTokenizer.nextToken();

					for (int j = 0; j < parent.length; j++) {
						if (originalData.equals(value[j])) {
							value[j] = newData;
						}
					}
				} else {
					int row = Integer.parseInt(stringTokenizer.nextToken());
					int column = Integer.parseInt(stringTokenizer.nextToken());
					String data = stringTokenizer.nextToken();
					int index = convertPointToIndex(row, column);
					value[find(index)] = data;
				}
			} else if ("MERGE".equals(command)) {
				int row1 = Integer.parseInt(stringTokenizer.nextToken());
				int column1 = Integer.parseInt(stringTokenizer.nextToken());
				int row2 = Integer.parseInt(stringTokenizer.nextToken());
				int column2 = Integer.parseInt(stringTokenizer.nextToken());

				int index1 = convertPointToIndex(row1, column1);
				int index2 = convertPointToIndex(row2, column2);

				int root1 = find(index1);
				int root2 = find(index2);

				if (root1 == root2) {
					continue;
				}

				String rootValue = "";
				if (value[root1].isEmpty()) {
					rootValue = value[root2];
				} else {
					rootValue = value[root1];
				}

				value[root1] = "";
				value[root2] = "";

				union(root1, root2);
				value[root1] = rootValue;
			} else if ("UNMERGE".equals(command)) {
				int row = Integer.parseInt(stringTokenizer.nextToken());
				int column = Integer.parseInt(stringTokenizer.nextToken());
				int index = convertPointToIndex(row, column);
				int root = find(index);

				String rootValue = value[root];
				value[root] = "";
				value[index] = rootValue;

				final List<Integer> deleteList = new ArrayList<>();
				for (int j = 1; j <= 2500; j++) {
					if (find(j) == root) {
						deleteList.add(j);
					}
				}

				for (Integer integer : deleteList) {
					parent[integer] = integer;
				}

			} else {
				int row = Integer.parseInt(stringTokenizer.nextToken());
				int column = Integer.parseInt(stringTokenizer.nextToken());
				int index = convertPointToIndex(row, column);
				int root = find(index);

				if (value[root].isEmpty()) {
					result.add("EMPTY");
				} else {
					result.add(value[root]);
				}
			}

		}

		return result.toArray(new String[0]);
	}

	private static void makeSet() {
		for (int i = 1; i <= 2500; i++) {
			parent[i] = i;
			value[i] = "";
		}
	}

	public static int find(int value) {
		if (parent[value] == value) {
			return value;
		}

		return parent[value] = find(parent[value]);
	}

	public static void union(int value1, int value2) {
		value1 = find(value1);
		value2 = find(value2);

		if (value1 != value2) {
			parent[value2] = value1;
		}
	}

	public static int convertPointToIndex(int row, int column) {
		int point = 50 * (row - 1);

		return point + column;
	}
}
