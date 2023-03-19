//철도 공사
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static final int MAXSIZE = 1_000_001;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		int[] prev = new int[MAXSIZE];
		int[] next = new int[MAXSIZE];

		st = new StringTokenizer(br.readLine());
		int firstNum = Integer.parseInt(st.nextToken());
		int prevNum = firstNum;
		for (int n = 1; n < N; n++) {
			int nowNum = Integer.parseInt(st.nextToken());
			next[prevNum] = nowNum;
			prev[nowNum] = prevNum;
			prevNum = nowNum;
		}
		next[prevNum] = firstNum;
		prev[firstNum] = prevNum;

		StringBuilder sb = new StringBuilder();
		for (int m = 0; m < M; m++) {
			st = new StringTokenizer(br.readLine());
			String s = st.nextToken();
			if(s.equals("BN")){
				int i = Integer.parseInt(st.nextToken());
				int j = Integer.parseInt(st.nextToken());
				int k = next[i];
				sb.append(next[i]).append("\n");
				prev[j] = i;
				next[j] = k;
				next[i] = prev[k] = j;
			}else if(s.equals("BP")){
				int i = Integer.parseInt(st.nextToken());
				int j = Integer.parseInt(st.nextToken());
				int k = prev[i];
				sb.append(prev[i]).append("\n");
				prev[j] = k;
				next[j] = i;
				prev[i] = next[k] = j;
			}else if(s.equals("CN")){
				int i = Integer.parseInt(st.nextToken());
				int d = next[i];
				int j = next[d];
				sb.append(d).append("\n");
				next[i] = j;
				prev[j] = i;
				next[d] = prev[d] = 0;
			}else{
				int i = Integer.parseInt(st.nextToken());
				int d = prev[i];
				int j = prev[d];
				sb.append(d).append("\n");
				prev[i] = j;
				next[j] = i;
				next[d] = prev[d] = 0;
			}
		}
		System.out.print(sb);
	}
}
