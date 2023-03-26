package pratice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

/*
* 나이트로 이동한다는건 예시이며 각 예제마다 규칙을 새로 주는데 그거대로 움직여야한다는거임
* 최소를 구하는게 핵심
*
* 맨 위에 세로 블럭을 모두 큐에 넣은다음에 bfs 1번만 돌리면됨
* 각 세로 블럭마다 bfs를 돌려서 그것들 중에 최소값을 구하게 되면 1000개에 대해 각각 bfs를 돌리고 시간초과 날거같음
*
* 그러면 제일 먼저 아래줄에 오는 녀석이 제일 빨리 온거니까  그냥 거기서 bfs 종료시키고 출력하면 될듯?
*
* */

public class Main_13903 {
    static int R,C,N;
    static int[] dx,dy;
    static int[][] board,dp;
    static Queue<int[]> queue = new ArrayDeque<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        board = new int[R][C];

        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < C; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        N = Integer.parseInt(br.readLine());

        dx = new int[N];
        dy = new int[N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            dx[i] = Integer.parseInt(st.nextToken());
            dy[i] = Integer.parseInt(st.nextToken());
        }

        dp = new int[R][C];

        for (int i = 0; i < R; i++) {
            Arrays.fill(dp[i],-1);
        }

        for (int i = 0; i < C; i++) {
            if (board[0][i] == 1){
                queue.add(new int[]{0,i});
                dp[0][i] = 0;
            }
        }
        bfs();

    }

    public static void bfs(){
        while(!queue.isEmpty()){
            int[] temp = queue.poll();
            int x = temp[0];
            int y = temp[1];

            if (x == R-1){
                System.out.println(dp[x][y]);
                return;
            }

            for (int i = 0; i < N; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                if (nx < 0 || ny < 0 || nx >= R || ny >= C)
                    continue;
                if (dp[nx][ny] == -1 && board[nx][ny] == 1){
                    queue.add(new int[]{nx,ny});
                    dp[nx][ny] = dp[x][y] + 1;
                }
            }
        }
        System.out.println(-1);

    }

}
