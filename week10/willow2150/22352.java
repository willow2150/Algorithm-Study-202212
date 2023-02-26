import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static final int[][] DELTAS = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    private static int N, M;

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        N = Integer.parseInt(tokenizer.nextToken());
        M = Integer.parseInt(tokenizer.nextToken());
        int[][][] grids = new int[2][N][M];
        boolean[][] isChecked = new boolean[N][M];
        int vaccinationCnt = 0;

        for (int gridIdx = 0; gridIdx < 2; gridIdx++) {
            int[][] grid = grids[gridIdx];
            for (int row = 0; row < N; row++) {
                tokenizer = new StringTokenizer(reader.readLine());
                for (int col = 0; col < M; col++)
                    grid[row][col] = Integer.parseInt(tokenizer.nextToken());
            }
        }

        for (int row = 0; row < N; row++) {
            for (int col = 0; col < M; col++) {
                if (isChecked[row][col] ||
                        grids[0][row][col] == grids[1][row][col]) {
                    continue;
                }
                dfs(grids, isChecked, row, col, grids[0][row][col], grids[1][row][col]);
                vaccinationCnt++;
            }
        }
        System.out.print(
                isSameGrid(grids[0], grids[1]) && vaccinationCnt <= 1 ? "YES" : "NO"
        );
    }

    public static void dfs(int[][][] grids, boolean[][] isChecked,
                           int row, int col, int beforeValue, int afterValue) {
        isChecked[row][col] = true;
        grids[0][row][col] = afterValue;
        for (int[] delta : DELTAS) {
            int nr = row + delta[0];
            int nc = col + delta[1];
            if (nr < 0 || nc < 0 || nr == N || nc == M
                    || grids[0][nr][nc] != beforeValue
                    || isChecked[nr][nc]) {
                continue;
            }
            dfs(grids, isChecked, nr, nc, beforeValue, afterValue);
        }
    }

    public static boolean isSameGrid(int[][] gridA, int[][] gridB) {
        for (int row = 0; row < N; row++)
            for (int col = 0; col < M; col++)
                if (gridA[row][col] != gridB[row][col])
                    return false;
        return true;
    }
}
