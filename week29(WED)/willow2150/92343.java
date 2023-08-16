import java.util.ArrayDeque;
import java.util.Queue;

class Solution {
    static class Node {
        Node parent;
        Node left;
        Node right;
        int number;
        int bit;
        boolean isSheep;

        public Node(int number, int bit, boolean isSheep) {
            this.number = number;
            this.bit = bit;
            this.isSheep = isSheep;
        }
    }

    public int solution(int[] info, int[][] edges) {
        Node[] graph = getGraph(info, edges);
        return findMaxNumOfSheep(graph);
    }

    public Node[] getGraph(int[] info, int[][] edges) {
        final int SHEEP = 0;
        Node[] graph = new Node[info.length];
        int bit = 1;

        for (int nodeNumber = 0; nodeNumber < info.length; nodeNumber++) {
            graph[nodeNumber] = new Node(nodeNumber, bit, info[nodeNumber] == SHEEP);
            bit <<= 1;
        }

        for (int[] edge : edges) {
            Node parent = graph[edge[0]];
            Node child = graph[edge[1]];
            child.parent = parent;
            if (parent.left == null) {
                parent.left = child;
            } else {
                parent.right = child;
            }
        }

        return graph;
    }

    public int findMaxNumOfSheep(Node[] graph) {
        Queue<int[]> queue = new ArrayDeque<>();
        boolean[][] visited = new boolean[1 << graph.length][graph.length];
        int maxNumOfSheep = 0;

        queue.add(new int[] {1, 0, 1});
        visited[1][0] = true;

        while (!queue.isEmpty()) {
            int[] state = queue.poll();
            int[] nextState;
            int numOfSheep = state[0];
            int numOfWolfs = state[1];
            int bitSum = state[2];
            int bit = 1;
            maxNumOfSheep = Math.max(maxNumOfSheep, state[0]);
            for (int nodeNumber = 0; nodeNumber < graph.length; nodeNumber++, bit <<= 1) {
                if ((bitSum & bit) == 0) {
                    continue;
                }
                Node node = graph[nodeNumber];
                nextState = getNextState(
                        visited, node.parent, numOfSheep, numOfWolfs, bitSum
                );
                if (nextState != null) {
                    queue.add(nextState);
                }
                nextState = getNextState(
                        visited, node.left, numOfSheep, numOfWolfs, bitSum
                );
                if (nextState != null) {
                    queue.add(nextState);
                }
                nextState = getNextState(
                        visited, node.right, numOfSheep, numOfWolfs, bitSum
                );
                if (nextState != null) {
                    queue.add(nextState);
                }
            }
        }

        return maxNumOfSheep;
    }

    public int[] getNextState(
            boolean[][] visited, Node node, int numOfSheep, int numOfWolfs, int bitSum
    ) {
        if (node != null && !visited[bitSum | node.bit][node.number]) {
            int nextBitSum = bitSum | node.bit;
            if ((bitSum & node.bit) == 0) {
                int nextNumOfSheep = numOfSheep + (node.isSheep ? 1 : 0);
                int nextNumOfWolfs = numOfWolfs + (node.isSheep ? 0 : 1);
                if (nextNumOfWolfs < nextNumOfSheep) {
                    visited[nextBitSum][node.number] = true;
                    return new int[] {nextNumOfSheep, nextNumOfWolfs, nextBitSum};
                }
            } else {
                visited[nextBitSum][node.number] = true;
                return new int[] {numOfSheep, numOfWolfs, nextBitSum};
            }
        }
        return null;
    }
}
