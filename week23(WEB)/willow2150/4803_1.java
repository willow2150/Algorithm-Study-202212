import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		final int MAX_N = 500;
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder output = new StringBuilder();
		StringTokenizer tokenizer;

		int[] parentVertex = new int[MAX_N + 1];

		for (int caseNumber = 1; ; caseNumber++) {
			tokenizer = new StringTokenizer(reader.readLine());
			int n = Integer.parseInt(tokenizer.nextToken());
			int m = Integer.parseInt(tokenizer.nextToken());

			if (n == 0) {
				break;
			}

			for (int vertex = 1; vertex <= n; vertex++) {
				parentVertex[vertex] = vertex;
			}

			for (int edgeIndex = 0; edgeIndex < m; edgeIndex++) {
				tokenizer = new StringTokenizer(reader.readLine());
				int vertexA = Integer.parseInt(tokenizer.nextToken());
				int vertexB = Integer.parseInt(tokenizer.nextToken());
				unionVertices(parentVertex, vertexA, vertexB);
			}

			int numOfTrees = 0;

			for (int vertex = 1; vertex <= n; vertex++) {
				if (vertex == findParentVertex(parentVertex, vertex)) {
					numOfTrees++;
				}
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

	public static void unionVertices(int[] parentVertex, int vertexA, int vertexB) {
		vertexA = findParentVertex(parentVertex, vertexA);
		vertexB = findParentVertex(parentVertex, vertexB);
		if (vertexA < vertexB) {
			parentVertex[vertexB] = vertexA;
		} else if (vertexA > vertexB) {
			parentVertex[vertexA] = vertexB;
		} else {
			parentVertex[vertexA] = parentVertex[vertexB] = 0;
		}
	}

	public static int findParentVertex(int[] parentVertex, int vertex) {
		if (parentVertex[vertex] == vertex) {
			return vertex;
		}
		return parentVertex[vertex] = findParentVertex(parentVertex, parentVertex[vertex]);
	}
}
