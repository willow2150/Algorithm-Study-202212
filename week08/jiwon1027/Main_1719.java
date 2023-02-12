package pratice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_1719 {
    static int N,M;
    static List<List<int[]>> graph = new ArrayList<>();
    static int INF = (int)1e9;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N+1; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            graph.get(a).add(new int[]{b, c});
            graph.get(b).add(new int[]{a, c});
        }

        for (int i = 1; i < N+1; i++) {
            dijkstra(i);
        }


    }

    public static void dijkstra(int start) {

        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);
        pq.add(new int[]{start, 0});

        boolean[] visited = new boolean[N+1];
        int[] path = new int[N+1];
        int[] dp = new int[N+1];

        Arrays.fill(dp, INF);

        dp[start] = 0;
        path[start] = start;

        while (!pq.isEmpty()) {

            int[] node = pq.poll();
            int now = node[0];
            int dist = node[1];

            if (visited[now]) {
                continue;
            }

            visited[now] = true;

            for (int i=0; i<graph.get(now).size(); i++) {
                int cost = dp[now] + graph.get(now).get(i)[1];

                if (cost < dp[graph.get(now).get(i)[0]]) {
                    dp[graph.get(now).get(i)[0]] = cost;
                    path[graph.get(now).get(i)[0]] = now;
                    pq.add(new int[]{graph.get(now).get(i)[0], cost});
                }
            }

        }

//        System.out.println(Arrays.toString(path));

        for (int i = 1; i < N+1; i++) {
            if (i == start) {
                System.out.print("- ");
            } else {
                int answer = 0;
                for (int j=i; j!=start; j=path[j]) {
                    answer = j;
                }
                System.out.print(answer + " ");
            }
        }
        System.out.println();



    }


}
