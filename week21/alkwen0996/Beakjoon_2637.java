import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Beakjoon_2637 {
    public static final String SPACE = " ";
    public static final String NEW_LINE = "\n";

    public static void main(String[] args) throws IOException {
        final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        final int N = Integer.parseInt(bufferedReader.readLine()); // 완제품 번호
        final int M = Integer.parseInt(bufferedReader.readLine()); // 부품 관계 개수

        final int[][] graph = new int[N + 1][N + 1];
        final int[] inDegree = new int[N + 1];
        final boolean[] isMiddlePart = new boolean[N + 1];

        StringTokenizer stringTokenizer;

        for (int i = 0; i < M; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());

            final int X = Integer.parseInt(stringTokenizer.nextToken());
            final int Y = Integer.parseInt(stringTokenizer.nextToken());
            final int K = Integer.parseInt(stringTokenizer.nextToken());

            graph[X][Y] = K; // X를 만드는데 Y가 K개 필요함.
            inDegree[Y]++; // 진입차수 저장 배열
            isMiddlePart[X] = true; // 중간부품여부 확인.
        }

        topologySort(graph, inDegree, isMiddlePart);
    }

    private static void topologySort(final int[][] graph, final int[] inDegree, final boolean[] isMiddlePart) {
        final Deque<Integer> deque = new ArrayDeque<>();
        final int[] result = new int[inDegree.length];

        for (int i = 1; i < inDegree.length; i++) {
            if (inDegree[i] == 0) {
                deque.add(i);
                result[i] = 1;
            }
        }

        while (!deque.isEmpty()) {
            final int partNumber = deque.poll();

            for (int i = 1; i < graph.length; i++) {
                if (graph[partNumber][i] == 0) {
                    continue;
                }

                inDegree[i]--;
                result[i] += graph[partNumber][i] * result[partNumber];

                if (inDegree[i] == 0) {
                    deque.add(i);
                }
            }
        }

        final StringBuilder stringBuilder = new StringBuilder();

        for (int i = 1; i < isMiddlePart.length; i++) {
            if (isMiddlePart[i]) {
                continue;
            }

            stringBuilder.append(i).append(SPACE).append(result[i]).append(NEW_LINE);
        }

        System.out.println(stringBuilder);
    }

}
