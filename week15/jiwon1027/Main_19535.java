package pratice;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/*
* 일단 D, G를 구하는게 최종 목표겠네, N<=300,000 조합 안됨!
* D : 1:2:2:1 이니까 부모-자식 관계가 2:2인 애들을 고른다음 x-1 * y-1하면 경우의 수 다 나오겠는데?
* G : 1:3:1:1 이니까 정점 하나가 3보다 크다면 걔내랑 연결된 애들중에 3개만 뽑는 경우의수니까 nC3 => 시간초과 x
*
* */

public class Main_19535 {
    static int N;
    static long D, G;
    static long[] nodes;
    static Queue<int[]> queue;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());

        nodes = new long[N+1];
        queue = new ArrayDeque<>();

        int parent, child;

        for(int i=0; i<N-1; i++){
            st = new StringTokenizer(br.readLine());

            parent = Integer.parseInt(st.nextToken());
            child = Integer.parseInt(st.nextToken());

            nodes[parent]++;
            nodes[child]++;

            queue.add(new int[]{parent, child});
        }
        for(int i=1; i<N+1; i++){
            if(nodes[i] >= 3){
                G += (nodes[i] * (nodes[i] -1) * (nodes[i]-2)) / 6 ;
            }
        }
        while (!queue.isEmpty()){
            int[] temp = queue.poll();
            if (nodes[temp[0]] >=2 && nodes[temp[1]]>=2)
                D += (nodes[temp[0]]-1) * (nodes[temp[1]]-1);
        }

        if(D > G*3){
            System.out.println("D");
        }
        else if (D < G*3){
            System.out.println("G");
        }
        else{
            System.out.println("DUDUDUNGA");
        }
    }

}
