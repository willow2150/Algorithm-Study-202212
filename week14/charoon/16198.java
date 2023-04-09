import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static int N, max = Integer.MIN_VALUE;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());

		//입력
		List<Integer> list = new ArrayList<>();
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int n=0; n<N; n++)
			list.add(Integer.parseInt(st.nextToken()));

		//dfs
		dfs(list,0);
		System.out.print(max);
	}

	private static void dfs(List<Integer> list, int sum) {
		if(list.size()==2) {
			if(max < sum)
				max = sum;
			return;
		}
		for(int i=1; i<list.size()-1; i++) {
			int temp = list.get(i);
			int num = list.get(i-1) * list.get(i+1);
			list.remove(i);
			dfs(list, sum + num);
			list.add(i, temp);
		}
	}
}
