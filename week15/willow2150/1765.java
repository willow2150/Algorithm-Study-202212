import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		final char FRIEND = 'F';
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokenizer;
		int n = Integer.parseInt(reader.readLine());
		int m = Integer.parseInt(reader.readLine());
		int[] friend = new int[n + 1];
		int[] enemy = new int[n + 1];
		int maxTeamCnt = 0;

		for (int student = 1; student <= n; student++)
			friend[student] = student;

		for (int i = 0; i < m; i++) {
			tokenizer = new StringTokenizer(reader.readLine());
			boolean isFriend = tokenizer.nextToken().charAt(0) == FRIEND;
			int studentA = Integer.parseInt(tokenizer.nextToken());
			int studentB = Integer.parseInt(tokenizer.nextToken());
			if (isFriend) {
				unionFriend(
					friend,
					findFriend(friend, studentA),
					findFriend(friend, studentB)
				);
			} else {
				int enemyStudentA = enemy[studentA];
				int enemyStudentB = enemy[studentB];
				if (enemyStudentA != 0) {
					unionFriend(
						friend,
						findFriend(friend, enemyStudentA),
						findFriend(friend, studentB)
					);
				}
				if (enemyStudentB != 0) {
					unionFriend(
						friend,
						findFriend(friend, studentA),
						findFriend(friend, enemyStudentB)
					);
				}
				enemy[studentA] = studentB;
				enemy[studentB] = studentA;
			}
		}

		for (int student = 1; student <= n; student++) {
			if (findFriend(friend, student) == student) {
				maxTeamCnt++;
			}
		}

		System.out.print(maxTeamCnt);
	}

	public static int findFriend(int[] friend, int student) {
		if (friend[student] == student)
			return student;
		return friend[student]
			= findFriend(friend, friend[student]);
	}

	public static void unionFriend(int[] friend, int studentA, int studentB) {
		studentA = friend[studentA];
		studentB = friend[studentB];
		if (studentA < studentB) {
			friend[studentB] = studentA;
		} else {
			friend[studentA] = studentB;
		}
	}
}
