import java.util.Scanner;
// 12프로에서 틀려요ㅜ
public class Main_1743 {
  static final int [] dy = {0, 0, -1, 1};
  static final int [] dx = {-1, 1, 0, 0};

  static int ROW, COL, result, ams;
  static boolean [][] map, isVisited;
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    ROW = sc.nextInt();
    COL = sc.nextInt();
    int K = sc.nextInt();
    map = new boolean [ROW][COL];
    isVisited = new boolean [ROW][COL];
    for(int i = 0; i < K; i++) map[sc.nextInt()-1][sc.nextInt()-1] = true;

    for(int i = 0; i < ROW; i++) {
      for(int j = 0; j < COL; j++) {
        if(map[i][j] && !isVisited[i][j]) {
          isVisited[i][j] = true;
          result = 0;
          DFS(i, j);
          ams = Math.max(ams, result);
        }
      }
    }

    System.out.println(ams);
    sc.close();
  }

  private static void DFS(int r, int c) {
    result++;

    for(int d = 0; d < 4; d++) {
      int nr = r + dy[d];
      int nc = c + dx[d];
      if(nr < 0 || nc < 0 || nr >= ROW || nc >= COL) continue;
      if(isVisited[nr][nc]) continue;
      
      if(map[nr][nc]) {
        isVisited[nr][nc] =  true;
        DFS(nr, nc);
      }
    }
  }
}
