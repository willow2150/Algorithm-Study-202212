package week10.dankim0213;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

class Main_22352 {
  static int N, M;
  static int[][] initGrid, resultGrid;
  static Queue<int[]> qChecking;
  static boolean[][] visited;
  static int numOfChanges;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    N = stoi(st.nextToken());
    M = stoi(st.nextToken());
    initGrid = new int[N][M];
    resultGrid = new int[N][M];
    qChecking = new ArrayDeque<>();
    visited = new boolean[N][M];

    // 1. initialize two grids: initGrid and resultGrid
    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine(), " ");
      for (int j = 0; j < M; j++) {
        initGrid[i][j] = stoi(st.nextToken());
      }
    }
    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine(), " ");
      for (int j = 0; j < M; j++) {
        resultGrid[i][j] = stoi(st.nextToken());
      }
    }

    // 2.
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < M; j++) {
        if (visited[i][j])
          continue;

        if (!bfs(new int[] { i, j })) {
          System.out.println("NO");
          return;
        }
      }
    }

    if (numOfChanges <= 1)
      System.out.println("YES");
    else
      System.out.println("NO");
  }

  static int[][] dir = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

  private static boolean bfs(int[] src) {

    Queue<int[]> q1 = new ArrayDeque<>();
    q1.add(src);
    boolean[][] v1 = new boolean[N][M];
    v1[src[0]][src[1]] = true;
    while (!q1.isEmpty()) {
      int[] at = q1.poll();
      qChecking.offer(at);
      for (int di = 0; di < 4; di++) {
        int nr = at[0] + dir[di][0];
        int nc = at[1] + dir[di][1];

        boolean isAvailable = 0 <= nr && nr < N && 0 <= nc && nc < M;
        if (isAvailable && !v1[nr][nc] && initGrid[src[0]][src[1]] == initGrid[nr][nc]) {
          v1[nr][nc] = true;
          q1.offer(new int[] { nr, nc });
        }
      }
    }

    Queue<int[]> q2 = new ArrayDeque<>();
    q2.add(src);
    boolean[][] v2 = new boolean[N][M];
    v2[src[0]][src[1]] = true;
    while (!q2.isEmpty()) {
      int[] at = q2.poll();

      // 3. check if it is valid
      if (!v1[at[0]][at[1]])
        continue;
      int[] atChecking = qChecking.poll();
      if (at[0] != atChecking[0] || at[1] != atChecking[1])
        return false;
      visited[at[0]][at[1]] = true;

      for (int di = 0; di < 4; di++) {
        int nr = at[0] + dir[di][0];
        int nc = at[1] + dir[di][1];

        boolean isAvailable = 0 <= nr && nr < N && 0 <= nc && nc < M;
        if (isAvailable && !v2[nr][nc] && resultGrid[src[0]][src[1]] == resultGrid[nr][nc]) {
          v2[nr][nc] = true;
          q2.offer(new int[] { nr, nc });
        }
      }
    }

    numOfChanges += (initGrid[src[0]][src[1]] != resultGrid[src[0]][src[1]] ? 1 : 0);
    return qChecking.isEmpty() ? true : false;
  }

  private static int stoi(String num) {
    return Integer.parseInt(num);
  }

}

/**
 * 단계별 풀이
 * 1. 방문하지 않은 지점이 있다면 bfs로 방문체크한다.
 * 2. bfs할때, checking 큐를 두어 결과 그리드와 비교한다.
 * 3. 만약 결과 그리드와 값이 다르거나 변한 영역이 두 곳 이상이라면 "NO"
 */
