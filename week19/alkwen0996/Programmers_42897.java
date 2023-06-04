import java.util.Arrays;

public class Programmers_42897 {

	// case1: 현재집을 털고 이전집을 털지 않고 전전집을 턴다.
	// 마지막집-1 번째 집이 최대
	// case2: 현재집을 털지 않고 이전집을 턴다.
	// 마지막집이 최대

	public static void main(String[] args) {
		final int[] money = {1, 2, 3, 1};

		System.out.println(solution(money));
	}

	public static int solution(int[] money) {
		final int length = money.length;
		final int[] start_first = new int[length];
		final int[] start_second = new int[length];

		start_first[0] = start_first[1] = money[0];
		// 첫번째집 선택 -> 두번쨰집 < 첫번째집 + 세번째집
		start_second[1] = money[1];
		// 두번째집 선택 -> 두번째집 > 첫번째집 + 세번째집

		for (int i = 2; i < money.length; i++) {
			start_first[i] = Math.max(start_first[i - 1], money[i] + start_first[i - 2]);
			start_second[i] = Math.max(start_second[i - 1], money[i] + start_second[i - 2]);
		}

		return Math.max(start_first[length - 2], start_second[length - 1]);
	}
}
