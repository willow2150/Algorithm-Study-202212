import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
* 건물을 짓기 위해 특정건물이 먼저 지어져있어야 한다는건 위상정렬을 이용하라는 소리 같음
* why? 위상정렬이 순서를 정해놓고 차례로 수행하라는거니까
* */

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());

        List<Integer>[] list = new ArrayList[N + 1];
        int[] inDegree = new int[N + 1];
        int[] time = new int[N + 1];
        int[] result = new int[N + 1];

        for (int i = 1; i < N+1; i++) {
            list[i] = new ArrayList<>();
        }

        for (int i = 1; i < N+1; i++) {
            st = new StringTokenizer(br.readLine());
            time[i] = Integer.parseInt(st.nextToken());

            while (st.hasMoreTokens()) {
                int temp = Integer.parseInt(st.nextToken());
                if (temp != -1) {
                    inDegree[i]++;
                    list[temp].add(i);
                }
            }
        }

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 1; i < N+1; i++) {
            if (inDegree[i] == 0) {
                queue.offer(i);
                result[i] = time[i];
            }
        }

        while (!queue.isEmpty()) {
            int poll_data = queue.poll();
            for (int i = 0; i < list[poll_data].size(); i++) {
                int next = list[poll_data].get(i);
                inDegree[next]--;

                result[next] = Math.max(result[next], result[poll_data] + time[next]);

                if (inDegree[next] == 0) {
                    queue.add(next);
                }
            }
        }

        for (int i = 1; i < N+1; i++) {
            System.out.println(result[i]);
        }

    }
}