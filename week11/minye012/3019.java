
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int C, P, res;
    static int[] heights;
    static int[][][] shapes = {
            {{0}, {0, 0, 0, 0}},
            {{0, 0}},
            {{1, 1, 0}, {0, 1}},
            {{0, 1, 1}, {1, 0}},
            {{0, 0, 0}, {1, 0}, {0, 1, 0}, {0, 1}},
            {{0, 0, 0}, {0, 0}, {1, 0, 0}, {0, 2}},
            {{0, 0, 0}, {2, 0}, {0, 0, 1}, {0, 0}}
    };

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        C = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken()) - 1;
        heights = new int[C];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < C; i++) {
            heights[i] = Integer.parseInt(st.nextToken());
        }

        int cases = shapes[P].length;
        for (int c = 0; c < cases; c++) {
            int len = shapes[P][c].length;
            for (int i = 0; i < C - len + 1; i++) {
                int gap = shapes[P][c][0] + heights[i];
                boolean check = true;
                for (int j = 0; j < len; j++) {
                    if (shapes[P][c][j] + heights[i + j] != gap) {
                        check = false;
                        break;
                    }
                }
                if (check)
                    res++;
            }
        }

        System.out.println(res);
    }
}
