import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

class Solution {
    public int solution(int k, int n, int[][] reqs) {
        Queue<int[]> combinations = new ArrayDeque<>();
        int[][] timeTable = new int[k + 1][n];
        int[] counselorsCountTable = new int[k + 1];
        int[] selected = new int[n];
        int minTotalWaitingTime = Integer.MAX_VALUE;

        for (int i = 0; i < k; i++) {
            selected[i] = i + 1;
        }
        findCombinations(combinations, selected, 1, k, k);

        while (!combinations.isEmpty()) {
            int[] combination = combinations.poll();
            int totalWaitingTime = 0;

            for (int consultingType : combination) {
                counselorsCountTable[consultingType]++;
            }

            for (int[] req : reqs) {
                int consultingType = req[2];
                totalWaitingTime += calcMinWaitingTime(
                        req,
                        timeTable[consultingType],
                        counselorsCountTable[consultingType]
                );
            }

            minTotalWaitingTime = Math.min(minTotalWaitingTime, totalWaitingTime);

            for (int[] endTimes : timeTable) {
                Arrays.fill(endTimes, 0);
            }

            for (int consultingType : combination) {
                counselorsCountTable[consultingType] = 0;
            }
        }

        return minTotalWaitingTime;
    }

    public void findCombinations(
            Queue<int[]> combinations, int[] selected, int left, int right, int index
    ) {
        if (index == selected.length) {
            combinations.add(Arrays.copyOf(selected, selected.length));
            return;
        }
        for (int consultingType = left; consultingType <= right; consultingType++) {
            selected[index] = consultingType;
            findCombinations(combinations, selected, consultingType, right, index + 1);
        }
    }

    public static int calcMinWaitingTime(int[] req, int[] endTimes, int numOfCounselors) {
        int startTime = req[0];
        int timeTaken = req[1];
        int fastestEndTime = Integer.MAX_VALUE;
        int targetCounselorIndex = 0;

        for (int idx = 0; idx < numOfCounselors; idx++) {
            if (endTimes[idx] <= startTime) {
                endTimes[idx] = startTime + timeTaken;
                return 0;
            }
            if (endTimes[idx] < fastestEndTime) {
                fastestEndTime = endTimes[idx];
                targetCounselorIndex = idx;
            }
        }
        endTimes[targetCounselorIndex] += timeTaken;

        return fastestEndTime - startTime;
    }
}
