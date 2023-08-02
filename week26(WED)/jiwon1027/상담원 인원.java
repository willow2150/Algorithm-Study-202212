import java.util.*;
/*
    모든 경우를 보면서 백트래킹 하는 방식으로 해볼까
*/

class Solution {
    public static final int INF = Integer.MAX_VALUE;
    int[] mentoCase;
    int answer = INF;

    public int solution(int k, int n, int[][] reqs) {
        mentoCase = new int[k];
        dfs(k, n, reqs, 0);
        return answer;
    }

    public void dfs(int k, int n, int[][] reqs, int depth) {
        if (depth == k) {
            int result = getMinWaitTime(k, reqs);
            answer = Math.min(answer, result);
            return;
        }

        if (depth == k-1) {
            mentoCase[depth] = n;
            dfs(k, 0, reqs, depth+1);
            return;
        }

        for (int i = 1; i <= n - (k - depth) + 1; i++) {
            mentoCase[depth] = i;
            dfs(k, n-i, reqs, depth+1);
        }
    }

    public int getMinWaitTime(int k, int[][] reqs) {
        int[][] endTime = new int[k][20]; 
        int waitTimeSum = 0;

        for (int[] req : reqs) {
            int start = req[0];
            int end = req[1];
            int type = req[2] - 1;

            int minWaitTime = INF;
            int minWaitMentoIdx = 0;
            for (int i = 0; i < mentoCase[type]; i++) {
                int waitTime = endTime[type][i] - start;

                if (waitTime < minWaitTime) {
                    minWaitMentoIdx = i;

                    if (waitTime <= 0) { 
                        minWaitTime = 0;
                        break;
                    } else { 
                        minWaitTime = waitTime;
                    }
                }

            }

            endTime[type][minWaitMentoIdx] = start + end + minWaitTime;
            waitTimeSum += minWaitTime;
        }

        return waitTimeSum;
    }
}