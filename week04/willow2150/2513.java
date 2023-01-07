import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static class Apartment implements Comparable<Apartment> {
        int distanceToSchool;
        int numOfStudents;

        public Apartment(int distanceToSchool, int numOfStudents) {
            this.distanceToSchool = distanceToSchool;
            this.numOfStudents = numOfStudents;
        }

        @Override
        public int compareTo(Apartment apartment) {
            return this.distanceToSchool < apartment.distanceToSchool ? 1 : -1;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Queue<Apartment> leftApartments = new PriorityQueue<>();
        Queue<Apartment> rightApartments = new PriorityQueue<>();

        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        int N = Integer.parseInt(tokenizer.nextToken());
        int K = Integer.parseInt(tokenizer.nextToken());
        int S = Integer.parseInt(tokenizer.nextToken());

        for (int apartmentIndex = 0; apartmentIndex < N; apartmentIndex++) {
            tokenizer = new StringTokenizer(reader.readLine());
            int point = Integer.parseInt(tokenizer.nextToken());
            int numOfStudents = Integer.parseInt(tokenizer.nextToken());
            if (point < S) leftApartments.add(new Apartment(S - point, numOfStudents));
            else rightApartments.add(new Apartment(point - S, numOfStudents));
        }

        System.out.print(
                calcTotalDistance(leftApartments, K)
                        + calcTotalDistance(rightApartments, K)
        );
    }

    public static int calcTotalDistance(Queue<Apartment> apartments, int K) {
        int totalDistance = 0;
        int numOfSeatsLeft = K;
        int distance = apartments.isEmpty() ? 0 : apartments.peek().distanceToSchool;

        while (!apartments.isEmpty()) {
            Apartment apartment = apartments.poll();
            if (apartment.numOfStudents < numOfSeatsLeft) {
                numOfSeatsLeft -= apartment.numOfStudents;
            } else if (apartment.numOfStudents == numOfSeatsLeft) {
                numOfSeatsLeft = K;
                totalDistance += (distance << 1);
                distance = apartments.isEmpty() ? 0 : apartments.peek().distanceToSchool;
            } else {
                apartment.numOfStudents -= numOfSeatsLeft;
                totalDistance += (distance << 1);
                distance = apartment.distanceToSchool;
                totalDistance += (distance << 1) * (apartment.numOfStudents / K);
                numOfSeatsLeft = K - apartment.numOfStudents % K;
                if (numOfSeatsLeft == K)
                    distance = apartments.isEmpty() ? 0 : apartments.peek().distanceToSchool;
            }
        }
        totalDistance += (distance << 1);
        return totalDistance;
    }
}
