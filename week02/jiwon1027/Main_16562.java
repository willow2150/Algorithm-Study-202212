package pratice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
* Union-Find 문제
* 1. 마지막에 싼 가격의 친구를 가져와야할 때 union을 x<y가 아닌 money[x] < money[y]로 해주기
* 2. set과 dict로 visited 체크해주기(예전에 풀었을때)
*
* */


public class Main_16562 {
    static int N,M,K;
    static int[] money, parent;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        money = new int[N+1];
        parent = new int[N+1];
        for (int i = 1; i < N+1; i++) {
            parent[i] = i;
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i < N+1; i++) {
            money[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int V = Integer.parseInt(st.nextToken());
            int W = Integer.parseInt(st.nextToken());

            union(V,W);
        }

//        System.out.println(Arrays.toString(parent));

        int sum = 0;
        for(int i=1; i<=N; i++) {
            if(parent[i] == i) {
                sum += money[i];
            }
        }
        if(K < sum) {
            System.out.println("Oh no");
        }
        else {
            System.out.println(sum);
        }
    }

    private static void union(int x, int y){
        x = find(x);
        y = find(y);

        if (money[x] < money[y])
            parent[y] = x;
        else
            parent[x] = y;
    }

    private static int find(int x){
        if (x != parent[x])
            parent[x] = find(parent[x]);
        return parent[x];
    }




}
