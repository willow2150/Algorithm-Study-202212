import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        int N = Integer.parseInt(tokenizer.nextToken());
        int M = Integer.parseInt(tokenizer.nextToken());

        String[] map = new String[N];
        int[][] visited = new int[N][M];
        int[][] deltas = new int['U' + 1][];

        for (int row = 0; row < N; row++) {
            map[row] = reader.readLine();
        }

        deltas['D'] = new int[] {1, 0};
        deltas['L'] = new int[] {0, -1};
        deltas['R'] = new int[] {0, 1};
        deltas['U'] = new int[] {-1, 0};

        int numOfSafeZones = 0;
        int marking = 1;
        for (int row = 0; row < N; row++) {
            for (int col = 0; col < M; col++) {
                if (visited[row][col] != 0) {
                    continue;
                }
                if (dfs(map, row, col, visited, marking++, deltas)) {
                    numOfSafeZones++;
                }
            }
        }
        System.out.print(numOfSafeZones);
    }

    public static boolean dfs(
            String[] map, int row, int col, int[][] visited, int marking, int[][] deltas
    ) {
        if (visited[row][col] == marking) {
            return true;
        }
        if (visited[row][col] != 0) {
            return false;
        }
        visited[row][col] = marking;
        int[] delta = deltas[map[row].charAt(col)];
        return dfs(map, row + delta[0], col + delta[1], visited, marking, deltas);
    }
}
