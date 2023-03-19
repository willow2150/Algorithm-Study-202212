//줄서기
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        Queue<Person> persons = new LinkedList();
        PriorityQueue<Person> order = new PriorityQueue<>();

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), "-| ");
            for (int j = 0; j < 5; j++) {
                int alp = st.nextToken().charAt(0);
                int num = Integer.parseInt(st.nextToken());
                Person person = new Person(alp, num);
                persons.add(person);
                order.add(person);
            }
        }

        Stack<Person> wait = new Stack<>();
        while (!persons.isEmpty() || !wait.isEmpty()) {
            if (!wait.isEmpty() && wait.peek() == order.peek()) {
                wait.pop();
                order.poll();
                continue;
            } else {
                Person person = persons.poll();
                if (order.peek() == person) {
                    order.poll();
                    continue;
                }
                wait.push(person);
            }
            if ((persons.isEmpty() && !wait.isEmpty()) && order.peek() != wait.peek()) break;
        }
        if (wait.isEmpty()) System.out.print("GOOD");
        else System.out.print("BAD");
    }

    static class Person implements Comparable<Person> {
        int row;
        int col;

        public Person(int row, int col) {
            this.row = row;
            this.col = col;
        }

        @Override
        public int compareTo(Person other) {
            if (row != other.row) {
                return Integer.compare(row, other.row);
            }
            return Integer.compare(col, other.col);
        }
    }
}
