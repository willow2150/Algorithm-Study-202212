import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
 * 하루에 최대 하나의 당근을 먹을 수 있고 당근을 먹지 않을 수도 있다
 * 당근이 없으면 심고, 당근이 제대로 있으면 영양제 투입
 *
 * 그럼 영양제가 큰 애들은 나중에 먹는게 이득이네
 *
 * 1 3
 * 2 9
 * 3 7
 *
 * */


public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int T = Integer.parseInt(st.nextToken());
        long res = 0;
        int[][] data = new int[N][2];


        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            data[i][0] = Integer.parseInt(st.nextToken());
            data[i][1] = Integer.parseInt(st.nextToken());
            res += data[i][0];
        }

        Arrays.sort(data, (o1, o2) -> (o1[1] - o2[1]));

        for (int i = 0; i < N; i++) {
            res += (long) data[i][1] * (T - N + i);
        }

        System.out.println(res);
    }


}
