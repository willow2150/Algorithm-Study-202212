import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static class Part {
		Part anotherParentPart;
		int parentPartNumber;
		int numNeeded;

		Part(Part anotherParentPart, int parentPartNumber, int numNeeded) {
			this.anotherParentPart = anotherParentPart;
			this.parentPartNumber = parentPartNumber;
			this.numNeeded = numNeeded;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder output = new StringBuilder();
		StringTokenizer tokenizer;
		int N = Integer.parseInt(reader.readLine());
		int M = Integer.parseInt(reader.readLine());

		int[][] countTable = new int[N + 1][N + 1];
		Part[] graph = new Part[N + 1];
		int[] degree = new int[N + 1];
		boolean[] isMiddlePart = new boolean[N + 1];

		for (int i = 0; i < M; i++) {
			tokenizer = new StringTokenizer(reader.readLine());
			int X = Integer.parseInt(tokenizer.nextToken());
			int Y = Integer.parseInt(tokenizer.nextToken());
			int K = Integer.parseInt(tokenizer.nextToken());
			countTable[X][Y] += K;
			graph[Y] = new Part(graph[Y], X, K);
			degree[X]++;
			isMiddlePart[X] = true;
		}

		topologicalSort(countTable, graph, degree);

		for (int part = 1; part < N; part++) {
			if (!isMiddlePart[part]) {
				output.append(part).append(' ').append(countTable[N][part]).append('\n');
			}
		}

		System.out.print(output);
	}

	public static void topologicalSort(int[][] countTable, Part[] graph, int[] degree) {
		int numOfParts = countTable.length - 2;
		int[] queue = new int[numOfParts + 1];
		int head = 0;
		int tail = 0;

		for (int part = 1; part <= numOfParts; part++) {
			if (degree[part] == 0) {
				queue[tail++] = part;
			}
		}

		while (head < tail) {
			int partNumber = queue[head++];
			Part parentPart = graph[partNumber];
			while (parentPart != null) {
				int parentPartNumber = parentPart.parentPartNumber;
				int numNeeded = parentPart.numNeeded;
				int[] parentPartCountList = countTable[parentPartNumber];
				int[] partCountList = countTable[partNumber];
				for (int part = 1; part <= numOfParts; part++) {
					parentPartCountList[part] += partCountList[part] * numNeeded;
				}
				if (--degree[parentPartNumber] == 0) {
					queue[tail++] = parentPartNumber;
				}
				parentPart = parentPart.anotherParentPart;
			}
		}
	}
}
