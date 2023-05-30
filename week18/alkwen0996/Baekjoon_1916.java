import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Baekjoon_1916 {

	public static void main(String[] args) throws IOException {
		final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		final int cityCount = Integer.parseInt(bufferedReader.readLine());
		final int busLineCount = Integer.parseInt(bufferedReader.readLine());

		final List<List<Bus>> map = new ArrayList<>();

		for (int i = 0; i <= cityCount; i++) {
			map.add(new ArrayList<>());
		}

		StringTokenizer stringTokenizer;

		for (int i = 0; i < busLineCount; i++) {
			stringTokenizer = new StringTokenizer(bufferedReader.readLine());

			final int start = Integer.parseInt(stringTokenizer.nextToken());
			final int end = Integer.parseInt(stringTokenizer.nextToken());
			final int cost = Integer.parseInt(stringTokenizer.nextToken());

			map.get(start).add(new Bus(end, cost));
		}

		stringTokenizer = new StringTokenizer(bufferedReader.readLine());
		final int startPoint = Integer.parseInt(stringTokenizer.nextToken());
		final int endPoint = Integer.parseInt(stringTokenizer.nextToken());

		System.out.println(findMinimumCostPath(startPoint, endPoint, map));
	}

	private static int findMinimumCostPath(final int startPoint, final int endPoint, final List<List<Bus>> map) {
		final int[] costs = new int[map.size()];
		Arrays.fill(costs, Integer.MAX_VALUE);

		final Queue<Bus> queue = new PriorityQueue<>();
		queue.add(new Bus(startPoint, 0));
		costs[startPoint] = 0;
		costs[0] = 0;

		while (!queue.isEmpty()) {
			final Bus bus = queue.poll();

			if (costs[bus.end] < bus.cost) {
				continue;
			}

			for (int i = 0; i < map.get(bus.end).size(); i++) {
				final Bus nextBus = map.get(bus.end).get(i);

				if (costs[nextBus.end] > bus.cost + nextBus.cost) {
					costs[nextBus.end] = bus.cost + nextBus.cost;
					queue.add(new Bus(nextBus.end, costs[nextBus.end]));
				}
			}
		}

		return costs[endPoint];
	}

	static class Bus implements Comparable<Bus> {
		private final int end;
		private final int cost;

		public Bus(final int end, final int cost) {
			this.end = end;
			this.cost = cost;
		}

		@Override
		public int compareTo(final Bus bus) {
			return this.cost - bus.cost;
		}
	}

}
