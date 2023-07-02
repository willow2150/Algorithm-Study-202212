import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class Main {
	public static void main(String[] args) throws Exception {
		final char BLANK = ' ';
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(reader.readLine());
		int[][] lectures = new int[n][];

		for (int lectureIndex = 0; lectureIndex < n; lectureIndex++) {
			String inputLine = reader.readLine();
			int boundaryIndex = inputLine.indexOf(BLANK);
			int d = Integer.parseInt(inputLine.substring(0, boundaryIndex));
			int p = Integer.parseInt(inputLine.substring(boundaryIndex + 1));
			lectures[lectureIndex] = new int[] {d, p};
		}

		System.out.print(calcMaxRevenue(lectures));
	}

	public static int calcMaxRevenue(int[][] lectures) {
		Queue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
			@Override
			public int compare(int[] lectureA, int[] lectureB) {
				if (lectureA[0] == lectureB[0]) {
					return 0;
				}
				return lectureA[0] < lectureB[0] ? -1 : 1;
			}
		});
		int maxRevenue = 0;

		Arrays.sort(lectures, new Comparator<int[]>() {
			@Override
			public int compare(int[] lectureA, int[] lectureB) {
				if (lectureA[1] == lectureB[1]) {
					return 0;
				}
				return lectureA[1] < lectureB[1] ? -1 : 1;
			}
		});

		for (int[] lecture : lectures) {
			if (pq.size() < lecture[1]) {
				maxRevenue += lecture[0];
				pq.add(lecture);
			} else if (pq.peek()[0] < lecture[0]) {
				maxRevenue += lecture[0] - pq.poll()[0];
				pq.add(lecture);
			}
		}

		return maxRevenue;
	}
}
