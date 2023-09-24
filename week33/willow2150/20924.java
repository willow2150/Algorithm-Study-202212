import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static class Edge {
        Edge another;
        int arrNodeNumber;
        int length;

        public Edge(Edge another, int arrNodeNumber, int length) {
            this.another = another;
            this.arrNodeNumber = arrNodeNumber;
            this.length = length;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        int N = Integer.parseInt(tokenizer.nextToken());
        int R = Integer.parseInt(tokenizer.nextToken());

        Edge[] graph = new Edge[N + 1];
        int[] numOfConnectedEdges = new int[N + 1];

        for (int edgeNumber = 1; edgeNumber < N; edgeNumber++) {
            tokenizer = new StringTokenizer(reader.readLine());
            int aNode = Integer.parseInt(tokenizer.nextToken());
            int bNode = Integer.parseInt(tokenizer.nextToken());
            int edgeLength = Integer.parseInt(tokenizer.nextToken());
            graph[aNode] = new Edge(graph[aNode], bNode, edgeLength);
            graph[bNode] = new Edge(graph[bNode], aNode, edgeLength);
            numOfConnectedEdges[aNode]++;
            numOfConnectedEdges[bNode]++;
        }

        numOfConnectedEdges[R]++;
        int[] gigaNodeInfo = findGigaNodeInfo(
                graph,
                numOfConnectedEdges,
                R,
                0
        );
        int maxBranchLength = findMaxBranchLength(
                graph,
                numOfConnectedEdges,
                gigaNodeInfo[1]
        );

        System.out.printf("%d %d", gigaNodeInfo[0], maxBranchLength);
    }

    public static int[] findGigaNodeInfo(
            Edge[] graph, int[] numOfConnectedEdges, int nodeNumber, int length
    ) {
        if (numOfConnectedEdges[nodeNumber] != 2) {
            numOfConnectedEdges[nodeNumber] = 0;
            return new int[] {length, nodeNumber};
        }
        numOfConnectedEdges[nodeNumber] = 0;

        Edge edge = graph[nodeNumber];
        while (edge != null) {
            if (numOfConnectedEdges[edge.arrNodeNumber] != 0) {
                return findGigaNodeInfo(
                        graph,
                        numOfConnectedEdges,
                        edge.arrNodeNumber,
                        length + edge.length
                );
            }
            edge = edge.another;
        }

        return new int[] {length, nodeNumber};
    }

    public static int findMaxBranchLength(
            Edge[] graph, int[] numOfConnectedEdges, int nodeNumber
    ) {
        Edge edge = graph[nodeNumber];
        int maxBranchLength = 0;

        numOfConnectedEdges[nodeNumber] = 0;

        while (edge != null) {
            if (numOfConnectedEdges[edge.arrNodeNumber] != 0) {
                maxBranchLength = Math.max(
                        maxBranchLength,
                        edge.length + findMaxBranchLength(
                                graph,
                                numOfConnectedEdges,
                                edge.arrNodeNumber
                        )
                );
            }
            edge = edge.another;
        }

        return maxBranchLength;
    }
}
