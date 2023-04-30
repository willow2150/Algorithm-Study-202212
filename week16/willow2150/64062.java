class Solution {
	public int solution(int[] stones, int k) {
		int minPeopleCnt = 1;
		int maxPeopleCnt = 200_000_000;
		while (minPeopleCnt <= maxPeopleCnt) {
			int peopleCnt = (minPeopleCnt + maxPeopleCnt) >> 1;
			int skippedStoneCnt = 0;
			for (int stone : stones) {
				if (k <= skippedStoneCnt)
					break;
				if (stone < peopleCnt) {
					skippedStoneCnt++;
				} else {
					skippedStoneCnt = 0;
				}
			}
			if (k <= skippedStoneCnt) {
				maxPeopleCnt = peopleCnt - 1;
			} else {
				minPeopleCnt = peopleCnt + 1;
			}
		}
		return maxPeopleCnt;
	}
}
