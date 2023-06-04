import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baekjoon_1407 {
    public static void main(String[] args) throws IOException {
        final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        final StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());

        final long a = Long.parseLong(stringTokenizer.nextToken());
        final long b = Long.parseLong(stringTokenizer.nextToken());

        System.out.println(sumDivisor(b) - sumDivisor(a - 1));
    }

    private static long sumDivisor(final long number) {
        long result = number;

        System.out.println("number: " + number + " / result: " + result);
        for (long i = 2; i <= number; i <<= 1) {
            result += (number / i) * (i >> 1);
            System.out.println("number: " + number + " / i: " + i + " / result: " + result);
        }

        return result;
    }

}
