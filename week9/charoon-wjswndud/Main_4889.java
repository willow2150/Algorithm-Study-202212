import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main_4889 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int cntLoop = 0;
		while (true) {
			cntLoop++;
			String line = br.readLine();

			//종료
			if(line.charAt(0) == '-') break;

			int cnt = 0;
			Stack<Character> stack = new Stack<>();
			for (int idx = 0; idx < line.length(); idx++) {
				if (line.charAt(idx) == '{') stack.push('{');
				else {	//}
					if(stack.isEmpty()) {
						stack.push('{');
						cnt++;
					} else  {
						stack.pop();
					}
				}
			}
			cnt += stack.size()/2;


			sb.append(cntLoop).append(". ").append(cnt).append("\n");
		}
		sb.deleteCharAt(sb.length()-1);
		System.out.print(sb);
	}
}
