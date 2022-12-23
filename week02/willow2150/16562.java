import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        int N = Integer.parseInt(tokenizer.nextToken());
        int M = Integer.parseInt(tokenizer.nextToken());
        int k = Integer.parseInt(tokenizer.nextToken());

        int[] costTable = new int[N + 1];
        int[] representativeTable = new int[N + 1];
        tokenizer = new StringTokenizer(reader.readLine());
        for (int student = 1; student <= N; student++) {
            representativeTable[student] = student;
            costTable[student] = Integer.parseInt(tokenizer.nextToken());
        }

        for (int relationship = 0; relationship < M; relationship++) {
            tokenizer = new StringTokenizer(reader.readLine());
            int studentA = Integer.parseInt(tokenizer.nextToken());
            int studentB = Integer.parseInt(tokenizer.nextToken());
            if (findRepresentative(studentA, representativeTable)
                    != findRepresentative(studentB, representativeTable)) {
                electRepresentative(studentA, studentB, representativeTable, costTable);
            }
        }

        int minTotalCost = 0;
        for (int student = 1; student <= N; student++) {
            int representative = findRepresentative(student, representativeTable);
            if (costTable[representative] == 0) continue;
            minTotalCost += costTable[representative];
            costTable[representative] = 0;
        }
        System.out.print(minTotalCost <= k ? minTotalCost : "Oh no");
    }

    public static int findRepresentative(int student, int[] representativeTable) {
        if (representativeTable[student] == student) return student;
        return representativeTable[student]
                = findRepresentative(representativeTable[student], representativeTable);
    }

    public static void electRepresentative(int studentA, int studentB,
                                           int[] representativeTable, int[] costTable) {
        studentA = representativeTable[studentA];
        studentB = representativeTable[studentB];
        if (costTable[studentA] <= costTable[studentB])
            representativeTable[studentB] = studentA;
        else
            representativeTable[studentA] = studentB;
    }
}
