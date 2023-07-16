import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.PriorityQueue;
import java.util.Queue;

public class Baekjoon_1464 {
    private static String s;
    private static Deque<Character> deque;

    public static void main(String[] args) throws IOException {
        inputData();
        reverseWord();
        print();
    }

    private static void print() {
        StringBuilder stringBuilder = new StringBuilder();

        while (!deque.isEmpty()) {
            stringBuilder.append(deque.poll());
        }

        System.out.println(stringBuilder);
    }

    private static void reverseWord() {
        deque = new ArrayDeque<>();

        for (int i = 0; i < s.length(); i++) {
            char pointer = s.charAt(i);

            if (deque.isEmpty()) {
                deque.add(s.charAt(i));
            } else if (deque.peekFirst() >= pointer) {
                deque.addFirst(pointer);
            } else if (deque.peekLast() <= pointer) {
                deque.addLast(pointer);
            } else {
                deque.addLast(pointer);
            }
        }

    }

    private static void inputData() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        s = bufferedReader.readLine();
    }

}
