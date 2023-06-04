import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
* 일단 A ~ B까지 일일이 탐색하면서 2로 일일이 나눈다는 생각은 미친짓임 => 규칙이 있거나 각 숫자 N에 대한 S(N)을 합하면 뭐가 나올지도?
* => S(A) + S(A+1) + ... + S(B-1) + S(B) 이렇게해서 뭐 해볼려고 했는데 애초에 A to B 논하는게 말이안되네
* 그럼 S(B) - S(A-1)을 하는게 맞겠네.
*
* 홀수는 일단 무조껀 == 1
* 짝수는 타고타고 가야됨 -> 재귀?
*
* N :    2 4 6 8 10 12 14 16 18 20
* f(N) : 2 4 2 8 2  4  2  16 2  4
*
* 이거 보니까 f(2N) = 2*f(N)인데? -> 와 이거 하나 찾는게 왜케 힘들었지?
*
* 그러면, S(18) - S(14) 구하는거라고 치면
* S(18) = f(1) + f(2) + ... + f(17) + f(18)
*       = f(2) + f(4) + ... + f(16) + f(18) + 18/2
*       => 짝수들의 합은 어케 구하지? f(2N) = 2*f(N) 이건 아는데.
*       => 걍 재귀돌리면 되겠네.
* */

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        long A = Long.parseLong(st.nextToken());
        long B = Long.parseLong(st.nextToken());

        System.out.println(recursive(B) - recursive(A-1));
    }

    private static long recursive(long N) {
//        System.out.println(N);

        if (N == 0)
            return 0;
        else if (N == 1)
            return 1;
        else if (N % 2 == 0) {
            return N / 2 + 2 * recursive(N / 2);
        }
        else{
            return N / 2 + 2 * recursive(N / 2) + 1;
        }
    }
}
