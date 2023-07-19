import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Baekjoon_11812 {
    // 제일 가까운 부모노드를 찾아서 부모노드에서부터 두 노드에 대한 거리를 구하면 될듯.
    private static long n, k, q;
    private static List<Node> points;

    public static void main(String[] args) throws IOException {
        inputData();
        findPath();
    }

    private static void findPath() {
        StringBuilder stringBuilder = new StringBuilder();

        for (Node node : points) {
            long x = node.x;
            long y = node.y;
            long count = 0;

            if (k == 1) {
                // k = 1일 경우 바닥부터 10^15까지 찾아서 시간초과 나는듯
                count = Math.abs(x - y);
            } else {
                while (x != y) {
                    // 부모 노드 찾는 공식 ((노드-2) / k) + 1
                    if (x < y) {
                        y = (y - 2) / k + 1;
                    } else {
                        x = (x - 2) / k + 1;
                    }

                    count++;
                }
            }

            stringBuilder.append(count).append("\n");
        }

        System.out.println(stringBuilder);
    }

    private static void inputData() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());

        n = Long.parseLong(stringTokenizer.nextToken());
        k = Integer.parseInt(stringTokenizer.nextToken());
        q = Integer.parseInt(stringTokenizer.nextToken());

        points = new ArrayList<>();

        for (int i = 0; i < q; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());

            points.add(new Node(
                    Long.parseLong(stringTokenizer.nextToken()),
                    Long.parseLong(stringTokenizer.nextToken())
            ));
        }
    }

    static class Node {
        private long x;
        private long y;

        public Node(final long x, final long y) {
            this.x = x;
            this.y = y;
        }
    }

}
