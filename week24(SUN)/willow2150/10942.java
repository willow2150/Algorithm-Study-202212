import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        final char TRUE = '1';
        final char FALSE = '0';
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder output = new StringBuilder();
        StringTokenizer tokenizer;

        int N = Integer.parseInt(reader.readLine());
        boolean[][] palindromeTable = new boolean[N + 1][N + 1];
        int[] numbers = new int[N + 1];

        tokenizer = new StringTokenizer(reader.readLine());
        for (int index = 1; index <= N; index++) {
            numbers[index] = Integer.parseInt(tokenizer.nextToken());
        }

        for (int lengthMinusOne = 0; lengthMinusOne < N; lengthMinusOne++) {
            int boundary = N - lengthMinusOne;
            for (int left = 1; left <= boundary; left++) {
                int right = left + lengthMinusOne;
                palindromeTable[left][right] = isPalindrome(
                        numbers, palindromeTable, left, right
                );
            }
        }

        int M = Integer.parseInt(reader.readLine());
        for (int queryIndex = 0; queryIndex < M; queryIndex++) {
            tokenizer = new StringTokenizer(reader.readLine());
            int left = Integer.parseInt(tokenizer.nextToken());
            int right = Integer.parseInt(tokenizer.nextToken());
            output.append(palindromeTable[left][right] ? TRUE : FALSE).append('\n');
        }

        System.out.print(output);
    }

    public static boolean isPalindrome(
            int[] numbers, boolean[][] isPalindrome, int left, int right
    ) {
        if (left >= right) {
            return numbers[left] == numbers[right];
        }
        if (isPalindrome[left][right]) {
            return true;
        }
        return numbers[left] == numbers[right]
                && isPalindrome(numbers, isPalindrome, left + 1, right - 1);
    }
}
