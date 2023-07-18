import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.StringTokenizer;

public class Baekjoon_1939 {
    private static List<List<Node>> graph;
    private static int n, m, plant1, plant2, lowWeight, highWeight;

    public static void main(String[] args) throws IOException {
        inputData();
        findMaximumWeight();
    }

    private static void findMaximumWeight() {
        int maximumWeight = 0;
        int midWeight;

        while (lowWeight <= highWeight) {
            midWeight = (lowWeight + highWeight) / 2;

            if (findPath(midWeight)) {
                lowWeight = midWeight + 1;
                maximumWeight = Math.max(midWeight, maximumWeight);
            } else {
                highWeight = midWeight - 1;
            }
        }

        System.out.println(maximumWeight);
    }

    private static boolean findPath(final int weight) {
        Deque<Node> queue = new ArrayDeque<>();
        boolean[] isVisited = new boolean[n + 1];
        queue.add(new Node(plant1, 0));
        isVisited[plant1] = true;

        while (!queue.isEmpty()) {
            Node current = queue.poll();

            if (current.island == plant2) {
                return true;
            }

            for (Node nextIsland : graph.get(current.island)) {
                if (weight > nextIsland.weight || isVisited[nextIsland.island]) {
                    continue;
                }

                queue.add(nextIsland);
                isVisited[nextIsland.island] = true;
            }
        }

        return false;
    }

    private static void inputData() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());

        n = Integer.parseInt(stringTokenizer.nextToken());
        m = Integer.parseInt(stringTokenizer.nextToken());
        lowWeight = Integer.MAX_VALUE;
        highWeight = Integer.MIN_VALUE;

        graph = new ArrayList<>();

        for (int i = 0; i < n + 1; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());

            int island1 = Integer.parseInt(stringTokenizer.nextToken());
            int island2 = Integer.parseInt(stringTokenizer.nextToken());
            int weight = Integer.parseInt(stringTokenizer.nextToken());

            graph.get(island1).add(new Node(island2, weight));
            graph.get(island2).add(new Node(island1, weight));

            lowWeight = Math.min(lowWeight, weight);
            highWeight = Math.max(highWeight, weight);
        }

        stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        plant1 = Integer.parseInt(stringTokenizer.nextToken());
        plant2 = Integer.parseInt(stringTokenizer.nextToken());
    }

    static class Node {
        private int island;
        private int weight;

        public Node(final int island, final int weight) {
            this.island = island;
            this.weight = weight;
        }
    }

}
