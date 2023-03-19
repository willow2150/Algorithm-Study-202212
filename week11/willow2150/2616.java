import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        final int LOCOMOTIVE_CNT = 3;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int trainCarCnt = Integer.parseInt(reader.readLine());
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        int[] passengerCntArr = new int[trainCarCnt + 1];
        int transitCnt = Integer.parseInt(reader.readLine());

        for (int trainCar = 1; trainCar <= trainCarCnt; trainCar++)
            passengerCntArr[trainCar] = Integer.parseInt(tokenizer.nextToken());

        int[][] dp = new int[LOCOMOTIVE_CNT + 1][trainCarCnt + 1];
        int[] prefixSum = getPrefixSum(passengerCntArr, trainCarCnt, transitCnt);

        for (int locomotive = 1; locomotive <= LOCOMOTIVE_CNT; locomotive++) {
            int trainCar = transitCnt * locomotive;
            for (; trainCar <= trainCarCnt; trainCar++) {
                dp[locomotive][trainCar] = Math.max(
                        dp[locomotive - 1][trainCar - transitCnt] + prefixSum[trainCar],
                        dp[locomotive][trainCar - 1]
                );
            }
        }
        System.out.print(dp[LOCOMOTIVE_CNT][trainCarCnt]);
    }

    public static int[] getPrefixSum(int[] passengerCntArr,
                                     int trainCarCnt, int movableTrainCarCnt) {
        int[] prefixSum = new int[trainCarCnt + 1];
        int trainCar = 1;

        prefixSum[trainCar++] = passengerCntArr[1];
        for (; trainCar <= movableTrainCarCnt; trainCar++) {
            prefixSum[trainCar] =
                    prefixSum[trainCar - 1]
                            + passengerCntArr[trainCar];
        }

        for (; trainCar <= trainCarCnt; trainCar++) {
            prefixSum[trainCar] =
                    prefixSum[trainCar - 1]
                            + passengerCntArr[trainCar]
                            - passengerCntArr[trainCar - movableTrainCarCnt];
        }
        return prefixSum;
    }
}
