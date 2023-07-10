import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
* 하루에 최대 한 곳에서만 강연
* 이득충이 되는 최적의 루트를 짜는거구나
*
* 기간순 정렬 -> 페이순 정렬
*
* */

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());

        PriorityQueue<int[]> pq_list = new PriorityQueue<>((o1, o2) -> o1[1]-o2[1]);
        for (int i = 1; i < N + 1; i++) {
            st = new StringTokenizer(br.readLine());
            pq_list.add(new int[]{Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken())});
        }

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        while (!pq_list.isEmpty()){
            int[] cur = pq_list.poll();
            pq.add(cur[0]);
            if(pq.size() > cur[1])
                pq.poll();
        }
        int res = 0;
        while (!pq.isEmpty())
            res += pq.poll();

        System.out.println(res);

    }

}