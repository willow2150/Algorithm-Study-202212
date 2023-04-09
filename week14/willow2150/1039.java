import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static boolean[][] visited;
    static char[] integer;
    static int integerLength;
    static int maxInteger = 0;
    static int optimalMaxInteger = 0;
    static boolean isCompleted = false;

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        integer = tokenizer.nextToken().toCharArray();
        integerLength = integer.length;
        int numOfSwaps = Integer.parseInt(tokenizer.nextToken());

        findMaxInteger();
        visited = new boolean[maxInteger + 1][numOfSwaps];
        findOptimalMaxInteger(numOfSwaps);
        System.out.print(optimalMaxInteger == 0 ? -1 : optimalMaxInteger);
    }

    public static void findMaxInteger() {
        char[] temp = new char[integerLength];
        char[] numOfNumbers = new char[10];
        int index = 0;

        for (int idx = integerLength - 1; idx > -1; idx--)
            numOfNumbers[integer[idx] - '0'] += 1;
        for (int number = 9; number > -1; number--) {
            while (numOfNumbers[number] != 0) {
                temp[index] = (char) (number + '0');
                index += 1;
                numOfNumbers[number] -= 1;
            }
        }
        maxInteger = Integer.parseInt(String.valueOf(temp));
    }

    public static void findOptimalMaxInteger(int swapCounter) {
        if (swapCounter == 0) {
            optimalMaxInteger = Math.max(
                    optimalMaxInteger,
                    Integer.parseInt(String.valueOf(integer))
            );
            if (optimalMaxInteger == maxInteger)
                isCompleted = true;
            return;
        }
        swapCounter -= 1;
        for (int i = integerLength - 1; i > 0; i--) {
            for (int j = i - 1; j > -1; j--) {
                if (j == 0 && integer[i] == '0')
                    continue;
                swap(i, j);
                int newInteger = Integer.parseInt(String.valueOf(integer));
                if (!visited[newInteger][swapCounter]) {
                    visited[newInteger][swapCounter] = true;
                    findOptimalMaxInteger(swapCounter);
                    if (isCompleted)
                        return;
                }
                swap(i, j);
            }
        }
    }

    public static void swap(int indexA, int indexB) {
        char temp = integer[indexA];
        integer[indexA] = integer[indexB];
        integer[indexB] = temp;
    }
}
