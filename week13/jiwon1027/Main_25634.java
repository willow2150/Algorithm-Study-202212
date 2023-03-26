package pratice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_25634 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());

        int[] data = new int[N];
        int[] onoff = new int[N];

        int sum = 0;
        int max_value = -5000;

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            data[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            onoff[i] = Integer.parseInt(st.nextToken());
        }

        int temp = 0;
        for (int i = 0; i < N; i++) {
            if (onoff[i] == 1) {
                sum += data[i];
                temp -= data[i];
            }
            else{
                temp += data[i];
            }
            max_value = Math.max(max_value, temp);
            if (temp < 0)
                temp = 0;
        }

        System.out.println(sum + max_value);



    }

}
