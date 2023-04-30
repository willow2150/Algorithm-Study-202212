class Solution {
	private static final int HOUR = 3_600;
	private static final int MINUTE = 60;

	public String solution(String play_time, String adv_time, String[] logs) {
		int playTimeSec = changeTimeToSecond(play_time);
		int advTimeSec = changeTimeToSecond(adv_time);
		long[] timeTable = new long[playTimeSec + 1];

		for (String log : logs) {
			String[] timeRange = log.split("-");
			int startTimeSec = changeTimeToSecond(timeRange[0]);
			int endTimeSec = changeTimeToSecond(timeRange[1]);
			timeTable[++startTimeSec]++;
			if (++endTimeSec <= playTimeSec)
				timeTable[endTimeSec]--;
		}

		for (int second = 1; second <= playTimeSec; second++) {
			timeTable[second] += timeTable[second - 1];
		}

		for (int second = 1; second <= playTimeSec; second++) {
			timeTable[second] += timeTable[second - 1];
		}

		int advStartSec = 0;
		long maxAdvExposureSec = timeTable[advTimeSec];
		for (int advEndSec = advTimeSec + 1; advEndSec <= playTimeSec; advEndSec++) {
			long advExposureSec = timeTable[advEndSec] - timeTable[advEndSec - advTimeSec];
			if (maxAdvExposureSec < advExposureSec) {
				maxAdvExposureSec = advExposureSec;
				advStartSec = advEndSec - advTimeSec;
			}
		}

		return changeSecondToTime(advStartSec);
	}

	public int changeTimeToSecond(String time) {
		String[] timeParts = time.split(":");
		int hour = Integer.parseInt(timeParts[0]);
		int minute = Integer.parseInt(timeParts[1]);
		int second = Integer.parseInt(timeParts[2]);
		return hour * HOUR + minute * MINUTE + second;
	}

	public String changeSecondToTime(int second) {
		StringBuilder time = new StringBuilder();
		time.append(String.format("%02d", second / HOUR)).append(':');
		second %= HOUR;
		time.append(String.format("%02d", second / MINUTE)).append(':');
		time.append(String.format("%02d", second % MINUTE));
		return time.toString();
	}
}
