import java.util.*;

/*
    O(4^(n*n)) 나올꺼같은데 백트래킹으로 조건들 없애보는게 맞나?
*/

class Solution {
    public static int n = 0;
    public static int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}, {0, 0}};
    public static int[] zero = null;
    public static int minCount = Integer.MAX_VALUE;
    public int solution(int[][] clockHands) {
        n = clockHands.length;
        zero = new int[n];
        recursive(clockHands, 0, 0);                

        return minCount;
    }

    public void recursive(int[][] clocks, int count, int depth){
        if(minCount <= count) 
            return;
        if(isComplete(clocks)){
            minCount = Math.min(minCount, count); 
            return;
        }
        if(depth >= n*n) 
            return;

        int r = depth/n;
        int c = depth%n;
        for(int i = 0; i < 4; i++){
            rotate(clocks, r, c, i);
            if(checkValue(clocks, r, c)){
                recursive(clocks, count+i, depth+1);
            }
            rotate(clocks, r, c, -i);
        }
    }

    public void rotate(int[][] clocks, int r, int c, int plusValue){
        for(int i = 0; i < dir.length; i++){
            int nr = r + dir[i][0];
            int nc = c + dir[i][1];
            if(!valid(nr, nc)) 
                continue;
            int nextValue = (clocks[nr][nc] + plusValue) % 4;
            nextValue = nextValue < 0 ? nextValue + 4 : nextValue;
            clocks[nr][nc] = nextValue;
        }
    }

    public boolean checkValue(int[][] clocks, int r, int c){
        int nr = r + dir[0][0];
        int nc = c + dir[0][1];
        if(!valid(nr, nc)) 
            return true;
        if(clocks[nr][nc] == 0) 
            return true;
        return false;
    }

    public boolean valid(int r, int c){
        if(r < 0 || c < 0 || r >= n || c >= n){
            return false;
        }
        return true;
    }

    public boolean isComplete(int[][] clocks){
        for(int i = 0; i < n; i++) {
            if(!Arrays.equals(clocks[i], zero)) 
                return false;
        }
        return true;
    }
}