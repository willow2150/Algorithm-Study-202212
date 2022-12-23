import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
    static class Command {
        int time;

        public Command(int time) {
            this.time = time;
        }
    }

    static class Typing extends Command {
        char character;

        public Typing(char character, int time) {
            super(time);
            this.character = character;
        }
    }

    static class Undo extends Command {
        int pastTime;
        String before, after;

        public Undo(int pastTime, int time, String before) {
            super(time);
            this.pastTime = pastTime;
            this.before = before;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer;
        Deque<Command> commandList = new ArrayDeque<>();
        StringBuilder text = new StringBuilder();
        int N = Integer.parseInt(reader.readLine());

        for (int commandIdx = 0; commandIdx < N; commandIdx++) {
            tokenizer = new StringTokenizer(reader.readLine());
            tokenizer.nextToken();
            String pastTimeOrCharacter = tokenizer.nextToken();
            int time = Integer.parseInt(tokenizer.nextToken());
            if ('a' <= pastTimeOrCharacter.charAt(0)
                    && pastTimeOrCharacter.charAt(0) <= 'z') {
                Typing command = new Typing(pastTimeOrCharacter.charAt(0), time);
                text.append(command.character);
                commandList.addFirst(command);
            } else {
                int pastTime = Integer.parseInt(pastTimeOrCharacter);
                int point = Math.max(0, time - pastTime);
                Undo command = new Undo(pastTime, time, text.toString());
                for (Command cmd: commandList) {
                    if (cmd.time < point) break;
                    if (cmd instanceof Typing) {
                        text.deleteCharAt(text.length() - 1);
                    } else {
                        text = new StringBuilder(((Undo) cmd).before);
                    }
                }
                command.after = text.toString();
                commandList.addFirst(command);
            }
        }
        System.out.print(text);
    }
}
