import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static class Edge implements Comparable<Edge> {
        int nodeA;
        int nodeB;
        int weightLimit;

        public Edge(int nodeA, int nodeB, int weightLimit) {
            this.nodeA = nodeA;
            this.nodeB = nodeB;
            this.weightLimit = weightLimit;
        }

        @Override
        public int compareTo(Edge edge) {
            if (this.weightLimit == edge.weightLimit) {
                return 0;
            }
            return this.weightLimit < edge.weightLimit ? 1 : -1;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        int N = Integer.parseInt(tokenizer.nextToken());
        int M = Integer.parseInt(tokenizer.nextToken());

        Queue<Edge> edgeQueue = new PriorityQueue<>();
        int[] parentNode = new int[N + 1];

        for (int node = 1; node <= N; node++) {
            parentNode[node] = node;
        }

        for (int m = 0; m < M; m++) {
            tokenizer = new StringTokenizer(reader.readLine());
            int A = Integer.parseInt(tokenizer.nextToken());
            int B = Integer.parseInt(tokenizer.nextToken());
            int C = Integer.parseInt(tokenizer.nextToken());
            edgeQueue.add(new Edge(A, B, C));
        }

        tokenizer = new StringTokenizer(reader.readLine());
        int dep = Integer.parseInt(tokenizer.nextToken());
        int arr = Integer.parseInt(tokenizer.nextToken());

        System.out.print(findMaxWeightLimit(edgeQueue, parentNode, dep, arr));
    }

    public static int findMaxWeightLimit(
            Queue<Edge> edgeQueue, int[] parentNode, int dep, int arr
    ) {
        int maxWeightLimit = 0;

        while (findParentNode(parentNode, dep) != findParentNode(parentNode, arr)) {
            Edge edge = edgeQueue.poll();
            int nodeA = edge.nodeA;
            int nodeB = edge.nodeB;
            int weightLimit = edge.weightLimit;
            if (findParentNode(parentNode, nodeA) == findParentNode(parentNode, nodeB)) {
                continue;
            }
            unionNodes(parentNode, nodeA, nodeB);
            maxWeightLimit = weightLimit;
        }

        return maxWeightLimit;
    }

    public static int findParentNode(int[] parentNode, int node) {
        if (parentNode[node] == node) {
            return node;
        }
        return parentNode[node] = findParentNode(parentNode, parentNode[node]);
    }

    public static void unionNodes(int[] parentNode, int nodeA, int nodeB) {
        nodeA = parentNode[nodeA];
        nodeB = parentNode[nodeB];
        if (nodeA < nodeB) {
            parentNode[nodeB] = nodeA;
        } else {
            parentNode[nodeA] = nodeB;
        }
    }
}
