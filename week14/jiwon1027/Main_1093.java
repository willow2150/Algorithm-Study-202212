package pratice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
/*
* N이 1,000,000이고 K는 10
* 진짜 그냥 하나하나 해보는 수 밖에 없는데?
*
* O(1,000,000 * 10) 이니까 시간초과는 아닐듯?
*
* */
public class Main_1093 {
    static boolean visited[][];
    static int N, K, length;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        K = Integer.parseInt(input[1]);
        length = Integer.toString(N).length();

        visited = new boolean[1000001][11];

        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[] { N, 0 });
        visited[N][0] = true;

        int res = -1;

        while (!queue.isEmpty()) {
            int[] now = queue.poll();

            if (now[1] == K) {
                if (res < now[0]) {
                    res = now[0];
                }
                continue;
            }

            for (int i = 0; i < length - 1; i++) {
                for (int j = i + 1; j < length; j++) {
                    int next = swap(now[0], i, j);
                    if (next != -1 && !visited[next][now[1] + 1]) {
                        visited[next][now[1] + 1] = true;
                        queue.add(new int[] { next, now[1] + 1 });
                    }
                }
            }

        }

        System.out.println(res);
    }

    private static int swap(int x, int i, int j) {
        char[] input = Integer.toString(x).toCharArray();
        //System.out.println(Arrays.toString(input));

        if (i == 0 && input[j] == '0')
            return -1;

        char tmp = input[i];

        input[i] = input[j];
        input[j] = tmp;

        int res = 0;
        for (int k = 0; k < input.length; k++) {
            res *= 10;
            res += input[k] - '0';
        }
        return res;
    }
}
