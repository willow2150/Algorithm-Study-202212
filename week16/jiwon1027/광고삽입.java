import java.util.LinkedList;
import java.util.Queue;

/*
 * 특별한 규칙이 없는 데이터들을 일단 배열에 넣는건 필수
 * 그 다음 광고 길이 만큼 탐색을 해야하는데 슬라이딩 윈도우로 하면 될듯?
 * */
class Solution {

    static int[] data = new int[360000];
    public String solution(String play_time, String adv_time, String[] logs) {

        for (int i = 0; i < logs.length; i++) {
            int start = stringToSecend(logs[i].substring(0, 8));
            int end = stringToSecend(logs[i].substring(9, 17));

            for (int j = start; j < end; j++) {
                data[j]++;
            }
        }

        int play_time_int = stringToSecend(play_time);
        int adv_time_int = stringToSecend(adv_time);

        // 1. 큐를 이용한 슬라이딩 윈도우
        int start = 0;
        long sum = 0;
        Queue<Integer> queue = new LinkedList<>();
        for(int i=0; i<adv_time_int; i++) {
            sum += data[i];
            queue.add(data[i]);
        }

        long max = sum;

        for(int i=adv_time_int; i<=play_time_int; i++) {
            sum += data[i];
            queue.add(data[i]);
            sum -= queue.poll();

            if(sum > max) {
                start = i - adv_time_int + 1;
                max = sum;
            }
        }

        // 2. 투 포인터
//        long sum = 0;
//        for (int i = 0; i < adv_time_int; i++) {
//            sum += data[i];
//        }
//
//        long max = sum;
//        int start = 0;
//        for (int i = 1, j = adv_time_int; j < play_time_int; i++, j++) {
//            sum += data[j] - data[i - 1];
//
//            if (max < sum) {
//                max = sum;
//                start = i;
//            }
//        }

        return secendToString(start);
    }

    public int stringToSecend(String time){
        String[] time_split = time.split(":");
        int hour = Integer.parseInt(time_split[0]) * 60 * 60;
        int min = Integer.parseInt(time_split[1]) * 60;
        int sec = Integer.parseInt(time_split[2]);

        return hour + min + sec;
    }

    public String secendToString(int secend){

        String hour = Integer.toString(secend / 3600);
        secend %= 3600;

        String min = Integer.toString(secend / 60);
        secend %= 60;

        String sec = Integer.toString(secend);

        if (Integer.parseInt(hour) < 10) {
            hour = "0" + hour;
        }
        if (Integer.parseInt(min) < 10) {
            min = "0" + min;
        }
        if (Integer.parseInt(sec) < 10) {
            sec = "0" + sec;
        }

        return hour+":"+min+":"+sec;
    }
}
