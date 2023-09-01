class Solution {
    public int solution(int[] a) {
        int[] b = new int[a.length];
        int result = 0;

        int number = Integer.MAX_VALUE;
        for (int index = 0; index < a.length; index++) {
            if (a[index] < number) {
                number = a[index];
            }
            b[index] = number;
        }

        number = Integer.MAX_VALUE;
        for (int index = a.length - 1; 0 <= index; index--) {
            if (a[index] < number) {
                number = a[index];
            }
            if (a[index] == b[index] || a[index] == number) {
                result++;
            }
        }

        return result;
    }
}
