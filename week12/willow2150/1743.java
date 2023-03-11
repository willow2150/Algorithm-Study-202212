import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static final int[][] DELTAS = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    private static int N, M, K;

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        N = Integer.parseInt(tokenizer.nextToken());
        M = Integer.parseInt(tokenizer.nextToken());
        K = Integer.parseInt(tokenizer.nextToken());

        boolean[][] isFood = new boolean[N + 1][M + 1];
        int maxFoodSize = 0;

        for (int i = 0; i < K; i++) {
            tokenizer = new StringTokenizer(reader.readLine());
            int row = Integer.parseInt(tokenizer.nextToken());
            int col = Integer.parseInt(tokenizer.nextToken());
            isFood[row][col] = true;
        }

        for (int row = 1; row <= N; row++)
            for (int col = 1; col <= M; col++)
                if (isFood[row][col])
                    maxFoodSize = Math.max(dfs(isFood, row, col), maxFoodSize);

        System.out.print(maxFoodSize);
    }

    public static int dfs(boolean[][] isFood, int row, int col) {
        int foodSize = 1;
        isFood[row][col] = false;
        K--;
        for (int[] delta : DELTAS) {
            int nr = row + delta[0];
            int nc = col + delta[1];
            if (nr == 0 || nc == 0 || N < nr || M < nc || !isFood[nr][nc]) continue;
            foodSize += dfs(isFood, nr, nc);
        }
        return foodSize;
    }
}
