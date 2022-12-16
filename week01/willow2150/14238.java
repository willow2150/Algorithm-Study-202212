import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String S = reader.readLine();
        int days = S.length();
        boolean[][][][][] dp = new boolean[days + 1][days + 1][days + 1][3][3];
        int[] counter = new int[3];
        char[] attendance = new char[days];
        for (int day = 0; day < days; day++)
            counter[S.charAt(day) - 'A']++;
        System.out.print(
                dfs(dp, counter, attendance, -1, -1) ?
                        String.valueOf(attendance) : -1
        );
    }

    public static boolean dfs(boolean[][][][][] dp, int[] counter,
                              char[] attendance, int yesterday, int twoDaysAgo) {
        if (twoDaysAgo >= 0) {
            if (dp[counter[0]][counter[1]][counter[2]][yesterday][twoDaysAgo]) return false;
            dp[counter[0]][counter[1]][counter[2]][yesterday][twoDaysAgo] = true;
        }
        int day = attendance.length - counter[0] - counter[1] - counter[2];
        if (day == attendance.length) return true;
        if (counter[0] > 0) {
            attendance[day] = 'A';
            counter[0]--;
            if (dfs(dp, counter, attendance, 0, yesterday)) return true;
            counter[0]++;
        }
        if (counter[1] > 0 && yesterday != 1) {
            attendance[day] = 'B';
            counter[1]--;
            if (dfs(dp, counter, attendance, 1, yesterday)) return true;
            counter[1]++;
        }
        if (counter[2] > 0 && yesterday != 2 && twoDaysAgo != 2) {
            attendance[day] = 'C';
            counter[2]--;
            if (dfs(dp, counter, attendance, 2, yesterday)) return true;
            counter[2]++;
        }
        return false;
    }
}
