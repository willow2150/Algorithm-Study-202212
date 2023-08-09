class Solution {
    static class Point {
        int row;
        int col;

        Point(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    private final int NUM_OF_ALPHABETS = 26;
    private final char A = 'A';
    private final char BLANK = '.';
    private final char WALL = '*';

    public String solution(int m, int n, String[] board) {
        final String IMPOSSIBLE = "IMPOSSIBLE";
        StringBuilder output = new StringBuilder();
        Point[][] pointTable = new Point[NUM_OF_ALPHABETS][2];
        String answer;

        setPoints(m, n, board, pointTable);

        while (true) {
            char character = getNextCharacter(board, pointTable);
            if (character == BLANK) {
                break;
            }
            output.append(character);
        }

        answer = output.toString();

        for (Point[] points : pointTable) {
            if (points[0] == null) {
                continue;
            }
            answer = IMPOSSIBLE;
            break;
        }

        return answer;
    }

    public void setPoints(int m, int n, String[] board, Point[][] pointTable) {
        for (int row = 0; row < m; row++) {
            String line = board[row];
            for (int col = 0; col < n; col++) {
                char character = line.charAt(col);
                if (character == WALL || character == BLANK) {
                    continue;
                }
                int index = character - A;
                if (pointTable[index][0] == null) {
                    pointTable[index][0] = new Point(row, col);
                } else {
                    pointTable[index][1] = new Point(row, col);
                }
            }
        }
    }

    public char getNextCharacter(String[] board, Point[][] pointTable) {
        for (int index = 0; index < NUM_OF_ALPHABETS; index++) {
            if (pointTable[index][0] == null) {
                continue;
            }

            int topRow = Math.min(pointTable[index][0].row, pointTable[index][1].row);
            int bottomRow = Math.max(pointTable[index][0].row, pointTable[index][1].row);
            int leftCol = Math.min(pointTable[index][0].col, pointTable[index][1].col);
            int rightCol = Math.max(pointTable[index][0].col, pointTable[index][1].col);
            char targetCharacter = (char)(index + A);
            boolean left = checkVertical(
                    board, pointTable, targetCharacter, topRow, bottomRow, leftCol
            );
            boolean right = checkVertical(
                    board, pointTable, targetCharacter, topRow, bottomRow, rightCol
            );
            boolean top = checkHorizon(
                    board, pointTable, targetCharacter, leftCol, rightCol, topRow
            );
            boolean bottom = checkHorizon(
                    board, pointTable, targetCharacter, leftCol, rightCol, bottomRow
            );

            if (pointTable[index][0].col <= pointTable[index][1].col) {
                if ((left && bottom) || (right && top)) {
                    pointTable[index][0] = null;
                    pointTable[index][1] = null;
                    return targetCharacter;
                }
            } else {
                if ((left && top) || (right && bottom)) {
                    pointTable[index][0] = null;
                    pointTable[index][1] = null;
                    return targetCharacter;
                }
            }
        }
        return BLANK;
    }

    public boolean checkVertical(
            String[] board, Point[][] pointTable,
            char targetCharacter, int topRow, int bottomRow, int col
    ) {
        for (int row = topRow; row <= bottomRow; row++) {
            char character = board[row].charAt(col);
            int index = character - A;
            if (character == WALL) {
                return false;
            }
            if (character == targetCharacter || character == BLANK) {
                continue;
            }
            if (pointTable[index][0] == null) {
                continue;
            }
            return false;
        }
        return true;
    }

    public boolean checkHorizon(
            String[] board, Point[][] pointTable,
            char targetCharacter, int leftCol, int rightCol, int row
    ) {
        for (int col = leftCol; col <= rightCol; col++) {
            char character = board[row].charAt(col);
            int index = character - A;
            if (character == WALL) {
                return false;
            }
            if (character == targetCharacter || character == BLANK) {
                continue;
            }
            if (pointTable[index][0] == null) {
                continue;
            }
            return false;
        }
        return true;
    }
}
