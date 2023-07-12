import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
 * bfs로 내 주변사람들 탐색하면 될 듯
 *
 * */

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        List<Integer>[] list = new ArrayList[N+1];
        int[] time = new int[N + 1];
        int[] data = new int[N + 1];
        Queue<Integer> queue = new ArrayDeque<>();

        for (int i = 0; i < N+1; i++) {
            list[i] = new ArrayList<>();
            time[i] = -1;
        }

        for (int i = 1; i < N+1; i++) {
            String[] input = br.readLine().split(" ");
            for (int j = 0; j < input.length-1; j++) {
                int x = Integer.parseInt(input[j]);
                list[i].add(x);
                list[x].add(i);
            }
//            System.out.println(list[i].toString());
        }
        int M = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<M; i++) {
            int node = Integer.parseInt(st.nextToken());
            time[node] = 0;
            queue.add(node);
        }


        while(!queue.isEmpty()) {
            int cur = queue.poll();

            for(int next: list[cur]) {
                data[next]++;

                if(time[next] == -1 && (list[next].size() + 1)/2 <= data[next]) {
                    queue.add(next);
                    time[next] = time[cur]+1;
                }
            }
        }

        for (int i = 1; i < N+1; i++) {
            System.out.print(time[i] + " ");
        }

    }
}

