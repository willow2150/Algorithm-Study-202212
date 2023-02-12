import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        int n = Integer.parseInt(tokenizer.nextToken());
        int m = Integer.parseInt(tokenizer.nextToken());

        List<List<int[]>> graph = new ArrayList<>(n + 1);
        Queue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] <= o2[0] ? -1 : 1;
            }
        });
        int[][] pathTable = new int[n + 1][n + 1];
        int[] weightArray = new int[n + 1];

        for (int vertex = 0; vertex <= n; vertex++)
            graph.add(new ArrayList<>());

        for (int edge = 0; edge < m; edge++) {
            tokenizer = new StringTokenizer(reader.readLine());
            int dep = Integer.parseInt(tokenizer.nextToken());
            int arr = Integer.parseInt(tokenizer.nextToken());
            int cost = Integer.parseInt(tokenizer.nextToken());
            graph.get(dep).add(new int[]{arr, cost});
            graph.get(arr).add(new int[]{dep, cost});
        }

        for (int vertex = 1; vertex <= n; vertex++)
            dijkstra(graph, pq, pathTable[vertex], weightArray, vertex);
        printPathTable(pathTable, n);
    }

    public static void dijkstra(List<List<int[]>> graph, Queue<int[]> pq,
                                int[] pathRecord, int[] weightArray, int dep) {
        Arrays.fill(weightArray, Integer.MAX_VALUE);
        weightArray[dep] = 0;
        pq.add(new int[]{0, dep, dep});
        while (!pq.isEmpty()) {
            int[] edge = pq.poll();
            for (int[] anotherEdge : graph.get(edge[1])) {
                if (edge[0] + anotherEdge[1] < weightArray[anotherEdge[0]]) {
                    weightArray[anotherEdge[0]] = edge[0] + anotherEdge[1];
                    if (dep == edge[2]) {
                        pathRecord[anotherEdge[0]] = anotherEdge[0];
                        pq.add(new int[]{
                                weightArray[anotherEdge[0]],
                                anotherEdge[0],
                                anotherEdge[0]
                        });
                    } else {
                        pathRecord[anotherEdge[0]] = edge[2];
                        pq.add(new int[]{
                                weightArray[anotherEdge[0]],
                                anotherEdge[0],
                                edge[2]
                        });
                    }
                }
            }
        }
    }

    public static void printPathTable(int[][] pathTable, int n) {
        StringBuilder output = new StringBuilder();
        for (int vertexA = 1; vertexA <= n; vertexA++) {
            for (int vertexB = 1; vertexB <= n; vertexB++) {
                if (vertexA == vertexB) output.append('-');
                else output.append(pathTable[vertexA][vertexB]);
                output.append(' ');
            }
            output.append('\n');
        }
        System.out.print(output);
    }
}
