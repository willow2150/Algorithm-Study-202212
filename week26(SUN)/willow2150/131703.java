class Solution {
    public int solution(int[][] beginning, int[][] target) {
        final int INVALID = -1;
        int minNumOfFlips = findMinNumOfFlips(beginning, target, 0, 0);
        return minNumOfFlips == Integer.MAX_VALUE ? INVALID : minNumOfFlips;
    }

    public int findMinNumOfFlips(int[][] tableA, int[][] tableB, int code, int numOfFlips) {
        if (isSameTable(tableA, tableB)) {
            return numOfFlips;
        }
        if (code == (tableA.length + tableA[0].length)) {
            return Integer.MAX_VALUE;
        }
        int numOfFlipsA = findMinNumOfFlips(tableA, tableB, code + 1, numOfFlips);
        flipTable(tableA, code);
        int numOfFlipsB = findMinNumOfFlips(tableA, tableB, code + 1, numOfFlips + 1);
        flipTable(tableA, code);
        return Math.min(numOfFlipsA, numOfFlipsB);
    }

    public boolean isSameTable(int[][] tableA, int[][] tableB) {
        int tableHeight = tableA.length;
        int tableWidth = tableA[0].length;
        for (int row = 0; row < tableHeight; row++) {
            for (int col = 0; col < tableWidth; col++) {
                if (tableA[row][col] == tableB[row][col]) {
                    continue;
                }
                return false;
            }
        }
        return true;
    }

    public void flipTable(int[][] table, int code) {
        int tableHeight = table.length;
        int tableWidth = table[0].length;
        if (code < tableHeight) {
            for (int col = 0; col < tableWidth; col++) {
                table[code][col] ^= 1;
            }
        } else {
            code -= tableHeight;
            for (int row = 0; row < tableHeight; row++) {
                table[row][code] ^= 1;
            }
        }
    }
}
