package pratice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeSet;

/*
* recommend 1 => 가장 어려운거 출력(여러개면 문제번호 가장 큰거)
* recommend -1 => 가장 쉬운거 출력(여러개면 문제 번호 가장 작은거)
* ====> recommend는 comparable 재정의 필요할듯
*
* add P L => 똑같은 문제 번호가 다른 난이도로 add 될 수 있음. 그럼 그 전의 add 한건 어떻게하지?
* 1. 지우는건가? (뭔가 얘가 맞는거같기도하고)
* 2. 똑같은 문제에 대해서 여러개의 난이도를 둘 수 있는건가?
* ==> 똑같은 문제에 대해 새로 넣어주면 이전에 넣었던 데이터는 get으로 안됨
*
*
* solved P => P번 문제 제거하기
* ==> 동일한 문제들은 한번에 다 사라짐
*
* 일단 입력 자체가 이상하게 들어오는 일은 없는듯? 만약 있다면 -1를 출력하거나 이런 조건이 있었을텐데 없는거보니
* => recommend, solved 명령어의 충분 조건인 "문제 하나 이상 있을때만 주어진다" 이거 따로 체크안해도 되겠네
*
* 중복된 문제에 대해서 새
*
*
* */
public class Main_21939 {
    public static int N, M;
    public static TreeSet<int[]> treeSet = new TreeSet<>(new Comparator<int[]>() {
        @Override
        public int compare(int[] o1, int[] o2) {
            if (o1[1] == o2[1])
                return o1[0] - o2[0];
            else
                return o1[1] - o2[1];
        }
    });

    public static Map<Integer,Integer> map = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        for(int i=0; i<N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int P = Integer.parseInt(st.nextToken());
            int L = Integer.parseInt(st.nextToken());

            treeSet.add(new int[]{P,L});
            map.put(P, L);
        }

        M = Integer.parseInt(br.readLine());

        for(int j=0; j<M; j++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String cmd = st.nextToken();

            if(cmd.equals("recommend")) {
                int x = Integer.parseInt(st.nextToken());

                if(x == 1) {
                    System.out.println(treeSet.last()[0]);
                }
                else if(x == -1) {
                    System.out.println(treeSet.first()[0]);
                }
            }
            else if(cmd.equals("add")){

                int P = Integer.parseInt(st.nextToken());
                int L = Integer.parseInt(st.nextToken());

                treeSet.add(new int[]{P,L});
                map.put(P, L);
            }
            else if(cmd.equals("solved")) {
                int P = Integer.parseInt(st.nextToken());
                int L = map.get(P);

                treeSet.remove(new int[]{P,L});
                map.remove(P);
//                System.out.println(P + " " + map.get(P));

            }
        }
    }

}
