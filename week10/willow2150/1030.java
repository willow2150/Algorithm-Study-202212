import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static final char BLACK = '1';
    private static final char WHITE = '0';
    private static int N;
    private static int startBoundary, endBoundary;

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        StringBuilder output = new StringBuilder();
        int s = Integer.parseInt(tokenizer.nextToken());
        N = Integer.parseInt(tokenizer.nextToken());
        int k = Integer.parseInt(tokenizer.nextToken());
        int r1 = Integer.parseInt(tokenizer.nextToken());
        int r2 = Integer.parseInt(tokenizer.nextToken());
        int c1 = Integer.parseInt(tokenizer.nextToken());
        int c2 = Integer.parseInt(tokenizer.nextToken());

        startBoundary = (N - k) >> 1;
        endBoundary = (N + k) >> 1;

        int size = (int) Math.pow(N, s);
        for (int row = r1; row <= r2; row++) {
            for (int col = c1; col <= c2; col++)
                output.append(findColor(row, col, size));
            output.append('\n');
        }
        System.out.print(output);
    }

    public static char findColor(int row, int col, int size) {
        if (size == 1) return WHITE;
        size /= N;
        if (startBoundary * size <= row && row < endBoundary * size
                && startBoundary * size <= col && col < endBoundary * size) {
            return BLACK;
        }
        return findColor(row % size, col % size, size);
    }
}
