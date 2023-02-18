import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Set;
import java.util.TreeSet;


// arr[x] + arr[y] + arr[z] = arr[k] => n^3
// arr[x] + arr[y] = arr[k] - arr[z] => n^2
// arr[x] + arr[y]를 treeset에 저장 [treeset은 이진탐색]
//
public class Main_2295 {
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}

		Set<Integer> treeSet = new TreeSet<>();
		for (int x = 0; x < N; x++) {
			for (int y = 0; y < N; y++) {
				treeSet.add(arr[x] + arr[y]);
			}
		}

		int maxNum = Integer.MIN_VALUE;
		for (int k = 0; k < N; k++) {
			for (int z = 0; z <N; z++) {
				int num = arr[k] - arr[z];
				if(treeSet.contains(num) && maxNum < arr[k])
					maxNum = arr[k];
			}
		}

		System.out.println(maxNum);
	}
}
