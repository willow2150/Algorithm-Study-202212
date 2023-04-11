package week14.dankim0213;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

// BFS
class Main_1039 {
  public static void main(String[] args) throws IOException {
    Util util = new Util();
    String[] data = util.readLine().split(" ");
    System.out.println(new Calculator(util.num(data[0]), util.k(data[1])).calculate());
  }

  static class Util {
    private BufferedReader br;

    public Util() {
      br = new BufferedReader(new InputStreamReader(System.in));
    }

    public String readLine() throws IOException {
      return br.readLine();
    }

    public char[] num(String input) {
      return input.toCharArray();
    }

    public int k(String input) {
      return Integer.parseInt(input);
    }
  }

  static class Calculator {
    private boolean[][] visited;
    private char[] num;
    private int k;

    public Calculator(char[] num, int k) {
      this.visited = new boolean[k + 1][1000001];
      this.num = num;
      this.k = k;
    }

    public int calculate() {
      if (num.length < 2 || (num.length == 2 && num[1] == '0'))
        return -1;

      Queue<Node> q = new ArrayDeque<>();
      q.offer(new Node(num, 0));
      visited[0][parseInt(num)] = true;

      int max = 0;
      while (!q.isEmpty()) {
        Node node = q.poll();

        if (node.depth() == k) {
          max = Integer.max(max, parseInt(node.num()));
          continue;
        }

        for (int from = 0; from < num.length - 1; from++) {
          for (int to = from + 1; to < num.length; to++) {
            int nextDepth = node.depth() + 1;
            char[] nextNum = swap(node.num(), from, to);
            if (!visited[nextDepth][parseInt(nextNum)]) {
              q.offer(new Node(nextNum, nextDepth));
              visited[nextDepth][parseInt(nextNum)] = true;
            }
          }
        }
      }
      return max;
    }

    private char[] swap(char[] n, int from, int to) {
      char[] result = Arrays.copyOf(n, n.length);
      char temp = result[from];
      result[from] = result[to];
      result[to] = temp;
      return result;
    }

    private int parseInt(char[] n) {
      return Integer.parseInt(String.valueOf(n));
    }
  }

  static class Node {
    private char[] num;
    private int depth;

    public Node(char[] num, int depth) {
      this.num = num;
      this.depth = depth;
    }

    public char[] num() {
      return Arrays.copyOf(num, num.length);
    }

    public int depth() {
      return depth;
    }
  }
}