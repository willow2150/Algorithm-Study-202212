import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main_24891 {

    public static final String NEW_LINE = "\n";
    public static List<String> selectWords;

    public static void main(String[] args) throws IOException {
        final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        final int wordMapLength = Integer.parseInt(stringTokenizer.nextToken());
        final int wordCount = Integer.parseInt(stringTokenizer.nextToken());
        final String[] words = new String[wordCount];

        for (int i = 0; i < wordCount; i++) {
            words[i] = bufferedReader.readLine();
        }

        Arrays.sort(words);
        final String[] permutation = new String[wordMapLength];
        final boolean[] isSelected = new boolean[wordCount];
        selectWords = new ArrayList<>();
        permutation(words, 0, permutation, isSelected);

        if (selectWords.size() > 0) {
            final StringBuilder stringBuilder = new StringBuilder();

            for (String selectWord : selectWords) {
                stringBuilder.append(selectWord).append(NEW_LINE);
            }

            System.out.println(stringBuilder);
        } else {
            System.out.println("NONE");
        }
    }

    private static void permutation(final String[] words, final int depth, final String[] permutation, final boolean[] isSelected) {
        if (selectWords.size() > 0) {
            return;
        }

        if (depth == permutation.length) {
            if (checkPossible(permutation)) {
                selectWords.addAll(Arrays.asList(permutation));
            }

            return;
        }

        for (int i = 0; i < words.length; i++) {
            if (isSelected[i]) {
                continue;
            }

            permutation[depth] = words[i];
            isSelected[i] = true;
            permutation(words, depth + 1, permutation, isSelected);
            isSelected[i] = false;
        }
    }

    private static boolean checkPossible(final String[] permutation) {
        final char[][] wordMap = new char[permutation.length][permutation.length];

        for (int i = 0; i < permutation.length; i++) {
            char[] chars = permutation[i].toCharArray();
            wordMap[i] = chars;
        }

        for (int i = 0; i < wordMap.length; i++) {
            for (int j = 0; j < wordMap.length; j++) {
                if (i == j) {
                    continue;
                }

                if (wordMap[i][j] != wordMap[j][i]) {
                    return false;
                }
            }
        }

        return true;
    }

}
