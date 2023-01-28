import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static class Node {
        int parent;
        List<Node> children;

        Node() {
            this.children = new ArrayList<>();
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(reader.readLine());
        Node[] tree = new Node[N];
        Node root = null;

        for (int node = 0; node < N; node++)
            tree[node] = new Node();

        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        for (int nodeNumber = 0; nodeNumber < N; nodeNumber++) {
            int parent = Integer.parseInt(tokenizer.nextToken());
            if (parent == -1) {
                root = tree[nodeNumber];
                continue;
            }
            tree[nodeNumber].parent = parent;
            tree[parent].children.add(tree[nodeNumber]);
        }

        Node nodeToRemove = tree[Integer.parseInt(reader.readLine())];
        tree[nodeToRemove.parent].children.remove(nodeToRemove);
        System.out.print(nodeToRemove == root ? 0 : countNumOfLeafNodes(root));
    }

    public static int countNumOfLeafNodes(Node node) {
        if (node.children.isEmpty()) return 1;
        int numOfLeafNodes = 0;
        for (Node child : node.children)
            numOfLeafNodes += countNumOfLeafNodes(child);
        return numOfLeafNodes;
    }
}
