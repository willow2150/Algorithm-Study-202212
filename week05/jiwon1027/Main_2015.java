package pratice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/*
* 1
* 12
* 123
* 1234
*
* 2
* 23
* 234
*
* ...
*
* 이런식으로 하게되면 N(N+1)/2 니까 O(N^2)이라서 시간초과 => Map을 쓰자
*
* */

public class Main_2015 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] data = new int[N+1];

        long res = 0;

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i < N+1; i++) {
            data[i] = data[i - 1] + Integer.parseInt(st.nextToken());

            if (data[i] == K)
                res++;
        }

        Map<Integer, Long> map = new HashMap<>();

        for (int i = 1; i < N+1; i++) {
            // data[i] - data[j] = K , data[i] - K = data[j]
            if(map.containsKey(data[i] - K)) {
                res += map.get(data[i] - K);
            }

            if (map.containsKey(data[i])) {
                map.put(data[i], map.get(data[i]) + 1);
            } else {
                map.put(data[i], 1L);
            }
        }

        System.out.println(res);


    }

}
