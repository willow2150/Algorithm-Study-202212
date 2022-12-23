package pratice;

import java.util.Arrays;
import java.util.Scanner;

public class Main_2133 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();

        if (N % 2 == 1)
            System.out.println(0);
        else {
            int dp[] = new int[31];

            dp[0] = 1;
            dp[2] = 3;

            for (int i = 4; i < 31; i+=2) {
                dp[i] = dp[i-2] * 3;
                for (int j = i-4; j >=0 ; j-=2) {
                    dp[i] += dp[j] * 2;
                }
            }


//            System.out.println(Arrays.toString(dp));
            System.out.println(dp[N]);

        }

    }
}
