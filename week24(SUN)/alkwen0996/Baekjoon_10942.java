import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Baekjoon_10942 {
    public static final String NEW_LINE = "\n";
    public static final int OK = 1;
    public static final int NO = 0;
    private static int[] numbers;
    private static int n;
    private static boolean[][] isPalindrome;
    private static List<Range> ranges;

    public static void main(String[] args) throws IOException {
        inputData();
        checkPalindrome();
        print();
    }

    private static void print() {
        StringBuilder stringBuilder = new StringBuilder();

        for (Range range : ranges) {
            if (isPalindrome[range.startIndex][range.endIndex]) {
                stringBuilder.append(OK).append(NEW_LINE);
                continue;
            }

            stringBuilder.append(NO).append("\n");
        }

        System.out.println(stringBuilder);
    }

    private static void checkPalindrome() {
        for (int i = 1; i <= n; i++) {
            isPalindrome[i][i] = true; // 1개 팰린드롬
        }

        for (int i = 1; i <= n - 1; i++) {
            if (numbers[i] == numbers[i + 1]) {
                isPalindrome[i][i + 1] = true; // 2개 팰린드롬
            }
        }

        for (int i = 3; i <= n; i++) { // 팰린드롬 길이
            for (int j = 1; j <= n - i + 1; j++) { // 시작점
                int end = j + i - 1; // 끝점

                if (numbers[j] == numbers[end] && isPalindrome[j + 1][end - 1]) {
                    isPalindrome[j][end] = true;
                }
            }
        }
    }

    private static void inputData() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(bufferedReader.readLine());
        numbers = new int[n + 1];
        isPalindrome = new boolean[n + 1][n + 1];

        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());

        for (int i = 1; i <= n; i++) {
            numbers[i] = Integer.parseInt(stringTokenizer.nextToken());
        }

        int m = Integer.parseInt(bufferedReader.readLine());
        ranges = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            ranges.add(new Range(
                    Integer.parseInt(stringTokenizer.nextToken()),
                    Integer.parseInt(stringTokenizer.nextToken()))
            );
        }
    }

    static class Range {
        private int startIndex;
        private int endIndex;

        public Range(final int startIndex, final int endIndex) {
            this.startIndex = startIndex;
            this.endIndex = endIndex;
        }
    }

}
