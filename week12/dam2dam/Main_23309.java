import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_23309 {

	static final String PRINT_NEXT_AND_OPEN = "BN";
	static final String PRINT_PREVIOUS_AND_OPEN = "BP";
	static final String CLOSE_AND_PRINT_NEXT = "CN";
	static final String CLOSE_AND_PRINT_PREVIOUS = "CP";
	static final int MAX_STATION_NUMBER = 1_000_000;
	static final int NOTHING = -1;
	static final String NEXT_LINE = "\n";

	static int theNumberOfStations, theNumberOfConstructions, stationNumber, criteriaStationNumber, openStationNumber;
	static String construction;
	static int[] previousStations, nextStations;

	public static void main(String[] args) throws Exception {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stringTokenizer;
		StringBuilder answer = new StringBuilder();

		stringTokenizer = new StringTokenizer(bufferedReader.readLine());
		theNumberOfStations = Integer.parseInt(stringTokenizer.nextToken());
		theNumberOfConstructions = Integer.parseInt(stringTokenizer.nextToken());

		previousStations = new int[MAX_STATION_NUMBER + 1];
		nextStations = new int[MAX_STATION_NUMBER + 1];
		criteriaStationNumber = NOTHING;
		stringTokenizer = new StringTokenizer(bufferedReader.readLine());
		for (int i = 0; i < theNumberOfStations; i++) {
			stationNumber = Integer.parseInt(stringTokenizer.nextToken());
			openStation(criteriaStationNumber, stationNumber);
			criteriaStationNumber = stationNumber;
		}

		for (int i = 0; i < theNumberOfConstructions; i++) {
			stringTokenizer = new StringTokenizer(bufferedReader.readLine());
			construction = stringTokenizer.nextToken();
			criteriaStationNumber = Integer.parseInt(stringTokenizer.nextToken());

			switch (construction) {
				case PRINT_NEXT_AND_OPEN:
					openStationNumber = Integer.parseInt(stringTokenizer.nextToken());

					answer.append(nextStations[criteriaStationNumber]).append(NEXT_LINE);
					openStation(criteriaStationNumber, openStationNumber);
					break;

				case PRINT_PREVIOUS_AND_OPEN:
					openStationNumber = Integer.parseInt(stringTokenizer.nextToken());

					answer.append(previousStations[criteriaStationNumber]).append(NEXT_LINE);
					openStation(previousStations[criteriaStationNumber], openStationNumber);
					break;

				case CLOSE_AND_PRINT_NEXT:

					answer.append(nextStations[criteriaStationNumber]).append(NEXT_LINE);
					closeStation(nextStations[criteriaStationNumber]);
					break;

				case CLOSE_AND_PRINT_PREVIOUS:

					answer.append(previousStations[criteriaStationNumber]).append(NEXT_LINE);
					closeStation(previousStations[criteriaStationNumber]);
					break;
			}
		}
		System.out.println(answer);
		bufferedReader.close();
	}

	static void openStation(int criteriaStationNumber, int openStationNumber) {
		if (criteriaStationNumber == NOTHING) {
			previousStations[openStationNumber] = nextStations[openStationNumber] = openStationNumber;
			return;
		}
		previousStations[openStationNumber] = criteriaStationNumber;
		nextStations[openStationNumber] = nextStations[criteriaStationNumber];

		previousStations[nextStations[criteriaStationNumber]] = openStationNumber;
		nextStations[criteriaStationNumber] = openStationNumber;
	}

	static void closeStation(int closeStation) {
		previousStations[nextStations[closeStation]] = previousStations[closeStation];
		nextStations[previousStations[closeStation]] = nextStations[closeStation];
	}
}
