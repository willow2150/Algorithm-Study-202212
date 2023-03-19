import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static final int r = 0, c = 1;
	static int R, C, N;
	static boolean[][] map;
	static int[][] dr;
	static int minNum;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		//R C입력, map 생성
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new boolean[R][C];

		//map 입력
		for (int ridx = 0; ridx < R; ridx++) {
			st = new StringTokenizer(br.readLine());
			for (int cidx = 0; cidx < C; cidx++) {
				map[ridx][cidx] = st.nextToken().equals("1");
			}
		}

		//규칙 생성
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		dr = new int[N][2];
		for (int n = 0; n < N; n++) {
			st = new StringTokenizer(br.readLine());
			dr[n][r] = Integer.parseInt(st.nextToken());
			dr[n][c] = Integer.parseInt(st.nextToken());
		}
		Queue<Block> queue = new LinkedList<>();
		for (int startCol = 0; startCol < C; startCol++) {
			if (map[0][startCol]) queue.add(new Block(0, startCol, 0));
			map[0][startCol] = false;
		}
		while (!queue.isEmpty()) {
			Block nowBlock = queue.poll();

			for (int[] d : dr) {
				Block nextBlock = new Block(nowBlock.row + d[r], nowBlock.col + d[c], nowBlock.depth+1);
				if (nextBlock.row < 0 || R <= nextBlock.row ||
						nextBlock.col < 0 || C <= nextBlock.col || !map[nextBlock.row][nextBlock.col]) continue;
				if (nextBlock.row == R-1) {
					System.out.print(nextBlock.depth);
					return;
				}
				map[nextBlock.row][nextBlock.col] = false;
				queue.add(nextBlock);
			}
		}

		System.out.print(-1);
	}

	private static void bfs(boolean[][] copyMap, int startCol) {
	}

	static private class Block{
		int row;
		int col;
		int depth;

		public Block(int row, int col, int depth) {
			this.row = row;
			this.col = col;
			this.depth = depth;
		}
	}
}
