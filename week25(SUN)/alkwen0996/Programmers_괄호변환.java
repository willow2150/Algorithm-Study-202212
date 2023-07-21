import java.util.ArrayDeque;
import java.util.Deque;

public class Programmers_괄호변환 {

    public static final char OPEN_PARENTHESIS = '(';
    public static final char CLOSE_PARENTHESIS = ')';
    public static final String EMPTY_STRING = "";

    public static void main(String[] args) {
        String p = "(()())()";
        System.out.println(solution(p));
    }

    private static String solution(final String p) {
        return changeToCorrectString(p);
    }

    private static String changeToCorrectString(final String w) {
        if (w.length() == 0) {
            return EMPTY_STRING;
        }

        StringBuilder u = new StringBuilder();
        StringBuilder v = new StringBuilder();
        int openCount = 0;
        int closeCount = 0;

        char[] wToCharacters = w.toCharArray();

        for (int i = 0; i < wToCharacters.length; i++) {
            u.append(wToCharacters[i]);

            if (wToCharacters[i] == OPEN_PARENTHESIS) {
                openCount++;
            } else {
                closeCount++;
            }

            if (openCount == closeCount) {
                v.append(w.substring(i + 1));
                break;
            }
        }

        if (isCorrectString(u.toString())) {
            return u.append(changeToCorrectString(v.toString())).toString();
        }

        StringBuilder newString = new StringBuilder();
        newString.append(OPEN_PARENTHESIS);
        newString.append(changeToCorrectString(v.toString()));
        newString.append(CLOSE_PARENTHESIS);

        u = new StringBuilder(u.substring(1, u.length() - 1));
        char[] uToCharacters = u.toString().toCharArray();

        for (final char uToCharacter : uToCharacters) {
            if (uToCharacter == OPEN_PARENTHESIS) {
                newString.append(CLOSE_PARENTHESIS);
                continue;
            }

            newString.append(OPEN_PARENTHESIS);
        }

        return newString.toString();
    }

    private static boolean isCorrectString(final String p) {
        Deque<Character> stack = new ArrayDeque<>();

        for (final char bracket : p.toCharArray()) {
            if (bracket == OPEN_PARENTHESIS) {
                stack.push(bracket);
                continue;
            }

            if (bracket == CLOSE_PARENTHESIS) {
                if (!stack.isEmpty() && stack.peek() == OPEN_PARENTHESIS) {
                    stack.pop();
                }
            }
        }

        return stack.size() == 0;
    }
}
