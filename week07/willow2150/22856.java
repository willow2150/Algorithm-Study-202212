import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static final int NONE = -1;
    private static final int LEFT_CHILD = 0;
    private static final int ROOT = 1;
    private static final int RIGHT_CHILD = 1;
    private static final int PARENT = 2;

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(reader.readLine());
        int[][] tree = new int[N + 1][3];
        boolean[] visited = new boolean[N + 1];
        for (int i = 0; i < N; i++) {
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            int node = Integer.parseInt(tokenizer.nextToken());
            int leftChild = Integer.parseInt(tokenizer.nextToken());
            int rightChild = Integer.parseInt(tokenizer.nextToken());
            tree[node][LEFT_CHILD] = leftChild;
            tree[node][RIGHT_CHILD] = rightChild;
            if (leftChild != NONE) tree[leftChild][PARENT] = node;
            if (rightChild != NONE) tree[rightChild][PARENT] = node;
        }
        tree[ROOT][PARENT] = NONE;
        System.out.print(countMoves(tree, visited, ROOT, findLastNode(tree, ROOT)));
    }

    public static int findLastNode(int[][] tree, int currentNode) {
        if (tree[currentNode][RIGHT_CHILD] == NONE) return currentNode;
        return findLastNode(tree, tree[currentNode][RIGHT_CHILD]);
    }

    public static int countMoves(int[][] tree, boolean[] visited,
                                 int currentNode, int lastNode) {
        visited[currentNode] = true;
        if (tree[currentNode][LEFT_CHILD] != NONE
                && !visited[tree[currentNode][LEFT_CHILD]]) {
            return 1 + countMoves(tree, visited, tree[currentNode][LEFT_CHILD], lastNode);
        }
        if (tree[currentNode][RIGHT_CHILD] != NONE
                && !visited[tree[currentNode][RIGHT_CHILD]]) {
            return 1 + countMoves(tree, visited, tree[currentNode][RIGHT_CHILD], lastNode);
        }
        if (currentNode == lastNode)
            return 0;
        if (tree[currentNode][PARENT] != NONE)
            return 1 + countMoves(tree, visited, tree[currentNode][PARENT], lastNode);
        return 0;
    }
}
