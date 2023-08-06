class Solution {
    public int solution(int n, int s, int a, int b, int[][] fares) {
        final int MAX_COST_SUM = 20_000_000;
        int[][] minCostTable = new int[n + 1][n + 1];
        int minCostSum = MAX_COST_SUM;

        for (int dep = 1; dep <= n; dep++) {
            for (int arr = dep + 1; arr <= n; arr++) {
                minCostTable[dep][arr] = minCostTable[arr][dep] = MAX_COST_SUM;
            }
        }

        for (int[] fare : fares) {
            int locA = fare[0];
            int locB = fare[1];
            int cost = fare[2];
            minCostTable[locA][locB] = minCostTable[locB][locA] = cost;
        }

        for (int loc = 1; loc <= n; loc++) {
            for (int dep = 1; dep <= n; dep++) {
                for (int arr = 1; arr <= n; arr++) {
                    minCostTable[dep][arr] = Math.min(
                            minCostTable[dep][arr],
                            minCostTable[dep][loc] + minCostTable[loc][arr]
                    );
                }
            }
        }

        for (int loc = 1; loc <= n; loc++) {
            minCostSum = Math.min(
                    minCostSum,
                    minCostTable[s][loc] + minCostTable[loc][a] + minCostTable[loc][b]
            );
        }

        return minCostSum;
    }
}
