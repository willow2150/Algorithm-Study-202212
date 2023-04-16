import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class Main_21939 {

	// 우선순위 큐 시간복잡도 삽입, 삭제 : O(logN)
	// 최대힙, 최소힙 활용 위해서 우선순위 큐 2개사용
	// recommend 조건 때문에 비교 조건 2개 걸어줌.
	// 우선순위 큐는 중간 값 삭제가 어려움;;;
	// Set에 푼 문제를 저장해둔다. -> recommend 시 풀었던 문제 확인해서 queue에서 삭제해준다.
	// 풀지 않은 문제를 찾으면 출력한다.

	public static final String RECOMMEND = "recommend";
	public static final String ADD = "add";
	public static final String NEW_LINE = "\n";

	public static void main(String[] args) throws IOException {
		final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		final int problemsCount = Integer.parseInt(bufferedReader.readLine());

		StringTokenizer stringTokenizer;
		final Queue<Problem> problems = new PriorityQueue<>((problem1, problem2) -> {
			if (problem1.difficulty == problem2.difficulty) {
				return problem2.problemNumber - problem1.problemNumber;
			}

			return problem2.difficulty - problem1.difficulty;
		}); // 오름차순 (큰거 -> 작은거)

		final Queue<Problem> reverseProblems = new PriorityQueue<>((problem1, problem2) -> {
			if (problem1.difficulty == problem2.difficulty) {
				return problem1.problemNumber - problem2.problemNumber;
			}

			return problem1.difficulty - problem2.difficulty;
		});

		for (int i = 0; i < problemsCount; i++) {
			stringTokenizer = new StringTokenizer(bufferedReader.readLine());

			final int problemNumber = Integer.parseInt(stringTokenizer.nextToken());
			final int difficulty = Integer.parseInt(stringTokenizer.nextToken());

			problems.add(new Problem(problemNumber, difficulty));
			reverseProblems.add(new Problem(problemNumber, difficulty));
		}

		final List<Recommendation> recommendProblems = new ArrayList<>();
		final int recommendProblemsCount = Integer.parseInt(bufferedReader.readLine());

		for (int i = 0; i < recommendProblemsCount; i++) {
			stringTokenizer = new StringTokenizer(bufferedReader.readLine());

			String order = stringTokenizer.nextToken();
			int problemNumber = 0;
			int difficulty = 0;
			int difficultyRank = 0;

			if (order.equals(RECOMMEND)) {
				difficultyRank = Integer.parseInt(stringTokenizer.nextToken());
			} else if (order.equals(ADD)) {
				problemNumber = Integer.parseInt(stringTokenizer.nextToken());
				difficulty = Integer.parseInt(stringTokenizer.nextToken());
			} else {
				problemNumber = Integer.parseInt(stringTokenizer.nextToken());
			}

			recommendProblems.add(new Recommendation(new Problem(problemNumber, difficulty), order, difficultyRank));
		}

		recommendProblems(problems, reverseProblems, recommendProblems);
	}

	private static void recommendProblems(final Queue<Problem> problems, final Queue<Problem> reverseProblems,
		final List<Recommendation> recommendProblems) {
		final StringBuilder stringBuilder = new StringBuilder();
		final Set<Integer> solvedProblems = new HashSet<>();

		for (final Recommendation recommendation : recommendProblems) {
			if (recommendation.order.equals(RECOMMEND)) {
				int recommendProblemNumber = 0;

				if (!problems.isEmpty() && !reverseProblems.isEmpty()) {
					if (recommendation.difficultyRank == 1) {
						recommendProblemNumber = isSolved(problems, solvedProblems);
					} else if (recommendation.difficultyRank == -1) {
						recommendProblemNumber = isSolved(reverseProblems, solvedProblems);
					}

					stringBuilder.append(recommendProblemNumber).append(NEW_LINE);
				}
			} else if (recommendation.order.equals(ADD)) {
				problems.add(new Problem(recommendation.problem.problemNumber, recommendation.problem.difficulty));
				reverseProblems.add(
					new Problem(recommendation.problem.problemNumber, recommendation.problem.difficulty));
			} else {
				solvedProblems.add(recommendation.problem.problemNumber);
			}
		}

		System.out.println(stringBuilder);
	}

	private static int isSolved(final Queue<Problem> problems, final Set<Integer> solvedProblems) {
		if (!problems.isEmpty() && solvedProblems.contains(problems.peek().problemNumber)) {
			problems.poll();
		}

		return Objects.requireNonNull(problems.peek()).problemNumber;
	}

	static class Recommendation {
		private final String order;
		private final int difficultyRank;
		private final Problem problem;

		public Recommendation(final Problem problem, final String order, final int difficultyRank) {
			this.problem = problem;
			this.order = order;
			this.difficultyRank = difficultyRank;
		}

		@Override
		public String toString() {
			return "Recommendation{" +
				"order='" + order + '\'' +
				", difficultyRank=" + difficultyRank +
				", problem=" + problem +
				'}';
		}

	}

	static class Problem {
		private final int problemNumber;
		private final int difficulty;

		public Problem(final int problemNumber, final int difficulty) {
			this.problemNumber = problemNumber;
			this.difficulty = difficulty;
		}

		@Override
		public String toString() {
			return "Problem{" +
				"problemNumber=" + problemNumber +
				", difficulty=" + difficulty +
				'}';
		}

	}

}