import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Beakjoon_18513 {

    // 일직선상 일차원 배열
    // N개의 샘터가 있다. K채의 집을 지을거다.
    // 오아시스 기준으로 집을 지어야한다... -> 오아시스를 중심으로.. 집을 지을 수 있는 방법이...?
    // 오아시스 좌표를 큐에 넣어서 그 주변으로 하나씩 지어 나가는거임!!
    // 음수 부분도 있으니 배열 인덱스도 바꿔야겠당.

    private static int N, K;
    private static int[] oasisLocations;

    public static void main(String[] args) throws IOException {
        final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());

        N = Integer.parseInt(stringTokenizer.nextToken());
        K = Integer.parseInt(stringTokenizer.nextToken());
        oasisLocations = new int[N];

        stringTokenizer = new StringTokenizer(bufferedReader.readLine());

        for (int i = 0; i < N; i++) {
            oasisLocations[i] = Integer.parseInt(stringTokenizer.nextToken());
        }

        findMinimumCase();
    }

    private static void findMinimumCase() {
        final Deque<Home> queue = new ArrayDeque<>();
        final boolean[] isVisited = new boolean[200_200_001]; // 샘터위치 1억씩 + 집이 최대 10만개 주어질 수 있으니까
        final int[] nextDirections = {-1, 1};

        for (final int oasis : oasisLocations) {
            queue.add(new Home(0, oasis));

            if (oasis >= 0) {
                isVisited[oasis] = true;
            } else {
                isVisited[changeIndexToPositiveNumber(isVisited.length, oasis)] = true;
            }
        }

        int houseCount = 0; // 집 지은 개수 저장
        long result = 0; // 총 불행도 저장

        while (!queue.isEmpty()) {
            final Home now = queue.poll();

            for (final int nextDirection : nextDirections) {
                int nextLocation = now.location + nextDirection;
                int nextDistance = now.distance + 1; // 다음 집이 지어질 거리
                int chaneIndex = changeIndexToPositiveNumber(isVisited.length, nextLocation); // 음수를 양수로 바꾼 인덱스

                if (nextLocation >= 0 && isVisited[nextLocation]) {
                    continue;
                }

                if (nextLocation < 0 && isVisited[chaneIndex]) {
                    continue;
                }

                if (houseCount == K) {
                    break;
                }

                houseCount++; // 다음 집 카운팅
                result += nextDistance; // 다음 집의 불행도 더해줌.

                if (nextLocation >= 0) {
                    isVisited[nextLocation] = true;
                } else {
                    isVisited[chaneIndex] = true;
                }

                queue.offer(new Home(nextDistance, nextLocation));
            }
        }

        System.out.println(result);
    }

    private static int changeIndexToPositiveNumber(final int length, final int index) {
        return (length / 2) + Math.abs(index);
    }

    static class Home {
        private final int distance;
        private final int location;

        public Home(final int distance, final int location) {
            this.distance = distance;
            this.location = location;
        }
    }

}
