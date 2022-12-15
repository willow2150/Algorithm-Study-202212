package pratice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/*
* A : 매일 출근 가능
* B : 출근한 다음날 쉬어야함
* C : 출근한 다음날, 다다음날 쉬어야함
*
* 처음 입력은 이상하게 BBA이렇게 나와도 되는데 이걸 다시 재조합해서 ABA처럼 올바른 출근 순서로 바꾸라는 것
*
* 1. 그리디
* 그러면 결국 B사이에 A나 C가 있어야하고 C사이에는 A,B가 있어야하니까 개수를 파악해야 되겠네
* A는 많이 있어도 상관없음
* B <= (A+C) +1 관계가 아니면 -1
* C <= (A+B) +2 관계가 아니면 -1
* 정답을 찾을 떄 B나 C부터 배열을 채워넣고 빈자리를 나머지 애들로 채우는 방식으로 접근했는데 뭔가 예외가 너무 많을 것 같음
* ex)B를 1칸 간격으로 먼저 배치하거나 C를 2칸 간격으로 먼저 배치하는데 꼭 이렇게 안될수도 있음, 그럼 각 자리에 나올 수 있는 A,B,C에 대해서 dfs 돌리기? 3^50인데 어림도 없음
*
* 2. dfs+memoization
* 각 자리에 배치할 때 필요한 정보는 이전값들의 정보가 필요한데 자리에 도착했을 때 A,B,C,이전값,전전값 총 5개를 모두 고려하는 완전탐색을 해야된다
* 근데 무조껀 시간초과가 나서 백트래킹을 해주는 애가 필요할거 같은데 visited[A][B][C][back1][back2]로 5차원배열을 만들어서 메모이제이션 해주면 되긴하는데 이게 맞나
*
* 이걸 맞추네
*
* 근데 이거 한번에 dfs+memoization이라고 생각하기엔 너무 힘든거 같음
*
*
* */

public class Main_14238 {
    static int[] cnt;
    static char[] res;
    static boolean visited[][][][][];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        cnt = new int[3];

        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == 'A')
                cnt[0]++;
            else if (input.charAt(i) == 'B')
                cnt[1]++;
            else if (input.charAt(i) == 'C')
                cnt[2]++;
        }
        int length = cnt[0] + cnt[1] + cnt[2];

        res = new char[length];
        visited = new boolean[51][51][51][3][3];

        if (dfs(0,0,0,'A','A')){
            for (int i = 0; i < res.length; i++) {
                System.out.print(res[i]);
            }
        }
        else
            System.out.println(-1);

    }

    private static boolean dfs(int a, int b, int c, char back1, char back2){
        if (visited[a][b][c][(back1-'A')][(back2-'A')]) //완전탐색을 돌리면서 한번 본 경우는 다시 안보기
            return false;

        if (a == cnt[0] && b == cnt[1] && c == cnt[2]){
            return true;
        }
//        System.out.println(a+" "+b+" "+c+" "+(back1-'A')+" "+(back2-'A'));

        visited[a][b][c][(back1-'A')][(back2-'A')] = true;

        if (cnt[0] >= a+1){
            res[a + b + c] = 'A';
            if (dfs(a+1,b,c,'A',back1))
                return true;
        }
        if (cnt[1] >= b+1){
            res[a + b + c] = 'B';
            if (back1 != 'B'){
                if (dfs(a,b+1,c,'B',back1))
                    return true;
            }

        }
        if (cnt[2] >= c+1){
            res[a + b + c] = 'C';
            if (back1 != 'C' && back2 != 'C'){
                if (dfs(a,b,c+1,'C',back1))
                    return true;
            }
        }
        return false;
    }
}
