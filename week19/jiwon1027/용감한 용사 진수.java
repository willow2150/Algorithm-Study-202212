package pratice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


/*
* 진수가 힘, 민첩, 지능 모두 >= 해야 이긴다는거네
* 하나하나 다 따지면서 해야될듯?
*
* */
public class Main_14718 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int res = Integer.MAX_VALUE;

        int[][] stats = new int[N][3];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            stats[i][0] = Integer.parseInt(st.nextToken());
            stats[i][1] = Integer.parseInt(st.nextToken());
            stats[i][2] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < N; k++) {

                    int cnt = 0;

                    for (int l = 0; l < N; l++) {
                        if (stats[i][0] >= stats[l][0] && stats[j][1] >= stats[l][1] && stats[k][2] >= stats[l][2]) {
                            cnt++;
                        }
                    }

                    if (cnt >= K)
                        res = Math.min(res, stats[i][0] + stats[j][1] + stats[k][2]);
                }
            }
        }

        System.out.println(res);




    }

}
