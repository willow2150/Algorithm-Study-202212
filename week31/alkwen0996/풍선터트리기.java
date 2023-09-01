public class 풍선터트리기 {
    public static void main(String[] args) {
        int[] a = {-16, 27, 65, -2, 58, -92, -71, -68, -61, -33};
        System.out.println(solution(a));
    }

    public static int solution(final int[] a) {
        if (a.length == 1) {
            return 1;
        }

        int answer = 2;
        int minValue = Integer.MAX_VALUE;

        int[] leftMinValues = new int[a.length];
        int[] rightMinValues = new int[a.length];

        for (int i = 0; i < a.length; i++) {
            if (minValue > a[i]) {
                minValue = a[i];
            }

            leftMinValues[i] = minValue;
        }

        minValue = Integer.MAX_VALUE;

        for (int i = a.length - 1; i >= 0; i--) {
            if (minValue > a[i]) {
                minValue = a[i];
            }

            rightMinValues[i] = minValue;
        }

        for (int i = 1; i < a.length - 1; i++) {
            if (leftMinValues[i] < a[i] && rightMinValues[i] < a[i]) {
                continue;
            }

            answer++;
        }

        return answer;
    }

}
