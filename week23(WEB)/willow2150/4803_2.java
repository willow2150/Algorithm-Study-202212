import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static class Vertex {
		int number;

		public Vertex(int number) {
			this.number = number;
		}
	}

	public static void main(String[] args) throws Exception {
		final int MAX_N = 500;
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder output = new StringBuilder();
		StringTokenizer tokenizer;

		List<List<Vertex>> graph = new ArrayList<>(MAX_N + 1);
		Vertex[] vertices = new Vertex[MAX_N + 1];
		boolean[] visited = new boolean[MAX_N + 1];

		for (int vertexNumber = 0; vertexNumber <= MAX_N; vertexNumber++) {
			graph.add(new ArrayList<>());
			vertices[vertexNumber] = new Vertex(vertexNumber);
		}

		for (int caseNumber = 1; ; caseNumber++) {
			tokenizer = new StringTokenizer(reader.readLine());
			int n = Integer.parseInt(tokenizer.nextToken());
			int m = Integer.parseInt(tokenizer.nextToken());

			if (n == 0) {
				break;
			}

			for (int edgeIndex = 0; edgeIndex < m; edgeIndex++) {
				tokenizer = new StringTokenizer(reader.readLine());
				int vertexANumber = Integer.parseInt(tokenizer.nextToken());
				int vertexBNumber = Integer.parseInt(tokenizer.nextToken());
				graph.get(vertexANumber).add(vertices[vertexBNumber]);
				graph.get(vertexBNumber).add(vertices[vertexANumber]);
			}

			int numOfTrees = 0;

			for (int vertexNumber = 1; vertexNumber <= n; vertexNumber++) {
				if (visited[vertexNumber]) {
					continue;
				}
				if (isTree(graph, visited, vertexNumber, 0)) {
					numOfTrees++;
				}
			}

			for (int vertexNumber = 1; vertexNumber <= n; vertexNumber++) {
				visited[vertexNumber] = false;
				graph.get(vertexNumber).clear();
			}

			output.append("Case ").append(caseNumber);

			if (numOfTrees == 0) {
				output.append(": No trees.");
			} else if (numOfTrees == 1) {
				output.append(": There is one tree.");
			} else {
				output.append(": A forest of ").append(numOfTrees).append(" trees.");
			}

			output.append('\n');
		}

		System.out.print(output);
	}

	public static boolean isTree(
			List<List<Vertex>> graph, boolean[] visited,
			int vertexNumber, int prevVertexNumber
	) {
		visited[vertexNumber] = true;
		for (Vertex nextVertex : graph.get(vertexNumber)) {
			if (nextVertex.number == prevVertexNumber) {
				continue;
			}
			if (visited[nextVertex.number]) {
				return false;
			}
			if (!isTree(graph, visited, nextVertex.number, vertexNumber)) {
				return false;
			}
		}
		return true;
	}
}
