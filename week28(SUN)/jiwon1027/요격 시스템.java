import java.util.*;
/*
    1억이니까 하나씩 체크하는건 불가능
    끝나는게 동일하다면 시작하는걸로 정렬 때리고 아니면 끝나는시간으로
*/

class Solution {
    public int solution(int[][] targets) {
        int answer = 0;
        
        // e 기준으로 오름차순 정렬
        Arrays.sort(targets, (o1, o2) -> {
            return o1[1] == o2[1] ? o1[0] - o2[0] : o1[1] - o2[1];
        });
 
        int end = targets[0][1];
        answer++;
        
        for (int[] t: targets) {
            if(t[0] >= end) {
                end = t[1];
                answer++;
            }
        }
        
        return answer;
    }
}