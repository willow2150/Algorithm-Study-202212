import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

public class 당구연습 {
    public static void main(String[] args) {
        int m = 10;
        int n = 10;
        int startX = 3;
        int startY = 7;
        int[][] balls = {{7, 7}, {2, 7}, {7, 3}};

        System.out.println(Arrays.toString(solution(m, n, startX, startY, balls)));
    }

    public static int[] solution(final int m, final int n, final int startX, final int startY, final int[][] balls) {
        int[] answer = new int[balls.length];

        for (int i = 0; i < balls.length; i++) {
            List<Point> moveBalls = calculateDistance(m, n, new Point(startX, startY), new Point(balls[i][0], balls[i][1]));

            int minDistance = Integer.MAX_VALUE;

            for (Point moveBall : moveBalls) {
                int bixX = Math.max(moveBall.x, startX);
                int bixY = Math.max(moveBall.y, startY);
                int smallX = Math.min(moveBall.x, startX);
                int smallY = Math.min(moveBall.y, startY);

                int distance = (int) Math.pow(bixX - smallX, 2) + (int) Math.pow(bixY - smallY, 2);
                minDistance = Math.min(minDistance, distance);
            }

            answer[i] = minDistance;
        }

        return answer;
    }

    public static List<Point> calculateDistance(final int m, final int n, final Point startPoint, Point targetPoint) {
        List<Point> balls = new ArrayList<>();

        if (!(startPoint.x == targetPoint.x && startPoint.y > targetPoint.y)) {
            balls.add(new Point(targetPoint.x, targetPoint.y * -1));
        }

        if (!(startPoint.x == targetPoint.x && startPoint.y < targetPoint.y)) {
            balls.add(new Point(targetPoint.x, (2 * n) - targetPoint.y));
        }

        if (!(startPoint.x < targetPoint.x && startPoint.y == targetPoint.y)) {
            balls.add(new Point((2 * m) - targetPoint.x, targetPoint.y));
        }

        if (!(startPoint.x > targetPoint.x && startPoint.y == targetPoint.y)) {
            balls.add(new Point(targetPoint.x * -1, targetPoint.y));
        }

        return balls;
    }

}
