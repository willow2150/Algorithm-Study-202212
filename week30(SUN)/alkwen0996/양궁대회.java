import java.util.Arrays;

public class 양궁대회 {
    // 라이언이 우승.
    // 어피치가 쏜 화살보다 많이 쏴야 그 점수를 얻음.
    // 어피치와 라이언 점수차가 최대.
    // 완탐, 각 화살은 어피치보다 1발 많이 쏘거나 or 0발

    public static void main(String[] args) {
        int n = 5;
        int[] info = {2, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0};

        System.out.println(Arrays.toString(solution(n, info)));
    }

    public static int maxGap;
    public static int[] lionInfo, answer;

    public static int[] solution(final int n, final int[] info) {
        maxGap = Integer.MIN_VALUE;
        lionInfo = new int[info.length];
        answer = new int[info.length];

        dfs(0, n, info, lionInfo);

        if (maxGap == Integer.MIN_VALUE) {
            return new int[]{-1};
        }

        return answer;
    }

    public static void dfs(final int startIndex, final int n, final int[] info, final int[] lionInfo) {
        if (n == 0) {
            checkWinner(info, lionInfo);

            return;
        }

        for (int i = startIndex; i < info.length; i++) {
            lionInfo[i]++;
            dfs(i, n - 1, info, lionInfo);
            lionInfo[i]--;
        }

    }

    private static void checkWinner(final int[] info, final int[] lionInfo) {
        int aPeachScore = 0;
        int lionScore = 0;
        int scoreGap;

        for (int i = 0; i < info.length; i++) {
            if (info[i] > lionInfo[i]) {
                aPeachScore += (10 - i);
            } else {
                if (lionInfo[i] == 0 && info[i] == 0) {
                    continue;
                }

                if (info[i] == lionInfo[i]) {
                    aPeachScore += (10 - i);
                } else {
                    lionScore += (10 - i);
                }
            }
        }

        scoreGap = lionScore - aPeachScore;

        if (scoreGap > 0) {
            if (maxGap < scoreGap) {
                maxGap = scoreGap;
                answer = lionInfo.clone();

            } else if (maxGap == scoreGap) {
                if (checkArr(answer, lionInfo)) {
                    answer = lionInfo.clone();
                }
            }
        }

    }

    public static boolean checkArr(final int[] answer, final int[] lionInfo) {
        for (int i = lionInfo.length - 1; i >= 0; i--) {
            if (answer[i] == lionInfo[i]) {
                continue;
            }

            return answer[i] <= lionInfo[i];
        }

        return false;
    }

}
