import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        final int TOTAL_TIME = 12 * 60;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        int N = Integer.parseInt(tokenizer.nextToken());
        int T = Integer.parseInt(tokenizer.nextToken());
        int P = Integer.parseInt(tokenizer.nextToken());

        boolean[][] seatTable = new boolean[TOTAL_TIME + 1][N + 1];
        int[][] reservations = new int[T][];

        for (int reservationIdx = 0; reservationIdx < T; reservationIdx++) {
            reservations[reservationIdx] = getTimes(reader.readLine());
        }

        Arrays.sort(reservations, new Comparator<int[]>() {
            @Override
            public int compare(int[] reservationA, int[] reservationB) {
                if (reservationA[0] == reservationB[0]) {
                    if (reservationA[1] == reservationB[1]) {
                        return 0;
                    }
                    return reservationA[1] < reservationB[1] ? -1 : 1;
                }
                return reservationA[0] < reservationB[0] ? -1 : 1;
            }
        });

        for (int[] reservation : reservations) {
            int startTime = reservation[0];
            int endTime = reservation[1];
            int seatNumber = findSeatNumber(seatTable[startTime], N);
            for (int time = startTime; time < endTime; time++) {
                seatTable[time][seatNumber] = true;
            }
        }

        int sittingMinutes = 0;

        for (int time = 0; time < TOTAL_TIME; time++) {
            if (seatTable[time][P]) {
                continue;
            }
            sittingMinutes++;
        }

        System.out.print(sittingMinutes);
    }

    public static int[] getTimes(String timeInfo) {
        int startTime = ((timeInfo.charAt(0) - '0') * 10 + (timeInfo.charAt(1) - '0')) * 60
                + (timeInfo.charAt(2) - '0') * 10 + (timeInfo.charAt(3) - '0')
                - 540;
        int endTime = ((timeInfo.charAt(5) - '0') * 10 + (timeInfo.charAt(6) - '0')) * 60
                + (timeInfo.charAt(7) - '0') * 10 + (timeInfo.charAt(8) - '0')
                - 540;
        return new int[] {startTime, endTime};
    }

    public static int findSeatNumber(boolean[] seats, int N) {
        int maxDistance = -1;
        int bestSeatNumber = 1;
        int lSeatNumber = 1;

        for (int seatNumber = 1; seatNumber <= N; seatNumber++) {
            if (seats[seatNumber]) {
                lSeatNumber = seatNumber;
                continue;
            }

            int leftDistance = seatNumber - lSeatNumber;
            int rightDistance = 1;
            int distance;

            for (int rSeatNumber = seatNumber + 1; rSeatNumber <= N; rSeatNumber++) {
                if (seats[rSeatNumber]) {
                    break;
                }
                rightDistance++;
            }

            if (seatNumber == 1) {
                distance = rightDistance;
            } else if (seatNumber == N) {
                distance = leftDistance;
            } else {
                distance = Math.min(leftDistance, rightDistance);
            }

            if (maxDistance < distance) {
                maxDistance = distance;
                bestSeatNumber = seatNumber;
            }
        }

        return bestSeatNumber;
    }
}
