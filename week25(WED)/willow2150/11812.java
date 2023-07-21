import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder output = new StringBuilder();

        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        long N = Long.parseLong(tokenizer.nextToken());
        int K = Integer.parseInt(tokenizer.nextToken());
        int Q = Integer.parseInt(tokenizer.nextToken());

        if (K == 1) {
            for (int q = 0; q < Q; q++) {
                tokenizer = new StringTokenizer(reader.readLine());
                long numberA = Long.parseLong(tokenizer.nextToken());
                long numberB = Long.parseLong(tokenizer.nextToken());
                output.append(Math.abs(numberA - numberB)).append('\n');
            }
        } else {
            long[] maxValueByLevel = new long[52];
            long maxValue = 1L;
            int maxLevel = 0;
            int kMinusTwo = K - 2;

            Arrays.fill(maxValueByLevel, Long.MAX_VALUE);

            for (; maxValue <= N; maxLevel++) {
                maxValueByLevel[maxLevel] = maxValue;
                maxValue = maxValue * K + 1;
            }
            maxLevel--;

            for (int q = 0; q < Q; q++) {
                tokenizer = new StringTokenizer(reader.readLine());
                long numberA = Long.parseLong(tokenizer.nextToken());
                long numberB = Long.parseLong(tokenizer.nextToken());

                if (numberB < numberA) {
                    numberA ^= numberB;
                    numberB ^= numberA;
                    numberA ^= numberB;
                }

                int distance = 0;
                int levelA = binarySearch(maxValueByLevel, numberA, maxLevel);
                int levelB = binarySearch(maxValueByLevel, numberB, maxLevel);

                while (levelA != levelB) {
                    numberB = (numberB + kMinusTwo) / K;
                    distance++;
                    levelA = binarySearch(maxValueByLevel, numberA, maxLevel);
                    levelB = binarySearch(maxValueByLevel, numberB, maxLevel);
                }

                while (numberA != numberB) {
                    numberA = (numberA + kMinusTwo) / K;
                    numberB = (numberB + kMinusTwo) / K;
                    distance += 2;
                }

                output.append(distance).append('\n');
            }
        }

        System.out.print(output);
    }

    public static int binarySearch(long[] array, long value, int length) {
        int left = 0;
        int right = length;
        while (left <= right) {
            int mid = (left + right) >> 1;
            if (value <= array[mid]) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }
}
