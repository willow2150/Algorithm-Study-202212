import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
 * start ~ end 까지 팰린드롬이 이루어지는지 보고싶음
 * 범위가 상당히 크기 때문에 연속된 요청에 대해서 시간을 짧게 하는게 좋아보이니까 dp로 하면 좋을듯?
 *
 * length = 1 -> 무조껀 팰린드롬
 * length = 2 -> 2개가 같으면 팰린드롬
 * length >= 3 -> 양 끝수가 같고 나머지가 팰린드롬(true)이면
 *
 * */


public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        int[] data = new int[N + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            data[i] = Integer.parseInt(st.nextToken());
        }

        boolean[][] check = new boolean[N + 1][N + 1];

        for (int i = 0; i < N + 1; i++) {
            check[i][i] = true;
        }

        for (int i = 1; i < N; i++) {
            if (data[i] == data[i + 1]) {
                check[i][i + 1] = true;
            }
        }

        for (int i = 2; i < N; i++) {
            for (int j = 1; i + j < N+1; j++) {
                if(check[j+1][j+i-1] && data[j]==data[j+i])
                    check[j][j+i] = true;
            }
        }
        StringBuilder sb = new StringBuilder();
        int M = Integer.parseInt(br.readLine());
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            sb.append(check[start][end] ? 1 : 0);
            sb.append('\n');
        }
        System.out.println(sb.toString());


    }
}

