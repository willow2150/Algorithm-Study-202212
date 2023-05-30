import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Baekjoon_1107 {
	// 0에서 -를 누르면 채널은 변하지 않음.
	// 제일 베스트는 이동해야 하는 버튼을 눌러서 이동.
	// 만약, 고장난 버튼이 있다면 제일 가까운 채널로 이동해야함.

	// 목표채널 target 까지 0~999999 사이까지 모두 탐색해서 만약 고장난 버튼이 없이 갈 수 있다면
	// 그 채널에서 target 차이와 자릿수를 합한 결과를 반환하면 된다

	public static void main(String[] args) throws IOException {
		final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		final int targetChannelNumber = Integer.parseInt(bufferedReader.readLine());
		final int brokenButtonCount = Integer.parseInt(bufferedReader.readLine());
		final boolean[] brokenButtonNumbers = new boolean[10];

		StringTokenizer stringTokenizer;

		if (brokenButtonCount > 0) {
			stringTokenizer = new StringTokenizer(bufferedReader.readLine());

			for (int i = 0; i < brokenButtonCount; i++) {
				brokenButtonNumbers[Integer.parseInt(stringTokenizer.nextToken())] = true;
			}
		}

		System.out.println(countPushButton(targetChannelNumber, brokenButtonNumbers));
	}

	private static int countPushButton(final int targetChannelNumber, final boolean[] brokenButtonNumbers) {
		int minimalPushButtonCount = Math.abs(targetChannelNumber - 100);
		// +또는 - 버튼으로만 이동할 경우를 초기값으로 잡는다.

		boolean isBrokenNumber;
		for (int i = 0; i < 1000_000; i++) {
			final String currentChannelNumber = String.valueOf(i);
			final int currentChannelNumberLength = currentChannelNumber.length();

			isBrokenNumber = false;
			for (int j = 0; j < currentChannelNumberLength; j++) {
				final char numberOfDigit = currentChannelNumber.charAt(j);

				if (brokenButtonNumbers[numberOfDigit - '0']) {
					isBrokenNumber = true;
					break;
				}
			}

			if (!isBrokenNumber) {
				int pushButtonCount = Math.abs(targetChannelNumber - i) + currentChannelNumberLength;
				minimalPushButtonCount = Math.min(pushButtonCount, minimalPushButtonCount);
			}
		}

		return minimalPushButtonCount;
	}

}
