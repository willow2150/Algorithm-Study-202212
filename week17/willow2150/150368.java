import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

class Solution {
	public int[] solution(int[][] users, int[] emoticons) {
		final double[] DIST_RATES = {1.0D, 0.9D, 0.8D, 0.7D, 0.6D};
		List<int[]> distRateIdxCombs = new ArrayList<>();
		double properRevenue = 0;
		int maxNumOfSubscribers = 0;
		int numOfEmoticons = emoticons.length;

		getDistRateIdxCombs(
				distRateIdxCombs, new int[numOfEmoticons], new int[] {10, 20, 30, 40}, 0
		);

		for (int[] distRateIdxComb : distRateIdxCombs) {
			double totalRevenue = 0;
			int numOfSubscribers = 0;
			for (int[] user : users) {
				int userDistRate = user[0];
				double revenue = 0;
				for (int idx = 0; idx < numOfEmoticons; idx++) {
					if (userDistRate <= distRateIdxComb[idx]) {
						revenue += emoticons[idx] * DIST_RATES[(distRateIdxComb[idx] / 10)];
					}
				}
				if (user[1] <= revenue) {
					numOfSubscribers++;
				} else {
					totalRevenue += revenue;
				}
			}
			if (maxNumOfSubscribers < numOfSubscribers) {
				maxNumOfSubscribers = numOfSubscribers;
				properRevenue = totalRevenue;
			} else if (maxNumOfSubscribers == numOfSubscribers) {
				properRevenue = Math.max(properRevenue, totalRevenue);
			}
		}

		return new int[] {maxNumOfSubscribers, (int)properRevenue};
	}

	public static void getDistRateIdxCombs(
			List<int[]> distRateIdxCombs,
			int[] distRateIdxComb, int[] distRateIdxArray, int index
	) {
		if (index == distRateIdxComb.length) {
			distRateIdxCombs.add(Arrays.copyOf(distRateIdxComb, index));
			return;
		}
		for (int distRateIdx : distRateIdxArray) {
			distRateIdxComb[index] = distRateIdx;
			getDistRateIdxCombs(
					distRateIdxCombs, distRateIdxComb,
					distRateIdxArray, index + 1
			);
		}
	}
}
