import java.util.ArrayList;
import java.util.List;

class Solution {
	static class Cell {
		String value;
		int row;
		int col;
		Cell parent;
		List<Cell> children;

		Cell(int row, int col) {
			this.value = "EMPTY";
			this.row = row;
			this.col = col;
			parent = this;
			children = new ArrayList<>();
		}
	}

	public String[] solution(String[] commands) {
		final int TABLE_SIZE = 50;
		List<String> output = new ArrayList<>();
		Cell[][] table = new Cell[TABLE_SIZE + 1][TABLE_SIZE + 1];

		for (int row = 1; row <= TABLE_SIZE; row++)
			for (int col = 1; col <= TABLE_SIZE; col++)
				table[row][col] = new Cell(row, col);

		for (String command : commands) {
			String[] commandParts = command.split(" ");
			String action = commandParts[0];
			if ("UPDATE".equals(action)) {
				if (commandParts.length == 4) {
					int row = Integer.parseInt(commandParts[1]);
					int col = Integer.parseInt(commandParts[2]);
					findParentCell(table[row][col]).value = commandParts[3];
				} else {
					String value1 = commandParts[1];
					String value2 = commandParts[2];
					for (int row = 1; row <= TABLE_SIZE; row++) {
						for (int col = 1; col <= TABLE_SIZE; col++) {
							if (value1.equals(table[row][col].value)) {
								table[row][col].value = value2;
							}
						}
					}
				}
			} else if ("MERGE".equals(action)) {
				int rowA = Integer.parseInt(commandParts[1]);
				int colA = Integer.parseInt(commandParts[2]);
				int rowB = Integer.parseInt(commandParts[3]);
				int colB = Integer.parseInt(commandParts[4]);
				Cell cellA = findParentCell(table[rowA][colA]);
				Cell cellB = findParentCell(table[rowB][colB]);
				if (cellA != cellB) {
					mergeCell(cellA, cellB);
				}
			} else if ("UNMERGE".equals(action)) {
				int row = Integer.parseInt(commandParts[1]);
				int col = Integer.parseInt(commandParts[2]);
				Cell cell = table[row][col];
				Cell parent = findParentCell(cell);
				for (Cell child : parent.children) {
					child.parent = child;
					child.value = "EMPTY";
				}
				if (cell != parent) {
					cell.parent = cell;
					cell.value = parent.value;
					parent.value = "EMPTY";
				}
				parent.children.clear();
			} else {
				int row = Integer.parseInt(commandParts[1]);
				int col = Integer.parseInt(commandParts[2]);
				output.add(findParentCell(table[row][col]).value);
			}
		}

		return output.toArray(new String[0]);
	}

	public Cell findParentCell(Cell cell) {
		if (cell.parent == cell)
			return cell;
		return cell.parent = findParentCell(cell.parent);
	}

	public void mergeCell(Cell cellA, Cell cellB) {
		if ("EMPTY".equals(cellA.value)) {
			cellA.value = cellB.value;
		}
		cellB.parent = cellA;
		cellA.children.add(cellB);
		cellA.children.addAll(cellB.children);
		cellB.children.clear();
	}
}
