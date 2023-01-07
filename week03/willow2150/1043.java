import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        tokenizer.nextToken();
        int M = Integer.parseInt(tokenizer.nextToken());
        long[] partyGoerList = new long[M];
        long peopleWhoKnowTruth = 0L;
        int answer = 0;

        tokenizer = new StringTokenizer(reader.readLine());
        int numOfPeopleWhoKnowTruth = Integer.parseInt(tokenizer.nextToken());
        for (int person = 0; person < numOfPeopleWhoKnowTruth; person++)
            peopleWhoKnowTruth |= (1L << Integer.parseInt(tokenizer.nextToken()));

        for (int party = 0; party < M; party++) {
            tokenizer = new StringTokenizer(reader.readLine());
            int numOfPartGoer = Integer.parseInt(tokenizer.nextToken());
            for (int person = 0; person < numOfPartGoer; person++)
                partyGoerList[party] |= (1L << Integer.parseInt(tokenizer.nextToken()));
        }

        for (int party = 0; party < M; party++) {
            for (long partyGoer : partyGoerList) {
                if ((peopleWhoKnowTruth & partyGoer) != 0)
                    peopleWhoKnowTruth |= partyGoer;
            }
        }
        for (long partyGoer : partyGoerList)
            if ((peopleWhoKnowTruth & partyGoer) == 0)
                answer++;
        System.out.print(answer);
    }
}
