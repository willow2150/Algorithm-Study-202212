class Solution {
    public int solution(int[] cookie) {
        int[] dp = new int[cookie.length + 1];
        int maxNumOfSnacks = 0;

        for (int index = 0; index < cookie.length; index++) {
            dp[index + 1] = dp[index] + cookie[index];
        }

        for (int mid = 1; mid < dp.length; mid++) {
            int right = mid + 1;
            for (int left = mid - 1; 0 <= left; left--) {
                int numOfSnacksForOlder = dp[mid] - dp[left];
                while (right < dp.length) {
                    int numOfSnacksForYoung = dp[right] - dp[mid];
                    if (numOfSnacksForOlder > numOfSnacksForYoung) {
                        right++;
                    } else {
                        if (numOfSnacksForOlder == numOfSnacksForYoung) {
                            maxNumOfSnacks = Math.max(
                                    maxNumOfSnacks,
                                    numOfSnacksForYoung
                            );
                        }
                        break;
                    }
                }
            }
        }

        return maxNumOfSnacks;
    }
}
