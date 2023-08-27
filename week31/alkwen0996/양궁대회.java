import java.util.Arrays;

public class 양궁대회 {
    // 라이언이 우승.
    // 어피치가 쏜 화살보다 많이 쏴야 그 점수를 얻음.
    // 어피치와 라이언 점수차가 최대.
    // 완탐, 각 화살은 어피치보다 1발 많이 쏘거나 or 0발

    public static int maxGap;
    public static int[] answer, lionInfo;

    public static void main(String[] args) {
        int n = 9;
        int[] info = {0, 0, 1, 2, 0, 1, 1, 1, 1, 1, 1};

        System.out.println(Arrays.toString(solution(n, info)));
    }

    public static int[] solution(final int n, final int[] info) {
        maxGap = Integer.MIN_VALUE;
        answer = new int[info.length];
        lionInfo = new int[info.length];

        dfs(0, n, info, lionInfo);

        if (maxGap == -1) {
            return new int[]{-1};
        }

        return answer;
    }

    public static void dfs(final int depth, final int n, final int[] info, final int[] lionInfo) {
        if (n == depth) {
            int gap = compareScore(info, lionInfo);

            if (maxGap <= gap) {
                maxGap = gap;
                answer = lionInfo.clone();
            }

            return;
        }

        for (int i = 0; i < info.length; i++) {
            if (info[i] < lionInfo[i]) {
                break;
            }

            lionInfo[i]++;
            dfs(depth + 1, n, info, lionInfo);
            lionInfo[i]--;
        }
    }

    public static int compareScore(final int[] info, final int[] lionInfo) {
        int aPeachScore = 0;
        int lionScore = 0;

        for (int i = 0; i < info.length; i++) {
            if (lionInfo[i] == 0 && info[i] == 0) {
                continue;
            }

            if (info[i] >= lionInfo[i]) {
                aPeachScore = aPeachScore + (10 - i);
                continue;
            }

            lionScore = lionScore + (10 - i);
        }

        if (lionScore > aPeachScore) {
            return (lionScore - aPeachScore);
        }

        return -1;
    }

}
