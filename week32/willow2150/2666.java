import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        final int INF = Integer.MAX_VALUE;

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer;
        int minNumOfClosetMoves = INF;

        int numOfClosets = Integer.parseInt(reader.readLine());
        tokenizer = new StringTokenizer(reader.readLine());
        int openedClosetA = Integer.parseInt(tokenizer.nextToken());
        int openedClosetB = Integer.parseInt(tokenizer.nextToken());
        int numOfClosetsToUse = Integer.parseInt(reader.readLine());

        int[][][] dp = new int[numOfClosetsToUse + 1][numOfClosets + 1][numOfClosets + 1];
        int[] closetsToUse = new int[numOfClosetsToUse + 1];

        for (int order = 0; order <= numOfClosetsToUse; order++) {
            for (int left = 1; left <= numOfClosets; left++) {
                for (int right = left + 1; right <= numOfClosets; right++) {
                    dp[order][left][right] = INF;
                }
            }
        }

        for (int order = 1; order <= numOfClosetsToUse; order++) {
            closetsToUse[order] = Integer.parseInt(reader.readLine());
        }

        dp[0][openedClosetA][openedClosetB] = 0;

        for (int order = 1; order <= numOfClosetsToUse; order++) {
            int prevOrder = order - 1;
            int closet = closetsToUse[order];
            for (int left = 1; left <= numOfClosets; left++) {
                for (int right = left + 1; right <= numOfClosets; right++) {
                    int prevNumOfClosetMoves = dp[prevOrder][left][right];
                    if (prevNumOfClosetMoves == INF) {
                        continue;
                    }
                    if (closet <= left) {
                        dp[order][closet][right] = Math.min(
                                dp[order][closet][right],
                                prevNumOfClosetMoves + left - closet
                        );
                    } else if (right <= closet) {
                        dp[order][left][closet] = Math.min(
                                dp[order][left][closet],
                                prevNumOfClosetMoves + closet - right
                        );
                    } else {
                        dp[order][closet][right] = Math.min(
                                dp[order][closet][right],
                                prevNumOfClosetMoves + closet - left
                        );
                        dp[order][left][closet] = Math.min(
                                dp[order][left][closet],
                                prevNumOfClosetMoves + right - closet
                        );
                    }
                }
            }
        }

        for (int left = 1; left <= numOfClosets; left++) {
            for (int right = left + 1; right <= numOfClosets; right++) {
                minNumOfClosetMoves = Math.min(
                        minNumOfClosetMoves,
                        dp[numOfClosetsToUse][left][right]
                );
            }
        }

        System.out.print(minNumOfClosetMoves);
    }
}
