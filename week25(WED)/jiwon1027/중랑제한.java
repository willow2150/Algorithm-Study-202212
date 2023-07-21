import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
 * 모든 다리는 양방향
 *
 * 이분탐색인거같은데
 * 1 -> 2
 * 3 -> 1
 * 2 -> 3
 *
 * 1 -> 3에서 1->2->3인지 1->3인지 모르니까 한번씩 다 봐줘야하네
 *
 * */


public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        List<int[]>[] list = new ArrayList[N+1];

        for(int i=1; i<N+1; i++)
            list[i] = new ArrayList<>();

        int max = 0;
        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());
            list[A].add(new int[]{B, C});
            list[B].add(new int[]{A,C});
            max = Math.max(C, max);
        }
        st = new StringTokenizer(br.readLine());
        int tx = Integer.parseInt(st.nextToken());
        int ty = Integer.parseInt(st.nextToken());



        int start = 1;
        int end = max;
        int res = 0;
        while(start<=end) {
            boolean[] visited = new boolean[N+1];
            int mid = (start+end)/2;
            if(check(list, mid, tx, ty, visited)) {
                start = mid+1;
                res = Math.max(res, mid);
            }
            else
                end = mid-1;
        }
        System.out.println(res);
    }

    static boolean check(List<int[]>[] list, int limit, int start, int end, boolean [] b) {
        if(b[start])
            return false;

        b[start] = true;

        if(start == end)
            return true;

        for(int[] v : list[start]) {
            if(v[1] >= limit) {
                if(check(list, limit, v[0], end, b)){
                    return true;
                }
            }
        }

        return false;
    }


}