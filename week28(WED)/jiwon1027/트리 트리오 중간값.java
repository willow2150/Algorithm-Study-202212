import java.util.*;

/*
    X로부터 가장 멀리 있는 Y 정점 구하기 위해선 bfs로 하면 좋을듯?
    최대 거리 정점
*/
class Solution {
    static ArrayList<Integer>[] nodes;
    static int N;

    public static int solution(int n, int[][] edges) {
        N = n;
        nodes = new ArrayList[N + 1];
        for (int i = 1; i < nodes.length; i++) {
            nodes[i] = new ArrayList<Integer>();
        }
        for (int[] edge : edges) {
            nodes[edge[0]].add(edge[1]);
            nodes[edge[1]].add(edge[0]);
        }

        int start = 1;
        int[] distanceArr = bfs(start);

        int X = start;
        for (int i = 1; i < distanceArr.length; i++) {
            if (distanceArr[X] <= distanceArr[i]) {
                X = i;
            }
        }

        distanceArr = bfs(X);
        int Y = X;
        for (int i = 1; i < distanceArr.length; i++) {
            if (distanceArr[Y] <= distanceArr[i]) {
                Y = i;
            }
        }

        int cnt = 0;
        for (int i = 1; i < distanceArr.length; i++) {
            if (distanceArr[Y] == distanceArr[i]) {
                cnt++;
            }
        }

        if (cnt >= 2) 
            return distanceArr[Y];


        distanceArr = bfs(Y);
        int Z = Y;
        for (int i = 0; i < distanceArr.length; i++) {
            if (distanceArr[Z] <= distanceArr[i]) {
                Z = i;
            }
        }

        cnt = 0;
        for (int i = 1; i < distanceArr.length; i++) {
            if (distanceArr[Z] == distanceArr[i]) {
                cnt++;
            }
        }

        if (cnt >= 2) 
            return distanceArr[Z];
        else         
            return distanceArr[Z] - 1;

    }


    private static int[] bfs(int node) {
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[N + 1];

        queue.add(node);
        visited[node] = true;
        int[] dist = new int[N + 1];

        while (!queue.isEmpty()) {
            int curNode = queue.poll();

            for (int linkedNode : nodes[curNode]) {
                if (visited[linkedNode]) continue;

                visited[linkedNode] = true;
                queue.add(linkedNode);
                dist[linkedNode] = dist[curNode] + 1;
            }

        }
        return dist;
    }
}
