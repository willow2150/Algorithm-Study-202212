import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(reader.readLine());
        long[] setU = new long[N];
        for (int index = 0; index < N; index++)
            setU[index] = Long.parseLong(reader.readLine());
        System.out.print(findMaxSum(setU, N));
    }

    public static long findMaxSum(long[] setU, int N) {
        Set<Long> setUPlusU = new HashSet<>();

        Arrays.sort(setU);
        for (int i = N - 1; i >= 0; i--)
            for (int j = i; j >= 0; j--)
                setUPlusU.add(setU[i] + setU[j]);
            
        for (int i = N - 1; i >= 0; i--)
            for (int j = i - 1; j >= 0; j--)
                if (setUPlusU.contains(setU[i] - setU[j]))
                    return setU[i];
        return 0L;
    }
}
