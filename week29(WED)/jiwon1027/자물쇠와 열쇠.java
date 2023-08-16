import java.util.*;
 /*
    열쇠를 이동해가면서 모든 칸에 하나씩 맞춰보고
    열쇠를 회전도 시켜야됨
    N + 2(M-1) x N + 2(M-1) 까지 키우면 1x1씩 가능
 */
class Solution {
    public boolean solution(int[][] key, int[][] lock) {
        int M = key.length;
        int N = lock.length;
        int exN = N + 2*(M-1);
        int[][] extendedLock = new int[exN][exN];
        int hole = 0;
        
        for(int i=0; i<exN; i++) {
            Arrays.fill(extendedLock[i], -1);
        }
        
        for(int i=M-1; i<M-1+N; i++) {
            for(int j=M-1; j<M-1+N; j++) {
                extendedLock[i][j] = lock[i-(M-1)][j-(M-1)];
                if(extendedLock[i][j] == 0) 
                    hole++;
            }
        }
        
        for(int i=0; i<4; i++) {
            if(open(M, key, exN, extendedLock, hole))
                return true;
            key = rotate(M, key);
        }
        return false;
    }
    
    private boolean open(int M, int[][] key, int N, int[][] lock, int hole) {
        for(int i=0; i<N-(M-1); i++) {
            for(int j=0; j<N-(M-1); j++) {
                if(fit(M, key, i, j, lock, hole)) 
                    return true;
            }
        }
        return false;
    }
    
    private boolean fit(int M, int[][] key, int r, int c, int[][] lock, int hole) {
        int ki = 0; 
        int kj = 0;
        int cnt = 0;
        
        for(int i=r; i<r+M; i++, ki++) {
            for(int j=c; j<c+M; j++, kj++) {
                if(lock[i][j] == key[ki][kj]) // 동일한거
                    return false;
                if(lock[i][j]==0 && key[ki][kj]==1) // 다른거
                    cnt++; 
            }
            kj = 0;
        }
        
        if(cnt != hole) 
            return false; 
        return true;
    }
    
    private int[][] rotate(int M, int[][] key) {
        int[][] rotated = new int[M][M];
        
        for(int r=0; r<M; r++) {
            for(int c=0; c<M; c++) {
                rotated[c][M-r-1] = key[r][c];
            }
        }
        
        return rotated;
    }
}