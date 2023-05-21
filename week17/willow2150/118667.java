import java.util.ArrayDeque;
import java.util.Deque;

class Solution {
	public int solution(int[] queue1, int[] queue2) {
		Deque<Integer> dequeA = new ArrayDeque<>();
		Deque<Integer> dequeB = new ArrayDeque<>();
		long dequeASum = 0L;
		long dequeBSum = 0L;

		for (int element : queue1) {
			dequeA.addLast(element);
			dequeASum += element;
		}
		for (int element : queue2) {
			dequeB.addLast(element);
			dequeBSum += element;
		}

		int size = (queue1.length + queue2.length) << 1;
		int count = 0;

		while (size-- > 0) {
			if (dequeASum == dequeBSum) {
				break;
			}
			if (dequeASum > dequeBSum) {
				Integer element = dequeA.pollFirst();
				dequeB.addLast(element);
				dequeASum -= element;
				dequeBSum += element;
			} else {
				Integer element = dequeB.pollFirst();
				dequeA.addLast(element);
				dequeASum += element;
				dequeBSum -= element;
			}
			count++;
		}
		return size > 0 ? count : -1;
	}
}
