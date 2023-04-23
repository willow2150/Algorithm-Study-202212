import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokenizer;
		int N = Integer.parseInt(reader.readLine());

		long[] degrees = new long[N + 1];
		int[][] connectedVertices = new int[N--][];

		for (int edgeIdx = 0; edgeIdx < N; edgeIdx++) {
			tokenizer = new StringTokenizer(reader.readLine());
			int vertexA = Integer.parseInt(tokenizer.nextToken());
			int vertexB = Integer.parseInt(tokenizer.nextToken());
			connectedVertices[edgeIdx] = new int[] {vertexA, vertexB};
			degrees[vertexA]++;
			degrees[vertexB]++;
		}

		long dCnt = 0;
		long gCnt = 0;

		for (int edgeIdx = 0; edgeIdx < N; edgeIdx++) {
			int[] vertices = connectedVertices[edgeIdx];
			dCnt += (degrees[vertices[0]] - 1) * (degrees[vertices[1]] - 1);
		}

		for (long degree : degrees) {
			if (degree >= 3) {
				gCnt += degree * (degree - 1) * (degree - 2) / 6;
			}
		}

		gCnt *= 3;
		System.out.print(dCnt == gCnt ? "DUDUDUNGA" : (dCnt > gCnt ? 'D' : 'G'));
	}
}
