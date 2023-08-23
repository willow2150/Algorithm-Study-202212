import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    public int solution(int[] picks, String[] minerals) {
        final String DIAMOND = "diamond";
        final String IRON = "iron";
        final String STONE = "stone";
        final int DIAMOND_TYPE = 0;
        final int IRON_TYPE = 1;
        final int STONE_TYPE = 2;

        List<int[]> selectedPicksList = new ArrayList<>();
        int[] mineralTypes = new int[minerals.length];
        int numOfPicks = Math.min((minerals.length + 4) / 5, picks[0] + picks[1] + picks[2]);
        int numOfMineralsAvailable = Math.min(minerals.length, 5 * numOfPicks);
        int minFatigue = Integer.MAX_VALUE;

        for (int mineralIdx = 0; mineralIdx < minerals.length; mineralIdx++) {
            String mineral = minerals[mineralIdx];
            if (DIAMOND.equals(mineral)) {
                mineralTypes[mineralIdx] = DIAMOND_TYPE;
                continue;
            }
            if (IRON.equals(mineral)) {
                mineralTypes[mineralIdx] = IRON_TYPE;
                continue;
            }
            if (STONE.equals(mineral)) {
                mineralTypes[mineralIdx] = STONE_TYPE;
            }
        }

        findPickSelectionCases(selectedPicksList, new int[numOfPicks], picks, numOfPicks);

        for (int[] selectedPicks : selectedPicksList) {
            int fatigue = 0;
            for (int mineralIdx = 0; mineralIdx < numOfMineralsAvailable; mineralIdx++) {
                fatigue += calcFatigue(
                        selectedPicks[mineralIdx / 5], mineralTypes[mineralIdx]
                );
            }
            minFatigue = Math.min(minFatigue, fatigue);
        }

        return minFatigue;
    }

    public void findPickSelectionCases(
            List<int[]> selectedPicksList, int[] selectedPicks, int[] picks, int numOfPicks
    ) {
        if (numOfPicks == 0) {
            selectedPicksList.add(Arrays.copyOf(selectedPicks, selectedPicks.length));
            return;
        }
        for (int pickType = 0; pickType <= 2; pickType++) {
            if (picks[pickType] == 0) {
                continue;
            }
            selectedPicks[selectedPicks.length - numOfPicks] = pickType;
            picks[pickType]--;
            findPickSelectionCases(selectedPicksList, selectedPicks, picks, numOfPicks - 1);
            picks[pickType]++;
        }
    }

    public int calcFatigue(int pickNumber, int mineralNumber) {
        return (int)Math.pow(5, Math.max(0, pickNumber - mineralNumber));
    }
}
