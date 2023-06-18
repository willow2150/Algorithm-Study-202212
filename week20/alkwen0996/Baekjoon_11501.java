import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baekjoon_11501 {
    public static final String NEW_LINE = "\n";
    // 하루에 1. 주식을 사거나 2. 원하는만큼 주식을 팔거나 3. 아무것도 안하거나 선택가능.
    // 앞에서부터 계산하려니까 너무 복잡
    // 뒤에서부터 계산하면... 되겠네...
    // 최대값 잡아서

    public static void main(String[] args) throws IOException {
        final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        final int testCaseCount = Integer.parseInt(bufferedReader.readLine());

        StringTokenizer stringTokenizer;
        int[] stocks;

        final StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < testCaseCount; i++) {
            final int days = Integer.parseInt(bufferedReader.readLine());
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            stocks = new int[days];

            int price;
            for (int j = 0; j < days; j++) {
                stocks[j] = Integer.parseInt(stringTokenizer.nextToken());
            }

            int maxPrice = 0;
            long revenue = 0;

            for (int j = stocks.length - 1; j >= 0; j--) {
                price = stocks[j];

                if (maxPrice < price) {
                    maxPrice = price;
                    continue;
                }

                revenue += maxPrice - price;
            }

            stringBuilder.append(revenue).append(NEW_LINE);
        }

        System.out.println(stringBuilder);
    }
}
