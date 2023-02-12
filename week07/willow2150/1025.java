import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        int N = Integer.parseInt(tokenizer.nextToken());
        int M = Integer.parseInt(tokenizer.nextToken());

        String[] table = new String[N];
        for (int row = 0; row < N; row++)
            table[row] = reader.readLine();

        double maxNumber = -1;
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < M; c++) {
                double number = table[r].charAt(c) - '0';
                if (Math.pow((long) Math.sqrt(number), 2) == number)
                    maxNumber = Math.max(maxNumber, number);
                for (int dr = 1 - N; dr < N; dr++) {
                    for (int dc = 1 - M; dc < M; dc++) {
                        if (dr == 0 && dc == 0) continue;
                        int row = r + dr;
                        int col = c + dc;
                        number = table[r].charAt(c) - '0';
                        while (0 <= row && 0 <= col && row < N && col < M) {
                            number = number * 10 + (table[row].charAt(col) - '0');
                            if (Math.pow((long) Math.sqrt(number), 2) == number)
                                maxNumber = Math.max(maxNumber, number);
                            row += dr;
                            col += dc;
                        }
                    }
                }
            }
        }
        System.out.print((long) maxNumber);
    }
}
