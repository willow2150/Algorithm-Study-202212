import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Baekjoon_23326 {

    public static final String NEW_LINE = "\n";

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());

        int n = Integer.parseInt(stringTokenizer.nextToken());
        int q = Integer.parseInt(stringTokenizer.nextToken());

        Map<Integer, Integer> tourRoute = new HashMap<>();
        int count = 0;
        stringTokenizer = new StringTokenizer(bufferedReader.readLine());

        for (int i = 0; i < n; i++) {
            int area = Integer.parseInt(stringTokenizer.nextToken());

            if (area == 1) {
                tourRoute.put(i, 1);
                count++;
                continue;
            }

            tourRoute.put(i, 0);
        }


        int orderNumber, areaNumber;
        int current = 0;
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < q; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            orderNumber = Integer.parseInt(stringTokenizer.nextToken());

            if (orderNumber == 1) { // 명소면 명소풀리고 명소아니면 명소제거
                areaNumber = Integer.parseInt(stringTokenizer.nextToken()) - 1;

                if (tourRoute.get(areaNumber) == 1) {
                    tourRoute.put(areaNumber, 0);
                    count--;
                } else {
                    tourRoute.put(areaNumber, 1);
                    count++;
                }
            } else if (orderNumber == 2) {
                areaNumber = Integer.parseInt(stringTokenizer.nextToken());
                current = current + areaNumber;
                current %= n;
            } else {
                if (count == 0) {
                    stringBuilder.append(-1).append(NEW_LINE);
                } else {
                    int distance = measureDistance(current, tourRoute);
                    stringBuilder.append(distance).append(NEW_LINE);
                }
            }
        }

        System.out.println(stringBuilder);
    }

    private static int measureDistance(final int current, final Map<Integer, Integer> tourRoute) {
        int nearSpot = 0;

        for (int key : tourRoute.keySet()) {
            if (tourRoute.get(key) == 1) {
                nearSpot = key;
                break;
            }
        }

        return nearSpot - current;
    }

}
