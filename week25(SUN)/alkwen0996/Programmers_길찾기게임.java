import java.awt.Point;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Programmers_길찾기게임 {
    // 1. 이진트리를 만들고
    // 2. 전위순회, 후위순회한 결과를 배열에 저장

    public static void main(String[] args) {
        int[][] nodeInfo = {{5, 3}, {11, 5}, {13, 3}, {3, 5}, {6, 1}, {1, 3}, {8, 6}, {7, 2}, {2, 2}};
        int[][] solution = solution(nodeInfo);

        for (final int[] ints : solution) {
            System.out.println(Arrays.toString(ints));
        }
    }

    public static int[][] solution(int[][] nodeInfo) {
        List<TreeNode> tree = new ArrayList<>();

        for (int i = 0; i < nodeInfo.length; i++) {
            tree.add(new TreeNode(i + 1, new Point(nodeInfo[i][0], nodeInfo[i][1])));
        }

        Collections.sort(tree);
        TreeNode rootNode = tree.get(0);

        for (int i = 1; i < tree.size(); i++) {
            createTree(rootNode, tree.get(i));
        }

        List<Integer> preOrderResult = new ArrayList<>();
        List<Integer> postOrderResult = new ArrayList<>();

        preOrder(rootNode, preOrderResult);
        postOrder(rootNode, postOrderResult);

        int[] preOrderResultToArray = listToArray(preOrderResult);
        int[] postOrderResultToArray = listToArray(postOrderResult);

        int[][] answer = new int[2][nodeInfo.length];
        answer[0] = preOrderResultToArray;
        answer[1] = postOrderResultToArray;

        return answer;
    }

    private static int[] listToArray(final List<Integer> resultList) {
        return resultList.stream().mapToInt(Integer::intValue).toArray();
    }

    private static void preOrder(final TreeNode node, final List<Integer> preOrderResult) {
        if (node != null) {
            preOrderResult.add(node.value);
            preOrder(node.leftNode, preOrderResult);
            preOrder(node.rightNode, preOrderResult);
        }
    }

    private static void postOrder(final TreeNode node, final List<Integer> postOrderResult) {
        if (node != null) {
            postOrder(node.leftNode, postOrderResult);
            postOrder(node.rightNode, postOrderResult);
            postOrderResult.add(node.value);
        }
    }

    private static void createTree(final TreeNode parentNode, final TreeNode childNode) {
        if (parentNode.point.x > childNode.point.x) { // left 서브트리
            if (parentNode.leftNode == null) {
                parentNode.leftNode = childNode;
            } else {
                createTree(parentNode.leftNode, childNode);
            }
        } else {  // right 서브트리
            if (parentNode.rightNode == null) {
                parentNode.rightNode = childNode;
            } else {
                createTree(parentNode.rightNode, childNode);
            }
        }

    }

    static class TreeNode implements Comparable<TreeNode> {
        private final int value;
        private final Point point;
        private TreeNode leftNode;
        private TreeNode rightNode;

        public TreeNode(final int value, final Point point) {
            this.value = value;
            this.point = point;
        }

        @Override
        public int compareTo(final TreeNode treeNode) {
            if (treeNode.point.y > this.point.y) {
                return 1;
            } else if (treeNode.point.y < this.point.y) {
                return -1;
            } else {
                return Integer.compare(this.point.x, treeNode.point.x);
            }
        }

        @Override
        public String toString() {
            return "TreeNode{" +
                    "value=" + value +
                    ", point=" + point +
                    '}';
        }
    }

}
