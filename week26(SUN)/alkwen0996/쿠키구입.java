public class 쿠키구입 {
    public static void main(String[] args) {
        int[] cookie = {1, 1, 2, 3};
        System.out.println(solution(cookie));
    }

    public static int solution(int[] cookie) {
        int answer = 0;

        for (int i = 0; i < cookie.length - 1; i++) {
            int leftPointer = i;
            int rightPointer = i + 1;

            int leftSumCookies = cookie[leftPointer];
            int rightSumCookies = cookie[rightPointer];

            while (true) {
                if (leftSumCookies == rightSumCookies) {
                    if (answer < leftSumCookies) {
                        answer = leftSumCookies;
                    }
                }

                if (leftPointer > 0 && leftSumCookies <= rightSumCookies) {
                    leftPointer--;
                    leftSumCookies += cookie[leftPointer];
                } else if (rightPointer < cookie.length - 1 && leftSumCookies >= rightSumCookies) {
                    rightPointer++;
                    rightSumCookies += cookie[rightPointer];
                } else {
                    break;
                }

            }

        }

        return answer;
    }

}
