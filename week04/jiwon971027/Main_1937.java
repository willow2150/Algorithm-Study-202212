package pratice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_1937 {

    static int N;
    static int[][] map;
    static int[][] dp;
    static int max;
    static int[] dx = {0,0,1,-1};
    static int[] dy = {1,-1,0,0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        map = new int[N][N];
        dp = new int[N][N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
            Arrays.fill(dp[i], -1);
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                max = Math.max(max, dfs(i,j));
            }
        }

        System.out.println(max);
    }

    static int dfs(int x, int y) {
        if(dp[x][y] != -1)
            return dp[x][y];

        int temp = 1;
        for (int i = 0; i < 4; i++) {
            int nx = x +dx[i];
            int ny = y +dy[i];

            if(!isRange(nx, ny) || map[x][y] >= map[nx][ny])
                continue;

            temp = Math.max(temp, dfs(nx,ny) +1);
        }
        dp[x][y] = temp;

        return dp[x][y];
    }

    static boolean isRange(int x, int y) {
        if(x < 0 || x >= N || y < 0 || y >= N) return false;
        return true;
    }
}
