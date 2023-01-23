package pratice;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.parseInt;

public class Main_10653 {

    static int INF = Integer.MAX_VALUE;
    static List<Node> nodes;
    static int[][] dist;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String varsLine = br.readLine();
        String[] varsStr = varsLine.split(" ");
        int n = parseInt(varsStr[0]);
        int k = parseInt(varsStr[1]);

        nodes = new ArrayList<>(n+1);
        nodes.add(new Node(0,0));

        for(int i=0; i<n; ++i) {
            String posLine = br.readLine();
            String[] posStr = posLine.split(" ");
            nodes.add(new Node(parseInt(posStr[1]), parseInt(posStr[0])));
        }

        dist = new int[n+1][n+1];
        for(int i=1; i<=n; ++i) {
            for(int j=1; j<=n; ++j) {
                dist[i][j] = getDist(nodes.get(i), nodes.get(j));
                dist[j][i] = dist[i][j];
            }
        }

        dp = new int[n+1][k+1];
        for(int i=0; i<n; ++i) {
            for(int j=0; j<=k; ++j) {
                dp[i][j] = 0;
            }
        }

        System.out.println(String.valueOf(solve(n, k)));
    }

    public static int solve(int n, int k) {
        if(dp[n][k] != 0) {
            return dp[n][k];
        }
        if(n == 1) {
            return 0;
        }

        dp[n][k] = INF;
        for(int i=0; i<=k; ++i) {
            if(n-i-1 > 0) {
                dp[n][k] = Math.min(solve(n-i-1, k-i) + dist[n-i-1][n], dp[n][k]);
            }
        }
        return dp[n][k];
    }

    public static int getDist(Node a, Node b) {
        return Math.abs(a.y - b.y) + Math.abs(a.x - b.x);
    }

    static class Node {
        int y, x;

        public Node(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
}