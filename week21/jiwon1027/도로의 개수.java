import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
* 잼민이 때 경우의 수 배울 때 격자점만 지나서 하는 그 원리에서 공사중이라는 조건 하나가 추가된거네
*
*
* */

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(br.readLine());

        long[][] dp = new long[N+1][M+1];
        int[][] width = new int[N][M+1];
        int[][] height = new int[N+1][M];

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());

            if (b == d) {
                width[Math.min(a, c)][b] = 1;
            } else {
                height[a][Math.min(b, d)] = 1;
            }
        }

        for (int i = 1; i < N+1; i++) {
            if(width[i-1][0] == 1)
                break;
            dp[i][0] = 1;
        }

        for (int i = 1; i < M+1; i++) {
            if(height[0][i-1] == 1)
                break;
            dp[0][i] = 1;
        }

        for (int i = 1; i < N+1; i++) {
            for (int j = 1; j < M+1; j++) {
                dp[i][j] = dp[i-1][j] + dp[i][j-1];

                if (width[i - 1][j] == 1) {
                    dp[i][j] -= dp[i-1][j];
                }

                if (height[i][j - 1] == 1) {
                    dp[i][j] -= dp[i][j - 1];
                }
            }
        }

        System.out.println(dp[N][M]);
    }
}