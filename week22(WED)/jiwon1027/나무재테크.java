import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
* 가장 처음에 양분은 모든 칸에 5만큼 들어있다
* K년이 지난 후 살아있는 나무의 개수는?
*
* 시뮬레이션 이네
* */

public class Main {
    static int N,M,K;
    static int[][] A, map;
    static List<int[]> trees;
    static List<int[]> liveTrees;
    static List<int[]> deadTrees;
    static int[] dx = { -1, -1, -1, 0, 0, 1, 1, 1 };
    static int[] dy = { -1, 0, 1, -1, 1, -1, 0, 1 };
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        A = new int[N][N];
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            Arrays.fill(map[i],5);
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                A[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        trees = new ArrayList<>();

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            trees.add(new int[]{ Integer.parseInt(st.nextToken())-1, Integer.parseInt(st.nextToken())-1, Integer.parseInt(st.nextToken())});
        }

        while (K > 0) {
            liveTrees = new ArrayList<>();
            deadTrees = new ArrayList<>();

            spring();
            summer();
            fall();
            winter();
            K--;
        }

        System.out.println(trees.size());

    }


    /*
     * 봄 : 자신의 나이만큼 양분을 먹고, 나이 1 증가
     * 하나의 칸에 여러 개의 나무가 있다면, 나이가 어린 나무부터 양분을 먹는다
     * 땅에 양분이 부족해 자신의 나이만큼 양분을 먹을 수 없는 나무는 양분을 먹지 못하고 즉시 죽는다.
     * 
     * 나무가 없어질 수 있으니 List로 하는게 편해보이네
     * */
    public static void spring() {
        Collections.sort(trees, (o1, o2) -> (o1[2] - o2[2]));

        for (int i = 0; i < trees.size(); i++) {
            int[] cur_tree = trees.get(i);
            int x = cur_tree[0];
            int y = cur_tree[1];
            int age = cur_tree[2];

            if (age > map[x][y]) {
                deadTrees.add(new int[]{x, y, age});
            } else {
                map[x][y] -= age;
                liveTrees.add(new int[]{x, y, ++age});
            }
        }

        trees.clear();
        trees.addAll(liveTrees);
    }

    /*
    * 여름 : 봄에 죽은 나무가 양분으로 변하게 된다. 각각의 죽은 나무마다 나이를 2로 나눈 값이 나무가 있던 칸에 양분으로 추가된다. 소수점 아래는 버린다.
    * */
    public static void summer() {
        for (int i = 0; i < deadTrees.size(); i++) {
            int[] cur_tree = deadTrees.get(i);
            int x = cur_tree[0];
            int y = cur_tree[1];
            int age = cur_tree[2];

            map[x][y] += age/2;
        }
    }

    /*
    * 가을 :나무가 번식한다. 번식하는 나무는 나이가 5의 배수이어야 하며, 인접한 8개의 칸에 나이가 1인 나무가 생긴다.
    * 어떤 칸 (r, c)와 인접한 칸은 (r-1, c-1), (r-1, c), (r-1, c+1), (r, c-1), (r, c+1), (r+1, c-1), (r+1, c), (r+1, c+1) 이다.
    * 상도의 땅을 벗어나는 칸에는 나무가 생기지 않는다.
    * */
    public static void fall() {
        for (int i = 0; i < trees.size(); i++) {
            int[] cur_tree = trees.get(i);
            int x = cur_tree[0];
            int y = cur_tree[1];
            int age = cur_tree[2];

            if (age % 5 == 0) {
                for (int j = 0; j < 8; j++) {
                    int nx = x + dx[j];
                    int ny = y + dy[j];
                    if (0 <= nx && 0 <= ny && nx < N && ny < N) {
                        trees.add(new int[]{nx, ny, 1});
                    }
                }
            }
        }
    }

    /*
    * 겨울 : 땅을 돌아다니면서 땅에 양분을 추가한다. 각 칸에 추가되는 양분의 양은 A[r][c]
    * */
    public static void winter() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                map[i][j] += A[i][j];
            }
        }

    }
}