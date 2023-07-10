import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		int L = Integer.parseInt(reader.readLine());
		StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
		int range = Integer.parseInt(tokenizer.nextToken());
		int power = Integer.parseInt(tokenizer.nextToken());
		int C = Integer.parseInt(reader.readLine());

		boolean[] isBombsUsed = new boolean[L + 1];
		int numOfBombsInRange = 0;

		for (int distance = 1; distance <= L; distance++) {
			int zombieLife = Integer.parseInt(reader.readLine());
			if (range < distance && isBombsUsed[distance - range]) {
				numOfBombsInRange--;
			}
			if (power * (Math.min(distance, range) - numOfBombsInRange) < zombieLife) {
				isBombsUsed[distance] = true;
				C--;
				numOfBombsInRange++;
			}
		}

		System.out.print(0 <= C ? "YES" : "NO");
	}
}
