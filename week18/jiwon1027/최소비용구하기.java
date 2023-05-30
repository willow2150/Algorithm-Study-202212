import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
* 시작점, 끝점 명확한 다익스트라 기본 문제네
*
* 출발점에서 도착점을 갈 수 있는 경우만 입력으로 주어진다 => 예외처리 안해도 되겠네
* 100,000보다 작은 정수라서 int로 하면 될듯
* */
public class Main {
    static int N,M,start,end;
    static List<int[]>[] graph;
    static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        dp = new int[N+1];
        graph = new ArrayList[N + 1];
        for (int i = 0; i < N+1; i++) {
            graph[i] = new ArrayList<>();
            if (i == 0)
                continue;
            dp[i] = Integer.MAX_VALUE;
        }

        StringTokenizer st = null;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            graph[start].add(new int[]{end, cost});
        }

        st = new StringTokenizer(br.readLine());
        start = Integer.parseInt(st.nextToken());
        end = Integer.parseInt(st.nextToken());

        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> (o1[1] - o2[1]));

        pq.add(new int[]{start, 0});
        dp[start] = 0;

        while(!pq.isEmpty()){
            int[] poll_data = pq.poll();
            int dist = poll_data[0];
            int cost = poll_data[1];

            if (dist == end)
                break;

            for(int[] next : graph[dist]){
                if (dp[next[0]] > cost + next[1]) {
                    dp[next[0]] = cost + next[1];
                    pq.add(new int[] { next[0], dp[next[0]] });
                }
            }
        }

        System.out.println(dp[end]);


    }
}