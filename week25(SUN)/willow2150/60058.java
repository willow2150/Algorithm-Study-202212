class Solution {
    public String solution(String p) {
        char[] string = p.toCharArray();
        convertToProperString(string, 0, p.length() - 1);
        return String.valueOf(string);
    }

    public static void convertToProperString(char[] string, int left, int right) {
        if (left >= right) {
            return;
        }

        int[] stringInfo = parseString(string, left, right);
        int boundary = stringInfo[0];
        boolean isProperString = (stringInfo[1] != 0);

        if (isProperString) {
            convertToProperString(string, boundary + 1, right);
        } else {
            char[] uString = new char[boundary - left + 1];
            char[] vString = new char[right - boundary];
            int idx = left;

            convertToProperString(string, boundary + 1, right);

            for (int index = left; index <= boundary; index++) {
                uString[index - left] = string[index];
            }
            for (int index = boundary + 1; index <= right; index++) {
                vString[index - boundary - 1] = string[index];
            }

            string[idx++] = '(';
            for (char character : vString) {
                string[idx++] = character;
            }
            string[idx++] = ')';
            for (int index = 1; index < uString.length - 1; index++) {
                if (uString[index] == '(') {
                    string[idx++] = ')';
                } else {
                    string[idx++] = '(';
                }
            }
        }
    }

    public static int[] parseString(char[] string, int left, int right) {
        int score = 0;
        boolean isProperString = true;

        for (int index = left; index <= right; index++) {
            if (string[index] == '(') {
                score++;
            } else {
                score--;
            }
            if (score < 0) {
                isProperString = false;
            }
            if (score == 0) {
                return new int[] {index, isProperString ? 1 : 0};
            }
        }
        return new int[] {right, isProperString ? 1 : 0};
    }
}
