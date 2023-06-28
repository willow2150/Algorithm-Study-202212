import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Beakjoon_20002 {
    public static void main(String[] args) throws IOException {
        final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        final int length = Integer.parseInt(bufferedReader.readLine());
        final int[][] map = new int[length + 1][length + 1];

        StringTokenizer stringTokenizer;

        for (int i = 1; i <= length; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());

            for (int j = 1; j <= length; j++) {
                map[i][j] = Integer.parseInt(stringTokenizer.nextToken());
            }
        }

        getMaximumRevenue(length, map);
    }

    private static void getMaximumRevenue(final int length, final int[][] map) {
        final int[][] sum = new int[map.length][map.length];

        for (int i = 1; i <= length; i++) {
            for (int j = 1; j <= length; j++) {
                sum[i][j] = map[i][j] + sum[i - 1][j] + sum[i][j - 1] - sum[i - 1][j - 1];
            }
        }

        int maxRevenue = Integer.MIN_VALUE;

        for (int k = 0; k <= length; k++) {
            for (int i = 1; i <= length - k; i++) {
                for (int j = 1; j <= length - k; j++) {
                    maxRevenue = Math.max(maxRevenue, sum[i + k][j + k] - sum[i - 1][j + k] - sum[i + k][j - 1] + sum[i - 1][j - 1]);
                }
            }
        }

        System.out.println(maxRevenue);
    }
}
