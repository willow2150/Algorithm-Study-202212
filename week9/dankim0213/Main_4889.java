import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

class Main_4889 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String input = br.readLine();
    int order = 1;
    while (!input.contains("-")) {
      System.out.println(order++ + ". " + check(input));
      input = br.readLine();
    }
  }

  private static int check(String input) {
    int numOfConversion = 0;
    Deque<Character> stack = new ArrayDeque<>();
    for (int i = 0; i < input.length(); i++) {
      if (input.charAt(i) == '{') {
        stack.push('{');
      } else {
        if (stack.isEmpty()) {
          numOfConversion++;
          stack.push('{');
        } else {
          stack.pop();
        }
      }
    }

    return numOfConversion + stack.size() / 2;
  }
}

/**
 * 1. try brute-force to make the left input safe (DFS)
 * O(2^N) === 2^2000
 * 
 * vs
 * 
 * 1. remove if the opening brace is closed immediately
 * 2. calculate how many opening braces are left
 */