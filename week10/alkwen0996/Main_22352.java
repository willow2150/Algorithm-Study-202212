import java.awt.Point;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main_22352 {

  // 1. 백신 놓기전 배열에 백신을 주입하고 백신 놓은 결과 배열과 비교.
  //    1-1. 백신 주입하고 탐색시 after 첫 값으로 temp 배열에 백신을 퍼트린다.
  //    1-2. 단, after 값과 before 값이 같으면 백신을 퍼뜨리지 안흔다.
  // 2. 방문 배열에 백신 주입 체크.
  // 3. 같으면 yes 반환.
  // 4. 같지 않으면 방문배열에 체크되지 않은 장소에 다시 백신을 주입.

  public static void main(String[] args) throws IOException {
    final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());

    final int height = Integer.parseInt(stringTokenizer.nextToken());
    final int width = Integer.parseInt(stringTokenizer.nextToken());

    final int[][] before = new int[height][width];
    final int[][] after = new int[height][width];

    for (int i = 0; i < height; i++) {
      stringTokenizer = new StringTokenizer(bufferedReader.readLine());

      for (int j = 0; j < width; j++) {
        before[i][j] = Integer.parseInt(stringTokenizer.nextToken());
      }
    }

    for (int i = 0; i < height; i++) {
      stringTokenizer = new StringTokenizer(bufferedReader.readLine());

      for (int j = 0; j < width; j++) {
        after[i][j] = Integer.parseInt(stringTokenizer.nextToken());
      }
    }

    System.out.println(bfs(before, after));
  }

  private static String bfs(final int[][] before, final int[][] after) {
    final boolean[][] visited = new boolean[before.length][before[0].length];

    final int[] dx = {1, 0, -1, 0};
    final int[] dy = {0, 1, 0, -1};

    int[][] temp;
    int vaccineValue;
    int initialValue;
    Deque<Point> queue;
    boolean isSame = false;

    for (int i = 0; i < before.length; i++) {
      for (int j = 0; j < before[0].length; j++) {
        queue = new ArrayDeque<>();

        if (visited[i][j]) {
          continue;
        }

        temp = copyBefore(before);
        vaccineValue = after[i][j];
        initialValue = before[i][j];

        queue.add(new Point(i, j));
        visited[i][j] = true;
        temp[i][j] = vaccineValue;

        while (!queue.isEmpty()) {
          final Point point = queue.poll();

          for (int k = 0; k < dx.length; k++) {
            int nextX = point.x + dx[k];
            int nextY = point.y + dy[k];

            if (nextX >= 0 && nextY >= 0 && nextX < visited.length && nextY < visited[0].length) {
              if (!visited[nextX][nextY] && before[nextX][nextY] == initialValue) {
                visited[nextX][nextY] = true;
                queue.add(new Point(nextX, nextY));
                temp[nextX][nextY] = vaccineValue;
              }
            }
          }
        }

        if (isSame(temp, after)) {
          isSame = true;
        }
      }

      if (isSame) {
        return "YES";
      }
    }

    return "NO";
  } // 약물 투입 후 탐색.

  private static int[][] copyBefore(final int[][] before) {
    final int[][] temp = new int[before.length][before[0].length];

    for (int i = 0; i < before.length; i++) {
      System.arraycopy(before[i], 0, temp[i], 0, temp[i].length);
    }

    return temp;
  } // 임시배열 생성.

  private static boolean isSame(final int[][] temp, final int[][] after) {
    for (int i = 0; i < temp.length; i++) {
      for (int j = 0; j < temp[0].length; j++) {
        if (temp[i][j] != after[i][j]) {
          return false;
        }
      }
    }

    return true;
  } // 두 배열 비교.

}