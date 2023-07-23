/*
    구현문제인듯?
    기둥, 보 지도 만들어서 이용
*/
class Solution {
	static int [][] map_1, map_2;
	static int count;
	
    public int[][] solution(int n, int[][] build_frame) {
    	int[][] answer;
    	map_1 = new int[n+2][n+2];
    	map_2 = new int[n+2][n+2];
    	
    	
    	for(int i=0; i <build_frame.length; i++) {
    		build(build_frame[i], map_1, map_2, n);
    	}
    	
    	if(count > 0) {
    		answer = new int[count][3];
    	}
    	else {
    		return null;
    	}
    	
    	int idx = 0;
    	
    	for(int i=0; i<n+2; i++) {
    		for(int j=0; j<n+2; j++) {
    			if(map_2[i][j] == 1) {
    				int[] a = {i-1,j-1,0};
    				answer[idx++] = a;
    			}
    			if(map_1[i][j] == 1) {
    				int[] a = {i-1,j-1,1};
    				answer[idx++] = a;
    			}
    		}
    	}
    	
        return answer;
    }
    
    public void build(int[] order, int[][] map_1, int[][] map_2, int n) {
    	int x = order[0]+1; 
    	int y = order[1]+1; 
    	int a = order[2];
    	int b = order[3];
    	
    	switch (b) {
			case 0: 
				if(a == 0) {
					map_2[x][y] = 0;
					count--;
					
					if(!delete(x,y,n)) {
						map_2[x][y] = 1;
						count++;
					}
				}
				
				else if (a == 1) {
					map_1[x][y] = 0; 
					count --;
					
					if(!delete(x,y,n)) {
						map_1[x][y] = 1;
						count++;
					}
				}
				break;
				
			case 1: 
				if(a == 0) { //기둥
					if(make_2(x,y)) {
						map_2[x][y] = 1;
						count++;
					}
				}
				else if(a == 1) { //보
					if(make_1(x,y)) {
						map_1[x][y] = 1;
						count++;
					}
				}
				break;
			}
    }
    
    public boolean make_1 (int x, int y) {
        //왼쪽, 오른쪽 보
    	if(map_1[x-1][y] == 1 && map_1[x+1][y] == 1) 
            return true;
        
    	//왼쪽, 오른쪽 기둥
    	if(map_2[x][y-1] == 1 || map_2[x+1][y-1] ==1 ) 
            return true;

    	return false;
    }
    public boolean make_2 (int x, int y) {
        //보의 끝
    	if(map_1[x-1][y] == 1 || map_1[x][y] == 1) 
            return true;
        
    	//바닥 or 밑이 기둥
    	if(y == 1 || map_2[x][y-1] == 1) 
            return true;

        
    	return false;
    }
    
    public boolean delete(int x, int y, int n) {
    	for(int i=0; i<n+2; i++) {
    		for(int j=0; j<n+2; j++) {
    			if(map_1[i][j] == 1) {
    				if(!make_1(i,j)) {
    					return false;
    				}
    			}
    			
    			if(map_2[i][j] == 1) {
    				if(!make_2(i,j)) {
    					return false;
    				}
    			}
    			
    		}
    	}
    	return true;
    }
    
    
}