import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        int N = Integer.parseInt(tokenizer.nextToken());
        int M = Integer.parseInt(tokenizer.nextToken());
        int[] sequence = new int[N];
        int minDifference = 0, maxDifference = 9_999;

        tokenizer = new StringTokenizer(reader.readLine());
        for (int index = 0; index < N; index++)
            sequence[index] = Integer.parseInt(tokenizer.nextToken());

        while (minDifference <= maxDifference) {
            int difference = (minDifference + maxDifference) >> 1;
            int maxNumberOfSegment = 1, minNumberOfSegment = 10_000;
            int numOfSegments = 1;
            for (int number : sequence) {
                if (number < minNumberOfSegment) minNumberOfSegment = number;
                if (maxNumberOfSegment < number) maxNumberOfSegment = number;
                if (maxNumberOfSegment - minNumberOfSegment <= difference) continue;
                minNumberOfSegment = maxNumberOfSegment = number;
                numOfSegments++;
            }
            if (M < numOfSegments) {
                minDifference = difference + 1;
            } else {
                maxDifference = difference - 1;
            }
        }
        System.out.print(minDifference);
    }
}
