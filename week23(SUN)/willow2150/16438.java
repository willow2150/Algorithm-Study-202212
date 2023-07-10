import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    private static final int NUM_OF_DAYS = 7;

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder output = new StringBuilder();
        int N = Integer.parseInt(reader.readLine());
        char[][] monkeyTeamTable = new char[NUM_OF_DAYS][N];

        divideTeams(monkeyTeamTable, 0, N, 0);

        for (char[] monkeyTeams : monkeyTeamTable) {
            for (char monkeyTeam : monkeyTeams) {
                output.append(monkeyTeam);
            }
            output.append('\n');
        }
        System.out.print(output);
    }

    public static void divideTeams(char[][] monkeyTeamTable, int left, int right, int day) {
        if (NUM_OF_DAYS == day || left >= right) {
            return;
        }
        final char TEAM_A = 'A';
        final char TEAM_B = 'B';
        int mid = (left + right) >> 1;

        if (left + 1 == right) {
            monkeyTeamTable[day][left] = monkeyTeamTable[day - 1][left];
        } else {
            Arrays.fill(monkeyTeamTable[day], left, mid, TEAM_A);
            Arrays.fill(monkeyTeamTable[day], mid, right, TEAM_B);
        }

        day++;
        divideTeams(monkeyTeamTable, left, mid, day);
        divideTeams(monkeyTeamTable, mid, right, day);
    }
}
