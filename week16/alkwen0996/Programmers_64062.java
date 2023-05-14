public class Programmers_64062 {
    private static int solution(final int[] stones, final int k) {
        int crossPeople = 0;
        int start = 1;
        int end = 200_000_000;

        // 이분탐색 알고리즘 사용
        // 시간복잡도 O(N) -> O(logN)까지 줄일 수 있음.
        while (start <= end) {
            int mid = (start + end) / 2;

            if (isCross(stones, k, mid)) {
                // 절반이 건널 수 있으면 mid 기준 + 1 의 숫자를 시작값으로 다시 이분탐색
                start = mid + 1;

                if (crossPeople < mid) {
                    crossPeople = mid;
                }
            } else {
                // 절반이 건널 수 없으면 mid 기준 -1 의 숫자를 끝값으로 다시 이분탐색
                end = mid - 1;
            }
        }

        return crossPeople;
    }

    private static boolean isCross(final int[] stones, final int k, final int mid) {
        int skipStones = 0;

        for (int stoneValue : stones) {
            if (stoneValue - mid < 0) {
                skipStones++;
            } else {
                // 0이상이면 밟을 수 있는 돌의미.
                skipStones = 0;
            }

            if (skipStones == k) {
                return false;
            }
        }

        return true;
    }
}
