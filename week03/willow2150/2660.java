import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        final int MAX_NUM_OF_MEMBERS = 50;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder output = new StringBuilder();
        int numOfMembers = Integer.parseInt(reader.readLine());
        int[][] relationship = new int[numOfMembers + 1][numOfMembers + 1];
        int[] scores = new int[numOfMembers + 1];
        int minScore = MAX_NUM_OF_MEMBERS, numOfCandidates = 0;
        int memberA, memberB;

        for (memberA = 1; memberA <= numOfMembers; memberA++) {
            relationship[memberA][memberA] = 0;
            for (memberB = memberA + 1; memberB <= numOfMembers; memberB++) {
                relationship[memberA][memberB]
                        = relationship[memberB][memberA]
                        = MAX_NUM_OF_MEMBERS;
            }
        }

        while (true) {
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            memberA = Integer.parseInt(tokenizer.nextToken());
            memberB = Integer.parseInt(tokenizer.nextToken());
            if (memberA == -1 && memberB == -1) break;
            relationship[memberA][memberB] = relationship[memberB][memberA] = 1;
        }

        for (int memberC = 1; memberC <= numOfMembers; memberC++) {
            for (memberA = 1; memberA <= numOfMembers; memberA++) {
                for (memberB = 1; memberB <= numOfMembers; memberB++) {
                    relationship[memberA][memberB] = Math.min(
                            relationship[memberA][memberB],
                            relationship[memberA][memberC] + relationship[memberC][memberB]
                    );
                }
            }
        }

        for (memberA = 1; memberA <= numOfMembers; memberA++) {
            for (memberB = 1; memberB <= numOfMembers; memberB++) {
                if (relationship[memberA][memberB] != MAX_NUM_OF_MEMBERS
                        && scores[memberA] < relationship[memberA][memberB]) {
                    scores[memberA] = relationship[memberA][memberB];
                }
            }
            if (scores[memberA] < minScore) {
                minScore = scores[memberA];
                numOfCandidates = 1;
            } else if (minScore == scores[memberA]) {
                numOfCandidates++;
            }
        }

        output.append(minScore).append(' ').append(numOfCandidates).append('\n');
        for (memberA = 1; memberA <= numOfMembers; memberA++)
            if (scores[memberA] == minScore)
                output.append(memberA).append(' ');
        System.out.print(output);
    }
}
