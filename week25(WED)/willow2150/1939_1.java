import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static class Node {
        Node another;
        int number;
        int weightLimit;

        public Node(Node another, int number, int weightLimit) {
            this.another = another;
            this.number = number;
            this.weightLimit = weightLimit;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        int N = Integer.parseInt(tokenizer.nextToken());
        int M = Integer.parseInt(tokenizer.nextToken());

        Node[] graph = new Node[N + 1];

        for (int m = 0; m < M; m++) {
            tokenizer = new StringTokenizer(reader.readLine());
            int A = Integer.parseInt(tokenizer.nextToken());
            int B = Integer.parseInt(tokenizer.nextToken());
            int C = Integer.parseInt(tokenizer.nextToken());
            graph[A] = new Node(graph[A], B, C);
            graph[B] = new Node(graph[B], A, C);
        }

        tokenizer = new StringTokenizer(reader.readLine());
        int dep = Integer.parseInt(tokenizer.nextToken());
        int arr = Integer.parseInt(tokenizer.nextToken());

        System.out.print(findMaxWeightLimit(graph, N, dep, arr));
    }

    public static int findMaxWeightLimit(Node[] graph, int N, int dep, int arr) {
        Queue<int[]> queue = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] stateA, int[] stateB) {
                if (stateA[0] == stateB[0]) {
                    return 0;
                }
                return stateA[0] < stateB[0] ? 1 : -1;
            }
        });
        int[] maxWeightLimit = new int[N + 1];

        queue.add(new int[] {Integer.MAX_VALUE, dep});
        maxWeightLimit[dep] = Integer.MAX_VALUE;

        while (!queue.isEmpty()) {
            int[] state = queue.poll();
            int weightLimit = state[0];
            int nodeNumber = state[1];

            if (nodeNumber == arr) {
                break;
            }

            Node node = graph[nodeNumber];

            while (node != null) {
                int nextNodeNumber = node.number;
                int nextWeightLimit = Math.min(weightLimit, node.weightLimit);
                if (maxWeightLimit[nextNodeNumber] < nextWeightLimit) {
                    maxWeightLimit[nextNodeNumber] = nextWeightLimit;
                    queue.add(new int[] {nextWeightLimit, nextNodeNumber});
                }
                node = node.another;
            }
        }
        return maxWeightLimit[arr];
    }
}
