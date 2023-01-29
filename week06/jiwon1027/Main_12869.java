package pratice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
* 이거 뮤탈이 공격해서 나오는 패턴이 6가지 밖에 안나옴
* dp 3차원 해가지고 각 scv 상태에 따라 cnt를 기억해두자
*
* */


public class Main_12869 {
    static int[][] data = {{1,9,3}, {1,3,9},{3,9,1},{3,1,9},{9,3,1},{9,1,3}};
    static int[][][] dp;
    static int min;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        int[] scv = new int[3];

        st = new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++) {
            scv[i] = Integer.parseInt(st.nextToken());
        }

        dp = new int[61][61][61];
        min = Integer.MAX_VALUE;

        dfs(scv,0);

        System.out.println(min);
    }

    public static void dfs(int[] scv, int cnt) {

        if(min <= cnt)
            return;

        int scv1 = scv[0];
        int scv2 = scv[1];
        int scv3 = scv[2];

        if(dp[scv1][scv2][scv3] != 0 && dp[scv1][scv2][scv3] <= cnt)
            return;


        if(scv1 == 0 && scv2 == 0 && scv3 == 0) {
            min = Math.min(min, cnt);
            return;
        }

        dp[scv1][scv2][scv3] = cnt;

        for(int i=0;i<6;i++) {
            dfs(new int[] {Math.max(scv1 - data[i][0], 0), Math.max(scv2 - data[i][1], 0),Math.max(scv3 - data[i][2], 0)}, cnt+1);
        }
    }

}
