import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        int N = Integer.parseInt(tokenizer.nextToken());
        int K = Integer.parseInt(tokenizer.nextToken());
        int[][] coordinates = new int[N][2];
        int[][] dp = new int[N][K + 1];
        int[][] distance = new int[N][N];

        tokenizer = new StringTokenizer(reader.readLine());
        coordinates[0][0] = Integer.parseInt(tokenizer.nextToken());
        coordinates[0][1] = Integer.parseInt(tokenizer.nextToken());
        for (int i = 1; i < N; i++) {
            tokenizer = new StringTokenizer(reader.readLine());
            coordinates[i][0] = Integer.parseInt(tokenizer.nextToken());
            coordinates[i][1] = Integer.parseInt(tokenizer.nextToken());
            for (int j = 0; j < i; j++) {
                distance[j][i] = Math.abs(coordinates[i][0] - coordinates[j][0]) 
                        + Math.abs(coordinates[i][1] - coordinates[j][1]);
            }
            dp[i][0] = dp[i - 1][0] + distance[i - 1][i];
        }

        for (int n = 1; n < N; n++) {
            for (int k = Math.min(n - 1, K); k > 0; k--) {
                dp[n][k] = dp[n][0];
                for (int m = 0; m <= k; m++) {
                    if (n - 1 == k && k != m) continue;
                    dp[n][k] = Math.min(
                            dp[n][k], 
                            dp[n - m - 1][k - m] + distance[n - m - 1][n]
                    );
                }
            }
        }
        System.out.print(dp[N - 1][Math.min(N - 2, K)]);
    }
}
