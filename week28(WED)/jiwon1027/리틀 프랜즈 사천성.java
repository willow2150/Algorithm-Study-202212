import java.util.*;

/*
    사천성
    Map을 이용해서 dfs 탐색으로 해볼까
*/
class Solution {
    static final String fail = "IMPOSSIBLE";
    static char[][] visited;
    static Map<Character, List<int[]>> map;
    static List<String> result;
    static boolean isAllow(int[] tileA, int[] tileB) {
        int i = Math.min(tileA[0], tileB[0]);
        int j = tileA[1];
        int m = Math.max(tileA[0], tileB[0]);
        
        
        for (; i <= m && visited[i][j] == '.'; i++)
            
            
        if (i > m) {
            i = tileB[0];
            j = Math.min(tileA[1], tileB[1]);
            int n = Math.max(tileA[1], tileB[1]);
            for (; j <= n && visited[i][j] == '.'; j++) {}
            if (j > n) {
                return true;
            }
        }
        return false;
    }
    private static String dfs(int d, List<Character> keys, char[] arr) {
        if (d == arr.length) {
            return String.valueOf(arr);
        }
        String result = fail;
        for (int i=0, n=keys.size(); i < n; i++) {
            char c = keys.get(i);
            List<int[]> pair = map.get(c);
            int[] tileA = pair.get(0), tileB = pair.get(1);
            if (visited[tileA[0]][tileA[1]] == '.') {
                continue;
            }
            arr[d] = c;
            visited[tileA[0]][tileA[1]] = '.';
            visited[tileB[0]][tileB[1]] = '.';
            if (isAllow(tileA, tileB)) {
                return dfs(d+1, keys, arr);
            } else if (tileA[0] != tileB[0] && tileA[1] != tileB[1] && isAllow(tileB, tileA)) {
                return dfs(d+1, keys, arr);
            }
            visited[tileA[0]][tileA[1]] = c;
            visited[tileB[0]][tileB[1]] = c;
        }
        return result;
    }
    public String solution(int m, int n, String[] board) {
        map = new HashMap<>();
        visited = new char[m][];
        for (int i=0; i < m; i++) {
            visited[i] = board[i].toCharArray();
            for (int j=0; j < n; j++) {
                if (Character.isLetter(visited[i][j])) {
                    List<int[]> anony = new ArrayList<>();
                    anony.add(new int[]{i, j});
                    map.merge(visited[i][j], anony, (ov, nv)-> {
                        ov.addAll(nv);
                        return ov;
                    });
                }
            }
        }
        List<Character> keys = new ArrayList<>(map.keySet());
        Collections.sort(keys);
        String answer = dfs(0, keys, new char[keys.size()]);
        return answer;
    }
}
