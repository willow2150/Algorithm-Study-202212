package pratice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
/*
* N <= 50,000이니까 O(N^2)으로는 풀수가없네
* 누적합으로 O(N)으로 바꾼다음 dp로 풀면될듯?
*
* */
public class Main_2616 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        int[] data = new int[N+1];
        int[] sum = new int[N+1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i < N+1; i++) {
            data[i]=Integer.parseInt(st.nextToken());
            sum[i]=sum[i-1]+data[i];
        }

        int length = Integer.parseInt(br.readLine());

        // i번째 소형기관차가 j번째 객차까지 고려했을때 최대로 운송할 수 있는 수
        int[][] dp = new int[4][N+1];

        for (int i = 1; i <= 3; i++) {
            for (int j = i * length; j <N+1; j++) {
                // length만큼 끌 수 있으니까 슬라이딩 하는것 처럼 하나씩 밀리면서 검사함
                dp[i][j] = Math.max(dp[i][j-1], dp[i-1][j-length]+(sum[j]-sum[j-length]));
            }
        }
//        System.out.println(Arrays.deepToString(dp));
        System.out.println(dp[3][N]);
    }

}
