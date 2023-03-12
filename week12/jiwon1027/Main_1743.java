import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
* 단순 그래프 문제네
* 시간도 2초고 범위도 적당하고 그냥 바로 dfs 사방탐색 돌리면 될듯
*
* */
public class Main {
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    static int N,M,K,res;
    static int[][] board;
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        board = new int[N][M];
        visited = new boolean[N][M];

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            board[x-1][y-1] = 1;
        }
//      System.out.println(Arrays.deepToString(board));

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (board[i][j] == 1){
                    int temp = res;
                    res = 1;
                    dfs(i,j);
                    res = Math.max(temp, res);
                }
            }
        }
        System.out.println(res);

    }

    public static void dfs(int x, int y){

        visited[x][y] = true;
        for (int k = 0; k < 4; k++) {
            int nx = x + dx[k];
            int ny = y + dy[k];
            if (nx<0 || ny <0 || nx>=N || ny>=M || visited[nx][ny])
                continue;
            if (board[nx][ny] == 1){
                visited[nx][ny] = true;
                res++;
                dfs(nx,ny);
            }
        }
    }
}

