import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        final int MAX_SCV_LIFE = 60;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        reader.readLine();
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        int[][][] dp = new int[MAX_SCV_LIFE + 1][MAX_SCV_LIFE + 1][MAX_SCV_LIFE + 1];
        int[][] attackCases = new int[][]{
                {9, 3, 1}, {9, 1, 3}, {3, 9, 1}, {3, 1, 9}, {1, 9, 3}, {1, 3, 9}
        };
        int scvALife = 0, scvBLife = 0, scvCLife = 0;

        if (tokenizer.hasMoreTokens()) scvALife = Integer.parseInt(tokenizer.nextToken());
        if (tokenizer.hasMoreTokens()) scvBLife = Integer.parseInt(tokenizer.nextToken());
        if (tokenizer.hasMoreTokens()) scvCLife = Integer.parseInt(tokenizer.nextToken());
        System.out.print(findMinNumOfAttack(dp, attackCases, scvALife, scvBLife, scvCLife));
    }

    public static int findMinNumOfAttack(int[][][] dp, int[][] attackCases,
                                         int scvALife, int scvBLife, int scvCLife) {
        if (dp[scvALife][scvBLife][scvCLife] != 0) return dp[scvALife][scvBLife][scvCLife];
        if (scvALife == 0 && scvBLife == 0 && scvCLife == 0) return 0;
        dp[scvALife][scvBLife][scvCLife] = Integer.MAX_VALUE;
        for (int[] attackCase : attackCases) {
            int nextScvALife = Math.max(scvALife - attackCase[0], 0);
            int nextScvBLife = Math.max(scvBLife - attackCase[1], 0);
            int nextScvCLife = Math.max(scvCLife - attackCase[2], 0);
            dp[scvALife][scvBLife][scvCLife] = Math.min(
                    dp[scvALife][scvBLife][scvCLife],
                    findMinNumOfAttack(
                            dp, attackCases, nextScvALife, nextScvBLife, nextScvCLife
                    )
            );
        }
        return ++dp[scvALife][scvBLife][scvCLife];
    }
}
