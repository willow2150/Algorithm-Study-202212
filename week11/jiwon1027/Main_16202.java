package pratice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;


/*
* MST 너무 오랜만이네
* 기억나는게 union-find하면서 서로 연결되어있는지 확인하면서 그리디적으로 가장 작은거 뽑아내면서 하는걸로 기억함
* */

class Main_16202 {
    static int[] parent;
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[][] data = new int[M][3];

        for(int i=1;i<M+1;i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            data[i-1] = new int[]{x, y, i};
        }

//        System.out.println(Arrays.deepToString(data));

        for(int t=0;t<K;t++) {
            parent = new int[N+1];
            make(N);

            int res = 0;
            for(int i=t;i<M;i++) {
                int x = find(data[i][0]);
                int y = find(data[i][1]);

                if(x==y)
                    continue;
                union(x,y);
                res += data[i][2];
            }

            boolean flag = true;
            int temp = 0;
            for(int i=1;i<N+1;i++) {
                if(i==1) {
                    temp = find(1);
                }
                if(temp != find(i)) {
                    flag = false;
                    break;
                }
            }
            if(flag) {
                System.out.print(res+" ");
            }else {
                for(int i=t;i<K;i++) {
                    System.out.print("0 ");
                }
                break;
            }
        }
    }

    public static boolean union(int x, int y) {
        x = find(x);
        y = find(y);
        if(x == y) return false;
        parent[x] = y;

        return true;
    }
    public static int find(int x) {
        if (parent[x] != x)
            parent[x] = find(parent[x]);
        return parent[x];

    }
    public static void make(int N) {
        for(int i=1;i<N+1;i++) {
            parent[i] = i;
        }
    }
}