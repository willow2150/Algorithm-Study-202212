import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    private static int L, N;

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        L = Integer.parseInt(tokenizer.nextToken());
        N = Integer.parseInt(tokenizer.nextToken());

        String[] words = new String[N];
        for (int wordIdx = 0; wordIdx < N; wordIdx++)
            words[wordIdx] = reader.readLine();

        Arrays.sort(words);
        List<String[]> permutations = new ArrayList<>();
        findPermutations(permutations, new String[L], words, 0, 0);
        System.out.print(findMagicSquare(permutations));
    }

    public static void findPermutations(
            List<String[]> permutations, String[] permutation,
            String[] words, int order, int selected) {
        if (order == L) {
            permutations.add(Arrays.copyOf(permutation, L));
            return;
        }
        for (int wordIdx = 0; wordIdx < N; wordIdx++) {
            if ((selected & (1 << wordIdx)) != 0) continue;
            selected |= (1 << wordIdx);
            permutation[order] = words[wordIdx];
            findPermutations(permutations, permutation, words, order + 1, selected);
            selected ^= (1 << wordIdx);
        }
    }

    public static String findMagicSquare(List<String[]> permutations) {
        StringBuilder magicSquare = new StringBuilder();

        for (String[] permutation : permutations) {
            if (isMagicSquare(permutation)) {
                for (String word : permutation)
                    magicSquare.append(word).append('\n');
                return magicSquare.toString();
            }
        }
        return "NONE";
    }

    public static boolean isMagicSquare(String[] permutation) {
        for (int row = 0; row < L; row++) {
            for (int col = row + 1; col < L; col++) {
                if (permutation[row].charAt(col) == permutation[col].charAt(row))
                    continue;
                return false;
            }
        }
        return true;
    }
}
