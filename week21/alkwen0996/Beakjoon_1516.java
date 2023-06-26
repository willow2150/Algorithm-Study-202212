import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.StringTokenizer;

public class Beakjoon_1516 {
    public static final String NEW_LINE = "\n";

    public static void main(String[] args) throws IOException {
        final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        final int N = Integer.parseInt(bufferedReader.readLine());

        StringTokenizer stringTokenizer;
        final List<List<Integer>> graph = new ArrayList<>(); // 사이클 없느 방향그래프
        final int[] time = new int[N + 1]; // 각 건물 별 건축시간
        final int[] inDegree = new int[N + 1]; // 진입차수 관계설정

        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 1; i <= N; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            final int spendTime = Integer.parseInt(stringTokenizer.nextToken());
            time[i] = spendTime;

            while (stringTokenizer.hasMoreTokens()) {
                final int requirementBuildingNumber = Integer.parseInt(stringTokenizer.nextToken());

                if (requirementBuildingNumber != -1) {
                    inDegree[i]++; // 진입차수 카운팅.
                    graph.get(requirementBuildingNumber).add(i); // 선수조건 빌딩 추가.
                }
            }
        }

        topologySort(graph, time, inDegree);
    }

    private static void topologySort(final List<List<Integer>> graph, final int[] time, final int[] inDegree) {
        final Deque<Integer> queue = new ArrayDeque<>();
        final int[] result = new int[inDegree.length];

        for (int i = 1; i < inDegree.length; i++) {
            if (inDegree[i] == 0) {
                queue.add(i);
                result[i] = time[i];
            }
        }

        while (!queue.isEmpty()) {
            final int now = queue.poll();

            for (int i = 0; i < graph.get(now).size(); i++) {
                final int requirementBuildingNumber = graph.get(now).get(i);
                inDegree[requirementBuildingNumber]--;

                result[requirementBuildingNumber] = Math.max(result[requirementBuildingNumber], result[now] + time[requirementBuildingNumber]);

                if (inDegree[requirementBuildingNumber] == 0) {
                    queue.offer(requirementBuildingNumber);
                }
            }
        }

        final StringBuilder stringBuilder = new StringBuilder();

        for (int i = 1; i < result.length; i++) {
            stringBuilder.append(result[i]).append(NEW_LINE);
        }

        System.out.println(stringBuilder);
    }
}
