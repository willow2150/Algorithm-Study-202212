import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder output = new StringBuilder();

        String S = reader.readLine();
        int strLength = S.length();
        char[] deque = new char[(strLength << 1) - 1];
        int left = strLength - 1;
        int right = left;

        deque[left] = S.charAt(0);

        for (int index = 1; index < strLength; index++) {
            char character = S.charAt(index);
            if (character <= deque[left]) {
                deque[--left] = character;
            } else {
                deque[++right] = character;
            }
        }

        for (int index = left; index <= right; index++) {
            output.append(deque[index]);
        }

        System.out.print(output);
    }
}
