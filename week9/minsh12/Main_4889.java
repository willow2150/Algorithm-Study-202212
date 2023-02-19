

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder output = new StringBuilder();

        int seq = 1;
        while (true) {
            String str = br.readLine();
            Stack<Character> stack = new Stack<>();

            if (str.contains("-"))
                break;

            int minCnt = 0;
            for (int i = 0; i < str.length(); i++) {
                char c = str.charAt(i);
                if (c == '{') {         // 여는 괄호
                    stack.push(c);
                } else {                // 닫는 괄호
                    if (stack.isEmpty()) {
                        stack.push('{');
                        minCnt++;
                    } else {
                        stack.pop();
                    }
                }
            }

            minCnt += (stack.size() / 2);
            output.append(seq++).append(". ").append(minCnt).append("\n");
        }

        System.out.println(output);
    }

}
