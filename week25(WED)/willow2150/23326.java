import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Main {
    static class Zone implements Comparable<Zone> {
        int number;
        boolean isSights;

        Zone(int number, boolean isSights) {
            this.number = number;
            this.isSights = isSights;
        }

        @Override
        public int compareTo(Zone zone) {
            if (this.number == zone.number) {
                return 0;
            }
            return this.number < zone.number ? -1 : 1;
        }
    }

    public static void main(String[] args) throws Exception {
        final char SIGHTS = '1';
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder output = new StringBuilder();
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        int N = Integer.parseInt(tokenizer.nextToken());
        int Q = Integer.parseInt(tokenizer.nextToken());

        TreeSet<Zone> sightsSet = new TreeSet<>();
        Zone[] zones = new Zone[N + 1];
        int currentZoneNumber = 1;

        String inputLine = reader.readLine();
        for (int zoneNumber = 1; zoneNumber <= N; zoneNumber++) {
            char zoneInfo = inputLine.charAt((zoneNumber - 1) << 1);
            if (zoneInfo == SIGHTS) {
                zones[zoneNumber] = new Zone(zoneNumber, true);
                sightsSet.add(zones[zoneNumber]);
            } else {
                zones[zoneNumber] = new Zone(zoneNumber, false);
            }
        }

        zones[0] = new Zone(0, false);

        for (int q = 0; q < Q; q++) {
            String query = reader.readLine();
            char queryType = query.charAt(0);
            if (queryType == '1') {
                int zoneNumber = Integer.parseInt(query.substring(2));
                Zone zone = zones[zoneNumber];
                if (zone.isSights) {
                    zone.isSights = false;
                    sightsSet.remove(zone);
                } else {
                    zone.isSights = true;
                    sightsSet.add(zone);
                }
            } else if (queryType == '2') {
                int distance = Integer.parseInt(query.substring(2));
                currentZoneNumber = (currentZoneNumber + distance) % N;
                if (currentZoneNumber == 0) {
                    currentZoneNumber = N;
                }
            } else {
                Zone currentZone = zones[currentZoneNumber];
                if (sightsSet.isEmpty()) {
                    output.append(-1).append('\n');
                } else if (currentZone.isSights) {
                    output.append(0).append('\n');
                } else {
                    Zone nextZone = sightsSet.higher(currentZone);
                    if (nextZone == null) {
                        nextZone = sightsSet.higher(zones[0]);
                        output.append(N - currentZoneNumber + nextZone.number).append('\n');
                    } else {
                        output.append(nextZone.number - currentZoneNumber).append('\n');
                    }
                }
            }
        }

        System.out.print(output);
    }
}
