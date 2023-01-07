package pratice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_13397 {

    static int N, M;
    static int[] data;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        data = new int[N + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; ++i) {
            data[i] = Integer.parseInt(st.nextToken());
        }

        int left = 0;
        int right = 10001;

        while (left <= right) {
            int middle = (left + right) / 2;
            if (fun(middle))
                right = middle - 1;
            else
                left = middle + 1;
        }
        System.out.println(left);

    }
    public static boolean fun(int temp) {
        int min = 10001;
        int max = -1;
        int cnt = 1;

        for (int i = 1; i < N+1; i++) {
            min = Math.min(min, data[i]);
            max = Math.max(max, data[i]);
            if (max - min > temp) {
                min = 10001;
                max = -1;
                cnt++;
                i--;
            }
        }
        if (cnt <= M)
            return true;
        else
            return false;
    }

}
