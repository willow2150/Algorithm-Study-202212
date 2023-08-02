import java.util.*;
/*
    이것도 백트래킹으로 해서 풀면 되겠는데?
    어떤 자료구조를 써서 하는게 편하려나. HashSet?

*/

class Solution {
    public static List<String> names = new ArrayList<String>();
    public static List<List<Integer>> banList = new ArrayList<List<Integer>>();
    public static int N;
    
    public int solution(String[] user_id, String[] banned_id) {
        int answer = 0;
        N = banned_id.length;
        for(int i=0;i<N;i++) {
            banList.add(new ArrayList<Integer>());
            for(int j=0;j<user_id.length;j++) {
                if(takeBan(banned_id[i], user_id[j])) {
                    banList.get(i).add(j);
                }
            }
        }
        boolean[] visit = new boolean[user_id.length];
        dfs(user_id, banned_id, 0, "", visit);
        answer = names.size();
        return answer;
    }
    public static void dfs(String[] user_id, String[] banned_id, int cnt, String set, boolean[] visit) {
        if(cnt == N) {
            char[] StringtoChar = set.toCharArray();
            Arrays.sort(StringtoChar);
            String str = new String(StringtoChar);
            if(!names.contains(str)) {
                names.add(str);
            }
            return;
        }
        for(Integer i:banList.get(cnt)) {
            if(visit[i]) {
                continue;
            }
            visit[i] = true;
            String nextStr = set + i;
            dfs(user_id, banned_id, cnt + 1, nextStr, visit);
            visit[i] = false;
        }
    }
    public static boolean takeBan(String ban, String name) {
        int len = ban.length();
        if(len != name.length()) {
            return false;
        }
        for(int i=0;i<len;i++) {
            if(ban.charAt(i) != '*' && ban.charAt(i) != name.charAt(i)) {
                return false;
            }
        }
        return true;
    }
}