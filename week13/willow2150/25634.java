import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        final char ON = '1';
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int maxBrightnessChangeAmount = -5_000;
        int brightnessSum = 0;

        int N = Integer.parseInt(reader.readLine());
        int[] brightnessArray = new int[N];

        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        for (int bulb = 0; bulb < N; bulb++)
            brightnessArray[bulb] = Integer.parseInt(tokenizer.nextToken());

        String onOrOff = reader.readLine();
        int brightnessChangeAmount = 0;
        for (int bulb = 0; bulb < N; bulb++) {
            if (onOrOff.charAt(bulb << 1) == ON) {
                brightnessSum += brightnessArray[bulb];
                brightnessChangeAmount -= brightnessArray[bulb];
            } else {
                brightnessChangeAmount += brightnessArray[bulb];
            }
            maxBrightnessChangeAmount = Math.max(
                    maxBrightnessChangeAmount,
                    brightnessChangeAmount
            );
            if (brightnessChangeAmount < 0) brightnessChangeAmount = 0;
        }
        System.out.print(brightnessSum + maxBrightnessChangeAmount);
    }
}
