package pratice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
* dfs 돌리면서
* */

public class Main_15566 {
    static int N, M, data[][], fav[][], conn[][], isSelected[];
    static boolean visit[];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        data = new int[N][4];
        fav = new int[N][2];
        conn = new int[M][3];
        isSelected = new int[N];
        visit = new boolean[N];

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < 4; j++) {
                data[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            fav[i][0] = Integer.parseInt(st.nextToken()) - 1;
            fav[i][1] = Integer.parseInt(st.nextToken()) - 1;
            if (fav[i][0] == fav[i][1])
                fav[i][1] = -1;
        }
        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < 3; j++) {
                conn[i][j] = Integer.parseInt(st.nextToken())-1;
            }
        }

        dfs(0);

        System.out.println("NO");

    }

    private static void dfs(int idx) {
//        System.out.println(Arrays.toString(isSelected));

        if (idx == N) {
            if (check()) {
                System.out.println("YES");
                for (int i = 0; i < N; i++)
                    System.out.print((isSelected[i]+1) + " ");
                System.exit(0);
            }
            return;
        }

        for (int j = 0; j < 2; j++) {
            if (fav[idx][j] == -1)
                continue;

            int num = fav[idx][j];

            if (!visit[num]) {
                visit[num] = true;
                isSelected[num] = idx;
                dfs(idx + 1);
                visit[num] = false;
            }
        }

    }

    private static boolean check() {
        for(int i = 0; i < conn.length; i++) {
            int a = conn[i][0];
            int b = conn[i][1];
            int like = conn[i][2];
            int data1 = isSelected[a];
            int data2 = isSelected[b];
            if(data[data1][like] != data[data2][like])
                return false;
        }
        return true;
    }
}
