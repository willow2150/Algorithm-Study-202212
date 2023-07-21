import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
 * 부모 노드를 구하는 방법 => (A-2 / K)+1
 *
 *
 * */


public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        long N = Long.parseLong(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());

        for (int t = 0; t < Q; t++) {
            st = new StringTokenizer(br.readLine());
            long x = Long.parseLong(st.nextToken());
            long y = Long.parseLong(st.nextToken());

            if (K == 1) {
                System.out.println(Math.abs(x - y));
                continue;
            }

            long res = 0;
            while (x != y) {
                long max = Math.max(x, y);
                y = Math.min(x, y);
                x = ((max - 2) / K) + 1;
                res++;
            }
            System.out.println(res);
        }
    }
}