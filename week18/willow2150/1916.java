import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static class Bus implements Comparable<Bus> {
		final int to;
		final int cost;

		public Bus(int to, int cost) {
			this.to = to;
			this.cost = cost;
		}

		@Override
		public int compareTo(Bus bus) {
			return this.cost - bus.cost;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokenizer;
		ArrayList<ArrayList<Bus>> busForEachCity = new ArrayList<>();
		int numOfCities = Integer.parseInt(reader.readLine());
		int numOfBus = Integer.parseInt(reader.readLine());

		for (int i = 0; i <= numOfCities; i++)
			busForEachCity.add(new ArrayList<>());

		for (int i = 0; i < numOfBus; i++) {
			tokenizer = new StringTokenizer(reader.readLine());
			int from = Integer.parseInt(tokenizer.nextToken());
			int to = Integer.parseInt(tokenizer.nextToken());
			int cost = Integer.parseInt(tokenizer.nextToken());
			busForEachCity.get(from).add(new Bus(to, cost));
		}

		tokenizer = new StringTokenizer(reader.readLine());
		System.out.print(printMinCost(
				busForEachCity,
				numOfCities,
				Integer.parseInt(tokenizer.nextToken()),
				Integer.parseInt(tokenizer.nextToken())
		));
	}

	public static int printMinCost(
			ArrayList<ArrayList<Bus>> busForEachCity, 
			int numOfCities, int depCity, int arrCity
	) {
		final int INF = 100_000_000;
		Queue<Bus> priorityQueue = new PriorityQueue<>();
		int[] minCostForEachCity = new int[numOfCities + 1];

		Arrays.fill(minCostForEachCity, INF);
		priorityQueue.add(new Bus(depCity, 0));

		while (!priorityQueue.isEmpty()) {
			Bus bus = priorityQueue.poll();
			if (bus.cost > minCostForEachCity[bus.to])
				continue;
			if (bus.to == arrCity)
				break;
			for (Bus nextBus : busForEachCity.get(bus.to)) {
				if (bus.cost + nextBus.cost < minCostForEachCity[nextBus.to]) {
					minCostForEachCity[nextBus.to] = bus.cost + nextBus.cost;
					priorityQueue.add(new Bus(nextBus.to, minCostForEachCity[nextBus.to]));
				}
			}
		}
		return minCostForEachCity[arrCity];
	}
}
