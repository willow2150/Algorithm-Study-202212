import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        final int NUM_OF_PEOPLE_PER_LINE = 5;
        final int MAX_CODE_NUMBER = 1_000;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());

        int[][] map = new int[n][NUM_OF_PEOPLE_PER_LINE];
        int[] sortedTicketList = new int[n * NUM_OF_PEOPLE_PER_LINE];
        int order = 0;
        for (int row = 0; row < n; row++) {
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            for (int col = 0; col < NUM_OF_PEOPLE_PER_LINE; col++) {
                String ticket = tokenizer.nextToken();
                int number = (ticket.charAt(0) - 'A') * MAX_CODE_NUMBER
                        + Integer.parseInt(ticket.substring(2));
                map[row][col] = sortedTicketList[order++] = number;
            }
        }
        Arrays.sort(sortedTicketList);
        System.out.print(isAbleToEnter(map, sortedTicketList) ? "GOOD" : "BAD");
    }

    public static boolean isAbleToEnter(int[][] map, int[] sortedTicketList) {
        int[] stack = new int[sortedTicketList.length];
        int top = -1;
        int order = 0;

        for (int[] line : map) {
            for (int ticketNumber : line) {
                while (top >= 0 && stack[top] == sortedTicketList[order]) {
                    top--;
                    order++;
                }
                if (ticketNumber == sortedTicketList[order]) order++;
                else if (top >= 0 && stack[top] < ticketNumber) return false;
                else stack[++top] = ticketNumber;
            }
        }
        return true;
    }
}
