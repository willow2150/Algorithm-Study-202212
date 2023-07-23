class Solution {
    private final int COLUMN = 0;
    private final int BEAM = 1;

    public int[][] solution(int n, int[][] build_frame) {
        final int REMOVE = 0;
        final int INSTALL = 1;

        boolean[][][] installed = new boolean[n + 1][n + 1][2];
        int numOfBuildings = 0;

        for (int[] buildFrame : build_frame) {
            int x = buildFrame[0];
            int y = buildFrame[1];
            int type = buildFrame[2];
            int command = buildFrame[3];

            if (command == INSTALL) {
                if (type == COLUMN) {
                    if (canInstallColumn(installed, x, y, n)) {
                        installed[x][y][COLUMN] = true;
                        numOfBuildings++;
                    }
                } else {
                    if (canInstallBeam(installed, x, y, n)) {
                        installed[x][y][BEAM] = true;
                        numOfBuildings++;
                    }
                }
            } else {
                installed[x][y][type] = false;
                if (isValidCase(installed, n)) {
                    numOfBuildings--;
                    continue;
                }
                installed[x][y][type] = true;
            }
        }
        
        int[][] answer = new int[numOfBuildings][];
        int index = 0;

        for (int x = 0; x <= n; x++) {
            for (int y = 0; y <= n; y++) {
                for (int type = COLUMN; type <= BEAM; type++) {
                    if (installed[x][y][type]) {
                        answer[index++] = new int[] {x, y, type};
                    }
                }
            }
        }

        return answer;
    }

    public boolean canInstallColumn(boolean[][][] installed, int x, int y, int n) {
        if (y == 0)
            return true;
        if (installed[x][y - 1][COLUMN])
            return true;
        if (0 < x && installed[x - 1][y][BEAM])
            return true;
        return x < n && installed[x][y][BEAM];
    }

    public boolean canInstallBeam(boolean[][][] installed, int x, int y, int n) {
        if (installed[x][y - 1][COLUMN])
            return true;
        if (installed[x + 1][y - 1][COLUMN])
            return true;
        return 0 < x && installed[x - 1][y][BEAM] && x + 1 < n && installed[x + 1][y][BEAM];
    }

    public boolean isValidCase(boolean[][][] installed, int n) {
        for (int x = 0; x <= n; x++) {
            for (int y = 0; y <= n; y++) {
                if (installed[x][y][COLUMN] && !canInstallColumn(installed, x, y, n)) {
                    return false;
                }
                if (installed[x][y][BEAM] && !canInstallBeam(installed, x, y, n)) {
                    return false;
                }
            }
        }
        return true;
    }
}
