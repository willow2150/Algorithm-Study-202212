package pratice;
/*
*
* N이 10^3 이기때문에 O(N^3)이하로 해결해야되는 문제같은데
* 문제를 읽어보니 그리디는 생각이 안나고 logN으로 하려면 이분탐색으로 해야될거같은데
* 완전탐색으로 3개의 숫자를 찾아서 가장 커지는 경우를 보려면
* O(10^3)^3 + O(logN)인거같은데 그럼 무조껀 시간초과인데?
*
* 음 이분탐색 변형인가?
* 이분탐색으로 생각하려니까 너무 복잡한데 그냥 단순히 생각할까?
* a+b+c = k에서 k를 찾으려니 a,b,c 3중 반복을 돌리니까 시간초과가 나기 때문에
* a,b만 구해서 k-c를 구하면 되지않나?
* */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main_2295 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[] data = new int[N];
        List<Integer> list = new ArrayList<>();
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < N; i++) {
            data[i] = Integer.parseInt(br.readLine());
        }

        for (int i = 0; i < N; i++) {
            for (int j = i; j < N; j++) {
                list.add(data[i] + data[j]);

                map.put(data[j] - data[i], data[j]);
            }
        }
//        System.out.println(list.toString());
//        System.out.println(map.toString());
        int res = 0;
        for (int i = 0; i < list.size(); i++) {
            Integer temp = map.get(list.get(i));
            if (temp == null)
                temp = 0;

            res = Math.max(res, temp);

        }


        System.out.println(res);


    }


}
