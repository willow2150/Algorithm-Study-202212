import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        final int DEFAULT_VALUE = 200_000;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(reader.readLine());

        int[] numbers = new int[N];
        boolean[] isPresent = new boolean[400_001];
        int numOfGoodNumbers = 0;

        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        for (int index = 0; index < N; index++) {
            numbers[index] = Integer.parseInt(tokenizer.nextToken());
        }

        for (int index = 0; index < N; index++) {
            int number = numbers[index];
            for (int idx = 0; idx < index; idx++) {
                if (isPresent[DEFAULT_VALUE + number - numbers[idx]]) {
                    numOfGoodNumbers++;
                    break;
                }
            }
            for (int idx = 0; idx <= index; idx++) {
                isPresent[DEFAULT_VALUE + number + numbers[idx]] = true;
            }
        }

        System.out.print(numOfGoodNumbers);
    }
}
