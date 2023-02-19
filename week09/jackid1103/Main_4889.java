import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

/**
 * Main
 */
public class Main_4889 {
  static String input;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int index = 1;
    while(true) {
      input = br.readLine();
      if(input.contains("-")) break;
      System.out.println(index + ". " + sol());
      index++;
    }
  }

  private static int sol() {
    Stack<Character> s = new Stack<>();
    int count = 0;
    for(int i = 0; i < input.length(); i++) {
      char now = input.charAt(i);
      
      if(now == '{') {
        s.push(now);
      } else { 
        if(!s.isEmpty()) {
          s.pop();
        } else {
          s.push('{');
          count++;
        }
      }
    }
    return count += s.size() / 2;
  }
}