import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
* 정사각형 모양으로 수확할 수 있음
* 1x1로 해서 양수인 애들만 골라서 하면 이득아님? 아. 한번만 수확할 수 있네.
* 즉, 정사각형 모양으로 한번 딱 수확을 했을 떄 가장 이득이 될때는 언제인가를 보는거네
* If all value < 0:
*   가장 최소값 1x1하는게 베스트
* else:
*   양수 음수 섞인 logic
*
* 과수원을 수확해서 최대한 이득을 얻기 위해선? -> 모르지. 규칙이 없는데 하나씩 해보는게 답일듯?
* 90000 + 22500 + 10000 + ... 브루트포스로 하면 시간초과는 안날듯
*
* 사각형 형태니까 누적합 이용해서 하면 편리할듯
* 2x2의 사각형 누적합은 (2,2)의 값에 저장되는 그런 원리
*
*
* */

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        int[][] data = new int[N+1][N+1];
        int[][] prefixSum = new int[N+1][N+1];

        for (int i = 1; i < N+1; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j < N+1; j++) {
                data[i][j] = Integer.parseInt(st.nextToken());
                prefixSum[i][j] = prefixSum[i - 1][j] + prefixSum[i][j - 1] - prefixSum[i - 1][j - 1] + data[i][j];
            }
        }

//        System.out.println(Arrays.deepToString(data));
//        System.out.println(Arrays.deepToString(prefixSum));

        int res = prefixSum[1][1];

        for (int k = 0; k < N; k++) {
            for (int i = 1; i < N-k+1; i++) {
                for (int j = 1; j < N-k+1; j++) {
                    int temp = prefixSum[i + k][j + k] - prefixSum[i + k][j - 1] - prefixSum[i - 1][j + k] + prefixSum[i - 1][j - 1];
                    res = Math.max(res, temp);
                }
            }
        }

        System.out.println(res);

    }
}