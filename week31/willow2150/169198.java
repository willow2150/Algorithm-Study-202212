class Solution {
    public int[] solution(int m, int n, int startX, int startY, int[][] balls) {
        int[] result = new int[balls.length];
        int doubleM = m << 1;
        int doubleN = n << 1;

        for (int ballIdx = 0; ballIdx < balls.length; ballIdx++) {
            int[] ball = balls[ballIdx];
            int xDifference = ball[0] - startX;
            int xSum = ball[0] + startX;
            int yDifference = ball[1] - startY;
            int ySum = ball[1] + startY;
            int minDistancePow = Integer.MAX_VALUE;

            if (startX == ball[0]) {
                minDistancePow = Math.min(
                        minDistancePow,
                        (int)Math.pow(startY < ball[1] ? ySum : doubleN - ySum, 2)
                );
            } else {
                minDistancePow = Math.min(
                        minDistancePow,
                        (int)Math.pow(xDifference, 2) + (int)Math.pow(ySum, 2)
                );
                minDistancePow = Math.min(
                        minDistancePow,
                        (int)Math.pow(xDifference, 2) + (int)Math.pow(doubleN - ySum, 2)
                );
            }

            if (startY == ball[1]) {
                minDistancePow = Math.min(
                        minDistancePow,
                        (int)Math.pow(startX < ball[0] ? xSum : doubleM - xSum, 2)
                );
            } else {
                minDistancePow = Math.min(
                        minDistancePow,
                        (int)Math.pow(xSum, 2) + (int)Math.pow(yDifference, 2)
                );
                minDistancePow = Math.min(
                        minDistancePow,
                        (int)Math.pow(doubleM - xSum, 2) + (int)Math.pow(yDifference, 2)
                );
            }

            result[ballIdx] = minDistancePow;
        }

        return result;
    }
}