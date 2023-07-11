import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;
import java.util.StringTokenizer;

public class Beakjoon_19538 {
    public static final String SPACE = " ";
    private static List<List<Integer>> graph;
    private static int[] neighbor, time;
    private static Deque<Integer> queue;

    public static void main(String[] args) throws IOException {
        inputData();
        getTime();
        print();
    }

    private static void print() {
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 1; i < time.length; i++) {
            stringBuilder.append(time[i]).append(SPACE);
        }

        System.out.println(stringBuilder);
    }

    private static void getTime() {
        while (!queue.isEmpty()) {
            int current = queue.poll();

            for (int i = 0; i < graph.get(current).size(); i++) {
                int connectVertex = graph.get(current).get(i);
                neighbor[connectVertex]++;

                if (time[connectVertex] == -1 && (graph.get(connectVertex).size() + 1) / 2 <= neighbor[connectVertex]) {
                    queue.add(connectVertex);
                    time[connectVertex] = time[current] + 1;
                }
            }
        }
    }

    private static void inputData() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(bufferedReader.readLine());
        graph = new ArrayList<>(N + 1);
        neighbor = new int[N + 1];
        time = new int[N + 1];

        Arrays.fill(time, -1);

        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        StringTokenizer stringTokenizer;

        for (int i = 1; i <= N; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            int connectVertex;

            while (stringTokenizer.hasMoreTokens()) {
                connectVertex = Integer.parseInt(stringTokenizer.nextToken());

                if (connectVertex == 0) {
                    break;
                }

                graph.get(i).add(connectVertex);
            }
        }

        int M = Integer.parseInt(bufferedReader.readLine());
        queue = new ArrayDeque<>();
        stringTokenizer = new StringTokenizer(bufferedReader.readLine());

        for (int i = 0; i < M; i++) {
            int startPoint = Integer.parseInt(stringTokenizer.nextToken());
            time[startPoint] = 0;
            queue.add(startPoint);
        }
    }

}
