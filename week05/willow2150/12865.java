import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        int N = Integer.parseInt(tokenizer.nextToken());
        int K = Integer.parseInt(tokenizer.nextToken());
        int[] dp = new int[K + 1];
        for (int goodsIdx = 0; goodsIdx < N; goodsIdx++) {
            tokenizer = new StringTokenizer(reader.readLine());
            int W = Integer.parseInt(tokenizer.nextToken());
            int V = Integer.parseInt(tokenizer.nextToken());
            for (int weight = K; weight >= W; weight--)
                dp[weight] = Math.max(dp[weight], dp[weight - W] + V);
        }
        System.out.print(dp[K]);
    }
}
