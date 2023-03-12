//음식물 피하기
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        boolean[][] map = new boolean[N][M];


        for (int k = 0; k < K; k++) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken())-1;
            int m = Integer.parseInt(st.nextToken())-1;
            map[n][m] = true;
        }

        int maxNum = Integer.MIN_VALUE;
        for (int row = 0; row < N; row++) {
            for (int col = 0; col < M; col++) {
                if(map[row][col]) {
                    maxNum = bfs(map, maxNum, new Point(col, row));
                }
            }
        }
        System.out.print(maxNum);
    }

    private static int bfs(boolean[][] map, int maxNum, Point point) {
        int[][] drs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        final int Y = 0, X = 1;
        int cnt = 1;

        Stack<Point> stack = new Stack<>();
        stack.add(point);
        map[point.y][point.x] = false;

        while (!stack.isEmpty()) {
            Point nowP = stack.pop();
            for (int[] dr : drs) {
                int nextPX = nowP.x+dr[X];
                int nextPY = nowP.y+dr[Y];

                if(0 <= nextPX && nextPX < M && 0 <= nextPY && nextPY < N && map[nextPY][nextPX]) {
                    stack.add(new Point(nextPX, nextPY));
                    cnt++;
                    map[nextPY][nextPX] = false;
                }
            }
        }

        return Math.max(maxNum, cnt);
    }
}
