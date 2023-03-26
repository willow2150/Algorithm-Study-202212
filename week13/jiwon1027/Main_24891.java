package pratice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
* 사전순으로 출력해야되기 때문에 일단 정렬해야됨
*
* 순열 돌린다음에 행,열 바꿔가지고 체킹해서 맞으면 그게 마방진이라고 하네
* */

public class Main_24891 {
    static int L, N;
    static String[] board, res;
    static boolean[] visited;
    static int[] selected;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        L = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        board = new String[N];
        visited = new boolean[N];
        selected = new int[N];
        res = new String[L];

        for(int i = 0; i < N; i++) {
            board[i] = br.readLine();
        }

        Arrays.sort(board);

        dfs(0);

        System.out.println("NONE");
    }

    static void dfs(int depth) {
        if(depth == L) {
            for(int i = 0; i < L; i++) {
                res[i] = board[selected[i]];
            }
            if(isCheck()){
                for(int i = 0; i < L; i++) {
                    for(int j = 0; j < L; j++) {
                        System.out.print(res[i].charAt(j));
                    }
                    System.out.println();
                }
                System.exit(0);
            }
            return;
        }

        for(int i = 0; i < N; i++) {
            if(!visited[i]) {
                visited[i] = true;
                selected[depth] = i;
                dfs(depth+1);
                visited[i] = false;
            }
        }
    }

    public static boolean isCheck() {
        for(int i = 0; i < L; i++) {
            for(int j = 0; j < L; j++) {
                if(res[i].charAt(j) != res[j].charAt(i)) {
                    return false;
                }
            }
        }
        return true;
    }
}
