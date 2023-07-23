import java.util.*;

/*
    문자열 판단
    문제에서 하라는대로 하면될 듯?
*/
class Solution {
	static int split;
    public String solution(String p) {
        String answer = "";
        
        if (p.equals("")) 
            return p;
        
        boolean right = isRight(p);
        
        String u = p.substring(0, split);
        String v = p.substring(split, p.length());
        
        if(right) {
        	return u + solution(v);
        }
        
        answer= "(" + solution(v) + ")";
        	
        for(int i=1; i<u.length()-1; i++) {
        	if(u.charAt(i) == '(') {
        		answer += ")";
        	}
        	else answer += "(";
        }
 
 
        return answer;
    }
    
    public boolean isRight(String p) {
    	Stack<Character> stack = new Stack<>();
    	
    	boolean right = true;
    	int l = 0; 
    	int r = 0;
    	
    	for(int i=0; i<p.length(); i++) {
    		
    		if(p.charAt(i) == '(') {
    			l++;
    			stack.push('(');
    		}
    		else {
    			r++;
    			if(!stack.isEmpty()) {
    				stack.pop();
    			}
    			else {
    				right = false; 
    			}
    		}
    		
    		if( l == r ) {
    			split = i + 1;
    			return right;
    		}
    	}
    	return right;
    }
}