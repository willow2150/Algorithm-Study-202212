import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static class Bead {
        List<Bead> heavierBeads;
        List<Bead> lighterBeads;
        int number;

        Bead(int number) {
            this.heavierBeads = new ArrayList<>();
            this.lighterBeads = new ArrayList<>();
            this.number = number;
        }
    }

    private static final int HEAVIER = 0;
    private static final int LIGHTER = 1;

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        int N = Integer.parseInt(tokenizer.nextToken());
        int M = Integer.parseInt(tokenizer.nextToken());
        Bead[] beads = new Bead[N + 1];
        boolean[][][] heavierOrLighter = new boolean[2][N + 1][N + 1];

        int numOfMiddleBeads = 0;

        for (int beadNumber = 1; beadNumber <= N; beadNumber++) {
            beads[beadNumber] = new Bead(beadNumber);
        }

        for (int edge = 0; edge < M; edge++) {
            tokenizer = new StringTokenizer(reader.readLine());
            int heavierBeadNumber = Integer.parseInt(tokenizer.nextToken());
            Bead heavierBead = beads[heavierBeadNumber];
            int lighterBeadNumber = Integer.parseInt(tokenizer.nextToken());
            Bead lighterBead = beads[lighterBeadNumber];

            heavierBead.lighterBeads.add(lighterBead);
            heavierOrLighter[LIGHTER][heavierBeadNumber][lighterBeadNumber] = true;
            lighterBead.heavierBeads.add(heavierBead);
            heavierOrLighter[HEAVIER][lighterBeadNumber][heavierBeadNumber] = true;
        }

        for (int beadNumber = 1; beadNumber <= N; beadNumber++) {
            Bead bead = beads[beadNumber];

            for (Bead heavierBead : bead.heavierBeads) {
                findHeavierOrLighterBeads(
                        beadNumber,
                        heavierBead.heavierBeads,
                        heavierOrLighter,
                        HEAVIER
                );
            }

            for (Bead lighterBead : bead.lighterBeads) {
                findHeavierOrLighterBeads(
                        beadNumber,
                        lighterBead.lighterBeads,
                        heavierOrLighter,
                        LIGHTER
                );
            }
        }

        for (int beadNumber = 1; beadNumber <= N; beadNumber++) {
            int numOfHeavierBeads = 0;
            int numOfLighterBeads = 0;

            for (boolean isLighter : heavierOrLighter[LIGHTER][beadNumber]) {
                if (isLighter) {
                    numOfLighterBeads++;
                }
            }

            for (boolean isHeavier : heavierOrLighter[HEAVIER][beadNumber]) {
                if (isHeavier) {
                    numOfHeavierBeads++;
                }
            }

            int value = Math.abs(numOfHeavierBeads - numOfLighterBeads) - N
                    + numOfHeavierBeads + numOfLighterBeads;

            if (0 < value) {
                numOfMiddleBeads++;
            }
        }

        System.out.print(numOfMiddleBeads);
    }

    public static void findHeavierOrLighterBeads(
            int depBeadNumber, List<Bead> heavierOrLighterBeads,
            boolean[][][] heavierOrLighter, int type
    ) {
        for (Bead heavierOrLighterBead : heavierOrLighterBeads) {
            if (heavierOrLighter[type][depBeadNumber][heavierOrLighterBead.number]) {
                continue;
            }
            heavierOrLighter[type][depBeadNumber][heavierOrLighterBead.number] = true;
            if (type == HEAVIER) {
                findHeavierOrLighterBeads(
                        depBeadNumber,
                        heavierOrLighterBead.heavierBeads,
                        heavierOrLighter,
                        type
                );
            } else {
                findHeavierOrLighterBeads(
                        depBeadNumber,
                        heavierOrLighterBead.lighterBeads,
                        heavierOrLighter,
                        type
                );
            }
        }
    }
}
