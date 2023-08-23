import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

class Solution {
    static class Point {
        Point another;
        int number;
        int time;

        public Point(Point another, int number, int time) {
            this.another = another;
            this.number = number;
            this.time = time;
        }
    }

    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        Point[] graph = getGraph(n, paths);
        return findMinIntensityPointInfo(n, gates, summits, graph);
    }

    public Point[] getGraph(int n, int[][] paths) {
        Point[] graph = new Point[n + 1];
        for (int[] path : paths) {
            int pointANumber = path[0];
            int pointBNumber = path[1];
            int time = path[2];
            graph[pointANumber] = new Point(graph[pointANumber], pointBNumber, time);
            graph[pointBNumber] = new Point(graph[pointBNumber], pointANumber, time);
        }
        return graph;
    }

    public int[] findMinIntensityPointInfo(int n, int[] gates, int[] summits, Point[] graph) {
        Set<Integer> summitSet = new HashSet<>();
        Queue<int[]> pq = new PriorityQueue<>(new Comparator<>() {
            @Override
            public int compare(int[] stateA, int[] stateB) {
                if (stateA[1] == stateB[1]) {
                    if (stateA[0] == stateB[0]) {
                        return 0;
                    }
                    return stateA[0] < stateB[0] ? -1 : 1;
                }
                return stateA[1] < stateB[1] ? -1 : 1;
            }
        });
        int[] fastestArrivalTimes = new int[n + 1];
        int[] minIntensityPointInfo = new int[2];

        Arrays.fill(fastestArrivalTimes, Integer.MAX_VALUE);
        Arrays.fill(minIntensityPointInfo, Integer.MAX_VALUE);

        for (int summit : summits) {
            summitSet.add(summit);
        }

        for (int gate : gates) {
            fastestArrivalTimes[gate] = 0;
            pq.add(new int[] {gate, 0});
        }

        while (!pq.isEmpty()) {
            int[] state = pq.poll();
            int pointNumber = state[0];
            int time = state[1];
            if (fastestArrivalTimes[pointNumber] < time) {
                continue;
            }
            Point point = graph[pointNumber];
            while (point != null) {
                int nextTime = Math.max(time, point.time);
                int nextPointNumber = point.number;
                if (nextTime < fastestArrivalTimes[nextPointNumber]) {
                    fastestArrivalTimes[nextPointNumber] = nextTime;
                    if (summitSet.contains(nextPointNumber)) {
                        if (nextTime < minIntensityPointInfo[1]) {
                            minIntensityPointInfo[0] = nextPointNumber;
                            minIntensityPointInfo[1] = nextTime;
                        } else if (nextTime == minIntensityPointInfo[1]) {
                            if (nextPointNumber < minIntensityPointInfo[0]) {
                                minIntensityPointInfo[0] = nextPointNumber;
                            }
                        }
                    } else {
                        pq.add(new int[] {nextPointNumber, nextTime});
                    }
                }
                point = point.another;
            }
        }

        return minIntensityPointInfo;
    }
}
