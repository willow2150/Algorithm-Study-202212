package pratice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
* 각 칸에서는 아래쪽, 오른쪽 위, 오른쪽 아래 칸으로만 이동
* 벌집에는 구멍 칸이 있을 수도 있는데, 구멍 칸으로는 이동할 수 없다.
*
* 1행 1열 -> N행 M열 까지의 경로의 개수 dfs로 하면 될 듯
*
* 근데 1000x1000인 board에서 과연 dfs가 될까? DP로 풀어야됨
*
* 1. dp table의 범위가 엄청 크기 때문에 long으로 해야함
* 2. table의 1열의 값은 어떻게 될까? 아래로 내려오는경우 밖에 없는데 도중에 빈칸이 있으면 그 아래는 경우가 되면 값은 0이 되야함
* 3. dp의 각 값도 F로 나눠줘야함
* */

public class Main_23083 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int K = Integer.parseInt(br.readLine());

        long[][] dp = new long[N+2][M+2];
        boolean[][] blank = new boolean[N+2][M+2];

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            blank[x][y] = true;
        }

        long F = 1000000000+7;


        for (int i = 1; i < N+1; i++) {
            if (!blank[i][1]){
                dp[i][1] = 1;
            }
            else{
                break;
            }
        }

        for (int j = 2; j < M+1 ; j++) {
            for (int i = 1; i < N+1; i++) {
                if (blank[i][j])
                    continue;

                if (j % 2 == 1){ //홀수
                    dp[i][j] = dp[i-1][j]% F+ dp[i][j-1]% F + dp[i-1][j-1]% F;
                }else{ //짝수
                    dp[i][j] = dp[i - 1][j]% F + dp[i][j - 1]% F + dp[i+1][j-1]% F;
                }
            }
        }

//        for (int i = 1; i < N+1; i++) {
//            for (int j = 1; j < M+1; j++) {
//                System.out.print(dp[i][j]+" ");
//            }
//            System.out.println();
//        }

        System.out.println(dp[N][M] % F) ;
    }
}
