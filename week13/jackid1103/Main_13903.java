import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

/**
 * 특정 규칙으로 이동
 * 1. 세로 블록만 밟기: 시작은 첫줄 row 아무데서나 시작가능
 * 2. 특정 규칙으로 이동 ( 상하좌우, 체스의 나이트 이동 규칙 ?)
 * 3. 첫번째 row에서 마지막 row 도착하면 출근 성공
 * 4. 최소 이동 거리를 구할 것 = BFS냄새 물씬
 * 
 */
public class Main_13903 {
  static int ROW, COL, N;
  static int [][] map, orders;
  static boolean [][] isVisted;

  static class Point {
    int r, c, depth;

    public Point(int r, int c, int depth) {
      this.r = r;
      this.c = c;
      this.depth = depth;
    }
  }
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    ROW = sc.nextInt();
    COL = sc.nextInt();
    map = new int [ROW][COL];
    for(int i = 0; i < ROW; i++) {
      for(int j = 0; j < COL; j++) {
        map[i][j] = sc.nextInt();
      }
    }

    N = sc.nextInt();
    orders = new int [N][2];
    for(int i = 0; i < N; i++) {
      for(int j = 0; j < 2; j++) {
        orders[i][j] = sc.nextInt();
      }
    }

    BFS();
    sc.close();
  }

  private static void BFS() {
    Queue<Point> q = new ArrayDeque<>();
    isVisted = new boolean [ROW][COL];
    for(int i = 0; i < COL; i++) {
      if(map[0][i] == 1) q.add(new Point(0, i, 0));
      isVisted[0][i] = true;
    }

    while(!q.isEmpty()) {
      Point now = q.poll();

      for(int d = 0; d < orders.length; d++) {
        Point next = new Point(now.r + orders[d][0], now.c + orders[d][1], now.depth + 1);
        if(next.r < 0 || next.r >= ROW || next.c < 0 || next.c >= COL) continue;
        if(isVisted[next.r][next.c] || map[next.r][next.c] == 0) continue;
        if(next.r == ROW - 1) {
          System.out.println(next.depth);
          return;
        }
        isVisted[next.r][next.c] = true;
        q.add(next);
      }
    }

    System.out.println(-1);
  }
}