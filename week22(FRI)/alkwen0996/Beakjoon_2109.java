import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Beakjoon_2109 {
    public static void main(String[] args) throws IOException {
        final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        final int n = Integer.parseInt(bufferedReader.readLine());
        final Queue<Lecture> lectures = new PriorityQueue<>();

        StringTokenizer stringTokenizer;
        for (int i = 0; i < n; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());

            lectures.add(new Lecture(
                    Integer.parseInt(stringTokenizer.nextToken()),
                    Integer.parseInt(stringTokenizer.nextToken())
            ));
        }

        getMaximumMoney(lectures);
    }

    private static void getMaximumMoney(final Queue<Lecture> lectures) {
        int[] fees = new int[10_001];
        Arrays.fill(fees, -1);
        long result = 0;

        while (!lectures.isEmpty()) {
            final Lecture lecture = lectures.poll();

            for (int i = lecture.schedule; i >= 1; i--) {
                if (fees[i] == -1) {
                    fees[i] = lecture.fee;
                    result += fees[i];
                    break;
                }
            }
        }

        System.out.println(result);
    }

    static class Lecture implements Comparable<Lecture> {
        private int fee;
        private int schedule;

        public Lecture(final int fee, final int schedule) {
            this.fee = fee;
            this.schedule = schedule;
        }

        @Override
        public int compareTo(final Lecture lecture) {
            if (lecture.fee > this.fee) {
                return 1;
            } else if (lecture.fee < this.fee) {
                return -1;
            } else {
                return Integer.compare(lecture.schedule, this.schedule);
            }

        }

        @Override
        public String toString() {
            return "Lecture{" +
                    "fee=" + fee +
                    ", schedule=" + schedule +
                    '}';
        }
    }

}
