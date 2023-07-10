import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
* 비교 결과가 모순되는 입력은 없다고 가정

* A > B > C
* B > D
* 라면 D가 어디 들어갈지 모름
*
* 그럼 주어진 A > B에 대해서 명확히 안다고 표시를 해야하는데
* 2차원배열로 해볼까?
*
* A > B 라는 조건이 있으면 check[A][B] = 1, check[B][A] = -1
*
* 그럼 여기서 B > C가 주어진다면? check[B][C] = 1, check[C][B]= -1
*
* check[A][C], check[C][A]도 추가해야되는데 이 로직은 어떻게 생겨야되는거지?
* AB , BC => AC이니까 플로이드와샬 느낌으로 std하나 고정시키고 for돌리면 될꺼같은데
*
* 그럼 AB, BC가 1이거나 -1일때로 해야겠네
* */

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        int check[][] = new int[N+1][N+1];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int front = Integer.parseInt(st.nextToken());
            int back = Integer.parseInt(st.nextToken());

            check[front][back] = 1;
            check[back][front] = -1;
        }

        for(int k = 1; k < N+1; k++) {
            for(int i = 1; i < N+1; i++) {
                for(int j = 1; j < N+1; j++) {
                    if(check[i][k] == 1 && check[k][j] == 1) {
                        check[i][j] = 1;
                        check[j][i] = -1;
                    }
                    if(check[i][k] == -1 && check[k][j] == -1) {
                        check[i][j] = -1;
                        check[j][i] = 1;
                    }
                }
            }
        }

        for(int i = 1; i < N+1; i++) {
            int res = 0;
            for(int j = 1; j < N+1; j++) {
                if(i == j)
                    continue;
                if(check[i][j] == 0)
                    res++;
            }
            System.out.println(res);
        }


    }
}