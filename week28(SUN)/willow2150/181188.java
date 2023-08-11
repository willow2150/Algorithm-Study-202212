import java.util.Arrays;
import java.util.Comparator;

class Solution {
    public int solution(int[][] targets) {
        Arrays.sort(targets, new Comparator<>() {
            public int compare(int[] targetA, int[] targetB) {
                if (targetA[1] == targetB[1]) {
                    return 0;
                }
                return targetA[1] < targetB[1] ? -1 : 1;
            }
        });

        int missileRightPoint = -1;
        int numOfDefenseMissiles = 0;

        for (int[] target : targets) {
            if (target[0] < missileRightPoint) {
                continue;
            }
            missileRightPoint = target[1];
            numOfDefenseMissiles++;
        }

        return numOfDefenseMissiles;
    }
}
