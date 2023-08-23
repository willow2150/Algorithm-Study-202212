
public class 광물캐기 {
    public static final String DIAMOND = "diamond";
    public static final String IRON = "iron";
    // 곡괭이는 한번 사용하면 사용할 수 없을때까지 사용한다.
    // 광물은 주어진 순대로만 캘 수 있다.
    // 사용할 곡괭이가 없거나 광물이 없을때까지 캔다.
    private static int minimalFatigue;

    public static void main(String[] args) {
        int[] picks = {1, 3, 2}; // 다이아, 철, 돌 곡괭이 순.
//        int[] picks = {0, 1, 1}; // 다이아, 철, 돌 곡괭이 순.
        String[] minerals = {"diamond", "diamond", "diamond", "iron", "iron", "diamond", "iron", "stone"};

        System.out.println(solution(picks, minerals));
    }

    public static int solution(final int[] picks, final String[] minerals) {
        minimalFatigue = Integer.MAX_VALUE;

        for (int i = 0; i < picks.length; i++) {
            if (picks[i] == 0) {
                continue;
            }

            int[] copyPicks = new int[picks.length];
            System.arraycopy(picks, 0, copyPicks, 0, picks.length);
            copyPicks[i]--;

            dfs(i, copyPicks, minerals, 0, 0);
        }

        return minimalFatigue;
    }

    public static void dfs(final int pickType, int[] picks, final String[] minerals, int fatigue, int startIndex) {
        int mineralCount = 0;

        for (int i = startIndex; i < minerals.length; i++) {
            if (mineralCount >= 5) {
                break;
            }

            String mineral = minerals[i];

            if (pickType == 0) {
                fatigue += 1;
            } else if (pickType == 1) {
                if (DIAMOND.equals(mineral)) {
                    fatigue += 5;
                } else {
                    fatigue += 1;
                }
            } else {
                if (DIAMOND.equals(mineral)) {
                    fatigue += 25;
                } else if (IRON.equals(mineral)) {
                    fatigue += 5;
                } else {
                    fatigue += 1;
                }
            }

            mineralCount++;
        }

        startIndex += mineralCount;

        if (fatigue >= minimalFatigue) {
            return;
        }

        if (isAllPicksUsed(picks) || startIndex >= minerals.length) {
            minimalFatigue = fatigue;

            return;
        }

        for (int i = 0; i < picks.length; i++) {
            if (picks[i] <= 0) {
                continue;
            }

            int[] copyPicks = new int[picks.length];
            System.arraycopy(picks, 0, copyPicks, 0, picks.length);
            copyPicks[i]--;
            dfs(i, copyPicks, minerals, fatigue, startIndex);
        }

    }

    public static boolean isAllPicksUsed(final int[] picks) {
        return picks[0] == 0 && picks[1] == 0 && picks[2] == 0;
    }

}
