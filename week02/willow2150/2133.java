import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(reader.readLine());
        int[] dp = new int[Math.max(N + 1, 3)];
        dp[0] = 1;
        dp[2] = 3;
        for (int width = 4; width <= N; width += 2)
            dp[width] = (dp[width - 2] << 2) - dp[width - 4];
        System.out.print(dp[N]);
    }
}
