import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
* 집을 샘터 주변에 지어서 불행도의 합이 최소가 되도록
* 불행도 = 가장 가까운 샘터까지의 거리
*
* 샘터 기준으로 좌우씩 뻗어나가면서 채우는 식으로 하는게 제일 최소임. 자신을 기준으로 하나씩 나아가는거니까 BFS네
* visited배열을 만들어야하는데 2억개를 하면 이거 공간복잡도에 영향이 없을려나. 음
* 이 위치에 이미 방문한걸 판별하려면
* 1. visited배열
* 2. Hastset - contain() ?
*
* 샘터위치를 먼저 줘서 다행이다. 만약 샘터위치까지 정하는거였으면 시뮬레이션이나 그리디 이런쪽으로 또 바꼈을듯?
*
* */

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] data = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            data[i] = Integer.parseInt(st.nextToken());
        }

        Queue<Integer> queue = new ArrayDeque<>();
        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < N; i++) {
            queue.add(data[i]);
            set.add(data[i]);
        }

        long res = 0;
        int dist = 1;
        boolean flag = false;

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int poll_data = queue.poll();
                int left = poll_data - 1;
                int right = poll_data + 1;

                if (!set.contains(left)) {
                    queue.add(left);
                    res += dist;
                    set.add(left);
                    if (--K == 0) {
                        flag = true;
                        break;
                    }
                }
                if (!set.contains(right)) {
                    queue.add(right);
                    res += dist;
                    set.add(right);
                    if (--K == 0) {
                        flag = true;
                        break;
                    }
                }


            }
            if (flag)
                break;

            dist++;
        }
        System.out.println(res);


    }
}