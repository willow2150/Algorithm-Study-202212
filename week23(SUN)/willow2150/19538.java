import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static class Node {
        Node another;
        int number;

        public Node(Node another, int number) {
            this.another = another;
            this.number = number;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder output = new StringBuilder();
        StringTokenizer tokenizer;

        int N = Integer.parseInt(reader.readLine());
        int[] times = new int[N + 1];
        int[] halfDegrees = new int[N + 1];
        Node[] graph = new Node[N + 1];
        Queue<Node> queue = new ArrayDeque<>();

        for (int person = 1; person <= N; person++) {
            tokenizer = new StringTokenizer(reader.readLine());
            int numOfConnections = 0;
            while (true) {
                int friend = Integer.parseInt(tokenizer.nextToken());
                if (friend == 0) {
                    break;
                }
                graph[person] = new Node(graph[person], friend);
                numOfConnections++;
            }
            halfDegrees[person] = (numOfConnections + 1) >> 1;
        }

        Arrays.fill(times, -1);

        int M = Integer.parseInt(reader.readLine());
        tokenizer = new StringTokenizer(reader.readLine());
        for (int diffuserIndex = 0; diffuserIndex < M; diffuserIndex++) {
            int diffuser = Integer.parseInt(tokenizer.nextToken());
            halfDegrees[diffuser] = 0;
            times[diffuser] = 0;
            if (graph[diffuser] != null) {
                queue.add(graph[diffuser]);
            }
        }

        for (int time = 1; !queue.isEmpty(); time++) {
            int queueSize = queue.size();
            while (0 < queueSize--) {
                Node node = queue.poll();
                while (node != null) {
                    int nodeNumber = node.number;
                    if (--halfDegrees[nodeNumber] == 0) {
                        times[nodeNumber] = time;
                        queue.add(graph[nodeNumber]);
                    }
                    node = node.another;
                }
            }
        }

        for (int person = 1; person <= N; person++) {
            output.append(times[person]).append(' ');
        }
        System.out.print(output);
    }
}
