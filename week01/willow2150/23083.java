import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        final long HOLE = -1L;
        final int DIV = 1_000_000_007;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        int N = Integer.parseInt(tokenizer.nextToken());
        int M = Integer.parseInt(tokenizer.nextToken());
        int K = Integer.parseInt(reader.readLine());
        long[][] dp = new long[N + 2][M + 1];
        for (int holeIdx = 0; holeIdx < K; holeIdx++) {
            tokenizer = new StringTokenizer(reader.readLine());
            int row = Integer.parseInt(tokenizer.nextToken());
            int col = Integer.parseInt(tokenizer.nextToken());
            dp[row][col] = HOLE;
        }

        dp[0][0] = 1;
        for (int c = 1; c <= M; c++) {
            for (int r = 1; r <= N; r++) {
                if (dp[r][c] == HOLE) {
                    dp[r][c] = 0;
                    continue;
                }
                if ((c & 1) == 0) {
                    dp[r][c] = dp[r][c - 1] + dp[r + 1][c - 1] + dp[r - 1][c];
                } else {
                    dp[r][c] = dp[r - 1][c - 1] + dp[r][c - 1] + dp[r - 1][c];
                }
                dp[r][c] %= DIV;
            }
        }
        System.out.print(dp[N][M]);
    }
}
