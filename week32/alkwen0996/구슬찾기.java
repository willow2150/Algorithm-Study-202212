import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class 구슬찾기 {
    public static List<List<Integer>> relations;
    public static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());

        int marbleCount = Integer.parseInt(stringTokenizer.nextToken());
        int marblePairCount = Integer.parseInt(stringTokenizer.nextToken());

        relations = new ArrayList<>();

        for (int i = 0; i < marbleCount + 1; i++) {
            relations.add(new ArrayList<>());
        }

        for (int i = 0; i < marblePairCount; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());

            int heavyMarble = Integer.parseInt(stringTokenizer.nextToken());
            int lightMarble = Integer.parseInt(stringTokenizer.nextToken());

            relations.get(heavyMarble).add(lightMarble);
        }

        dp = new int[100][2];

        for (int i = 1; i <= marbleCount; i++) {
            dfs(i, i, new boolean[100]);
        }

        int half = (marbleCount + 1) / 2;
        int noMiddleMarbleCount = 0;

        for (int i = 1; i <= marbleCount; i++) {
            if (dp[i][0] >= half || dp[i][1] >= half) {
                noMiddleMarbleCount++;
            }
        }

        System.out.println(noMiddleMarbleCount);
    }

    private static void dfs(final int current, final int heavyMarble, final boolean[] isVisited) {
        isVisited[current] = true;
        List<Integer> lightMarbles = relations.get(current);

        for (int lightMarble : lightMarbles) {
            if (isVisited[lightMarble]) {
                continue;
            }

            dp[heavyMarble][0]++; // current 보다 가벼운 구슬
            dp[lightMarble][1]++; // current 보다 무거운 구슬

            dfs(lightMarble, heavyMarble, isVisited);
        }

    }

}
