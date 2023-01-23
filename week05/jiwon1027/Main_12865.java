package pratice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


/*
* 그냥 단순히 배낭의 무게에 따라 그리디적으로 접근하면 분명 오류가 생긴다
* 따라서 DP를 이용한 배낭 문제
*
* */

public class Main_12865 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[][] data = new int[N][2];
        int[][] dp = new int[N+1][K+1];


        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int W = Integer.parseInt(st.nextToken());
            int V = Integer.parseInt(st.nextToken());

            data[i][0] = W;
            data[i][1] = V;

        }

        for (int i = 1; i < N+1; i++) {
            for (int j = 1; j < K+1; j++) {
                if (data[i - 1][0] <= j){
                    dp[i][j] = Math.max(dp[i - 1][j],
                            dp[i - 1][j - data[i - 1][0]] + data[i - 1][1]);
                }

                else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }

        System.out.println(dp[N][K]);
        
        
        
    }

}
