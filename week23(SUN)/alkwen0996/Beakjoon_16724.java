import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.util.StringTokenizer;

public class Beakjoon_16724 {
    private static char[][] map;
    private static int[][] countCircleMap;
    private static int N, M, circleCount;

    public static void main(String[] args) throws IOException {
        inputData();
        findSafeZone();
        System.out.println(circleCount - 1);
    }

    private static void findSafeZone() {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if (countCircleMap[i][j] != 0) {
                    continue;
                }

                countCircleMap[i][j] = dfs(i, j);
            }
        }
    }

    private static int dfs(final int i, final int j) {
        if (i < 0 || j < 0 || i >= map.length || j >= map[0].length || countCircleMap[i][j] == -1) {
            return circleCount++;
        }

        if (countCircleMap[i][j] > 0) {
            return countCircleMap[i][j];
        }

        int x = i, y = j;
        countCircleMap[i][j] = -1;

        if (map[i][j] == 'U') {
            x--;
        } else if (map[i][j] == 'D') {
            x++;
        } else if (map[i][j] == 'L') {
            y--;
        } else if (map[i][j] == 'R') {
            y++;
        }

        countCircleMap[i][j] = dfs(x, y);

        return countCircleMap[i][j];
    }

    private static void inputData() throws IOException {
        final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        final StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());

        N = Integer.parseInt(stringTokenizer.nextToken());
        M = Integer.parseInt(stringTokenizer.nextToken());

        map = new char[N][M];
        countCircleMap = new int[N][M];
        circleCount = 0;

        for (int i = 0; i < N; i++) {
            char[] line = bufferedReader.readLine().toCharArray();
            System.arraycopy(line, 0, map[i], 0, line.length);
        }

    }

}
