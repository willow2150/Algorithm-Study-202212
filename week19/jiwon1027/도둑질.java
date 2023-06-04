/*
DP 이긴한데
집을 털었는지 안털었는지에 대한 케이스도 나뉠테니 2차원으로 만들고
dp[i] = max(dp[i-2]+money[i], dp[i-1])

일자로 있다고 생각한다음에 처음과 끝이 이어져 있다고 생각만 하면 되지않을까? => 이걸 어떻게 표현하지?
0번째가 방문을 했으니 length-1과 이어져 있으니까 length-2을 선택
*/
class Solution {
    public int solution(int[] money) {
        int length = money.length;
        int[][] dp = new int[2][length];
        
		dp[0][0] = money[0];
		dp[0][1] = money[0];
		dp[1][0] = 0;
		dp[1][1] = money[1];
		
		for(int i=2; i<length ; i++) {
			dp[0][i] = Math.max(dp[0][i-2]+money[i], dp[0][i-1]);
			dp[1][i] = Math.max(dp[1][i-2]+money[i], dp[1][i-1]);
		}
		
		return Math.max(dp[0][length-2], dp[1][length-1]);
    }
}