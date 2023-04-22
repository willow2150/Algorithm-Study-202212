package pratice;

/*
연산자 우선순위를 동일하게 할 수 없다
계산된 결과가 음수라면 절댓값으로 변환

연산자가 3개니까 3!의 경우의수를 모두 고려해서 계산하면 되지않을까
*/

import java.util.List;
import java.util.ArrayList;

class Solution {
    char[] oper = {'+', '-', '*'};
    boolean[] visited = new boolean[3];
    List<Long> numbers = new ArrayList<>();
    List<Character> opers = new ArrayList<>();
    long answer;

    public long solution(String expression) {
        String num ="";

        for(int i  = 0; i < expression.length(); i++) {
            if(expression.charAt(i) >= '0' && expression.charAt(i) <= '9') {
                num += expression.charAt(i);
            }else {
                numbers.add(Long.parseLong(num));
                opers.add(expression.charAt(i));
                num = "";
            }
        }
        numbers.add(Long.parseLong(num));

        dfs(0, new char[3]);
        return answer;
    }

    public void dfs (int depth, char[] p) {
        if(depth == 3) {
            List<Long> copyNumbers = new ArrayList<>(numbers);
            List<Character> copyOpers = new ArrayList<>(opers);

            for(int i = 0; i < p.length; i++) {
                for(int j = 0; j < copyOpers.size(); j++) {
                    if(copyOpers.get(j) == p[i]) {
                        Long res = calc(copyNumbers.remove(j), copyNumbers.remove(j), p[i]);
                        copyNumbers.add(j, res);
                        copyOpers.remove(j);
                        j--;
                    }
                }
            }
            answer = Math.max(answer, Math.abs(copyNumbers.get(0)));
            return;
        }

        for(int i = 0; i < 3; i++) {
            if(!visited[i]) {
                visited[i] = true;
                p[depth] = oper[i];
                dfs(depth+1, p);
                visited[i] = false;
            }
        }

    }
    public Long calc(Long num1, Long num2, char oper) {
        long num = 0;
        if(oper == '*') {
            return num1 * num2;
        }else if(oper == '+') {
            return num1 + num2;
        }else {
            return num1 - num2;
        }
    }
}
