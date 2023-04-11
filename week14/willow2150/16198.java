import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(reader.readLine());
		StringTokenizer tokenizer = new StringTokenizer(reader.readLine());

		int[] list = new int[N];
		for (int i = 0; i < N; i++)
			list[i] = Integer.parseInt(tokenizer.nextToken());

		System.out.print(recursion(list, N - 1, N));
	}

	public static int recursion(int[] list, int lastIndex, int size) {
		if (size == 2)
			return 0;
		size--;

		int maxValue = 0;

		for (int index = 1; index < lastIndex; index++) {
			if (list[index] == 0)
				continue;
			int leftIndex = index - 1;
			int rightIndex = index + 1;
			while (0 < leftIndex && list[leftIndex] == 0)
				leftIndex--;
			while (rightIndex < lastIndex && list[rightIndex] == 0)
				rightIndex++;
			int temp = list[index];
			list[index] = 0;
			maxValue = Math.max(
				maxValue,
				list[leftIndex] * list[rightIndex] + recursion(list, lastIndex, size)
			);
			list[index] = temp;
		}
		return maxValue;
	}
}
