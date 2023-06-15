import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baekjoon_15566 {
    public static final String NO = "NO", YES = "YES", NEW_LINE = "\n", SPACE = " ";
    private static int[][] flogTopics, likeFlowers, relations;
    private static int[] selectFlogs;
    private static boolean[] isSelected;
    private static int N, M;
    private static StringBuilder stringBuilder;
    private static boolean checkResult;

    public static void main(String[] args) throws IOException {
        final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());

        N = Integer.parseInt(stringTokenizer.nextToken());
        M = Integer.parseInt(stringTokenizer.nextToken());

        flogTopics = new int[N][4];
        likeFlowers = new int[N][2];
        relations = new int[M][3];

        for (int i = 0; i < N; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());

            for (int j = 0; j < 4; j++) {
                flogTopics[i][j] = Integer.parseInt(stringTokenizer.nextToken());
            }
        }

        for (int i = 0; i < N; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());

            likeFlowers[i][0] = Integer.parseInt(stringTokenizer.nextToken()) - 1;
            likeFlowers[i][1] = Integer.parseInt(stringTokenizer.nextToken()) - 1;

            if (likeFlowers[i][0] == likeFlowers[i][1]) {
                likeFlowers[i][1] = -1;
            }
        }

        for (int i = 0; i < M; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());

            for (int j = 0; j < 3; j++) {
                relations[i][j] = Integer.parseInt(stringTokenizer.nextToken()) - 1;
            }
        }

        selectFlogs = new int[N];
        isSelected = new boolean[N];
        stringBuilder = new StringBuilder();
        dfs(0);

        if (checkResult) {
            System.out.println(stringBuilder);
        } else {
            System.out.println(NO);
        }
    }

    private static void dfs(final int depth) {
        if (depth > selectFlogs.length) {
            return;
        }

        if (selectFlogs.length == depth) {
            if (isAllCheck()) {
                checkResult = true;
                stringBuilder.append(YES).append(NEW_LINE);

                for (final int flog : selectFlogs) {
                    stringBuilder.append(flog + 1).append(SPACE);
                }
            }

            return;
        }

        for (int j = 0; j < 2; j++) {
            if (likeFlowers[depth][j] == -1) {
                continue;
            }

            int index = likeFlowers[depth][j];

            if (isSelected[index]) {
                continue;
            }

            isSelected[index] = true;
            selectFlogs[index] = depth;
            dfs(depth + 1);
            isSelected[index] = false;
        }
    }

    private static boolean isAllCheck() {
        for (final int[] relation : relations) {
            final int flowerA = relation[0];
            final int flowerB = relation[1];
            final int topic = relation[2];

            final int flogA = selectFlogs[flowerA];
            final int flogB = selectFlogs[flowerB];

            if (flogTopics[flogA][topic] != flogTopics[flogB][topic]) {
                return false;
            }
        }

        return true;
    }

}
