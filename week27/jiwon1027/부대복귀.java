import java.util.*;

class Solution {
    public int[] solution(int n, int[][] roads, int[] sources, int destination) {
        int[] distance = new int[n + 1];
        Arrays.fill(distance, -1);
        Map<Integer, Set<Integer>> c = new HashMap<>();
        for (int [] r : roads) {
            c.computeIfAbsent(r[0], x -> new HashSet<>()).add(r[1]);
            c.computeIfAbsent(r[1], x -> new HashSet<>()).add(r[0]);
        }
        Queue<Integer> q = new LinkedList<>();
        q.add(destination);
        distance[destination] = 0;
        while (!q.isEmpty()) {
            Integer p = q.remove();
            int d = distance[p] + 1;
            for (Integer adj : c.get(p)) {
                if (distance[adj] == -1) {
                    distance[adj] = d;
                    q.add(adj);
                }
            }
        }
        int[] answer = new int[sources.length];
        for (int i = 0; i < sources.length; i++) {
            answer[i] = distance[sources[i]];
        }
        return answer;
    }
}