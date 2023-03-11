import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        final String BN = "BN";
        final String BP = "BP";
        final String CN = "CN";
        final String CP = "CP";
        final int MAX_NODE_CNT = 1_000_000;

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder output = new StringBuilder();
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        int N = Integer.parseInt(tokenizer.nextToken());
        int M = Integer.parseInt(tokenizer.nextToken());

        int[] prevStations = new int[MAX_NODE_CNT + 1];
        int[] nextStations = new int[MAX_NODE_CNT + 1];

        tokenizer = new StringTokenizer(reader.readLine());
        int firstStation = Integer.parseInt(tokenizer.nextToken());
        int prevStation = firstStation;

        for (int stationIdx = 1; stationIdx < N; stationIdx++) {
            int currentStation = Integer.parseInt(tokenizer.nextToken());
            prevStations[currentStation] = prevStation;
            nextStations[prevStation] = currentStation;
            prevStation = currentStation;
        }
        prevStations[firstStation] = prevStation;
        nextStations[prevStation] = firstStation;

        for (int cmdIdx = 0; cmdIdx < M; cmdIdx++) {
            tokenizer = new StringTokenizer(reader.readLine());
            String cmd = tokenizer.nextToken();
            int i = Integer.parseInt(tokenizer.nextToken());
            int j = 0;
            if (tokenizer.hasMoreTokens()) j = Integer.parseInt(tokenizer.nextToken());
            if (BN.equals(cmd)) {
                int iNextStation = nextStations[i];
                nextStations[j] = iNextStation;
                prevStations[iNextStation] = j;
                nextStations[i] = j;
                prevStations[j] = i;
                output.append(iNextStation);
            } else if (BP.equals(cmd)) {
                int iPrevStation = prevStations[i];
                prevStations[j] = iPrevStation;
                nextStations[iPrevStation] = j;
                prevStations[i] = j;
                nextStations[j] = i;
                output.append(iPrevStation);
            } else if (CN.equals(cmd)) {
                int iNextStation = nextStations[i];
                nextStations[i] = nextStations[iNextStation];
                prevStations[nextStations[i]] = i;
                output.append(iNextStation);
            } else {
                int iPrevStation = prevStations[i];
                prevStations[i] = prevStations[iPrevStation];
                nextStations[prevStations[i]] = i;
                output.append(iPrevStation);
            }
            output.append('\n');
        }
        System.out.print(output);
    }
}
