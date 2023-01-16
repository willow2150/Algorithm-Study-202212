import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Map<Integer, Integer> counter = new HashMap<>();
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        int N = Integer.parseInt(tokenizer.nextToken());
        int K = Integer.parseInt(tokenizer.nextToken());
        long answer = 0;
        int cumulativeSum = 0;

        counter.put(0, 1);
        tokenizer = new StringTokenizer(reader.readLine());
        for (int index = 0; index < N; index++) {
            cumulativeSum += Integer.parseInt(tokenizer.nextToken());
            answer += counter.getOrDefault(cumulativeSum - K, 0);
            counter.put(cumulativeSum, counter.getOrDefault(cumulativeSum, 0) + 1);
        }
        System.out.print(answer);
    }
}
