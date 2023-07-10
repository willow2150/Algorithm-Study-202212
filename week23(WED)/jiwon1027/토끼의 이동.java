import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
 * A(N) = A(N-1) + N
 * A(N) = A(N-1) - N
 *
 *
 * */

public class Main {
    static int N,K;
    static long[] dp;
    static int x,y;
    static long score = 1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        String str = br.readLine();

        dp = new long[2*N-1];
        dp[0] = 1;
        for (int i = 1; i < N; i++) {
            dp[i] = dp[i - 1] + i;
        }

        dp[2*N-2] = (long) N *N;
        for (int i = 2; i < N; i++) {
            dp[2 * N - i - 1] = dp[2 * N - i] - i;
        }
//        System.out.println(Arrays.toString(dp));


        for(int i=0;i<K;i++){
            move(str.charAt(i));
//            System.out.println(x + " "+ y);
            setScore();
        }

        System.out.println(score);
    }

    private static void move(char c) {
        switch (c){
            case 'U':
                x--;
                break;
            case 'D':
                x++;
                break;
            case 'L':
                y--;
                break;
            case 'R':
                y++;
                break;
        }
    }
    private static void setScore() {
        score += dp[x + y] + correction();
    }

    private static int correction() {
        int tx = x;
        int ty = y;
        if (tx+ty >= N) {
            tx = N-1-tx;
            ty = N-1-ty;
            int tmp = tx;
            tx = ty;
            ty = tmp;
        }
        return (ty+tx)%2==0 ? ty : tx;
    }
}



