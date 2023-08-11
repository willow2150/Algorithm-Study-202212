import java.util.Stack;

class Solution {
    static class Row {
        Row prev;
        Row next;
        boolean removed;
    }

    public String solution(int n, int k, String[] cmd) {
        final char REMOVED = 'X';
        final char NOT_REMOVED = 'O';
        final char UP = 'U';
        final char DOWN = 'D';
        final char REMOVE = 'C';
        StringBuilder output = new StringBuilder();
        Stack<Row> stack = new Stack<>();
        Row[] rows = new Row[n];
        Row pointer;

        rows[0] = new Row();
        for (int rowNumber = 1; rowNumber < n; rowNumber++) {
            rows[rowNumber] = new Row();
            rows[rowNumber - 1].next = rows[rowNumber];
            rows[rowNumber].prev = rows[rowNumber - 1];
        }
        pointer = rows[k];

        for (String command : cmd) {
            char type = command.charAt(0);
            if (type == UP) {
                int numOfMoves = Integer.parseInt(command.split(" ")[1]);
                while (0 < numOfMoves-- && pointer.prev != null) {
                    pointer = pointer.prev;
                }
            } else if (type == DOWN) {
                int numOfMoves = Integer.parseInt(command.split(" ")[1]);
                while (0 < numOfMoves-- && pointer.next != null) {
                    pointer = pointer.next;
                }
            } else if (type == REMOVE) {
                stack.push(pointer);
                pointer.removed = true;
                if (pointer.next == null) {
                    pointer.prev.next = null;
                    pointer = pointer.prev;
                } else if (pointer.prev == null) {
                    pointer.next.prev = null;
                    pointer = pointer.next;
                } else {
                    pointer.prev.next = pointer.next;
                    pointer.next.prev = pointer.prev;
                    pointer = pointer.next;
                }
            } else {
                Row row = stack.pop();
                row.removed = false;
                if (row.prev != null) {
                    row.prev.next = row;
                }
                if (row.next != null) {
                    row.next.prev = row;
                }
            }
        }

        for (Row row : rows) {
            output.append(row.removed ? REMOVED : NOT_REMOVED);
        }

        return output.toString();
    }
}
