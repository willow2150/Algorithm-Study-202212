import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

/*
* 입력의 길이는 무조껀 짝수이기 때문에 안되는 케이스는 없음
* 문자열길이도 2000이니까 O(N) 가능
* { : 0
* } : 1
* 이라고 한다면 00,01,10,11 총 4가지가 나옴
* stack해가지고 최종적으로 남은거 한다음에 2개씩 잘라가지고 문자열 처리하면 끝날듯?
*
* 이거 1인 상태를 굳이 넣을필요없으니까 수학적으로 걍 해봐?
*
*
* */
public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int num = 1;

        while(true) {
            String input = br.readLine();

            if (input.charAt(0) == '-')
                break;

            Stack<Character> stack = new Stack<Character>();
            int res = 0;

            for (int i = 0; i < input.length(); i++) {
                char temp = input.charAt(i);

                if (stack.size() == 0) {
                    stack.push(temp);
                } else {
                    if (stack.peek() == '{' && temp == '}') {
                        stack.pop();
                    } else {
                        stack.push(temp);
                    }
                }

            }
            while (!stack.empty()) {
                char one = stack.pop();
                char two = stack.pop();

                if (one == two) {
                    res += 1;
                } else if (one == '{' && two == '}') {
                    res += 2;
                }
            }
            System.out.println(num++ + ". " + res);

        }

    }
}
