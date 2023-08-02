import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    private final int[][] DELTAS = new int[][] {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    public int solution(int[][] clockHands) {
        int minNumOfOperations = Integer.MAX_VALUE;
        int[] numOfOperationsArray = new int[(int)Math.pow(4, clockHands.length)];
        int[][][] clockHandsCases = findClockHandsCases(clockHands, numOfOperationsArray);

        for (int caseIdx = 0; caseIdx < clockHandsCases.length; caseIdx++) {
            for (int row = 1; row < clockHands.length; row++) {
                int prevRow = row - 1;
                for (int col = 0; col < clockHands.length; col++) {
                    int times = 4 - clockHandsCases[caseIdx][prevRow][col];
                    if (times < 4) {
                        turnClockHand(clockHandsCases[caseIdx], row, col, times);
                        numOfOperationsArray[caseIdx] += times;
                    }
                }
            }
            if (isSealBroken(clockHandsCases[caseIdx])) {
                minNumOfOperations = Math.min(
                        minNumOfOperations,
                        numOfOperationsArray[caseIdx]
                );
            }
        }
        return minNumOfOperations;
    }

    public int[][][] findClockHandsCases(int[][] clockHands, int[] numOfOperationsArray) {
        int[][][] cases = new int[numOfOperationsArray.length][][];
        List<int[]> lineTurnCases = new ArrayList<>(cases.length);

        findLineTurnCases(lineTurnCases, new int[clockHands.length], 0);

        for (int caseIdx = 0; caseIdx < cases.length; ) {
            for (int[] lineTurnCase : lineTurnCases) {
                cases[caseIdx] = copyClockHands(clockHands);
                for (int col = 0; col < clockHands.length; col++) {
                    if (0 < lineTurnCase[col]) {
                        numOfOperationsArray[caseIdx] += lineTurnCase[col];
                        turnClockHand(cases[caseIdx], 0, col, lineTurnCase[col]);
                    }
                }
                caseIdx++;
            }
        }
        lineTurnCases.clear();
        return cases;
    }

    public void findLineTurnCases(List<int[]> lineTurnCases, int[] lineTurnCase, int col) {
        if (col == lineTurnCase.length) {
            lineTurnCases.add(Arrays.copyOf(lineTurnCase, lineTurnCase.length));
            return;
        }
        for (int times = 0; times < 4; times++) {
            lineTurnCase[col] = times;
            findLineTurnCases(lineTurnCases, lineTurnCase, col + 1);
        }
    }

    public int[][] copyClockHands(int[][] clockHands) {
        int[][] newClockHands = new int[clockHands.length][clockHands.length];
        for (int row = 0; row < clockHands.length; row++) {
            System.arraycopy(clockHands[row], 0, newClockHands[row], 0, clockHands.length);
        }
        return newClockHands;
    }

    public void turnClockHand(int[][] clockHands, int row, int col, int times) {
        clockHands[row][col] = (clockHands[row][col] + times) % 4;
        for (int[] delta : DELTAS) {
            int nr = row + delta[0];
            int nc = col + delta[1];
            if (nr < 0 || nc < 0 || nr == clockHands.length || nc == clockHands.length) {
                continue;
            }
            clockHands[nr][nc] = (clockHands[nr][nc] + times) % 4;
        }
    }

    public boolean isSealBroken(int[][] clockHands) {
        for (int direction : clockHands[clockHands.length - 1]) {
            if (direction == 0) {
                continue;
            }
            return false;
        }
        return true;
    }
}
