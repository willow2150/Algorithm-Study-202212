import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        int N = Integer.parseInt(tokenizer.nextToken());
        int M = Integer.parseInt(tokenizer.nextToken());
        long minTime = 1L;
        long maxTime = 1_000_000_000_000_000_000L;
        int[] timeTakenList = new int[N];

        for (int index = 0; index < N; index++)
            timeTakenList[index] = Integer.parseInt(reader.readLine());

        while (minTime <= maxTime) {
            long midTime = (minTime + maxTime) >> 1;
            long passedPeopleCnt = 0;
            for (int timeTaken : timeTakenList) {
                passedPeopleCnt += midTime / timeTaken;
                if (passedPeopleCnt >= M) {
                    maxTime = midTime - 1;
                    break;
                }
            }
            if (passedPeopleCnt < M) minTime = midTime + 1;
        }
        System.out.print(minTime);
    }
}
