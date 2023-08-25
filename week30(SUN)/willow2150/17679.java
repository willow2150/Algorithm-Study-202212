import java.util.HashSet;
import java.util.Set;

class Solution {
    static class Block {
        int row;
        int col;
        char type;

        Block(int row, int col, char type) {
            this.row = row;
            this.col = col;
            this.type = type;
        }

        @Override
        public int hashCode() {
            return row * 30 + col;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj instanceof Block) {
                Block block = (Block)obj;
                return this.row == block.row && this.col == block.col;
            }
            return false;
        }
    }

    private final char BLANK = '*';

    public int solution(int m, int n, String[] board) {
        Block[][] map = new Block[m][n];
        int numOfTotalRemovedBlocks = 0;

        for (int row = 0; row < m; row++) {
            for (int col = 0; col < n; col++) {
                map[row][col] = new Block(row, col, board[row].charAt(col));
            }
        }

        while (true) {
            int numOfRemovedBlocks = removeBlocks(m, n, map);
            if (numOfRemovedBlocks == 0) {
                break;
            }
            numOfTotalRemovedBlocks += numOfRemovedBlocks;
            dropBlocks(m, n, map);
        }

        return numOfTotalRemovedBlocks;
    }

    public int removeBlocks(int m, int n, Block[][] map) {
        Set<Block> blockSet = new HashSet<>();
        int boundaryRow = m - 1;
        int boundaryCol = n - 1;

        for (int row = 0; row < boundaryRow; row++) {
            for (int col = 0; col < boundaryCol; col++) {
                if (map[row][col].type == BLANK) {
                    continue;
                }
                if (map[row][col].type != map[row + 1][col].type) {
                    continue;
                }
                if (map[row][col].type != map[row][col + 1].type) {
                    continue;
                }
                if (map[row][col].type != map[row + 1][col + 1].type) {
                    continue;
                }
                blockSet.add(map[row][col]);
                blockSet.add(map[row + 1][col]);
                blockSet.add(map[row][col + 1]);
                blockSet.add(map[row + 1][col + 1]);
            }
        }

        for (Block block : blockSet) {
            block.type = BLANK;
        }

        return blockSet.size();
    }

    public void dropBlocks(int m, int n, Block[][] map) {
        for (int col = 0; col < n; col++) {
            for (int row = m - 1; 0 < row; row--) {
                if (map[row][col].type == BLANK) {
                    int upperRow = row - 1;
                    while (0 <= upperRow && map[upperRow][col].type == BLANK) {
                        upperRow--;
                    }
                    if (0 <= upperRow) {
                        map[row][col].type = map[upperRow][col].type;
                        map[upperRow][col].type = BLANK;
                    }
                }
            }
        }
    }
}
