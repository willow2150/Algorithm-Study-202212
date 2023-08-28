import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class 양궁대회 {
    // 라이언이 우승.
    // 어피치가 쏜 화살보다 많이 쏴야 그 점수를 얻음.
    // 어피치와 라이언 점수차가 최대.
    // 완탐, 각 화살은 어피치보다 1발 많이 쏘거나 or 0발

    public static void main(String[] args) {
        int n = 9;
        int[] info = {0, 0, 1, 2, 0, 1, 1, 1, 1, 1, 1};

        System.out.println(Arrays.toString(solution(n, info)));
    }

    public static int maxGap;
    public static int[] lionInfo, answer;

    public static int[] solution(final int n, final int[] info) {
        maxGap = Integer.MIN_VALUE;
        lionInfo = new int[info.length];
        answer = new int[info.length];

        dfs(info.length - 1, 0, n, info, lionInfo);

        if (maxGap == Integer.MIN_VALUE) {
            return new int[]{-1};
        }

        return answer;
    }

    public static void dfs(final int startIndex, final int depth, final int n, final int[] info, final int[] lionInfo) {
        if (depth == n) {
            int aPeachScore = 0;
            int lionScore = 0;
            int scoreGap;

            for (int i = 0; i < info.length; i++) {
                if (lionInfo[i] == 0 && info[i] == 0) {
                    continue;
                }

                if (info[i] >= lionInfo[i]) {
                    aPeachScore += (10 - i);
                }

                if (lionInfo[i] > info[i]) {
                    lionScore += (10 - i);
                }
            }

            scoreGap = lionScore - aPeachScore;

            if (scoreGap <= 0) {
                return;
            }

            if (maxGap < scoreGap) {
                maxGap = scoreGap;
                answer = lionInfo.clone();

                return;
            }

            return;
        }

        for (int i = startIndex; i >= 0; i--) {
            lionInfo[i]++;
            dfs(i, depth + 1, n, info, lionInfo);
            lionInfo[i]--;
        }

    }

    public static boolean checkArr(final int[] answer, final int[] lionInfo) {
        int lionIndex = lionInfo.length;

        for (int i = lionInfo.length - 1; i >= 0; i--) {
            if (lionInfo[i] > answer[i]) {
                lionIndex = i;
                break;
            }
        }

        return lionIndex != lionInfo.length;
    }

}
