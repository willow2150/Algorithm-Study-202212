package pratice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
* 하나씩 이익 바라보고 하면 될듯
* */
public class Main_11501 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        while(T-- > 0) {
            int N = Integer.parseInt(br.readLine());
            int data[] = new int[N];
            st = new StringTokenizer(br.readLine());
            for(int i = 0; i < N; i ++) {
                data[i] = Integer.parseInt(st.nextToken());
            }

            long max = 0;
            long res = 0;
            for(int i = N-1; i >= 0; i--) {
                if(data[i] > max) {
                    max = data[i];
                } else {
                    res += max - data[i];
                }
            }
            System.out.println(res);
        }

    }

}
