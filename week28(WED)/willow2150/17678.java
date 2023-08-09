import java.util.Arrays;

class Solution {
    public String solution(int n, int t, int m, String[] timetable) {
        int[] times = new int[timetable.length];
        int busTime = 540;
        int lastBusTime = busTime + (n - 1) * t;
        int passengerIndex = 0;
        int numOfSeatsLeft = 0;

        for (int passengerIdx = 0; passengerIdx < timetable.length; passengerIdx++) {
            times[passengerIdx] = transTimeToMinute(timetable[passengerIdx]);
        }

        Arrays.sort(times);

        for (int turn = 0; turn < n; turn++) {
            numOfSeatsLeft = m;
            while (0 < numOfSeatsLeft && passengerIndex < timetable.length) {
                if (times[passengerIndex] <= busTime) {
                    numOfSeatsLeft--;
                    passengerIndex++;
                    continue;
                }
                break;
            }
            if (passengerIndex == timetable.length) {
                break;
            }
            busTime += t;
        }

        busTime = 0 < numOfSeatsLeft ? lastBusTime : times[passengerIndex - 1] - 1;
        return transMinuteToTime(busTime);
    }

    public int transTimeToMinute(String time) {
        return (time.charAt(0) - '0') * 600
                + (time.charAt(1) - '0') * 60
                + (time.charAt(3) - '0') * 10
                + (time.charAt(4) - '0');
    }

    public String transMinuteToTime(int minute) {
        return String.format("%02d:%02d", minute / 60, minute % 60);
    }
}
