import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static final int[][] DELTAS = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    private static int[][] counter;
    private static int[][] wood;
    private static int n;

    public static void main(String[] args) {
        if (!inputWood()) return;
        int maxNumOfSpacesToMove = 0;

        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                if (counter[row][col] == 0) {
                    maxNumOfSpacesToMove = Math.max(
                            maxNumOfSpacesToMove,
                            feedPandaWithBamboo(row, col)
                    );
                }
            }
        }
        System.out.print(maxNumOfSpacesToMove);
    }

    public static boolean inputWood() {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            n = Integer.parseInt(reader.readLine());
            counter = new int[n][n];
            wood = new int[n][n];
            for (int row = 0; row < n; row++) {
                StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
                int[] woodLine = wood[row];
                for (int col = 0; col < n; col++)
                    woodLine[col] = Integer.parseInt(tokenizer.nextToken());
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static int feedPandaWithBamboo(int row, int col) {
        for (int[] delta : DELTAS) {
            int nr = row + delta[0];
            int nc = col + delta[1];
            if (nr < 0 || nc < 0 || nr == n || nc == n || wood[row][col] >= wood[nr][nc])
                continue;
            counter[row][col] = Math.max(
                    counter[row][col],
                    counter[nr][nc] == 0 ? feedPandaWithBamboo(nr, nc) : counter[nr][nc]
            );
        }
        return ++counter[row][col];
    }
}
