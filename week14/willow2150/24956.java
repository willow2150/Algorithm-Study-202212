import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws Exception {
		final long MOD = 1_000_000_007;
		final char W = 'W';
		final char H = 'H';
		final char E = 'E';

		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(reader.readLine());
		String S = reader.readLine();
		long wCnt = 0;
		long hCnt = 0;
		long eCnt = 0;
		long whistleCnt = 0;

		for (int index = 0; index < N; index++) {
			char character = S.charAt(index);
			if (character == W) {
				wCnt++;
			} else if (character == H) {
				hCnt += wCnt;
			} else if (character == E) {
				whistleCnt = (whistleCnt << 1) + eCnt;
				whistleCnt %= MOD;
				eCnt += hCnt;
			}
		}
		System.out.print(whistleCnt);
	}
}
