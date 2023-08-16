public class 고고학최고의발견 {
    static int N, result = Integer.MAX_VALUE;
    static int[][] map;
    static int[] rotation;
    static int[] dx = {0, 0, 0, 1, -1};
    static int[] dy = {0, 1, -1, 0, 0};

    public static void main(String[] args) {
        int[][] clockHands = {
                {0, 3, 3, 0},
                {3, 2, 2, 3},
                {0, 3, 2, 0},
                {0, 3, 3, 3}
        };

        System.out.println(solution(clockHands));
    }

    public static int solution(int[][] clockHands) {
        N = clockHands.length;
        map = new int[N][N];
        rotation = new int[N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++)
                map[i][j] = 4 - clockHands[i][j] == 4 ? 0 : 4 - clockHands[i][j];
        }

        //dfs 진행
        dfs(0);

        return result;
    }

    //첫번째 줄 0~N-1 번째 칼럼의 회전수 정하기
    public static void dfs(int ind) {
        //정합성 판별
        if (ind == N) {

            //초기화
            int[][] tempMap = new int[N][N];
            int[] current = new int[N];

            System.arraycopy(rotation, 0, current, 0, N);

            for (int i = 0; i < N; i++) {
                System.arraycopy(map[i], 0, tempMap[i], 0, N);
            }

            int tempResult = 0;
            //0번째 row부터 마지막 row까지 반복
            for (int i = 0; i < N; i++) {
                //특정 row에 대해 시계 돌려줌
                for (int j = 0; j < N; j++) {
                    tempResult += current[j];
                    //4방향+현재 위치 시계 돌려줌
                    for (int d = 0; d < 5; d++) {
                        int X = i + dx[d];
                        int Y = j + dy[d];

                        if (X < 0 || Y < 0 || X >= N || Y >= N) {
                            continue;
                        }

                        tempMap[X][Y] = tempMap[X][Y] - current[j] >= 0 ? tempMap[X][Y] - current[j] : tempMap[X][Y] - current[j] + 4;
                    }
                }

                //다음 row가 돌려야할 횟수
                System.arraycopy(tempMap[i], 0, current, 0, N);
            }

            //마지막 열의 상태 판별하여 결과 계산
            boolean flag = true;

            for (int i = 0; i < N; i++) {
                if (current[i] != 0) {
                    flag = false;
                    break;
                }
            }

            if (flag) {
                result = Math.min(result, tempResult);
            }

            return;
        }

        //회전수 정하기
        for (int i = 0; i < 4; i++) {
            rotation[ind] = i;
            dfs(ind + 1);
        }
    }

}
