package pratice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
* 1. 아무 문자나 골라 k(1 ~ 25)만큼 +해서 바꾼다
* 2. 문자를 바꿀 때마다 k를 다시 고른다?
* 3. k의 합이 s가 될 때까지 문자를 계속 바꿈. 한번 바꾼 문자는 다시 x
*
* 이름이 바뀔 수 있는 경우의 수를 구하자
* */

public class Main_14437 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int s = Integer.parseInt(br.readLine());
        String problem = br.readLine();
        int length = problem.length();

        long dp[][] = new long[length+1][s+1];

        dp[0][0] = 1;
        for(int i=0;i<length;i++) {
            for(int j=0;j<s+1;j++) {
                if(dp[i][j]!=0) {
                    for(int k=0;k<26;k++) {
                        if(j+k>s)
                            continue;
                        dp[i+1][j+k] = (dp[i+1][j+k]+ dp[i][j])%1000000007;
                    }
                }
            }
        }

        System.out.println(dp[length][s]);




    }

}
