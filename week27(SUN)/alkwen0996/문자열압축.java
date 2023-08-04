import java.util.ArrayDeque;
import java.util.Deque;

public class 문자열압축 {
    public static void main(String[] args) {
        System.out.println(solution("aabbaccc"));
    }

    public static int solution(String s) {
        if (s.length() == 1) {
            return 1;
        }

        int answer = Integer.MAX_VALUE;

        for (int i = 1; i < s.length(); i++) {
            answer = Math.min(answer, compressString(i, s));
        }

        return answer;
    }

    private static int compressString(final int length, final String s) {
        Deque<String> queue = new ArrayDeque<>();

        int index = 0;
        String substring;

        while (index <= s.length()) {
            if (index + length > s.length()) {
                substring = s.substring(index);
            } else {
                substring = s.substring(index, index + length);
            }

            queue.add(substring);
            index = index + length;
        }

        StringBuilder stringBuilder = new StringBuilder();
        int sameCount = 1;
        String prev = queue.poll();

        while (!queue.isEmpty()) {
            String poll = queue.poll();

            if (prev.equals(poll)) {
                sameCount++;
                continue;
            }

            if (sameCount == 1) {
                stringBuilder.append(prev);
            } else {
                stringBuilder.append(sameCount).append(prev);
            }

            prev = poll;
            sameCount = 1;
        }

        if (prev.length() > 0) {
            if (sameCount == 1) {
                stringBuilder.append(prev);
            } else {
                stringBuilder.append(sameCount).append(prev);
            }
        }

        return stringBuilder.length();
    }

}
