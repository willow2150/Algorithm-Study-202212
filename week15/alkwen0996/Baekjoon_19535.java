import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Baekjoon_19535 {

    public static void main(String[] args) throws IOException {
        final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        final int vertexCount = Integer.parseInt(bufferedReader.readLine());

        final long[] edge = new long[vertexCount + 1];
        long D = 0, G = 0;

        final Queue<Point> queue = new LinkedList<>();
        StringTokenizer stringTokenizer;

        for (int i = 0; i < vertexCount - 1; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            final int vertex1 = Integer.parseInt(stringTokenizer.nextToken());
            final int vertex2 = Integer.parseInt(stringTokenizer.nextToken());

            edge[vertex1]++;
            edge[vertex2]++;

            queue.add(new Point(vertex1, vertex2));
        }

        // ㅈ Tree 찾기
        for (int i = 1; i <= vertexCount; i++) {
            if (edge[i] >= 3) {
                // nC3 = n! / 3!(n-3)! = n(n-1)(n-2) / 3 * 2
                final long n = edge[i];
                G += (n * (n - 1) * (n - 2)) / 6;
            }
        }

        // ㄷ Tree 찾기
        while (!queue.isEmpty()) {
            final Point now = queue.poll();
            D += (edge[now.x] - 1) * (edge[now.y] - 1);
        }

        if (G * 3 < D) {
            System.out.println("D");
        } else if (G * 3 > D) {
            System.out.println("G");
        } else {
            System.out.println("DUDUDUNGA");
        }
    }

}
