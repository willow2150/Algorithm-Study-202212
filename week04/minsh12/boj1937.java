package month01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_boj1937_욕심쟁이판다 {

    static int N, res;
    static int[][] map, dp;
    static int[] dr = {1, -1, 0, 0};
    static int[] dc = {0, 0, 1, -1};

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());    // 대나무 숲의 크기
        map = new int[N][N];
        dp = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                    res = Math.max(res, dfs(i, j));
            }
        }
        System.out.println(res);
    }

    private static int dfs(int r, int c) {
        // 저장된 값 있으면 해당 값 반환
        if (dp[r][c] != 0) {
            return dp[r][c];
        }

        dp[r][c] = 1;   // 대나무숲에서 최소 1년은 삶
        for (int d = 0; d < 4; d++) {
            int nr = r + dr[d];
            int nc = c + dc[d];

            if (nr < 0 || nc < 0 || nr >= N || nc >= N)     // 범위 벗어난 경우
                continue;
            if (map[nr][nc] <= map[r][c])                   // 다음 숲이 현재 숲보다 적거나 같은 양의 대나무가 있는 경우
                continue;

            dp[r][c] = Math.max(dp[r][c], dfs(nr, nc) + 1); // 가장 오랜기간 생존할 수 있는 기간 계산
        }
        return dp[r][c];
    }
}
