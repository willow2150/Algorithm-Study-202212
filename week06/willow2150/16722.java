import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
    private static final int NUM_OF_PICTURES = 9;
    private static final int NUM_OF_ATTRIBUTES = 3;

    public static void main(String[] args) throws Exception {
        final String[][] ATTRIBUTES = new String[][]{
                {"CIRCLE", "TRIANGLE", "SQUARE"},
                {"YELLOW", "RED", "BLUE"},
                {"GRAY", "WHITE", "BLACK"}
        };
        final char H = 'H';

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Set<Integer> combinations = new HashSet<>();
        int[][] pictures = new int[NUM_OF_PICTURES + 1][NUM_OF_ATTRIBUTES];
        int score = 0;

        for (int pictureIdx = 1; pictureIdx <= NUM_OF_PICTURES; pictureIdx++) {
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            for (int attribIdx = 0; attribIdx < NUM_OF_ATTRIBUTES; attribIdx++) {
                String attribute = tokenizer.nextToken();
                for (int index = 0; index < NUM_OF_ATTRIBUTES; index++) {
                    if (ATTRIBUTES[attribIdx][index].equals(attribute)) {
                        pictures[pictureIdx][attribIdx] = index;
                        break;
                    }
                }
            }
        }

        findCombinations(combinations, new int[NUM_OF_ATTRIBUTES], pictures, 0, 1);

        int n = Integer.parseInt(reader.readLine());
        boolean selectedG = false;
        for (int recordIdx = 0; recordIdx < n; recordIdx++) {
            String inputRecord = reader.readLine();
            if (inputRecord.charAt(0) == H) {
                int bit = 0;
                bit |= (1 << (inputRecord.charAt(2) - '0'));
                bit |= (1 << (inputRecord.charAt(4) - '0'));
                bit |= (1 << (inputRecord.charAt(6) - '0'));
                if (combinations.contains(bit)) {
                    combinations.remove(bit);
                    score++;
                } else {
                    score--;
                }
            } else if (selectedG || !combinations.isEmpty()) {
                score--;
            } else {
                selectedG = true;
                score += 3;
            }
        }
        System.out.print(score);
    }

    public static void findCombinations(Set<Integer> combinations, int[] combination,
                                        int[][] pictures, int order, int pictureIndex) {
        if (order == NUM_OF_ATTRIBUTES) {
            boolean flag = true;
            for (int attribIdx = 0; attribIdx < NUM_OF_ATTRIBUTES; attribIdx++) {
                int attribA = pictures[combination[0]][attribIdx];
                int attribB = pictures[combination[1]][attribIdx];
                int attribC = pictures[combination[2]][attribIdx];
                if ((attribA != attribB && attribB != attribC && attribC != attribA)
                        || (attribA == attribB && attribB == attribC)) {
                    continue;
                }
                flag = false;
                break;
            }
            if (flag) {
                int bit = 0;
                bit |= (1 << combination[0]);
                bit |= (1 << combination[1]);
                bit |= (1 << combination[2]);
                combinations.add(bit);
            }
            return;
        }
        for (int pictureIdx = pictureIndex; pictureIdx <= NUM_OF_PICTURES; pictureIdx++) {
            combination[order] = pictureIdx;
            findCombinations(combinations, combination, pictures, order + 1, pictureIdx + 1);
        }
    }
}
