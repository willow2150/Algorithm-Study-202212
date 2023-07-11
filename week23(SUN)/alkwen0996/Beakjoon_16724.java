import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Beakjoon_16724 {
    private static char[][] map;
    private static int[] parent;
    private static int N, M;

    public static void main(String[] args) throws IOException {
        inputData();
        findSafeZone();
        print();
    }

    private static void print() {
        Set<Integer> set = new HashSet<>();

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                set.add(findParent(i * M + j));
            }
        }

        System.out.println(set.size());
    }

    private static void findSafeZone() {
        int x, y;

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                x = i;
                y = j;

                if (map[i][j] == 'U') {
                    x--;
                } else if (map[i][j] == 'D') {
                    x++;
                } else if (map[i][j] == 'L') {
                    y--;
                } else if (map[i][j] == 'R') {
                    y++;
                }

                union(i * M + j, x * M + y);
            }
        }
    }

    private static void union(final int x, final int y) {
        int a = findParent(x);
        int b = findParent(y);

        if (a < b) {
            parent[b] = a;
            return;
        }

        parent[a] = b;
    }

    private static int findParent(final int number) {
        if (parent[number] == number) {
            return number;
        }

        return parent[number] = findParent(parent[number]);
    }

    private static void inputData() throws IOException {
        final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        final StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());

        N = Integer.parseInt(stringTokenizer.nextToken());
        M = Integer.parseInt(stringTokenizer.nextToken());

        map = new char[N][M];
        parent = new int[N * M];

        for (int i = 0; i < N; i++) {
            char[] line = bufferedReader.readLine().toCharArray();

            for (int j = 0; j < line.length; j++) {
                map[i][j] = line[j];
                parent[i * M + j] = i * M + j;
            }
        }

    }

}
