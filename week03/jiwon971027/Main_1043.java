package pratice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/*
 * 자기가 알고 있는 비밀을 누군가가 알고있냐 모르냐에 대한 데이터가 주어진 거고
 *
 * 각 파티마다 오는사람들이 다른데 그 구성원들의 부모노드가 같은지(진실을 알고있는지) -> Union-Find
 *
 * */

public class Main_1043 {
    static int N, M;
    static int[] parent;
    static boolean[] data;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        parent = new int[N+1];
        for(int i=1;i<N+1; i++) {
            parent[i] = i;
        }

        data = new boolean[51];

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        for(int i=0; i<n; i++) {
            data[Integer.parseInt(st.nextToken())] = true;
        }

        List<Integer>[] party_people = new ArrayList[M];
        for(int i=0; i<M; i++) {
            party_people[i] = new ArrayList<>();
        }



        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());

            int std = 0;

            for(int j=0; j<n; j++) {
                int temp = Integer.parseInt(st.nextToken());
                party_people[i].add(temp);

                if (j == 0)
                    std = temp;
                union(temp, std);
            }
        }

        for(int i=1; i<data.length; i++) {
            if(data[i]) {
                data[find(i)] = true;
            }
        }

        int res = 0;
        for(int i=0; i<M; i++) {
            if(party_people[i].size() > 0) {
                int value = find(party_people[i].get(0));
                if(!data[value])
                    res++;
            }
        }


        System.out.println(res);

    }


    static int find(int x){
        if (parent[x] !=x)
            parent[x] = find(parent[x]);
        return parent[x];
    }

    static void union(int x, int y){
        int a = find(x);
        int b = find(y);

        if (a>b)
            parent[a] = b;
        else if (a<b)
            parent[b] = a;
    }


}
