import java.util.Arrays;

class Solution {
    static class Result {
        int[] info;
        int scoreDifference;

        Result(int[] info, int scoreDifference) {
            this.info = Arrays.copyOf(info, info.length);
            this.scoreDifference = scoreDifference;
        }
    }

    private Result bestResult;

    public int[] solution(int n, int[] info) {
        findBestResult(new int[info.length], info, info.length - 1, n);
        return bestResult == null ? new int[] {-1} : bestResult.info;
    }

    public void findBestResult(int[] myInfo, int[] enemyInfo, int startIndex, int n) {
        if (n-- == 0) {
            int myScore = 0;
            int enemyScore = 0;
            int scoreDifference;
            for (int index = 0; index < enemyInfo.length; index++) {
                if (myInfo[index] > enemyInfo[index]) {
                    myScore += (10 - index);
                } else if (enemyInfo[index] != 0 && myInfo[index] <= enemyInfo[index]) {
                    enemyScore += (10 - index);
                }
            }
            if (myScore <= enemyScore) {
                return;
            }
            scoreDifference = myScore - enemyScore;
            if (bestResult == null || bestResult.scoreDifference < scoreDifference) {
                bestResult = new Result(myInfo, scoreDifference);
                return;
            }
            return;
        }
        
        for (int index = startIndex; 0 <= index; index--) {
            myInfo[index]++;
            findBestResult(myInfo, enemyInfo, index, n);
            myInfo[index]--;
        }
    }
}
