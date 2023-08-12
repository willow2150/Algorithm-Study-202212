import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

class Solution {
    public int solution(int[][] board) {
        return findMinCost(board);
    }

    public int findMinCost(int[][] board) {
        final int[][] DELTAS = new int[][] {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        final int CORNER_COST = 500;
        final int STRAIGHT_COST = 100;
        final int WALL = 1;
        final int EAST = 1;
        final int SOUTH = 2;

        int boardSize = board.length;
        int[][][] minCostTable = new int[boardSize][boardSize][4];
        Queue<int[]> pq = new PriorityQueue<>(new Comparator<>() {
            @Override
            public int compare(int[] stateA, int[] stateB) {
                if (stateA[0] == stateB[0]) {
                    return 0;
                }
                return stateA[0] < stateB[0] ? -1 : 1;
            }
        });

        Arrays.fill(minCostTable[0][0], -1);
        Arrays.fill(minCostTable[boardSize - 1][boardSize - 1], Integer.MAX_VALUE);
        pq.add(new int[] {0, EAST, 0, 0});
        pq.add(new int[] {0, SOUTH, 0, 0});

        while (!pq.isEmpty()) {
            int pqSize = pq.size();
            while (0 < pqSize--) {
                int[] state = pq.poll();
                int cost = state[0];
                int direction = state[1];
                int row = state[2];
                int col = state[3];
                for (int nextDirection = 0; nextDirection < 4; nextDirection++) {
                    if ((direction ^ nextDirection) == 2) {
                        continue;
                    }
                    int nr = row + DELTAS[nextDirection][0];
                    int nc = col + DELTAS[nextDirection][1];
                    if (nr < 0 || nc < 0 || nr == boardSize || nc == boardSize) {
                        continue;
                    }
                    if (board[nr][nc] == WALL) {
                        continue;
                    }
                    int nextCost = cost + STRAIGHT_COST;
                    if (direction != nextDirection) {
                        nextCost += CORNER_COST;
                    }
                    if (minCostTable[nr][nc][nextDirection] != 0
                            && minCostTable[nr][nc][nextDirection] <= nextCost) {
                        continue;
                    }
                    minCostTable[nr][nc][nextDirection] = nextCost;
                    pq.add(new int[] {nextCost, nextDirection, nr, nc});
                }
            }
        }

        int[] costs = minCostTable[boardSize - 1][boardSize - 1];
        return Math.min(Math.min(costs[0], costs[1]), Math.min(costs[2], costs[3]));
    }
}
