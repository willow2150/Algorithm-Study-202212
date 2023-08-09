import java.util.ArrayDeque;
import java.util.Queue;

class Solution {
    static class Node {
        Node another;
        int number;

        Node(Node another, int number) {
            this.another = another;
            this.number = number;
        }
    }

    public int solution(int n, int[][] edges) {
        Node[] graph = new Node[n + 1];
        int[] visited = new int[n + 1];

        for (int[] edge : edges) {
            int nodeANumber = edge[0];
            int nodeBNumber = edge[1];
            graph[nodeANumber] = new Node(graph[nodeANumber], nodeBNumber);
            graph[nodeBNumber] = new Node(graph[nodeBNumber], nodeANumber);
        }

        int[] firstSearchInfo = findFurthestNodeInfo(graph, visited, 1, 1);
        int[] secondSearchInfo = findFurthestNodeInfo(graph, visited, firstSearchInfo[0], 2);
        if (1 < secondSearchInfo[2]) {
            return secondSearchInfo[1];
        }
        int[] thirdSearchInfo = findFurthestNodeInfo(graph, visited, secondSearchInfo[0], 3);
        return 1 < thirdSearchInfo[2] ? thirdSearchInfo[1] : thirdSearchInfo[1] - 1;
    }

    public int[] findFurthestNodeInfo(
            Node[] graph, int[] visited, int depNodeNumber, int visitNumber
    ) {
        Queue<Node> queue = new ArrayDeque<>();
        int furthestNodeNumber = depNodeNumber;
        int distance = 0;
        int numOfFurthestNodes = 0;

        queue.add(graph[depNodeNumber]);
        visited[depNodeNumber] = visitNumber;

        for (; !queue.isEmpty(); distance++) {
            int size = queue.size();
            numOfFurthestNodes = size;
            while (0 < size--) {
                Node node = queue.poll();
                while (node != null) {
                    if (visited[node.number] != visitNumber) {
                        queue.add(graph[node.number]);
                        visited[node.number] = visitNumber;
                        furthestNodeNumber = node.number;
                    }
                    node = node.another;
                }
            }
        }

        return new int[] {furthestNodeNumber, --distance, numOfFurthestNodes};
    }
}
