import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    private static final int[][] DELTAS = {{0, 1}, {-1, 0}, {0, -1}, {1, 0}};
    private static final int BOUNDARY = 100;
    private static final int MAX_GENERATION = 10;

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        List<int[]> dirsList = new ArrayList<>();
        boolean[][] isCurveVertex = new boolean[BOUNDARY + 1][BOUNDARY + 1];
        int N = Integer.parseInt(reader.readLine());

        dirsList.add(new int[1]);
        for (int generation = 1; generation <= MAX_GENERATION; generation++)
            dirsList.add(new int[1 << (generation - 1)]);

        for (int curve = 0; curve < N; curve++) {
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            int x = Integer.parseInt(tokenizer.nextToken());
            int y = Integer.parseInt(tokenizer.nextToken());
            int d = Integer.parseInt(tokenizer.nextToken());
            int g = Integer.parseInt(tokenizer.nextToken());
            drawCurve(dirsList, isCurveVertex, x, y, d, g);
        }
        System.out.print(countNumOfCurveVertices(isCurveVertex));
    }

    public static void drawCurve(List<int[]> dirsList, boolean[][] isCurveVertex,
                                 int x, int y, int d, int g) {
        isCurveVertex[y][x] = true;
        for (int gen = 0; gen <= Math.min(1, g); gen++) {
            dirsList.get(gen)[0] = d;
            y += DELTAS[d][0];
            x += DELTAS[d][1];
            isCurveVertex[y][x] = true;
            d = (d + 1) % 4;
        }
        for (int currentGen = 2; currentGen <= g; currentGen++) {
            int[] currentDirs = dirsList.get(currentGen);
            int pPrevGen = currentGen - 2;
            int index = 0;
            for (int gen = 0; gen <= pPrevGen; gen++) {
                for (int dir : dirsList.get(gen)) {
                    dir ^= 2;
                    currentDirs[index++] = dir;
                    y += DELTAS[dir][0];
                    x += DELTAS[dir][1];
                    isCurveVertex[y][x] = true;
                }
            }
            for (int dir : dirsList.get(currentGen - 1)) {
                currentDirs[index++] = dir;
                y += DELTAS[dir][0];
                x += DELTAS[dir][1];
                isCurveVertex[y][x] = true;
            }
        }
    }

    public static int countNumOfCurveVertices(boolean[][] isCurveVertex) {
        int numOfCurveVertices = 0;
        for (int y = 0; y < BOUNDARY; y++) {
            for (int x = 0; x < BOUNDARY; x++) {
                if (isCurveVertex[y][x]
                        && isCurveVertex[y + 1][x]
                        && isCurveVertex[y][x + 1]
                        && isCurveVertex[y + 1][x + 1])
                    numOfCurveVertices++;
            }
        }
        return numOfCurveVertices;
    }
}
