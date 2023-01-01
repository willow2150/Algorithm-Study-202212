package pratice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;


/*
* 0 1 2 3으로 가다가 3에서 0으로 바뀌는데?
*
* 0
* 0 1
* 0 1 2 1
* 0 1 2 1 2 3 2 1
*
* 가장 최근에 그린걸 역순으로 하면서 반시계로 돌리면 될 듯?
*
* 음, 그러면 (현재 dict + 1) % 4
*
*
* */
public class Main_15685 {
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, -1, 0, 1};
    static boolean[][] map = new boolean[101][101];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int res = 0;
        int N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            fun(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        for(int i = 0; i < 100; i++) {
            for(int j = 0 ; j < 100; j++) {
                if(map[i][j] && map[i][j+1]&& map[i+1][j]&& map[i+1][j+1])
                    res++;
            }
        }
        System.out.println(res);
    }


    public static void fun(int x, int y, int d, int g) {
        List<Integer> dict = new ArrayList<>();
        dict.add(d);

        for(int i = 1; i < g+1; i++) {
            for(int j = dict.size() - 1; j >= 0; j--)
                dict.add((dict.get(j) + 1) % 4);
        }

        map[x][y] = true;
        for(Integer i : dict) {
            x += dx[i];
            y += dy[i];
            map[x][y] = true;
        }
    }
}
