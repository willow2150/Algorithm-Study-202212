import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
 * 하나의 숫자가 앞의 3개의 숫자를 더해서 나타낼 수 있을 때 좋은 수
 *
 * x + y + z = d
 * x + y = d - z
 * d를 찾는 거임
 *
 *
 * */


public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        int[] arr = new int[5001];
        boolean[] positive = new boolean[200001];
        boolean[] negative = new boolean[200001];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int cnt = 0;
        for (int i = 1; i < N; i++) {
            for (int j = 0; j < i; j++) {
                int num = arr[j] + arr[i - 1];
                if (num >= 0)
                    positive[num] = true;
                else
                    negative[-num] = true;
            }

            for (int j = 0; j < i; j++) {
                int num = arr[i] - arr[j];
                if (num >= 0 && positive[num]) {
                    cnt++;
                    break;
                } else if (num < 0 && negative[-num]) {
                    cnt++;
                    break;
                }
            }
        }

        System.out.println(cnt);
    }
}
