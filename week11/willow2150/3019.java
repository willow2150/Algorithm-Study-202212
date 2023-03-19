import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        int C = Integer.parseInt(tokenizer.nextToken());
        int P = Integer.parseInt(tokenizer.nextToken());

        tokenizer = new StringTokenizer(reader.readLine());
        int[] mapHeights = new int[C];
        for (int col = 0; col < C; col++)
            mapHeights[col] = Integer.parseInt(tokenizer.nextToken());

        // [block number], [shape number], [row], [column]
        int[][][][] allBlocks = new int[][][][]{
                {},
                {{{1}, {1}, {1}, {1}}, {{1, 1, 1, 1}}},
                {{{1, 1}, {1, 1}}},
                {{{0, 1, 1}, {1, 1, 0}}, {{1, 0}, {1, 1}, {0, 1}}},
                {{{1, 1, 0}, {0, 1, 1}}, {{0, 1}, {1, 1}, {1, 0}}},
                {
                        {{0, 1, 0}, {1, 1, 1},}, {{0, 1}, {1, 1}, {0, 1}},
                        {{1, 1, 1}, {0, 1, 0}}, {{1, 0}, {1, 1}, {1, 0}}
                },
                {
                        {{0, 0, 1}, {1, 1, 1}}, {{1, 0}, {1, 0}, {1, 1}},
                        {{1, 1, 1}, {1, 0, 0}}, {{1, 1}, {0, 1}, {0, 1}}
                },
                {
                        {{1, 0, 0}, {1, 1, 1}}, {{1, 1}, {1, 0}, {1, 0}},
                        {{1, 1, 1}, {0, 0, 1}}, {{0, 1}, {0, 1}, {1, 1}}
                }
        };

        System.out.print(findNumOfCases(allBlocks[P], mapHeights, C));
    }

    public static int findNumOfCases(int[][][] blocks, int[] mapHeights, int C) {
        int numOfCases = 0;

        for (int[][] block : blocks) {
            int blockHeight = block.length;
            int blockWidth = block[0].length;
            int[] blockHeightDiff = new int[blockWidth];

            for (int blockCol = 0; blockCol < blockWidth; blockCol++) {
                for (int blockRow = blockHeight - 1; blockRow >= 0; blockRow--) {
                    if (block[blockRow][blockCol] == 1) break;
                    blockHeightDiff[blockCol]++;
                }
            }

            for (int col = 0; col < C; col++) {
                if (col + blockWidth > C) break;
                int boundary = blockWidth - 1;
                numOfCases++;
                for (int blockCol = 0; blockCol < boundary; blockCol++) {
                    if (mapHeights[col + blockCol] + blockHeightDiff[blockCol + 1]
                            != mapHeights[col + blockCol + 1] + blockHeightDiff[blockCol]) {
                        numOfCases--;
                        break;
                    }
                }
            }
        }
        return numOfCases;
    }
}
