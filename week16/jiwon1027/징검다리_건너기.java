import java.util.*;
/*
* 이분탐색으로 하면 될듯
* 내구성 기준으로 sort 후 진행
*
* */

class 징검다리_건너기 {
    static int[] sortStones;

    public int solution(int[] stones, int k) {
        sortStones = stones.clone();

        Arrays.sort(sortStones);

        int answer = binarySearch(stones, k);

        return answer;
    }

    private static int binarySearch(int[] stones, int k)
    {
        int left = 0;
        int right = stones.length;
        int mid;
        int w = 0;
        int answer = 0;

        while (left < right)
        {
            mid = (left + right) / 2;
            w = sortStones[mid];

            if (go(stones, w, k)) {
                left = mid + 1;
            } else {
                right = mid;
                answer = w;
            }
        }
        return answer;
    }

    private static boolean go(int[] stones, int w, int k)
    {
        int zeroCnt = 0;

        for (int i = 0; i < stones.length; i++) {
            if (stones[i] <= w) zeroCnt++;
            else zeroCnt = 0;

            if (zeroCnt == k) return false;
        }
        return true;
    }
}