import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws Exception {
        final long DIV = 1_000_000_007;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int s = Integer.parseInt(reader.readLine());
        String problem = reader.readLine();

        long[][] dp = new long[2][s + 1];
        long[] prev = dp[0];
        long[] current = dp[1];
        long[] temp;
        int problemLength = problem.length();

        current[0] = prev[0] = 1;

        for (int strLength = 1; strLength <= problemLength; strLength++) {
            temp = prev;
            prev = current;
            current = temp;
            current[1] = strLength;
            for (int sVal = 1; sVal <= s; sVal++) {
                if (sVal >= 26) {
                    current[sVal] =
                            (prev[sVal] + current[sVal - 1] - prev[sVal - 26] + DIV) % DIV;
                } else {
                    current[sVal] =
                            (prev[sVal] + current[sVal - 1]) % DIV;
                }
            }
        }
        System.out.print(current[s]);
    }
}
