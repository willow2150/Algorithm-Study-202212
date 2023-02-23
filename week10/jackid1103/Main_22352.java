import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_22352 {
  final static int [] dy = {0, 0, -1, 1};
  final static int [] dx = {-1, 1, 0, 0};

  static int ROW, COL;
  static int [][] beforeMap, afterMap;
  static boolean [][] isVisited;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    ROW = Integer.parseInt(st.nextToken());
    COL = Integer.parseInt(st.nextToken());
    beforeMap = new int[ROW][COL];
    afterMap = new int[ROW][COL];
    isVisited = new boolean[ROW][COL];

    for(int i = 0; i < ROW; i++) {
      st = new StringTokenizer(br.readLine());
      for(int j = 0; j < COL; j++) {
        beforeMap[i][j] = Integer.parseInt(st.nextToken());
      }
    }

    for(int i = 0; i < ROW; i++) {
      st = new StringTokenizer(br.readLine());
      for(int j = 0; j < COL; j++) {
        afterMap[i][j] = Integer.parseInt(st.nextToken());
      }
    }

    sol();

    if(isValid()) System.out.println("YES");
    else System.out.println("NO");
  }

  private static boolean isValid() {
    for(int i = 0; i < ROW; i++) {
      for(int j = 0; j < COL; j++) {
        if(afterMap[i][j] != beforeMap[i][j]) return false;
      }
    }
    return true;
  }

  private static void sol() {
    for(int i = 0; i < ROW; i++) {
      for(int j = 0; j < COL; j++) {
        if(afterMap[i][j] != beforeMap[i][j]) {
          int temp = beforeMap[i][j];
          int temp2 = afterMap[i][j];
          isVisited[i][j] = true;
          beforeMap[i][j] = afterMap[i][j];
          DFS(i, j, temp ,temp2);
          return;
        }
      }
    }
  }
  

  private static void DFS(int r, int c, int nowValue, int afterValue) {
    
    for(int d = 0; d < 4; d++) {
      int nr = r + dy[d];
      int nc = c + dx[d];
      if(nr < 0 || nc < 0 || nr >= ROW || nc >= COL) continue;
      
      if(nowValue == beforeMap[nr][nc] && !isVisited[nr][nc]) {
        beforeMap[nr][nc] = afterValue;
        isVisited[nr][nc] = true;
        DFS(nr, nc, nowValue, afterValue);
      }

    }
  }

  // private static void printer(int [][] arr) {
  //   for(int i = 0; i < ROW; i++) {
  //     for(int j = 0; j < COL; j++) {
  //       System.out.print(arr[i][j] + " ");
  //     }
  //     System.out.println();
  //   }
  // }
}
