import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

class Main_2295 {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int N = stoi(br.readLine());

    int[] arr = new int[N];
    for (int i = 0; i < N; i++) {
      arr[i] = stoi(br.readLine());
    }

    Set<Integer> sum = new HashSet<>();
    for (int x = 0; x < N; x++) {
      for (int y = x; y < N; y++) {
        sum.add(arr[x] + arr[y]);
      }
    }

    // x + y == k - z
    Arrays.sort(arr);
    for (int k = N - 1; k >= 0; k--) {
      for (int z = k; z >= 0; z--) {
        int target = arr[k] - arr[z];
        if (sum.contains(target)) {
          System.out.println(arr[k]);
          return;
        }
      }
    }
  }

  private static int stoi(String num) {
    return Integer.parseInt(num);
  }
}

/**
 * 시간 복잡도 HashSet vs Binary Search
 * O(1) by Hashing vs O(logN)
 */

/**
 * x + y + z == k
 * 1_000_000_000 이므로 시간 초과
 * 
 * 따라서, x + y == k - z
 */