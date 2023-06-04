import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
		long A = Long.parseLong(tokenizer.nextToken());
		long B = Long.parseLong(tokenizer.nextToken());
		System.out.print(calcCumulativeFunctionSum(B) - calcCumulativeFunctionSum(A - 1));
	}

	public static long calcCumulativeFunctionSum(long number) {
		if (number <= 1L) {
			return number;
		}
		long halfNumber = number >> 1;
		return (number & 1) == 1 ?
				(calcCumulativeFunctionSum(halfNumber) << 1) + halfNumber + 1 :
				(calcCumulativeFunctionSum(halfNumber) << 1) + halfNumber;
	}
}
