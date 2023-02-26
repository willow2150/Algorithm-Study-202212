import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Main {
    static class Problem implements Comparable<Problem> {
        int number;
        int difficulty;

        Problem(int number, int difficulty) {
            this.number = number;
            this.difficulty = difficulty;
        }

        @Override
        public int compareTo(Problem problem) {
            if (this.difficulty == problem.difficulty) {
                if (this.number == problem.number) return 0;
                return this.number < problem.number ? -1 : 1;
            }
            return this.difficulty < problem.difficulty ? -1 : 1;
        }
    }

    public static void main(String[] args) throws Exception {
        final String RECOMMEND = "recommend";
        final String ADD = "add";
        final int MAX_N = 100_000;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder output = new StringBuilder();
        StringTokenizer tokenizer;
        
        TreeSet<Problem> recdProblems = new TreeSet<>();
        Problem[] problems = new Problem[MAX_N + 1];

        int N = Integer.parseInt(reader.readLine());
        for (int i = 0; i < N; i++) {
            tokenizer = new StringTokenizer(reader.readLine());
            Problem problem = new Problem(
                    Integer.parseInt(tokenizer.nextToken()),
                    Integer.parseInt(tokenizer.nextToken())
            );
            problems[problem.number] = problem;
            recdProblems.add(problem);
        }

        int M = Integer.parseInt(reader.readLine());
        for (int i = 0; i < M; i++) {
            tokenizer = new StringTokenizer(reader.readLine());
            String command = tokenizer.nextToken();
            if (command.equals(ADD)) {
                int number = Integer.parseInt(tokenizer.nextToken());
                int difficulty = Integer.parseInt(tokenizer.nextToken());
                Problem problem = problems[number];
                if (problem == null) {
                    problem = problems[number] = new Problem(number, difficulty);
                } else {
                    problem.difficulty = difficulty;
                }
                recdProblems.add(problem);
            } else if (command.equals(RECOMMEND)) {
                int x = Integer.parseInt(tokenizer.nextToken());
                output.append(x == 1 ?
                                recdProblems.last().number : recdProblems.first().number)
                        .append('\n');
            } else {
                int number = Integer.parseInt(tokenizer.nextToken());
                recdProblems.remove(problems[number]);
            }
        }
        System.out.print(output);
    }
}
