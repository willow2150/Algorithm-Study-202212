import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
 * 3이 들어올 때 set.isEmpty면 -1
 * 아니면 현재 위치보다 큰 애들 중 가장 작은 애를 찾음
 *
 * */


public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        TreeSet<Integer> treeSet = new TreeSet<>();
        int cur = 0;

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int temp = Integer.parseInt(st.nextToken());
            if (temp == 1) {
                treeSet.add(i);
            }
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            String order = st.nextToken();
            if (order.equals("3")) {
                if (treeSet.isEmpty()) {
                    System.out.println(-1);
                    continue;
                }
                Integer upper = treeSet.ceiling(cur);
                int lower = treeSet.first();

                if (upper == null) {
                    System.out.println(N - cur + lower);
                }
                else{
                    System.out.println((upper-cur));
                }
            } else if (order.equals("2")) {
                cur = (cur + Integer.parseInt(st.nextToken())) % N;
            } else {
                int temp = Integer.parseInt(st.nextToken()) - 1;
                if (treeSet.contains(temp)) {
                    treeSet.remove(temp);
                } else {
                    treeSet.add(temp);
                }
            }
        }


    }
}