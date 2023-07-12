import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
 * 방문하지않은 애들을 방문처리하면서 따라가다가 safezone 처리
 *
 * */

public class Main {
    static int col, row, cnt;
    static char[][] map;
    static int[][] visited;
    static int idx = 1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        row = Integer.parseInt(st.nextToken());
        col = Integer.parseInt(st.nextToken());
        map = new char[row][col];
        visited = new int[row][col];

        for (int i = 0; i < row; i++) {
            char[] temp = br.readLine().toCharArray();
            for (int j = 0; j < col; j++) {
                map[i][j] = temp[j];
            }
        }

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (visited[i][j] == 0) {
                    bfs(i, j);
                }
            }
        }
        System.out.println(cnt);
    }



    private static void bfs(int i, int j) {
        visited[i][j] = idx;

        int[] temp = move(map[i][j]);
        int tx = i + temp[0];
        int ty = j + temp[1];

        if (visited[tx][ty] == 0) {
            bfs(tx, ty);
        }
        else {
            if (visited[tx][ty] == idx) {
                cnt++;
            }
            idx++;
        }
    }

    private static int[] move(char cur) {
        switch (cur) {
            case 'U':
                return new int[]{-1, 0};
            case 'D':
                return new int[]{1, 0};
            case 'L':
                return new int[]{0, -1};
            case 'R':
                return new int[]{0, 1};
        }
        return new int[]{0};
    }
}

