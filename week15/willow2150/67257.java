import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

class Solution {
	public long solution(String expression) {
		List<Long> operations = new ArrayList<>();
		List<Character> operators = new ArrayList<>();
		char[][] priorityCases = new char[][] {
			{'-', '+', '*'}, {'-', '*', '+'},
			{'+', '-', '*'}, {'+', '*', '-'},
			{'*', '-', '+'}, {'*', '+', '-'}
		};
		int[] priority = new int['-' + 1];
		long maxValue = 0L;

		readExpression(operations, operators, expression);

		for (char[] priorityCase : priorityCases) {
			for (int index = 0; index < 3; index++) {
				priority[priorityCase[index]] = index;
			}
			maxValue = Math.max(
				maxValue,
				calcPostfix(getPostfix(operations, operators, priority))
			);
		}
		return maxValue;
	}

	public void readExpression(
		List<Long> operations, List<Character> operators, String expression
	) {
		long number = 0L;
		int expressionLength = expression.length();
		for (int index = 0; index < expressionLength; index++) {
			char character = expression.charAt(index);
			if ('0' <= character && character <= '9') {
				number = number * 10 + (character - '0');
			} else {
				operations.add(number);
				operators.add(character);
				number = 0L;
			}
		}
		operations.add(number);
	}

	public List<Object> getPostfix(
		List<Long> operations, List<Character> operators, int[] priority
	) {
		List<Object> postfix = new LinkedList<>();
		Deque<Character> stack = new ArrayDeque<>();
		int operationCntMinusOne = operations.size() - 1;

		for (int index = 0; index < operationCntMinusOne; index++) {
			char operator = operators.get(index);
			postfix.add(operations.get(index));
			if (!stack.isEmpty()) {
				while (!stack.isEmpty()
					&& priority[stack.peekLast()] >= priority[operator]) {
					postfix.add(stack.pollLast());
				}
			}
			stack.add(operator);
		}
		postfix.add(operations.get(operationCntMinusOne));
		while (!stack.isEmpty())
			postfix.add(stack.pollLast());

		return postfix;
	}

	public long calcPostfix(List<Object> postfix) {
		while (postfix.size() != 1) {
			int elementCnt = postfix.size();
			for (int index = 0; index < elementCnt; index++) {
				if (postfix.get(index) instanceof Character) {
					long result;
					long operationA = (long)postfix.get(index - 2);
					long operationB = (long)postfix.get(index - 1);
					int targetIndex = index - 2;
					char operator = (char)postfix.get(index);

					if (operator == '-') {
						result = operationA - operationB;
					} else if (operator == '+') {
						result = operationA + operationB;
					} else {
						result = operationA * operationB;
					}
					postfix.remove(targetIndex);
					postfix.remove(targetIndex);
					postfix.remove(targetIndex);
					postfix.add(targetIndex, result);
					break;
				}
			}
		}
		return Math.abs((Long)postfix.get(0));
	}
}
