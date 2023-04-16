import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_13903 {

    public static void main(String[] args) throws IOException {
        final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());

        final int row = Integer.parseInt(stringTokenizer.nextToken());
        final int height = Integer.parseInt(stringTokenizer.nextToken());

        final int[][] map = new int[row][height];

        for (int i = 0; i < row; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());

            for (int j = 0; j < height; j++) {
                map[i][j] = Integer.parseInt(stringTokenizer.nextToken());
            }
        }

        final int ruleCount = Integer.parseInt(bufferedReader.readLine());
        final Point[] moveRoles = new Point[ruleCount];

        for (int i = 0; i < ruleCount; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            moveRoles[i] = new Point(Integer.parseInt(stringTokenizer.nextToken()), Integer.parseInt(stringTokenizer.nextToken()));
        }

        findPath(moveRoles, map);
    }

    private static void findPath(final Point[] moveRoles, final int[][] map) {
        final Queue<Data> queue = new PriorityQueue<>();
        boolean[][] isVisited = new boolean[map.length][map[0].length];
        int minimumStep = Integer.MAX_VALUE;

        for (int i = 0; i < map[0].length; i++) {
            if (map[0][i] == 1) {
                queue.add(new Data(0, i, 0));
            }
        }

        while (!queue.isEmpty()) {
            final Data data = queue.poll();

            if (data.x == map.length - 1) {
                if (minimumStep > data.stepCount) {
                    minimumStep = data.stepCount;
                }
            }

            for (final Point moveRole : moveRoles) {
                final int nextX = data.x + moveRole.x;
                final int nextY = data.y + moveRole.y;

                if (nextX >= 0 && nextY >= 0 && map.length > nextX && map[0].length > nextY) {
                    if (map[nextX][nextY] == 1 && !isVisited[nextX][nextY]) {
                        queue.add(new Data(nextX, nextY, data.stepCount + 1));
                        isVisited[nextX][nextY] = true;
                    }
                }
            }
        }

        if (minimumStep == Integer.MAX_VALUE) {
            System.out.println(-1);
        } else {
            System.out.println(minimumStep);
        }
    }

    static class Data implements Comparable<Data> {
        private final int x;
        private final int y;
        private final int stepCount;

        public Data(final int x, final int y, final int stepCount) {
            this.x = x;
            this.y = y;
            this.stepCount = stepCount;
        }


        @Override
        public int compareTo(final Data data) {
            if (data.stepCount > stepCount) {
                return -1;
            } else if (data.stepCount < stepCount) {
                return 1;
            }

            return 0;
        }
    }

}