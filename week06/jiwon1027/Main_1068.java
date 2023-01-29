package pratice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 리프 노드를 구한다는건 재귀를 돌렸을 떄 가장 마지막 대상이라는 뜻이랑 일치함
 *
 * Solution : dfs돌려서 더 재귀탈께 없으면 그게 리프노드니까 counting해보면 될듯?
 * 구현하는 방법은 다양한데 입력이 재미있게 주어짐
 * 입력이 자신의 부모노드로 주어졌는데 부모노드라고 해서 union-find로 해서 찾아야하나 이런 생각을 잠깐 했지만
 * 그냥 있는 그대로 우린 리프노드만 구하면 되기 때문에 입력으로 들어온 숫자(부모노드)는 counting 하면안됨
 *
 *
 *
 * */

public class Main_1068 {
    static int N,K;
    static int[] data;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());

        data = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            data[i] = Integer.parseInt(st.nextToken());
        }

        K = Integer.parseInt(br.readLine());
        dfs(K);

//        System.out.println(Arrays.toString(data));
        int res= 0;

        for (int i = 0; i < N; i++) {
            if (data[i] != -2 && !find(i)){
                res++;
            }
        }

        System.out.println(res);

    }

    public static boolean find(int num){
        for (int i = 0; i < N; i++) {
            if (num == data[i])
                return true;
        }
        return false;

    }
    public static void dfs(int num){
        data[num] = -2;

        for (int i = 0; i < N; i++) {
            if (data[i] == num){
                dfs(i);
            }
        }


    }



}
