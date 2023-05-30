import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/*
* 0 ~ 9, +, -
* 채널 0에서 - 누르면 변하지 않음
* 시작은 100
*
* 그냥 모든 경우를 다 보면 될듯?
* 1. +,-해서 이동하는 경우
* 2. 숫자만 입력해서 이동하는경우
* 3. 숫자 입력 -> +,- 이동하는경우(숫자입력해서 최대한 targetNum랑 가까운 숫자를 찾고 +, - 횟수만 하면 되겠지?)
*
* 2,3번은 if-else로 두면 될듯.
* why? 1번 경우를 제외한 케이스들 중에선 숫자만 입력하는 경우가 제일 빠르기 때문에
*
*
* Ex)
* 5 4 5 7 -> 5 4 5 9가 되어야하는데
* 저 9를 도출하는 과정을 어떻게 해야되지?
* 7이 고장나서 가장 가까운 숫자로 이동해야되는데(+,- 를 이용해야되서) -> 이 가까운 숫자를 찾는 로직이 쉽지않음 -> 원래 가까운 숫자 찾는거 이분탐색으로 하지 않나?
* 7을 기준으로 +,-쪽으로 한칸씩 늘려나가면서 그게 고장났는지 아닌지 판단하면서 제일 먼저 찾은 버튼이 정답이겠네
*
* 근데 다른 자릿 수까지 영향을 준다면?
* 5 4 9 9 -> 5 4 8 8로 찾을텐데 사실 5 5 0 0에서 - 하는게 맞잖아.
*
* "가까운 숫자"를 찾는다는게 너무 힘드니까 다른 방식으로 해야될듯?
* "가까운 숫자"를 조건에 맞쳐서 찾는게 힘드니까 이걸 for로 하나씩 찾으면 되지 않을까?
* 0, 1000000 사이에서 나올 수 있겠네
*
* */
public class Main {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        List<Integer> broken_btn = new ArrayList<>();
        if (M != 0){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < M; i++) {
                broken_btn.add(Integer.parseInt(st.nextToken()));
            }
        }

        int temp = 0;
        int res = Math.abs(100-N);
        int maxValue = 2000000;

        for(int i = 0; i<=999999; i++) {
            String str_num = String.valueOf(i);
            boolean flag = true;
            for(int j = 0; j<str_num.length(); j++) {
                if(broken_btn.contains(str_num.charAt(j)-'0')) {
                    flag = false;
                    break;
                }
            }
            if(!flag)
                continue;

            temp = str_num.length() + Math.abs(i-N);
            if(temp < maxValue) {
                maxValue = temp;
            }
        }

        System.out.println(Math.min(res, maxValue));
    }
}