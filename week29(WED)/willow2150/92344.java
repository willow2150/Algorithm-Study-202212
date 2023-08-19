class Solution {
    public int solution(int[][] board, int[][] skill) {
        final int ATTACK = 1;
        int numOfUnbrokenBuildings = 0;
        int height = board.length;
        int width = board[0].length;
        int[][] prefixSum = new int[height][width];

        for (int[] s : skill) {
            int type = s[0];
            int rowA = s[1];
            int colA = s[2];
            int rowB = s[3];
            int colB = s[4];
            int damage = (type == ATTACK ? -s[5] : s[5]);

            prefixSum[rowA][colA] += damage;
            if (1 + rowB < height) {
                prefixSum[1 + rowB][colA] -= damage;
            }
            if (1 + colB < width) {
                prefixSum[rowA][1 + colB] -= damage;
            }
            if (1 + rowB < height && 1 + colB < width) {
                prefixSum[1 + rowB][1 + colB] += damage;
            }
        }

        for (int row = 0; row < height; row++) {
            for (int col = 1; col < width; col++) {
                prefixSum[row][col] += prefixSum[row][col - 1];
            }
        }

        for (int col = 0; col < width; col++) {
            for (int row = 1; row < height; row++) {
                prefixSum[row][col] += prefixSum[row - 1][col];
            }
        }

        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                if (0 < prefixSum[row][col] + board[row][col]) {
                    numOfUnbrokenBuildings++;
                }
            }
        }

        return numOfUnbrokenBuildings;
    }
}
