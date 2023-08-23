import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class 보석쇼핑 {
    // 배열의 크기가 변함.
    // O(n^2) 시간복잡도면 터짐. -> O(n)으로 해결해야함.
    // 투포인터 알고리즘
    public static void main(String[] args) {
        String[] gems = {"AA", "AB", "AC", "AA", "AC"};
//        String[] gems = {"XYZ", "XYZ", "XYZ"};
//        String[] gems = {"ZZZ", "YYY", "NNNN", "YYY", "BBB"};
//        String[] gems = {"DIA", "RUBY", "RUBY", "DIA", "DIA", "EMERALD", "SAPPHIRE", "DIA"};
        System.out.println(Arrays.toString(solution(gems)));
    }

    public static int[] solution(final String[] gems) {
        Set<String> gemsType = new HashSet<>(Arrays.asList(gems));
        Map<String, Integer> buyGems = new HashMap<>();
        int[] answer = new int[2];

        int start = 0;
        int length = 1_000_000;

        for (int i = 0; i < gems.length; i++) {
            buyGems.put(gems[i], buyGems.getOrDefault(gems[i], 0) + 1);

            while (buyGems.get(gems[start]) > 1) {
                buyGems.put(gems[start], buyGems.get(gems[start]) - 1);
                start++;
            }

            if (buyGems.size() == gemsType.size() && length > (i - start)) {
                length = i - start;
                answer[0] = start + 1;
                answer[1] = i + 1;
            }
        }

        return answer;
    }
}
