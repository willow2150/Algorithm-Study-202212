import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 벽장문의이동 {
    public static int wallClosetCount, usingWallClosetCount, minimumWallClosetCount;
    public static int[] usingWallClosetOrder;
    public static boolean[] usingWallClosetStats;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        wallClosetCount = Integer.parseInt(bufferedReader.readLine());

        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        int openWallClosetFirst = Integer.parseInt(stringTokenizer.nextToken());
        int openWallClosetSecond = Integer.parseInt(stringTokenizer.nextToken());

        usingWallClosetCount = Integer.parseInt(bufferedReader.readLine());
        usingWallClosetOrder = new int[usingWallClosetCount];
        usingWallClosetStats = new boolean[wallClosetCount + 1];

        for (int i = 0; i < usingWallClosetCount; i++) {
            usingWallClosetOrder[i] = Integer.parseInt(bufferedReader.readLine());
            usingWallClosetStats[usingWallClosetOrder[i]] = true;
        }

        minimumWallClosetCount = Integer.MAX_VALUE;
        countMove(0, 0, openWallClosetFirst, openWallClosetSecond);
        System.out.println(minimumWallClosetCount);
    }

    private static void countMove(final int index, final int count, final int openWallClosetFirst, final int openWallClosetSecond) {
        if (index == usingWallClosetCount) {
            minimumWallClosetCount = Math.min(minimumWallClosetCount, count);

            return;
        }

        int moveRight = Math.abs(openWallClosetFirst - usingWallClosetOrder[index]);
        int moveLeft = Math.abs(openWallClosetSecond - usingWallClosetOrder[index]);

        countMove(index + 1, count + moveRight, usingWallClosetOrder[index], openWallClosetSecond);
        countMove(index + 1, count + moveLeft, openWallClosetFirst, usingWallClosetOrder[index]);
    }

}
