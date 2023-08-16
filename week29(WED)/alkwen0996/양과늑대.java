import java.util.LinkedList;
import java.util.List;

public class 양과늑대 {
    public static int maximumSheep;

    public static void main(String[] args) {
        int[] info = {0, 1, 0, 1, 1, 0, 1, 0, 0, 1, 0};
        int[][] edges = {
                {0, 1}, {0, 2},
                {1, 3}, {1, 4},
                {2, 5}, {2, 6},
                {3, 7}, {4, 8},
                {6, 9}, {9, 10}
        };

        System.out.println(solution(info, edges));
    }

    public static int solution(int[] info, int[][] edges) {
        List<List<Integer>> tree = makeTree(edges);
        maximumSheep = 0;

        List<Integer> nextVertexes = new LinkedList<>();
        nextVertexes.add(0);

        dfs(tree, nextVertexes, info, 0, 0, 0);

        return maximumSheep;
    }

    public static void dfs(final List<List<Integer>> tree, final List<Integer> nextVertexes, final int[] info, final int current, int sheep, int wolf) {
        if (info[current] == 0) {
            sheep++;
        } else {
            wolf++;
        }

        if (sheep <= wolf) {
            return;
        }

        if (maximumSheep < sheep) {
            maximumSheep = sheep;
        }

        final List<Integer> newNextVertexes = new LinkedList<>(nextVertexes);
        newNextVertexes.remove(Integer.valueOf(current));
        // 객체로 전달시 current 값을 찾아서 해당 값과 일치하는 객체를 삭제.
        // primitive 타입으로 전달시 해당 index 값이 삭제됌.

        if (tree.get(current) != null) {
            newNextVertexes.addAll(tree.get(current));
        }

        for (Integer nextVertex : newNextVertexes) {
            dfs(tree, newNextVertexes, info, nextVertex, sheep, wolf);
        }

    }

    public static List<List<Integer>> makeTree(final int[][] edges) {
        List<List<Integer>> tree = new LinkedList<>();

        for (int i = 0; i <= edges.length; i++) {
            tree.add(new LinkedList<>());
        }

        for (final int[] edge : edges) {
            int vertex1 = edge[0];
            int vertex2 = edge[1];

            tree.get(vertex1).add(vertex2);
        }

        return tree;
    }

}
