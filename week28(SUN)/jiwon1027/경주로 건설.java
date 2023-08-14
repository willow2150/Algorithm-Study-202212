import java.util.*;

/*
    bfs로 최소비용 구하면 적합할듯
    이미 방문한 노드도 재 방문이 가능한데 3차원으로 해서 판단해주기
*/
class Solution {
    
    int[] dx = {0, 1, 0, -1};
    int[] dy = {1, 0, -1, 0};
    boolean[][][] visited;
    int N;
    
    public int solution(int[][] board) {
        visited = new boolean[board.length][board.length][4];
        N = board.length;

        return bfs(board);
    }
    
    public int bfs(int[][] board) {
        Queue<Node> q = new LinkedList<>();
       
        q.add(new Node(0, 0, -1, 0));
        
        int min_cost = Integer.MAX_VALUE;
        while(!q.isEmpty()) {
            Node current = q.poll();
            if(current.x == N - 1 && current.y == N - 1) 
                min_cost = Math.min(min_cost, current.cost);
            
            for(int i = 0; i < 4; i++) {
                int nx = current.x + dx[i];
                int ny = current.y + dy[i];
                
                if(nx >= 0 && ny >= 0 && nx < N && ny < N && board[nx][ny] != 1) {
                    int next_cost = 0;
                    if(current.dir == i || current.dir == -1) 
                        next_cost = current.cost + 100; 
                    else  
                        next_cost = current.cost + 600; 
                    
                    if(!visited[nx][ny][i] || board[nx][ny] >= next_cost) {
                        q.add(new Node(nx, ny, i, next_cost));
                        visited[nx][ny][i] = true;
                        board[nx][ny] = next_cost;
                    }
                }
            }
        }
        return min_cost;
    }
    
    public class Node {
        int x, y, dir, cost;
        
        public Node(int x, int y, int dir, int cost) {
            this.x = x;
            this.y = y;
            this.dir = dir;
            this.cost = cost;
        }
    }
}
