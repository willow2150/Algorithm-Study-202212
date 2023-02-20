import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws Exception {
        final int MAX_DATASET_LEN = 2_000;
        final char END_SYMBOL = '-';
        final char OPEN = '{';
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder output = new StringBuilder();
        char[] stack = new char[MAX_DATASET_LEN + 1];

        for (int tC = 1; ; tC++) {
            String dataset = reader.readLine();
            int datasetLen = dataset.length();
            int changeCnt = 0;
            int top = 0;
            if (dataset.charAt(0) == END_SYMBOL) break;
            for (int index = 0; index < datasetLen; index++) {
                if (dataset.charAt(index) == OPEN) {
                    stack[++top] = OPEN;
                } else if (stack[top] == OPEN) {
                    top--;
                } else {
                    stack[++top] = OPEN;
                    changeCnt++;
                }
            }
            output.append(tC).append('.').append(' ').append(changeCnt + (top >> 1)).append('\n');
        }
        System.out.print(output);
    }
}
