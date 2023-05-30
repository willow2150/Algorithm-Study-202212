/*
* 다익스트라 기본 문제네
*
* */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int N, res;
    static int[][] board, distance;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        board = new int[N][N];
        res = Integer.MAX_VALUE;
        distance = new int[N][N];

        StringTokenizer st = null;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                distance[i][j] = Integer.MAX_VALUE;
            }
        }

        //System.out.println(Arrays.deepToString(board));

        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> (o1[2] - o2[2]));

        pq.add(new int[]{0,0,0});
        distance[0][0] = 0;

        while (!pq.isEmpty()) {
            int[] poll_data = pq.poll();
            int x = poll_data[0];
            int y = poll_data[1];
            int value = poll_data[2];

            if (distance[x][y] < value)
                continue;

            if (x == N-1 && y == N-1)
                break;

            //System.out.println(x + " " + y);

            for (int i = 0; i < 4; i++) {
                int ix = x + dx[i];
                int iy = y + dy[i];
                if (ix < 0 || iy < 0 || ix >= N || iy >= N)
                    continue;

                int difference = Math.abs(board[x][y] - board[ix][iy]);
                if (distance[ix][iy] > Math.max(value, difference)) {
                    distance[ix][iy] = Math.max(value, difference);
                    pq.add(new int[]{ix,iy,distance[ix][iy]});
                }
            }
        }

        System.out.println(distance[N-1][N-1]);
    }

}
