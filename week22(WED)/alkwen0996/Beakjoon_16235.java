import java.awt.Point;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Beakjoon_16235 {
    private static int[][] map, foodMap;
    private static List<Tree> liveTrees;
    private static Queue<Tree> deadTrees;
    private static final int[] dx = {1, 0, 1, 1, -1, 0, -1, -1};
    private static final int[] dy = {0, 1, 1, -1, 0, -1, -1, 1};
    public static final int DEFAULT_FOOD = 5;

    public static void main(String[] args) throws IOException {
        final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());

        final int N = Integer.parseInt(stringTokenizer.nextToken());
        final int M = Integer.parseInt(stringTokenizer.nextToken());
        final int K = Integer.parseInt(stringTokenizer.nextToken());

        map = new int[N + 1][N + 1]; // 초기 양분이 세팅된 땅
        foodMap = new int[N + 1][N + 1]; // 땅의 각 칸에 추가되는 양문의 양

        for (int i = 1; i <= N; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());

            for (int j = 1; j <= N; j++) {
                map[i][j] = DEFAULT_FOOD;
                foodMap[i][j] = Integer.parseInt(stringTokenizer.nextToken());
            }
        }

        liveTrees = new LinkedList<>();
        deadTrees = new LinkedList<>();

        for (int i = 0; i < M; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            liveTrees.add(
                    new Tree(
                            new Point(
                                    Integer.parseInt(stringTokenizer.nextToken()),
                                    Integer.parseInt(stringTokenizer.nextToken())
                            ),
                            Integer.parseInt(stringTokenizer.nextToken())
                    )
            );
        }

        simulate(K);
        System.out.println(liveTrees.size());
    }

    private static void simulate(int K) {
        while (K > 0) {
            spring();
            summer();
            fall();
            winter();

            K--;
        }

    }

    private static void winter() {
        for (int i = 1; i < foodMap.length; i++) {
            for (int j = 1; j < foodMap.length; j++) {
                map[i][j] += foodMap[i][j];
            }
        }
    }

    private static void fall() {
        final List<Tree> newTrees = new LinkedList<>();

        for (final Tree tree : liveTrees) { // foreach 사용시 ConcurrentModificationException 발생.
            if (tree.age % 5 == 0) {
                for (int j = 0; j < dx.length; j++) {
                    final int newX = tree.point.x + dx[j];
                    final int newY = tree.point.y + dy[j];

                    if (newX < 1 || newX >= map.length || newY < 1 || newY >= map.length) {
                        continue;
                    }

                    newTrees.add(new Tree(new Point(newX, newY), 1));
                }
            }
        }

        liveTrees.addAll(0, newTrees); // 같은 장소에 있는 나무는 나이가 어린 나무부터 먼저 양분을 먹어야 하니까...
    }

    private static void summer() {
        while (!deadTrees.isEmpty()) {
            final Tree tree = deadTrees.poll();
            map[tree.point.x][tree.point.y] += (tree.age / 2);
        }

    }

    private static void spring() {
        final Iterator<Tree> iterator = liveTrees.iterator();

        while (iterator.hasNext()) {
            final Tree tree = iterator.next();

            if (map[tree.point.x][tree.point.y] >= tree.age) {
                map[tree.point.x][tree.point.y] -= tree.age; // 양분먹은만큼 감소
                tree.age++; // 양분먹고 나이 1살증가
            } else {
                deadTrees.add(tree); // 나중에 죽은나무 영양분으로 만들기위해 저장.
                iterator.remove(); // 살아있는 나무에서 삭제. -> ConcurrentModificationException 때문에 iterator로 삭제
            }
        }

    }

    static class Tree implements Comparable<Tree> {
        private final Point point;
        private int age;

        public Tree(final Point point, final int age) {
            this.point = point;
            this.age = age;
        }

        @Override
        public String toString() {
            return "Tree{" +
                    "point=" + point +
                    ", age=" + age +
                    '}';
        }

        @Override
        public int compareTo(final Tree tree) {
            return this.age - tree.age;
        }
    }

}