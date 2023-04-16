import java.util.ArrayList;
import java.util.List;

public class Programmers_67257 {

    private static final char[] operationType = {'+', '-', '*'};
    private static List<Long> numbers;
    private static List<Character> operations;
    private static char[] operationPriority;
    private static boolean[] isChecked;
    private static long max;

    public static void main(String[] args) {
//        final String expression = "100-200*300-500+20";
        final String expression = "50*6-3*2";

        System.out.println(solution(expression));
    }

    public static long solution(final String expression) {
        numbers = new ArrayList<>();
        operations = new ArrayList<>();

        final char[] expressionsToChar = expression.toCharArray();
        StringBuilder number = new StringBuilder();
        for (int i = 0; i < expression.length(); i++) {
            char currentCharacter = expressionsToChar[i];

            if (currentCharacter == '+' || currentCharacter == '*' || currentCharacter == '-') {
                numbers.add(Long.parseLong(number.toString()));
                number = new StringBuilder();

                operations.add(currentCharacter);
                continue;
            }

            number.append(currentCharacter);
        }

        numbers.add(Long.parseLong(number.toString()));
        max = Integer.MIN_VALUE;
        isChecked = new boolean[3];
        operationPriority = new char[3];
        permutation(0);

        return max;
    }

    private static void permutation(final int count) {
        if (count == 3) {
            final long result = calculateFormula();

            if (max < result) {
                max = result;
            }

            return;
        }

        for (int i = 0; i < 3; i++) {
            if (isChecked[i]) {
                continue;
            }

            operationPriority[count] = operationType[i];
            isChecked[i] = true;
            permutation(count + 1);
            isChecked[i] = false;
        }
    }

    private static long calculateFormula() {
        final List<Long> numbersClone = new ArrayList<>(numbers);
        final List<Character> operationsClone = new ArrayList<>(operations);

        for (final char operation : operationPriority) {
            for (int j = 0; j < operationsClone.size(); j++) {
                if (operation == operationsClone.get(j)) {
                    long result;

                    if (operation == operationType[0]) {
                        result = numbersClone.get(j) + numbersClone.get(j + 1);
                    } else if (operation == operationType[1]) {
                        result = numbersClone.get(j) - numbersClone.get(j + 1);
                    } else {
                        result = numbersClone.get(j) * numbersClone.get(j + 1);
                    }

                    numbersClone.remove(j + 1); // 20날리고
                    numbersClone.set(j, result);
                    operationsClone.remove(j);
                    j--;
                }
            }
        }

        return Math.abs(numbersClone.get(0));
    }

}
