package pratice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

/*
* 내 친구의 친구 => 우리팀
* 내 원수의 원수 => 우리팀
*
* 내 친구의 원수 => 적
* 내 원수의 친구 => 적
* 
* "내 친구의 원수의 원수"는 우리팀인가? => 아님
*
* 서로 연결되는 관계가 있으니까 공통으로 되는애들을 묶으려면 유니온 파인드해서 부모노드 통일 시킨다음에 몇팀 나오나 보면되겠네
* 팀이 최대로 나오려면 어떻게 해야하지? 유니온 파인드로 한 애가 최대가 나온다는 증명을 어떻게 할지 모르겠는데.
*
*
* */

public class Main_1765 {
    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        parent = new int[N+1];

        List<Integer>[] enemy = new ArrayList[N+1];
        List<Integer>[] friend = new ArrayList[N+1];

        for(int i=1; i<N+1; i++) {
            friend[i] = new ArrayList<>();
            enemy[i] = new ArrayList<>();
            parent[i] = i;
        }

        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            if("E".equals(st.nextToken())) {
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                enemy[a].add(b);
                enemy[b].add(a);

            } else {
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                friend[a].add(b);
                friend[b].add(a);
            }
        }
        
        // 원수의 원수
        for(int i=1; i<N+1; i++) {
            for(int j=0; j<enemy[i].size(); j++) {
                int n = enemy[i].get(j);
                for(int k=0; k<enemy[n].size(); k++) {
                    if(i==enemy[n].get(k))
                        continue;
                    friend[i].add(enemy[n].get(k));
                    friend[enemy[n].get(k)].add(i);
                }
            }
        }

        for(int i=1; i<N+1; i++) {
            for(int j=0; j<friend[i].size(); j++) {
                union(i, friend[i].get(j));
            }
        }

        //System.out.println(Arrays.toString(parent));

        Set<Integer> set = new HashSet<>();
        for(int i=1; i<N+1; i++) {
            set.add(parent[i]);
        }
        System.out.println(set.size());
    }

    public static int find(int i) {
        if (parent[i] != i)
            parent[i] = find(parent[i]);
        return parent[i];
    }

    public static void union(int a, int b) {
        a = find(a);
        b = find(b);
        if(a != b)
            parent[b] = a;
    }
}
