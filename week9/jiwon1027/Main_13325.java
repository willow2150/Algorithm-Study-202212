package pratice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
* 특정 edge 들의 가중치를 증가시켜서 루트에서 모든 리프까지의 거리가 같도록 하고 edge 가중치들의 총합을 최소화 하려고 한다
* 빼는건 안되네
*
* 재귀함수 돌리면서 left, right 차이를 계속 저장한다음에 빼는게 안되니까 max값을 return 해주면서 나아가야할듯?
* 이게 간선의 값을 저장하는거니까 2 ~ 2^k+1
*
* */

public class Main_13325 {
    static int K,res,treeSize;
    static int[] weight;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        K = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        treeSize = 1<<K+1;
        weight = new int[treeSize];
        for (int i = 2; i < treeSize; i++) {
            weight[i] = Integer.parseInt(st.nextToken());
        }

//        System.out.println(Arrays.toString(weight));
        dfs(1);
        System.out.println(res);
    }

    public static int dfs(int index) {
        if (index * 2 >= treeSize) {
            res += weight[index];

            return weight[index];
        }
        else {
            int left = dfs(index * 2);
            int right = dfs(index * 2 + 1);
            res += weight[index] + Math.abs(left - right);

            return weight[index] + Math.max(left, right);
        }
    }

}
