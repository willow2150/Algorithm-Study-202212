import java.util.Arrays;

class Solution {
    static class Location {
        Location another;
        int number;

        Location(Location another, int number) {
            this.another = another;
            this.number = number;
        }
    }

    public int[] solution(int n, int[][] roads, int[] sources, int destination) {
        Location[] graph = new Location[n + 1];

        for (int[] road : roads) {
            int locNumberA = road[0];
            int locNumberB = road[1];
            graph[locNumberA] = new Location(graph[locNumberA], locNumberB);
            graph[locNumberB] = new Location(graph[locNumberB], locNumberA);
        }

        int[] shortestTimes = getShortestTimes(graph, n, destination);
        int[] result = new int[sources.length];

        for (int srcIndex = 0; srcIndex < sources.length; srcIndex++) {
            int shortestTime = shortestTimes[sources[srcIndex]];
            result[srcIndex] = (shortestTime == Integer.MAX_VALUE ? -1 : shortestTime);
        }

        return result;
    }

    public int[] getShortestTimes(Location[] graph, int n, int dep) {
        int[] shortestTimes = new int[n + 1];
        int[] queue = new int[n];
        int head = 0;
        int tail = 0;

        Arrays.fill(shortestTimes, Integer.MAX_VALUE);
        queue[tail++] = dep;
        shortestTimes[dep] = 0;

        for (int time = 1; head < tail; time++) {
            int size = tail - head;
            while (0 < size--) {
                Location location = graph[queue[head++]];
                while (location != null) {
                    int locNumber = location.number;
                    if (time < shortestTimes[locNumber]) {
                        shortestTimes[locNumber] = time;
                        queue[tail++] = locNumber;
                    }
                    location = location.another;
                }
            }
        }

        return shortestTimes;
    }
}
