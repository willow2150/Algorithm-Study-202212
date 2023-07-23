import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

class Solution {
    static class Node implements Comparable<Node> {
        Node leftChild;
        Node rightChild;
        int x;
        int y;
        int number;

        Node(int x, int y, int number) {
            this.x = x;
            this.y = y;
            this.number = number;
        }

        @Override
        public int compareTo(Node node) {
            if (this.y == node.y) {
                return 0;
            }
            return this.y < node.y ? 1 : -1;
        }
    }

    public int[][] solution(int[][] nodeinfo) {
        int numOfNodes = nodeinfo.length;
        Node[] nodes = new Node[numOfNodes];

        for (int nodeIndex = 0; nodeIndex < numOfNodes; nodeIndex++) {
            int[] nodeInfo = nodeinfo[nodeIndex];
            nodes[nodeIndex] = new Node(nodeInfo[0], nodeInfo[1], nodeIndex + 1);
        }

        Arrays.sort(nodes);

        for (int nodeNumber = 1; nodeNumber < numOfNodes; nodeNumber++) {
            makeTree(nodes[0], nodes[nodeNumber]);
        }

        List<Node> preorderResult = new ArrayList<>(numOfNodes);
        List<Node> postorderResult = new ArrayList<>(numOfNodes);

        preorder(nodes[0], preorderResult);
        postorder(nodes[0], postorderResult);

        int[][] answer = new int[2][numOfNodes];

        for (int order = 0; order < numOfNodes; order++) {
            answer[0][order] = preorderResult.get(order).number;
        }
        for (int order = 0; order < numOfNodes; order++) {
            answer[1][order] = postorderResult.get(order).number;
        }

        return answer;
    }

    public static void makeTree(Node ancestor, Node node) {
        if (node.x < ancestor.x) {
            if (ancestor.leftChild == null) {
                ancestor.leftChild = node;
                return;
            }
            makeTree(ancestor.leftChild, node);
        } else {
            if (ancestor.rightChild == null) {
                ancestor.rightChild = node;
                return;
            }
            makeTree(ancestor.rightChild, node);
        }
    }

    public void preorder(Node node, List<Node> preorderResult) {
        preorderResult.add(node);
        if (node.leftChild != null) preorder(node.leftChild, preorderResult);
        if (node.rightChild != null) preorder(node.rightChild, preorderResult);
    }

    public void postorder(Node node, List<Node> postorderResult) {
        if (node.leftChild != null) postorder(node.leftChild, postorderResult);
        if (node.rightChild != null) postorder(node.rightChild, postorderResult);
        postorderResult.add(node);
    }
}
