import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static class Building {
		Building anotherNextBuilding;
		int number;

		public Building(Building anotherNextBuilding, int number) {
			this.anotherNextBuilding = anotherNextBuilding;
			this.number = number;
		}
	}

	public static void main(String[] args) throws Exception {
		final int END = -1;
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder output = new StringBuilder();
		StringTokenizer tokenizer;
		int N = Integer.parseInt(reader.readLine());

		Building[] nextBuildings = new Building[N + 1];
		int[][] timeTaken = new int[N + 1][2];
		int[] degree = new int[N + 1];

		for (int buildingNumber = 1; buildingNumber <= N; buildingNumber++) {
			tokenizer = new StringTokenizer(reader.readLine());
			timeTaken[buildingNumber][1] = Integer.parseInt(tokenizer.nextToken());
			while (tokenizer.hasMoreElements()) {
				int prevBuildingNumber = Integer.parseInt(tokenizer.nextToken());
				if (prevBuildingNumber == END) {
					break;
				}
				degree[buildingNumber]++;
				nextBuildings[prevBuildingNumber] = new Building(
						nextBuildings[prevBuildingNumber],
						buildingNumber
				);
			}
		}

		topologicalSorting(nextBuildings, timeTaken, degree);

		for (int buildingNumber = 1; buildingNumber <= N; buildingNumber++) {
			output.append(timeTaken[buildingNumber][0] + timeTaken[buildingNumber][1])
					.append('\n');
		}

		System.out.print(output);
	}

	public static void topologicalSorting(
			Building[] nextBuildings, int[][] timeTaken, int[] degree
	) {
		int numOfBuildings = nextBuildings.length - 1;
		int[] queue = new int[numOfBuildings];
		int head = 0;
		int tail = 0;

		for (int buildingNumber = 1; buildingNumber <= numOfBuildings; buildingNumber++) {
			if (degree[buildingNumber] == 0) {
				queue[tail++] = buildingNumber;
			}
		}

		while (head < tail) {
			int building = queue[head++];
			int time = timeTaken[building][0] + timeTaken[building][1];
			Building nextBuilding = nextBuildings[building];
			while (nextBuilding != null) {
				int nextBuildingNumber = nextBuilding.number;
				timeTaken[nextBuildingNumber][0] = Math.max(
						timeTaken[nextBuildingNumber][0],
						time
				);
				if (--degree[nextBuildingNumber] == 0) {
					queue[tail++] = nextBuildingNumber;
				}
				nextBuilding = nextBuilding.anotherNextBuilding;
			}
		}
	}
}
